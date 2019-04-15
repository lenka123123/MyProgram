package com.wokun.tysl.model.bean;

import com.shantoo.widget.popupwindow.ActionItem;


public class MActionItem extends ActionItem {

    public String sgc_id;

    public MActionItem(String title, int childTitleColor, int childTitleSelectedColor) {
        super(title, childTitleColor, childTitleSelectedColor);
    }

    public MActionItem(String title, int childTitleColor, int childTitleSelectedColor, boolean isSelected) {
        super(title, childTitleColor, childTitleSelectedColor, isSelected);
    }

    public String getSgc_id() {
        return sgc_id;
    }

    public void setSgc_id(String sgc_id) {
        this.sgc_id = sgc_id;
    }
}
