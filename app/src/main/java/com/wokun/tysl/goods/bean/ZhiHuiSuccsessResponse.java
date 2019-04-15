package com.wokun.tysl.goods.bean;

/**
 * Created by Administrator on 2018\10\11 0011.
 */

public class ZhiHuiSuccsessResponse {

     private   DuiDataBean  tuiData;
     private    OrderInBean     orderInfo;

    public DuiDataBean getTuiData() {
        return tuiData;
    }

    public void setTuiData(DuiDataBean tuiData) {
        this.tuiData = tuiData;
    }

    public OrderInBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class DuiDataBean {
        private   String  tui1_image;
        private   String  tui2_id;
        private   String  tui2_image;
        private   String  tui2_desc;

        public String getTui1_image() {
            return tui1_image;
        }

        public void setTui1_image(String tui1_image) {
            this.tui1_image = tui1_image;
        }

        public String getTui2_id() {
            return tui2_id;
        }

        public void setTui2_id(String tui2_id) {
            this.tui2_id = tui2_id;
        }

        public String getTui2_image() {
            return tui2_image;
        }

        public void setTui2_image(String tui2_image) {
            this.tui2_image = tui2_image;
        }

        public String getTui2_desc() {
            return tui2_desc;
        }

        public void setTui2_desc(String tui2_desc) {
            this.tui2_desc = tui2_desc;
        }
    }
    public static class OrderInBean {
        private  String  order_sn;
        private  String  pay_price;
        private  String  shipping_address;
        private  String  self_lifting;
        private  String  lifting_time;
        private  String  name;
        private  String  mobile;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public String getShipping_address() {
            return shipping_address;
        }

        public void setShipping_address(String shipping_address) {
            this.shipping_address = shipping_address;
        }

        public String getSelf_lifting() {
            return self_lifting;
        }

        public void setSelf_lifting(String self_lifting) {
            this.self_lifting = self_lifting;
        }

        public String getLifting_time() {
            return lifting_time;
        }

        public void setLifting_time(String lifting_time) {
            this.lifting_time = lifting_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
