package com.fhalo.application.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class PublicMethod {
	public static String joinTeamText="";
	public PublicMethod() {
		// TODO Auto-generated constructor stub
		super();
	}
	/**
	 * Dialog弹出方法
	 */
	public static void sqxDialog(Dialog  dialog,Context context,int yPosition){
//		WindowManager wManager=(WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//		Dialog dialog=new SendSOSDialog(activity);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		WindowManager manager=((Activity) context).getWindowManager();
		Display display=manager.getDefaultDisplay();
		LayoutParams params=dialog.getWindow().getAttributes();
/*-----------------分别设置dialog的宽度，高度，位置坐标以及透明度-----------------*/
		params.width=(int) (display.getWidth()*0.8);
		params.height=(int) (display.getHeight()*0.3);
		params.x=0;
		params.y=yPosition;
		params.alpha= 0.8f;	
		dialog.getWindow().setAttributes(params);
		dialog.show();
	}
}
