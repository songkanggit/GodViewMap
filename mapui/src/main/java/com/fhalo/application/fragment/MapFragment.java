package com.fhalo.application.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fhalo.application.R;

public class MapFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_map, null);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
		dataOper();
	}
	private void initView() {
//		ImageView mapImage=getActivity().findViewById(R.id.map_main);
	}
	private void initData() {
		// TODO Auto-generated method stub
		
	}
	private void dataOper() {
		// TODO Auto-generated method stub
		
	}
}
