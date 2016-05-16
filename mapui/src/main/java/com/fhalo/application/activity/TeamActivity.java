package com.fhalo.application.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fhalo.application.R;
import com.fhalo.application.base.BaseActivity;
import com.fhalo.application.base.BaseInterface;
import com.fhalo.application.fragment.MyteamCreatedFragment;
import com.fhalo.application.fragment.TeamsFragment;
import com.fhalo.application.fragment.CreateTeamFragment;

public class TeamActivity extends BaseActivity implements BaseInterface, OnClickListener {
	
	private Button topTeams , topMyteam;
	private LinearLayout layoutTopTeam;
	private FrameLayout layoutFragment;
	public static int teamFrom=0;
	private int miss=0;
	private boolean timeStart=false;
	private Chronometer myteamChronometer;
	private ImageView timeImageView,resetImageView;
	private LinearLayout timeLayout;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_team);
		initView();
		initData();
		DataOper();
	}

	@Override
	public void initView() {
		layoutTopTeam=linearlayout(R.id.team_top_layout);
		topTeams = button(R.id.team_top_teams);
		topMyteam=button(R.id.team_top_myteam);
		myteamChronometer = (Chronometer) findViewById(R.id.myteam_chronometer);
		timeImageView = imageView(R.id.myteam_time);
		resetImageView=imageView(R.id.myteam_reset);
		timeLayout = linearlayout(R.id.myteam_time_layout);
	}
	@Override
	public void initData() {
		/* -----------首次进入根据不同的来源跳转不同的界面----------------*/
		if(teamFrom==0){
			topTeams.setTextColor(Color.WHITE);
			topMyteam.setTextColor(Color.BLACK);
			/* -----------初始进入页面后执行topTeams的点击触发效果----------------*/
			topTeams.setBackgroundResource(R.drawable.team_top_btnseleted);
			layoutTopTeam.setBackgroundResource(R.drawable.team_top_round_background);
			topMyteam.setBackgroundResource(R.drawable.team_top_round_background);
			/*--------------------------------------------------------------*/
			TeamsFragment teamsFragment=new TeamsFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.team_framelayout, teamsFragment).commit();
			timeLayout.setVisibility(View.GONE);
		}else if(teamFrom==1){
			topMyteam.setTextColor(Color.WHITE);
			topTeams.setTextColor(Color.BLACK);
			topMyteam.setBackgroundResource(R.drawable.team_top_btnseleted);
			layoutTopTeam.setBackgroundResource(R.drawable.team_top_round_background);
			topTeams.setBackgroundResource(R.drawable.team_top_round_background);
			/*--------------------------------------------------------------*/
			MyteamCreatedFragment myTeamCreatedFragment=new MyteamCreatedFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.team_framelayout, myTeamCreatedFragment).commit();
			timeLayout.setVisibility(View.VISIBLE);
		}
		
		getClickListener();
	}
	
	@Override
	public void DataOper() {
		myteamChronometer.setFormat("00:"+"%s");
		/*myteamChronometer.setOnChronometerTickListener(new OnChronometerTickListener() {
			public void onChronometerTick(Chronometer chronometer) {
				miss++;
				chronometer.setText(FormatMiss(100));
			}
		});*/
	}
	/**
	 * team页面元素监听
	 * @description:
	 * @methodName: getClickListener
	 * @return: void
	 * @throws:
	 */
	private void getClickListener() {
		topMyteam.setOnClickListener(this);
		topTeams.setOnClickListener(this);
		timeImageView.setOnClickListener(this);
		resetImageView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.team_top_teams:
			topTeams.setBackgroundResource(R.drawable.team_top_btnseleted);
			layoutTopTeam.setBackgroundResource(R.drawable.team_top_round_background);
			topMyteam.setBackgroundResource(R.drawable.team_top_round_background);
			topTeams.setTextColor(Color.WHITE);
			topMyteam.setTextColor(Color.BLACK);
			TeamsFragment teamsFragment=new TeamsFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.team_framelayout, teamsFragment).commit();
			break;
		case R.id.team_top_myteam:
			topMyteam.setBackgroundResource(R.drawable.team_top_btnseleted);
			layoutTopTeam.setBackgroundResource(R.drawable.team_top_round_background);
			topTeams.setBackgroundResource(R.drawable.team_top_round_background);
			topMyteam.setTextColor(Color.WHITE);
			topTeams.setTextColor(Color.BLACK);
			if(teamFrom==1){
				timeLayout.setVisibility(View.VISIBLE);
				MyteamCreatedFragment myTeamCreatedFragment=new MyteamCreatedFragment();
				getSupportFragmentManager().beginTransaction().replace(R.id.team_framelayout, myTeamCreatedFragment).commit();
			}else if(teamFrom==0){
				timeLayout.setVisibility(View.GONE);
				CreateTeamFragment myteamFragment=new CreateTeamFragment();
				getSupportFragmentManager().beginTransaction().replace(R.id.team_framelayout, myteamFragment).commit();
			}
			break;
		case R.id.myteam_time:
			if(!timeStart){
				timeImageView.setImageResource(R.drawable.myteam_starttime);
				myteamChronometer.start();
				timeStart=true;
			}else {
				myteamChronometer.stop();
				timeImageView.setImageResource(R.drawable.myteam_pause);
				timeStart=false;
			}
			break;
		case R.id.myteam_reset:
			myteamChronometer.setBase(SystemClock.elapsedRealtime());
			break;
		}
	}
	public static String FormatMiss(int miss){     
        String hh=miss/3600>9?miss/3600+"":"0"+miss/3600;
        String mm=(miss % 3600)/60>9?(miss % 3600)/60+"":"0"+(miss % 3600)/60;
        String ss=(miss % 3600) % 60>9?(miss % 3600) % 60+"":"0"+(miss % 3600) % 60;
        return hh+":"+mm+":"+ss;      
    }
}
