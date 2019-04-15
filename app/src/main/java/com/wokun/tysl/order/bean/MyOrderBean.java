package com.wokun.tysl.order.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xl on 2018/7/24.
 */

public class MyOrderBean {

	private String avatar;
	@SerializedName("start_time")//付款时间
	private String startTime;
	@SerializedName("order_price")//单价
	private String orderPrice;
	@SerializedName("order_profit")//收益
	private String orderProfit;
	@SerializedName("order_amount")//购买数量
	private double orderAmount;
	@SerializedName("order_state")//订单状态：  跟进/已跟进
	private String orderState;
	@SerializedName("order_id")//订单编号
	private String orderId;
	@SerializedName("user_id")//用户id
	private String userId;
	@SerializedName("username")//用户姓名
	private String userName;

	@SerializedName("userphone")//用户手机号

	public String getOrderProfit() {
		return orderProfit;
	}

	public void setOrderProfit(String orderProfit) {
		this.orderProfit = orderProfit;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	private String userPhone;

	public String getStartTime() {
		return startTime;
	}


	public String getorderPrice() {
		return orderPrice;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public String getOrderState() {
		return orderState;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public void setorderPrice(String serviceLastTime) {
		this.orderPrice = serviceLastTime;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
