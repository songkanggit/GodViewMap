package com.fhalo.application.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fhalo.application.R;
import com.fhalo.application.base.BaseInterface;
import com.fhalo.application.base.PublicMethod;

public class JoinTeamDialog extends Dialog implements BaseInterface, View.OnClickListener {

	private Button joinTeamButton ,cancelJoinButton,joinSuccessButton;
	private TextView txtJoinTeam;
	private LinearLayout joinTeamLayout;
	private Context context;
	public JoinTeamDialog(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_team_jointeam);
		initView();
		initData();
		DataOper();
	}
	@Override
	public void initView() {
		txtJoinTeam=(TextView) findViewById(R.id.dialog_jointeam_text);
		joinTeamButton = (Button) findViewById(R.id.dialog_jointeam_yes);
		cancelJoinButton = (Button) findViewById(R.id.dialog_jointeam_no);
		joinSuccessButton=(Button) findViewById(R.id.dialog_joinsuccess_button);
		joinTeamLayout=(LinearLayout) findViewById(R.id.dialog_jointeam_layout);
	}
	@Override
	public void initData() {
		joinTeamButton.setOnClickListener(this);
		cancelJoinButton.setOnClickListener(this);
	}
	@Override
	public void DataOper() {
		txtJoinTeam.setText("您正在选择“"+ PublicMethod.joinTeamText+"”活动，你确定加入该战队吗？");
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_jointeam_yes:
			txtJoinTeam.setText("您已加入“"+PublicMethod.joinTeamText+"”活动战队，开始战斗吧！");
			joinTeamLayout.setVisibility(View.GONE);
			joinSuccessButton.setVisibility(View.VISIBLE);
			break;
		case R.id.dialog_jointeam_no:
			this.dismiss();
			break;
		}
	}
	
}
