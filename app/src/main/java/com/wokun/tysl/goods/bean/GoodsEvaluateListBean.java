package com.wokun.tysl.goods.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoodsEvaluateListBean implements Serializable {

    @SerializedName("goods_id")
    private int goodsId;

    @SerializedName("evalution_score")
    private int evalutionScore;

    private String text;
    private String imgs;

    public String getText() {
        return text;
    }

    public String getImgs() {
        return imgs;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public int getEvalutionScore() {
        return evalutionScore;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setEvalutionScore(int evalutionScore) {
        this.evalutionScore = evalutionScore;
    }
}
