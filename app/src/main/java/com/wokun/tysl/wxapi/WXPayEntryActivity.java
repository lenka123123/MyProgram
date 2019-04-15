package com.wokun.tysl.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wokun.tysl.R;
import com.wokun.tysl.config.Constants;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler{

	private IWXAPI api;
	private TextView result;
	private static final String TAG = "WXPayEntryActivity";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pay_result);
		WidgetBar mWidgetBar = (WidgetBar) findViewById(R.id.toolbar);

		result = (TextView) findViewById(R.id.result);
		mWidgetBar.setWidgetBarTitle("充值详情");
		findViewById(R.id.action_pay_finish).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {}

	@Override
	public void onResp(BaseResp resp) {
		if(resp.getType()==ConstantsAPI.COMMAND_PAY_BY_WX){
			if(resp.errCode==0){
				result.setText("支付成功");
			} else {
				result.setText("支付失败");
			}
		}
	}






}