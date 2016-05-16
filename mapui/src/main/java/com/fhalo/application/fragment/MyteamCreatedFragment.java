package com.fhalo.application.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.fhalo.application.R;
import com.fhalo.application.adapter.MemberAdapter;
import com.fhalo.application.base.BaseFragment;
import com.fhalo.application.base.BaseInterface;
import com.fhalo.application.bean.Member;

import java.util.ArrayList;
import java.util.List;

public class MyteamCreatedFragment extends BaseFragment implements BaseInterface, OnClickListener{
	
	private ListView memberListView;
	private List<Member> memberList;
	private EditText noticeEditText;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_team_myteam_created, null);
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
		noticeEditText = editText(R.id.myteam_notice);
		memberListView = (ListView) getActivity().findViewById(R.id.team_myteam_member);
	}
	@Override
	public void initData() {
		memberList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Member member=new Member();
			if(i==0){
				member.setMemberPosition("队长");
			}else{
				member.setMemberPosition("队员");
			}
			member.setMemberName("-15151516688");
			member.setMemberChat("明天张欢请吃饭，同志们都去啊！");
			memberList.add(member);
		}
	}
	@Override
	public void DataOper() {
		memberListView.setAdapter(new MemberAdapter(getActivity(), memberList));
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
}
