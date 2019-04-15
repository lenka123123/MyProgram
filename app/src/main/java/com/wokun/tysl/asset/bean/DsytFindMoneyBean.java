package com.wokun.tysl.asset.bean;

/**
 * Created by Administrator on 2018\11\29 0029.
 */

public class DsytFindMoneyBean {
    private  Float invite;
    private  Float sign_in;
    private  String buy_goods;
    private  Float goods_eval;
    private  Float buy_service;
    private  Float service_eval;
    private  Float share;

    @Override
    public String toString() {
        return "DsytFindMoneyBean{" +
                "invite=" + invite +
                ", sign_in=" + sign_in +
                ", buy_goods='" + buy_goods + '\'' +
                ", goods_eval=" + goods_eval +
                ", buy_service=" + buy_service +
                ", service_eval=" + service_eval +
                ", share=" + share +
                '}';
    }

    public Float getInvite() {
        return invite;
    }

    public void setInvite(Float invite) {
        this.invite = invite;
    }

    public Float getSign_in() {
        return sign_in;
    }

    public void setSign_in(Float sign_in) {
        this.sign_in = sign_in;
    }

    public String getBuy_goods() {
        return buy_goods;
    }

    public void setBuy_goods(String buy_goods) {
        this.buy_goods = buy_goods;
    }

    public Float getGoods_eval() {
        return goods_eval;
    }

    public void setGoods_eval(Float goods_eval) {
        this.goods_eval = goods_eval;
    }

    public Float getBuy_service() {
        return buy_service;
    }

    public void setBuy_service(Float buy_service) {
        this.buy_service = buy_service;
    }

    public Float getService_eval() {
        return service_eval;
    }

    public void setService_eval(Float service_eval) {
        this.service_eval = service_eval;
    }

    public Float getShare() {
        return share;
    }

    public void setShare(Float share) {
        this.share = share;
    }
}
