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
import com.fhalo.application.bean.Teams;

import java.util.List;

public class TeamsAdapter extends BaseAdapter{

	private Context context;
	private List<Teams> teamsList;
	public TeamsAdapter(Context context, List<Teams> teamsList) {
		super();
		this.context=context;
		this.teamsList=teamsList;
	}
	
	@Override
	public int getCount() {
		Log.i("shenquanxia", "getcount()="+teamsList.size());
		return teamsList.size();
	}

	@Override
	public Object getItem(int position) {
		return teamsList.get(position);
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
			convertView=LayoutInflater.from(context).inflate(R.layout.listview_fragment_teams, null);
			vh.teamsIcon=(ImageView) convertView.findViewById(R.id.teams_listview_icon);
			vh.teamsName=(TextView) convertView.findViewById(R.id.teams_listview_teamname);
			vh.teamsAddress=(TextView) convertView.findViewById(R.id.teams_listview_address);
			vh.teamLeftTime=(TextView) convertView.findViewById(R.id.teams_listview_lefttime);
			convertView.setTag(vh);
			Log.i("shenquanxia", "000000000000000");
		}else{
			vh=(ViewHolder) convertView.getTag();
			Log.i("shenquanxia", "11111111111111111111111");
		}
		
//		if(position%2==1){
//			Log.i("shenquanxia", "111111111");
//			vh.teamsIcon.setImageResource(R.drawable.teams_listview_unlock);
//		}
		vh.teamsName.setText(teamsList.get(position).getTeamName());
		vh.teamsAddress.setText(teamsList.get(position).getTeamAddress());
		vh.teamLeftTime.setText(teamsList.get(position).getTeamLeftTime());
		Log.i("shenquanxia", "position="+position);
		return convertView;
	}
	static class ViewHolder{
		private ImageView teamsIcon;
		private TextView teamsName;
		private TextView teamsAddress;
		private TextView teamLeftTime;
	}
}
