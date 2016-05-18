package com.fhalo.application.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fhalo.application.R;
import com.fhalo.application.activity.MyApplication;
import com.fhalo.application.activity.TeamActivity;
import com.fhalo.application.base.BaseFragment;
import com.fhalo.application.base.BaseInterface;
import com.fhalo.application.base.PublicMethod;
import com.fhalo.application.dialog.SendSOSDialog;
import com.fhalo.overlay.PathOverlay;
import com.fhalo.utils.AssetsHelper;
import com.fhalo.utils.LocationSvgInfo;
import com.fhalo.utils.ShortestPathCalculator;
import com.jiahuan.svgmapview.SVGMapView;
import com.jiahuan.svgmapview.SVGMapViewListener;
import com.jiahuan.svgmapview.overlay.SVGMapLocationOverlay;

import java.util.HashMap;

public class OutdoorFragment extends BaseFragment implements BaseInterface, OnClickListener {

	private SVGMapView mapView;
	private MyApplication app;
	private PathOverlay pol;
	private SensorManager mSensorManager;
	private LocationManager mLocationManager;
	private Sensor magneticSensor;
	private Sensor accSensor;
	SVGMapLocationOverlay locationOverlay;

	private boolean isMapLoadedCompletely = false;

    private ImageButton searchButton;

	//=============================================//
	private ImageView sosImageView,myinfoImageView;
	private AutoCompleteTextView searchScenery;
	private String[] sceneries;

	private static HashMap<String, LocationSvgInfo> locationMap = new HashMap<>();
	static {
			locationMap.put("官帽", new LocationSvgInfo("官帽", 200, 400));
			locationMap.put("水天一线", new LocationSvgInfo("官帽", 50, 50));
			locationMap.put("女娲娘娘庙", new LocationSvgInfo("官帽", 550, 480));
			locationMap.put("金蝉", new LocationSvgInfo("官帽", 250, 330));
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_outdoor, null);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
		DataOper();
	}
	@Override
	public void initView() {
        searchButton = (ImageButton) getActivity().findViewById(R.id.main_outdoor_search_button);
		sosImageView = imageView(R.id.main_outdoor_sos);
		myinfoImageView=imageView(R.id.main_outdoor_myinfo);
		searchScenery = (AutoCompleteTextView) getActivity().findViewById(R.id.main_outdoor_search);
	}
	@Override
	public void initData() {
		app = (MyApplication) getActivity().getApplication();

        searchButton.setOnClickListener(this);
		sosImageView.setOnClickListener(this);
		myinfoImageView.setOnClickListener(this);
		sceneries = getResources().getStringArray(R.array.sceneries);
		ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(), R.layout.item_main_outdoor_search, sceneries);
		searchScenery.setThreshold(0);
		searchScenery.setAdapter(adapter);
		searchScenery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				String selectText = (String) adapterView.getItemAtPosition(i);
				if(locationMap.containsKey((Object)selectText)) {
					ShortestPathCalculator spc= new ShortestPathCalculator(app.getPathLines());
					pol.setPathLines(spc.getShortestPath(new Point(200, 200), new Point(locationMap.get((Object)selectText).getSvg_abs_x(),
							locationMap.get((Object)selectText).getSvg_abs_y())));
					mapView.refresh();
				}
			}
		});

		//==========================================================

		mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		Criteria mCriteria = new Criteria();
		mCriteria.setAccuracy(Criteria.ACCURACY_FINE);
		mCriteria.setAltitudeRequired(false);
		mCriteria.setBearingRequired(false);
		mCriteria.setCostAllowed(true);
		mCriteria.setPowerRequirement(Criteria.ACCURACY_LOW);
		String provider = mLocationManager.getBestProvider(mCriteria,true);
		mLocationManager.requestLocationUpdates(provider, 2000, 10, mLocationListener);

		mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
		magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		mSensorManager.registerListener(mSensorListener, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(mSensorListener, accSensor, SensorManager.SENSOR_DELAY_NORMAL);

		mapView = (SVGMapView) getActivity().findViewById(R.id.navi_map_view);
		mapView.registerMapViewListener(new SVGMapViewListener() {
			@Override
			public void onMapLoadComplete() {
				locationOverlay = new SVGMapLocationOverlay(mapView);
				locationOverlay.setIndicatorArrowBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.indicator_arrow));
				locationOverlay.setPosition(new PointF(200, 200));
				locationOverlay.setIndicatorCircleRotateDegree(90);
				locationOverlay.setMode(SVGMapLocationOverlay.MODE_COMPASS);
				locationOverlay.setIndicatorArrowRotateDegree(45);
				mapView.getOverLays().add(locationOverlay);

				pol = new PathOverlay(mapView);
				mapView.getOverLays().add(pol);
				mapView.refresh();
				isMapLoadedCompletely = true;
			}

			@Override
			public void onMapLoadError() {

			}

			@Override
			public void onGetCurrentMap(Bitmap bitmap) {

			}
		});
		mapView.loadMap(AssetsHelper.getContent(getActivity(), "path_route.svg"));
	}

	private SensorEventListener mSensorListener = new SensorEventListener() {
		float[] magneticValues = new float[3];
		float[] accValues = new float[3];
		float[] rotation = new float[9];
		float[] values = new float[3];


		@Override
		public void onSensorChanged(SensorEvent sensorEvent) {
			if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				magneticValues = sensorEvent.values.clone();
			} else if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				accValues = sensorEvent.values.clone();
			}
			SensorManager.getRotationMatrix(rotation, null, accValues, magneticValues);
			SensorManager.getOrientation(rotation, values);

			float rotateDegree = -(float)Math.toDegrees(values[0]);
			if(isMapLoadedCompletely) {
				locationOverlay.setIndicatorArrowRotateDegree(rotateDegree);
				mapView.refresh();
			}

		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int i) {

		}
	};

	private LocationListener mLocationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
//            System.out.println("Longitude:" + location.getLongitude()
//            +"; Latitude:" + location.getLatitude());
		}

		@Override
		public void onStatusChanged(String s, int i, Bundle bundle) {

		}

		@Override
		public void onProviderEnabled(String s) {

		}

		@Override
		public void onProviderDisabled(String s) {

		}
	};

	@Override
	public void DataOper() {

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		    case R.id.main_outdoor_sos:
                SendSOSDialog dialog=new SendSOSDialog(getActivity());
                PublicMethod.sqxDialog(dialog,getActivity(),200);
                break;
		    case R.id.main_outdoor_myinfo:
                Intent teamIntent=new Intent(getActivity(), TeamActivity.class);
                startActivity(teamIntent);
                break;
            case R.id.main_outdoor_search_button:


                break;

		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mSensorManager != null) {
			mSensorManager.unregisterListener(mSensorListener);
		}
		if(mLocationManager != null) {
			mLocationManager.removeUpdates(mLocationListener);
		}
		mapView.onDestroy();
	}

	@Override
	public void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		mapView.onResume();
	}
}
