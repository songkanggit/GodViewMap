package com.fhalo.application.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.fhalo.application.R;
import com.fhalo.application.activity.JoinTeamVerifyActivity;
import com.fhalo.application.adapter.TeamsAdapter;
import com.fhalo.application.base.BaseInterface;
import com.fhalo.application.base.PublicMethod;
import com.fhalo.application.bean.Teams;
import com.fhalo.application.dialog.JoinTeamDialog;

import java.util.ArrayList;
import java.util.List;

public class TeamsFragment extends Fragment implements BaseInterface {
	
	private ListView teamsListView;
	private List<Teams> teamsList;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_team_teams, null);
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
		teamsListView = (ListView) getActivity().findViewById(R.id.team_teams_listview);
		teamsListView.setDivider(new ColorDrawable(Color.parseColor("#F8F8F8")));
		teamsListView.setDividerHeight(20);
	}
	
	@Override
	public void initData() {
		teamsList = new ArrayList<>();
	}
	@Override
	public void DataOper() {
		AddDataToList();
		teamsListView.setAdapter(new TeamsAdapter(getActivity(),teamsList));
//		Item短按事件
		teamsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(getActivity(),JoinTeamVerifyActivity.class);
				startActivity(intent);
			}
		});
//		Item项长按事件
		teamsListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				PublicMethod.joinTeamText=teamsList.get(position).getTeamName();
				JoinTeamDialog dialog=new JoinTeamDialog(getActivity());
				PublicMethod.sqxDialog(dialog, getActivity(), 100);
				return true ;
			}
		});
	}
	/**
	 * 向teamsList中添加数据
	 * @description:
	 * @methodName: AddDataToList
	 * @return: void
	 * @throws:
	 */
	private void AddDataToList() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			Teams teams =new Teams();
			teams.setTeamName("第707野战军77师");
			teams.setTeamAddress("河南省确山县某秘密基地");
			teams.setTeamLeftTime("还剩7天");
			teamsList.add(teams);
		}
	}
}
