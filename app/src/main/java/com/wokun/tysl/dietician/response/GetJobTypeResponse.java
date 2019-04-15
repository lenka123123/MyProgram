package com.wokun.tysl.dietician.response;

import com.wokun.tysl.dietician.bean.DieticianJobTypeBean;

import java.util.List;

public class GetJobTypeResponse {

    private List<DieticianJobTypeBean> jobType;

    public List<DieticianJobTypeBean> getJobType() {
        return jobType;
    }

    public void setJobType(List<DieticianJobTypeBean> jobType) {
        this.jobType = jobType;
    }
}
