package com.fhalo.application.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fhalo.application.R;
import com.fhalo.application.base.BaseFragment;
import com.fhalo.application.base.BaseInterface;

import java.util.Calendar;

public class CreateTeamFragment extends BaseFragment implements BaseInterface, OnClickListener{
	
	private Button dateButton,timeButton;
	private ImageView isOpenImageView;
	private int year,month,day,hour,minute;
	private boolean isOpen=true;										//判断活动是否公开
	private Spinner startLocation,endLocation;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_team_createteam, null);
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
		dateButton=button(R.id.createteam_date);
		timeButton=button(R.id.createteam_time);
		editText(R.id.createteam_name);
		startLocation = (Spinner) getActivity().findViewById(R.id.createteam_location_start);
		endLocation = (Spinner) getActivity().findViewById(R.id.createteam_location_start);
		isOpenImageView = imageView(R.id.createteam_isopen_true);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		dateButton.setOnClickListener(this);
		timeButton.setOnClickListener(this);
		isOpenImageView.setOnClickListener(this);
	}

	@Override
	public void DataOper() {
		// TODO Auto-generated method stub
		Calendar calendar=Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		final String[] starts=getResources().getStringArray(R.array.sceneries);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item, starts);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		startLocation.setAdapter(adapter);
		startLocation.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			        Toast.makeText(getActivity(), "你点击的是:"+starts[position], 0).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.createteam_date:
			new DatePickerDialog(getActivity(),3,new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					Log.i("shenquanxia", "year="+year);
					Log.i("shenquanxia", "month="+monthOfYear);
					Log.i("shenquanxia", "day="+dayOfMonth);
					dateButton.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
				}
			},year,month+1,day).show();
			break;
		case R.id.createteam_time:
			TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), 2,new OnTimeSetListener() {
				
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					timeButton.setText(hourOfDay+":"+minute);
				}
			}, hour, minute, true);
			timePickerDialog.show();
			break;
		case R.id.createteam_isopen_true:
				if(isOpen){
					isOpenImageView.setImageDrawable(getResources().getDrawable(R.drawable.image_button_close));
					isOpen=false;
				}else{
					isOpenImageView.setImageDrawable(getResources().getDrawable(R.drawable.image_button_open));
					isOpen=true;
				}
			break;
		}
	}
}