package com.fanting.aidongtan.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanting.aidongtan.AppContext;
import com.fanting.aidongtan.R;
import com.fanting.aidongtan.base.BaseActivity;
import com.fanting.aidongtan.utils.AppManager;
import com.fanting.aidongtan.utils.SharedPreferencesUtils;

public class MainActivity extends BaseActivity implements OnClickListener, HandleFragment.HandleBackListener {
	
	private ImageView btnHome, btnHandle, btnSearch;
	private FragmentManager mFragmentManager;
	
	private boolean isHomePage;
	
	private long firstime = 0;
	
	/**主页*/
	private HomeFragment homeFragment;
	/**办理页面*/
	private HandleFragment handleFragment;
	/**查询页面*/
	private QueryFragment searchFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AppManager.getAppManager().addActivity(this);
//		if (SharedPreferencesUtils.getBoolean("isPush", true)) {
//			PushUtils.initWithApiKey(getApplicationContext());
//			PushUtils.setPushTag(getApplicationContext());
//		}
		initView();
		mFragmentManager = getSupportFragmentManager();
		//开始时显示主页
		setTabSelection(0);
	}


	public void initView() {
		btnHome = (ImageView) findViewById(R.id.main_btn_home);
		btnHandle = (ImageView) findViewById(R.id.main_btn_handle);
		btnSearch = (ImageView) findViewById(R.id.main_btn_query);
		
		btnHome.setOnClickListener(this);
		btnHandle.setOnClickListener(this);
		btnSearch.setOnClickListener(this);
	}

	
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_btn_home:
			//单击home显示主页
			setTabSelection(0);
			isHomePage = true;
			break;

		case R.id.main_btn_handle:
			//单击办理显示办理页面
			setTabSelection(1);
			isHomePage = false;
			break;
			
		case R.id.main_btn_query:
			//单击查询显示查询页面
			setTabSelection(2);
			isHomePage = false;
			break;
		}
	}
	
	/** 
	* 根据传入的index参数来设置选中的tab页。
	* @param index 
	* 每个tab页对应的下标。0表示主页，1表示办理，2表示查询。 
	*/  
	private void setTabSelection(int index){
		//开启一个fragment事务
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		//隐藏所有的fragment
		hideFragments(transaction);
		
		switch (index) {
		case 0:
			if (homeFragment == null) {
				//创建页面并添加
				homeFragment = new HomeFragment();
				transaction.add(R.id.content, homeFragment, "home");
			} else {
				transaction.show(homeFragment);
			}
			getWindow().setBackgroundDrawableResource(R.drawable.main_bg);
			break;

		case 1:
			if (handleFragment == null) {
				handleFragment = new HandleFragment();
				handleFragment.setHandleBackListener(this);
				transaction.add(R.id.content, handleFragment, "handle");
			} else {
				transaction.show(handleFragment);
			}
			getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f1f2f6")));
			break;
			
		case 2:
			if (searchFragment == null) {
				searchFragment = new QueryFragment();
				transaction.add(R.id.content, searchFragment, "search");
			} else {
				transaction.show(searchFragment);
			}
			getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f1f2f6")));
			break;
		}
		transaction.commit();
	}

	/**将所有fragment设置为隐藏状态
	 * @param transaction 用于对fragment执行操作的事务*/
	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (handleFragment != null) {
			transaction.hide(handleFragment);
		}
		if (searchFragment != null) {
			transaction.hide(searchFragment);
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(isHomePage){
			if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
				long secondtime = System.currentTimeMillis();
				if (secondtime - firstime > 3000) {
					Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
					firstime = System.currentTimeMillis();
					return true;
				} else {
					AppContext.exit();
				}
			}
			return super.onKeyDown(keyCode, event);
		}
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
			goBack();
		}
		return isHomePage;
		
	}

	@Override
	public void onHandleBack() {
		goBack();
	}
	
	public void goBack(){
		if (!isHomePage) {
			setTabSelection(0);
			isHomePage = true;
		}
	}
}
