package com.fhalo.application.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fhalo.application.R;
import com.fhalo.application.base.BaseInterface;

public class SendSOSDialog extends Dialog implements BaseInterface, View.OnClickListener {

	private Button sendSosButton ,cancelSosButton;
	private Context context;
	public SendSOSDialog(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_sendsos);
		initView();
		initData();
		DataOper();
	}
	@Override
	public void initView() {
		sendSosButton = (Button) findViewById(R.id.dialog_send_sos);
		cancelSosButton = (Button) findViewById(R.id.dialog_send_cancel);
	}
	@Override
	public void initData() {
		sendSosButton.setOnClickListener(this);
		cancelSosButton.setOnClickListener(this);
	}
	@Override
	public void DataOper() {
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_send_sos:
			
			break;
		case R.id.dialog_send_cancel:
			this.dismiss();
			break;
		}
	}
	
}
