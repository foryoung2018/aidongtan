package com.fanting.aidongtan.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanting.aidongtan.R;

public class HandleFragment extends Fragment implements OnClickListener{

	private View view;
	
	private TextView btn1, btn2, btn3, btn4, btn5, btn6;
	
	private View btnBack;
	private ImageButton btnLogin;
	private ImageView userIcon;
	private ImageButton pullDownMenu;
	private TextView titleTv;
	private TextView stateTv, phoneTv;
	
	private HandleBackListener mHandleBackListener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_handle, null);
		initView();
		addListener();
		return view;
	}

	private void initView() {
	}
	
	private void addListener(){
//		btn1.setOnClickListener(this);
//		btn2.setOnClickListener(this);
//		btn3.setOnClickListener(this);
//		btn4.setOnClickListener(this);
//		btn5.setOnClickListener(this);
//		btn6.setOnClickListener(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onClick(View v) {
	}
	
	public void setHandleBackListener(HandleBackListener mHandleBackListener){
		this.mHandleBackListener = mHandleBackListener;
	}
	
	public interface HandleBackListener{
		void onHandleBack();
	}
}
