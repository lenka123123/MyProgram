package com.wokun.tysl.myyijian;

import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2018\10\17 0017.
 */

public class HistoryBean  {
       private  String content;
      private  String create_time;
      private  String status;
      private  String do_result;
      private  List<Object> images;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDo_result() {
        return do_result;
    }

    public void setDo_result(String do_result) {
        this.do_result = do_result;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }
}
