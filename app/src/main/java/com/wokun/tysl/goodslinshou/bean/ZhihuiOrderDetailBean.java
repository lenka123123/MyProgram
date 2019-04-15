package com.wokun.tysl.goodslinshou.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\9\29 0029.
 */

public class ZhihuiOrderDetailBean {

    private String order_id;
    private String order_sn;
    private String pay_price;
    private String order_state;
    private String express_name;
    private String express_code;
    private String shipping_address;
    private String create_time;
    private String self_lifting;
    private String self_lifting_code;
    private ArrayList<ZhihuiDetaiBean>  subOrder;
    private String link_man;
    private String mobile;
    private String shipping_methods;
    private String pay_type;
    private String freight;
    private String store_name;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

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

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getExpress_name() {
        return express_name;
    }

    public void setExpress_name(String express_name) {
        this.express_name = express_name;
    }

    public String getExpress_code() {
        return express_code;
    }

    public void setExpress_code(String express_code) {
        this.express_code = express_code;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSelf_lifting() {
        return self_lifting;
    }

    public void setSelf_lifting(String self_lifting) {
        this.self_lifting = self_lifting;
    }

    public String getSelf_lifting_code() {
        return self_lifting_code;
    }

    public void setSelf_lifting_code(String self_lifting_code) {
        this.self_lifting_code = self_lifting_code;
    }

    public ArrayList<ZhihuiDetaiBean> getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(ArrayList<ZhihuiDetaiBean> subOrder) {
        this.subOrder = subOrder;
    }

    public String getLink_man() {
        return link_man;
    }

    public void setLink_man(String link_man) {
        this.link_man = link_man;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShipping_methods() {
        return shipping_methods;
    }

    public void setShipping_methods(String shipping_methods) {
        this.shipping_methods = shipping_methods;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
}
