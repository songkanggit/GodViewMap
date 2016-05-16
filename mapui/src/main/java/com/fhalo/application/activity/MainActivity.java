package com.fhalo.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fhalo.application.R;
import com.fhalo.application.base.BaseActivity;
import com.fhalo.application.base.BaseInterface;
import com.fhalo.application.broadcast.TimeReminderReceiver;
import com.fhalo.application.fragment.FarmyardFragment;
import com.fhalo.application.fragment.InfoFragment;
import com.fhalo.application.fragment.MapFragment;
import com.fhalo.application.fragment.OutdoorFragment;

import java.util.Calendar;


public class MainActivity extends BaseActivity implements BaseInterface, OnClickListener {

    private FragmentManager fManager;
    private FragmentTransaction transaction;
    private OutdoorFragment outdoorFragment;
    private LinearLayout layoutMainOutdoor,layoutMainMap,layoutMainFarmyard,layoutMainInfo;
    private int[] layouts,images_white,images_blue;
    private MapFragment mapFragment;
    private FarmyardFragment farmyardFragment;
    private InfoFragment infoFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        DataOper();
    }
    @Override
    public void initView() {
		/*--------------------------------定义3个int数组，用于首页导航的逻辑处理----------------------------------------*/
        layouts= new int[] {R.id.main_outdoor_layout,R.id.main_map_layout,R.id.main_farmyard_layout,R.id.main_info_layout};
        images_white=new int[] {R.drawable.main_outdoor_white,R.drawable.main_map_white,R.drawable.main_farmyard_white,R.drawable.main_info_white};
        images_blue=new int[] {R.drawable.main_outdoor_blue,R.drawable.main_map_blue,R.drawable.main_farmyard_blue,R.drawable.main_info_blue};
		/*--------------------------------首页LinearLayout的控件初始化操作----------------------------------------*/
        layoutMainOutdoor = linearlayout(R.id.main_outdoor_layout);
        layoutMainMap = linearlayout(R.id.main_map_layout);
        layoutMainFarmyard = linearlayout(R.id.main_farmyard_layout);
        layoutMainInfo = linearlayout(R.id.main_info_layout);
    }

    @Override
    public void initData() {
        initFragmentOpera();
        getClickListener();
    }

    @Override
    public void DataOper() {
//		发送广播，全局监控时间
        Intent intent=new Intent(this, TimeReminderReceiver.class);
        Calendar calendar=Calendar.getInstance();
        long thisTime = calendar.getTimeInMillis();
        intent.putExtra("thisTime", thisTime);
        sendBroadcast(intent);
    }
    /**
     * 首次加载页面，fragment操作
     */
    private void initFragmentOpera() {
        fManager =getSupportFragmentManager();
        transaction = fManager.beginTransaction();
        outdoorFragment = new OutdoorFragment();
        mapFragment = new MapFragment();
        farmyardFragment = new FarmyardFragment();
        infoFragment = new InfoFragment();
        transaction.replace(R.id.main_framelayout, outdoorFragment);
        transaction.commit();
    }
    /**
     * main页面所有元素的监听初始化
     */
    private void getClickListener() {
        layoutMainOutdoor.setOnClickListener(this);
        layoutMainMap.setOnClickListener(this);
        layoutMainFarmyard.setOnClickListener(this);
        layoutMainInfo.setOnClickListener(this);
    }
    /**
     * onclick事件
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_outdoor_layout:                            //“户外运动”
                changeFragment(0);
                fManager = getSupportFragmentManager();
                transaction = fManager.beginTransaction();
                transaction.replace(R.id.main_framelayout, outdoorFragment);
                transaction.commit();
                break;
            case R.id.main_map_layout:                            //“旅游导览”
                changeFragment(1);
                fManager = getSupportFragmentManager();
                transaction = fManager.beginTransaction();
                transaction.replace(R.id.main_framelayout, mapFragment);
                transaction.commit();
                break;
            case R.id.main_farmyard_layout:                            //“农家小舍”
                changeFragment(2);
                fManager = getSupportFragmentManager();
                transaction = fManager.beginTransaction();
                transaction.replace(R.id.main_framelayout, farmyardFragment);
                transaction.commit();
                break;
            case R.id.main_info_layout:                             //“我的”
                changeFragment(3);
                fManager = getSupportFragmentManager();
                transaction = fManager.beginTransaction();
                transaction.replace(R.id.main_framelayout, infoFragment);
                transaction.commit();
                break;
        }
    }
    /**
     * 根据点击的控件，切换相应的Fragment页面
     */
    private void changeFragment(int id) {
        for(int i=0;i<layouts.length;i++){
            if(i==id){
                ((ImageView)(linearlayout(layouts[i]).getChildAt(0))).setImageResource(images_white[i]);
            }else{
                ((ImageView)(linearlayout(layouts[i]).getChildAt(0))).setImageResource(images_blue[i]);
            }
        }
    }
}
