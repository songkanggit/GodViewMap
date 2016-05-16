package com.fhalo.application.activity;

import android.app.Application;

import com.fhalo.utils.PathGenerator;
import com.fhalo.utils.PathLine;

import java.util.ArrayList;

import cn.bmob.sms.BmobSMS;

public class MyApplication extends Application {
	private PathGenerator pg;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		BmobSMS.initialize(this,"636966e9725409598e8a66146ce7dfcd");

		pg = new PathGenerator();
		pg.parse(this);
	}

	public ArrayList<PathLine> getPathLines() {
		return pg.getPathLines();
	}
}
