package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.BaseLevelThreeLinkageActivity;
import com.wokun.tysl.other.controler.PhotoMgr;
import com.wokun.tysl.utils.ImageLoader;

import java.io.File;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xl on 2018/7/4.
 */

public class PersonalInfoActivity extends BaseBindingActivity implements BaseLevelThreeLinkageActivity.OnAddressSelectedListener {
	@BindString(R.string.tysl_edit_userinfo)String title;
	@BindView(R.id.iv_image_header) ImageView ivImageHeader; //用户头像
	@BindView(R.id.tv_area)
	TextView tvVarea;
	@BindView(R.id.tv_yaoqingma)
	TextView tv_yaoqingma;
	@BindView(R.id.tv_sex)
	TextView tv_sex;

	private PhotoSelector mPhotoSelector;

	@Override
	public int createView() {
		return R.layout.activity_center_personalinfo;
	}

	@Override
	public WidgetBar createToolBar() {
		return mWidgetBar.setWidgetBarTitle(title);
	}

	@Override
	public void init() {
		ImageLoader.loadImage(TyslApp.getInstance().getUserInfo().getAvatar(),ivImageHeader);
		tvVarea.setText(TyslApp.getInstance().getUserInfo().getUserName());
		tv_yaoqingma.setText(TyslApp.getInstance().getUserInfo().getInvite_code());
		//tv_sex.setText(TyslApp.getInstance().getUserInfo().getSex());invite_code
      if(TyslApp.getInstance().getUserInfo().getSex()==1){
		  tv_sex.setText("女");
	  }
		if(TyslApp.getInstance().getUserInfo().getSex()==0){
			tv_sex.setText("男");
		}

		setOnAddressSelectedListener(this);
		mPhotoSelector = new PhotoSelector(this);
		mPhotoSelector.setOnPhotoUpLoadListener(new OnPhotoUpLoadListener() {
			@Override
			public void onPhotoUpLoad(ImageView photoImage, File photoFile) {
				PhotoMgr.getInstance().upLoadPicture(PersonalInfoActivity.this,photoFile);
			}
		});
	}


	private BaseLevelThreeLinkageActivity.OnAddressSelectedListener onAddressSelectedListener;

	@Override
	public void onAddressSelected(String address, int provinceCode, int cityCode, int districtCode) {

	}

	public interface OnAddressSelectedListener {
		void onAddressSelected(String address, int provinceCode, int cityCode, int districtCode);
	}

	public void setOnAddressSelectedListener(BaseLevelThreeLinkageActivity.OnAddressSelectedListener onAddressSelectedListener) {
		this.onAddressSelectedListener = onAddressSelectedListener;
	}

	@OnClick({R.id.action_edit_user_head_img})
	public void action(View view) {
		if (R.id.action_edit_user_head_img == view.getId()) {//修改用户头像
			mPhotoSelector.showView(ivImageHeader);
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mPhotoSelector.onActivityResult(requestCode, resultCode, data);
	}
}
