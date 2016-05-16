package com.fhalo.application.base;

import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseActivity extends FragmentActivity {

	private EditText edt_text;
	private TextView tv_View;
	private Button btn;
	private ImageView img;
	private LinearLayout ll_layout;
	private ImageButton ibtn;
	private FrameLayout fl_Layout;
	public LinearLayout linearlayout(int id){
		ll_layout = (LinearLayout) findViewById(id);
		return ll_layout;
	}
	//EditText
	public EditText editText(int id){
		edt_text=(EditText) findViewById(id);
		return edt_text;
	}
	//TextView
	public TextView textView(int id){
		tv_View=(TextView) findViewById(id);
		return tv_View;
	}
	//Button
	public Button button(int id){
		btn=(Button) findViewById(id);
		return btn;
	}
	public ImageView imageView(int id){
		img=(ImageView) findViewById(id);
		return img;
	}
	public ImageButton imageButton(int id){
		ibtn = (ImageButton) findViewById(id);
		return ibtn;
	}
	public FrameLayout frameLayout(int id){
		fl_Layout=(FrameLayout) findViewById(id);
		return fl_Layout;
	}
}
