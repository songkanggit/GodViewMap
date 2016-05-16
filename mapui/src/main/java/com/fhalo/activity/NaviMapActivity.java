package com.fhalo.activity;

import android.app.Activity;
import android.content.Context;
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
import android.view.View;
import android.widget.Button;

import com.fhalo.application.R;
import com.fhalo.overlay.PathOverlay;
import com.fhalo.utils.AssetsHelper;
import com.fhalo.utils.PathGenerator;
import com.fhalo.utils.ShortestPathCalculator;
import com.jiahuan.svgmapview.SVGMapView;
import com.jiahuan.svgmapview.SVGMapViewListener;
import com.jiahuan.svgmapview.overlay.SVGMapLocationOverlay;

/**
 * Created by SouKou on 2016/5/4.
 */
public class NaviMapActivity extends Activity {

    private SVGMapView mapView;
    private PathGenerator pg;
    private PathOverlay pol;
    private Button trigger;
    private SensorManager mSensorManager;
    private LocationManager mLocationManager;
    private Sensor magneticSensor;
    private Sensor accSensor;
    SVGMapLocationOverlay locationOverlay;

    private boolean isMapLoadedCompletely = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_map_layout);

        pg = new PathGenerator();
        pg.parse(getApplicationContext());

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria mCriteria = new Criteria();
        mCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        mCriteria.setAltitudeRequired(false);
        mCriteria.setBearingRequired(false);
        mCriteria.setCostAllowed(true);
        mCriteria.setPowerRequirement(Criteria.ACCURACY_LOW);
        String provider = mLocationManager.getBestProvider(mCriteria,true);
        mLocationManager.requestLocationUpdates(provider, 2000, 10, mLocationListener);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(mSensorListener, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorListener, accSensor, SensorManager.SENSOR_DELAY_NORMAL);

        trigger = (Button) findViewById(R.id.trigger);

        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShortestPathCalculator spc= new ShortestPathCalculator(pg.getPathLines());
                pol.setPathLines(spc.getShortestPath(new Point(200, 200), new Point(550, 550)));
                mapView.refresh();
            }
        });
        mapView = (SVGMapView) findViewById(R.id.navi_map_view);
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
        mapView.loadMap(AssetsHelper.getContent(this, "path_route.svg"));
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
    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(mSensorManager != null) {
            mSensorManager.unregisterListener(mSensorListener);
        }
        if(mLocationManager != null) {
            mLocationManager.removeUpdates(mLocationListener);
        }
        mapView.onDestroy();
    }
}
