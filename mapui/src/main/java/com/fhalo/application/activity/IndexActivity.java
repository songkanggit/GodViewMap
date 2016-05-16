package com.fhalo.application.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fhalo.application.R;
import com.fhalo.application.adapter.IndexPagerAdapter;
import com.fhalo.application.base.BaseActivity;
import com.fhalo.application.base.BaseInterface;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

public class IndexActivity extends BaseActivity implements BaseInterface, OnClickListener {

	private ViewPager pager;
	private List<ImageView> imageList;
	private int[] imgs={R.drawable.login_top_0,R.drawable.login_top_1,R.drawable.login_top_2,
			R.drawable.login_top_3,R.drawable.login_top_4};
	private TextView pagerText;
	private EditText phoneEditText,codeEditText;
	private Button loginButton,sendButton;
	private boolean phone=false;
	private boolean code=false;
	private String phoneNumber;	//输入的电话号码的值
	private String codeString;		//输入的验证码的值
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_index_login);
		initView();
		initData();
		DataOper();
	}

	@Override
	public void initView() {
		pager = (ViewPager) findViewById(R.id.index_viewpager);
		pagerText = textView(R.id.pager_text);
		phoneEditText = editText(R.id.index_edittext_phone);
		codeEditText=editText(R.id.index_edittext_code);
		loginButton = button(R.id.index_button);
		sendButton = button(R.id.index_sendmessage);
	}

	@Override
	public void initData() {
//		定义viewpager的图片对象数组
		imageList=new ArrayList<>();
//		监听手机号码EditText的变化
		phoneEditText.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(!(s.toString().equals(""))){
					phone=true;
					if(code==true){
						loginButton.setClickable(true);
						loginButton.setBackgroundResource(R.drawable.button_click_white);
					}else{
						loginButton.setClickable(false);
						loginButton.setBackgroundResource(R.drawable.button_click_gray);
					}
				}else{
					phone = false;
					loginButton.setClickable(false);
					loginButton.setBackgroundResource(R.drawable.button_click_gray);
				}
			}
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			public void afterTextChanged(Editable s) {
			}
		});
//		监听验证码EditText的变化
		codeEditText.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(!(s.toString().equals(""))){
					code=true;
					if(phone){
						loginButton.setClickable(true);
						loginButton.setBackgroundResource(R.drawable.button_click_white);
					}else{
						loginButton.setClickable(false);
						loginButton.setBackgroundResource(R.drawable.button_click_gray);
					}
				}else{
					code = false;
					loginButton.setClickable(false);
					loginButton.setBackgroundResource(R.drawable.button_click_gray);
				}
			}
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			public void afterTextChanged(Editable s) {
				
			}
		});
//		button监听事件
		loginButton.setOnClickListener(this);
		sendButton.setOnClickListener(this);
	}

	@Override
	public void DataOper() {
		pagerOper();
	}
	
	@SuppressLint("NewApi")
	private void pagerOper() {
		for(int i=0;i<imgs.length;i++){
			ImageView iv=new ImageView(this);
			iv.setBackgroundResource(imgs[i]);
			imageList.add(iv);
		}
		pager.setAdapter(new IndexPagerAdapter(imageList));
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					pagerText.setText("1/"+imageList.size());
					break;
				case 1:
					pagerText.setText("2/"+imageList.size());
					break;
				case 2:
					pagerText.setText("3/"+imageList.size());
					break;
				case 3:
					pagerText.setText("4/"+imageList.size());
					break;
				case 4:
					pagerText.setText("5/"+imageList.size());
					break;
				}
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.index_button:
			codeString = codeEditText.getText().toString().trim();
			verifyCode();
			break;
		case R.id.index_sendmessage:
			phoneNumber = phoneEditText.getText().toString().trim();
			if(phoneNumber.length()==11){
				sendCode();
			}else{
				Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_LONG).show();
			}
			break;
		}
	}
	/**
	 * 发送验证码
	 */
	private void sendCode() {
		BmobSMS.requestSMSCode(this, phoneNumber, "msgCode",new RequestSMSCodeListener() {
			public void done(Integer arg0, BmobException arg1) {
				 if(arg1==null){
//					 验证码发送成功
			         Log.i("bmob", "短信id："+arg1);
			        Toast.makeText(IndexActivity.this, "验证码已经发送", Toast.LENGTH_LONG).show();
			     }else{
			        Toast.makeText(IndexActivity.this, "验证码发送失败"+arg1.getMessage(), Toast.LENGTH_LONG).show();
			     }
			}
		});
	}
	/**
	 * 判断验证码是否正确
	 */
	private void verifyCode() {
		BmobSMS.verifySmsCode(this,phoneNumber, codeString, new VerifySMSCodeListener() {
		    public void done(BmobException ex) {
				if (ex == null) {//短信验证码已验证成功
					Toast.makeText(IndexActivity.this, "验证通过", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(IndexActivity.this, MainActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(IndexActivity.this, "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage(), Toast.LENGTH_LONG);
				}
			}
		});
	}
}
