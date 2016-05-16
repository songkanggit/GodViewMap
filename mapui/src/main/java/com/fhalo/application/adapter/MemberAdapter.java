package com.fhalo.application.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fhalo.application.R;
import com.fhalo.application.bean.Member;

import java.util.List;

public class MemberAdapter extends BaseAdapter{

	private Context context;
	private List<Member> memberList;
	public MemberAdapter(Context context, List<Member> memberList) {
		super();
		this.context=context;
		this.memberList=memberList;
	}
	
	@Override
	public int getCount() {
		return memberList.size();
	}

	@Override
	public Object getItem(int position) {
		return memberList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView==null){
			vh=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.listview_fragment_myteam_member, null);
			vh.memberPosition=(TextView) convertView.findViewById(R.id.myteam_position);
			vh.memberName=(TextView) convertView.findViewById(R.id.myteam_name);
			vh.memberChat=(TextView) convertView.findViewById(R.id.myteam_chat);
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}

		vh.memberPosition.setText(memberList.get(position).getMemberPosition());
		vh.memberName.setText(memberList.get(position).getMemberName());
		vh.memberChat.setText(memberList.get(position).getMemberChat());
		Log.i("shenquanxia", "position="+position);
		return convertView;
	}
	static class ViewHolder{
		private ImageView memberIcon;
		private TextView memberPosition;
		private TextView memberName;
		private TextView memberChat;
	}
}
