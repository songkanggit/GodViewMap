package com.fhalo.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fhalo.application.R;
import com.fhalo.application.bean.FarmyardInfo;

import java.util.List;

public class FarmyardAdapter extends BaseAdapter {
	Context context;
	List<FarmyardInfo> farmyardList;
	
	public FarmyardAdapter(Context context, List<FarmyardInfo> farmyardList) {
		super();
		this.context=context;
		this.farmyardList=farmyardList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return farmyardList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return farmyardList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView==null){
//			Toast.makeText(context.getApplicationContext(), "ddddddddddddddd", 1).show();
			vh=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.listview_fragment_farmyard, null);
			vh.farmImage=(ImageView) convertView.findViewById(R.id.farm_image);
			vh.farmName=(TextView) convertView.findViewById(R.id.farm_name);
			vh.farmPhone=(TextView) convertView.findViewById(R.id.farm_phone);
			vh.farmAddress=(TextView) convertView.findViewById(R.id.farm_address);
			vh.farmCoupon=(TextView) convertView.findViewById(R.id.farm_coupon);
			vh.farmDistance=(TextView) convertView.findViewById(R.id.farm_distance);
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
//		/*---------------如何添加图片到listView子项里面？----------------------*/
		vh.farmName.setText(farmyardList.get(position).getFarmName());
		vh.farmPhone.setText(farmyardList.get(position).getFarmPhone());
		vh.farmAddress.setText(farmyardList.get(position).getFarmAddress());
		vh.farmCoupon.setText(farmyardList.get(position).getFarmCoupon());
		vh.farmDistance.setText(farmyardList.get(position).getFarmDistance());
		return convertView;
	}
	static class ViewHolder
    {
        ImageView farmImage;
        TextView farmName;
        TextView farmPhone;
        TextView farmAddress;
        TextView farmCoupon;
        TextView farmDistance;
    }
}
