package com.fhalo.application.activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fhalo.application.R;
import com.fhalo.application.base.BaseActivity;
import com.fhalo.application.base.BaseInterface;

public class JoinTeamVerifyActivity extends BaseActivity implements BaseInterface, OnClickListener{

	private int count=0;          //标记当前光标的位置
    private int[] defaultNumber={2,9,3,6,0,8}; //模拟密码，需要从服务器获取
    private int[] password=new int[]{-1,-1,-1,-1,-1,-1}; //定义数组，用来保存已经修改的密码的每个位置的每个值
    private ImageView del,zero,one,two,three,four,five,six,seven,eight,nine;
    private TextView box1,box2,box3,box4,box5,box6;
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_paypassword);
        initView();
        initData();
        DataOper();
    }
	public void initView() {
		/*--------------------------------ImageView（虚拟键盘）的初始化-------------------------------------------*/
		del=imageView(R.id.pay_keyboard_del);
		zero=imageView(R.id.pay_keyboard_zero);
		one=imageView(R.id.pay_keyboard_one);
		two=imageView(R.id.pay_keyboard_two);
		three=imageView(R.id.pay_keyboard_three);
		four=imageView(R.id.pay_keyboard_four);
		five=imageView(R.id.pay_keyboard_five);
		six=imageView(R.id.pay_keyboard_six);
		seven=imageView(R.id.pay_keyboard_seven);
		eight=imageView(R.id.pay_keyboard_eight);
		nine=imageView(R.id.pay_keyboard_nine);
		/*--------------------------------TextView（密码框）的初始化-------------------------------------------*/
		box1=textView(R.id.pay_box1);
		box2=textView(R.id.pay_box2);
		box3=textView(R.id.pay_box3);
		box4=textView(R.id.pay_box4);
		box5=textView(R.id.pay_box5);
		box6=textView(R.id.pay_box6);
	}

	public void initData() {
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		five.setOnClickListener(this);
		six.setOnClickListener(this);
		seven.setOnClickListener(this);
		eight.setOnClickListener(this);
		nine.setOnClickListener(this);
		zero.setOnClickListener(this);
		del.setOnClickListener(this);
	}

	public void DataOper() {
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.pay_keyboard_zero:
			inputPwd(0);
			break;
		case R.id.pay_keyboard_one:
			inputPwd(1);
			break;
		case R.id.pay_keyboard_two:
			inputPwd(2);
			break;
		case R.id.pay_keyboard_three:
			inputPwd(3);
			break;
		case R.id.pay_keyboard_four:
			inputPwd(4);
			break;
		case R.id.pay_keyboard_five:
			inputPwd(5);
			break;
		case R.id.pay_keyboard_six:
			inputPwd(6);
			break;
		case R.id.pay_keyboard_seven:
			inputPwd(7);
			break;
		case R.id.pay_keyboard_eight:
			inputPwd(8);
			break;
		case R.id.pay_keyboard_nine:
			inputPwd(9);
			break;
		case R.id.pay_keyboard_del:
			switch (count) {
			case 1:
				box1.setText("");
				count--;
				break;
			case 2:
				box2.setText("");
				count--;
				break;
			case 3:
				box3.setText("");
				count--;
				break;
			case 4:
				box4.setText("");
				count--;
				break;
			case 5:
				box5.setText("");
				count--;
				break;
			}
			break;
		}
	}

    private void inputPwd(int number) {
		// TODO Auto-generated method stub
    	switch (count) {
		case 0:
			box1.setText(String.valueOf(number));
			password[0]=number;
			count++;
			break;
		case 1:
			box2.setText(String.valueOf(number));
			password[1]=number;
			count++;
			break;
		case 2:
			box3.setText(String.valueOf(number));
			password[2]=number;
			count++;
			break;
		case 3:
			box4.setText(String.valueOf(number));
			password[3]=number;
			count++;
			break;
		case 4:
			box5.setText(String.valueOf(number));
			password[4]=number;
			count++;
			break;
		case 5:
			box6.setText(String.valueOf(number));
			password[5]=number;
			for(int i=0;i<6;i++){
				if(defaultNumber[i]==password[i]){
					if(i==5){
						this.finish();
						Toast.makeText(this, "成功加入战队", 0).show();
					}
				}else{
						box1.setText("");
						box2.setText("");
						box3.setText("");
						box4.setText("");
						box5.setText("");
						box6.setText("");
						Toast.makeText(this, "密码错误，请重新输入", 0).show();
						count=0;
						break;
				}
			}
			break;
		}
	}
	public void onBackPressed() {
		super.onBackPressed();
    }
}
