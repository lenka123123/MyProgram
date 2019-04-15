package com.wokun.tysl.model.response;


import com.wokun.tysl.model.bean.ProfitLog;

import java.util.List;

public class ProfitLogResponse {

    private List<ProfitLog> profitLogList;

    public List<ProfitLog> getProfitLogList() {
        return profitLogList;
    }

    public void setProfitLogList(List<ProfitLog> profitLogList) {
        this.profitLogList = profitLogList;
    }
}
