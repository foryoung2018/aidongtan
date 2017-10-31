package com.fanting.aidongtan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fanting.aidongtan.R;

import java.util.ArrayList;
import java.util.Map;


public class HomeFragment extends Fragment implements OnClickListener{


	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		initView(view);
		addListener();
		return view;
	}
	
	private void initView(View v) {

	}
	
	private void addListener(){

	}
	
	@Override
	public void onClick(View v) {

	}
	
	private void intoFunction(Map<String, String> linkMap){
		Intent intent = null;
		try {
			intent = new Intent();
			intent.setClass(getActivity(), Class.forName("com.asiainfo.activity." + linkMap.get("link")));
			if (linkMap.containsKey("remark")) {
				String[] extras = linkMap.get("remark").split("=");
				//System.out.println();
				intent.putExtra(extras[0], extras[1]);
			}
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}