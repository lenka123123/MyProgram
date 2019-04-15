package com.wokun.tysl.model.response;


import com.wokun.tysl.asset.bean.AssetRecord;

import java.util.List;

public class IntegralRecordResponse {

    private List<AssetRecord> integralRList;

    public List<AssetRecord> getIntegralRList() {
        return integralRList;
    }

    public void setIntegralRList(List<AssetRecord> integralRList) {
        this.integralRList = integralRList;
    }
}
