package com.fhalo.application.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.fhalo.application.R;
import com.fhalo.application.activity.FarmyardActivity;
import com.fhalo.application.adapter.FarmyardAdapter;
import com.fhalo.application.base.BaseFragment;
import com.fhalo.application.base.BaseInterface;
import com.fhalo.application.bean.FarmyardInfo;

import java.util.ArrayList;
import java.util.List;

public class FarmyardFragment extends BaseFragment implements BaseInterface {
	private ListView farmyardListView;
	private List<FarmyardInfo> farmyardList;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_farmyard, null);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
		DataOper();
	}
	@Override
	public void initView() {
		farmyardListView = (ListView) getActivity().findViewById(R.id.farmyard_listview);
	}
	@Override
	public void initData() {
		farmyardList = new ArrayList<FarmyardInfo>();
		
		for(int i=0;i<10;i++){
			FarmyardInfo farmyardInfo=new FarmyardInfo();
			farmyardInfo.setFarmName("欢欢农庄");
			farmyardInfo.setFarmPhone("15133666688");
			farmyardInfo.setFarmAddress("丰台科技园六区二号楼101");
			farmyardInfo.setFarmCoupon("男的打5折，女的打8折");
			farmyardInfo.setFarmDistance("距离100米");
			farmyardList.add(farmyardInfo);
		}
	}
	@Override
	public void DataOper() {
		farmyardListView.setAdapter(new FarmyardAdapter(getActivity(), farmyardList));
		farmyardListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(getActivity(), FarmyardActivity.class);
				intent.putExtra("farmname", farmyardList.get(position).getFarmName());
				intent.putExtra("farmphone", farmyardList.get(position).getFarmPhone());
				intent.putExtra("farmaddress", farmyardList.get(position).getFarmAddress());
				intent.putExtra("farmcoupon", farmyardList.get(position).getFarmCoupon());
				intent.putExtra("farmdistance", farmyardList.get(position).getFarmDistance());
				startActivity(intent);
			}
		});
	}
}
