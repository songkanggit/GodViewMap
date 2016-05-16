package com.fhalo.application.base;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseFragment extends Fragment {

	private EditText edt_text;
	private TextView tv_View;
	private Button btn;
	private ImageView img;
	private LinearLayout ll_layout;
	private ImageButton ibtn;
	private FrameLayout fl_Layout;
	public LinearLayout linearlayout(int id){
		ll_layout = (LinearLayout) getActivity().findViewById(id);
		return ll_layout;
	}
	//EditText
	public EditText editText(int id){
		edt_text=(EditText) getActivity().findViewById(id);
		return edt_text;
	}
	//TextView
	public TextView textView(int id){
		tv_View=(TextView) getActivity().findViewById(id);
		return tv_View;
	}
	//Button
	public Button button(int id){
		btn=(Button) getActivity().findViewById(id);
		return btn;
	}
	public ImageView imageView(int id){
		img=(ImageView) getActivity().findViewById(id);
		return img;
	}
	public ImageButton imageButton(int id){
		ibtn = (ImageButton) getActivity().findViewById(id);
		return ibtn;
	}
	public FrameLayout frameLayout(int id){
		fl_Layout=(FrameLayout)getActivity(). findViewById(id);
		return fl_Layout;
	}
}
