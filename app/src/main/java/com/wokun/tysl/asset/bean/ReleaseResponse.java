package com.wokun.tysl.asset.bean;

import java.io.Serializable;
import java.util.List;

public class ReleaseResponse implements Serializable {

    private List<AssetReleaseBean> release;

    public List<AssetReleaseBean> getRelease() {
        return release;
    }

    public void setRelease(List<AssetReleaseBean> release) {
        this.release = release;
    }
}
