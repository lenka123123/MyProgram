package com.wokun.tysl.earnings.bean;

import java.util.List;

public class AccountList {

    private int have;
    private List<AccountListBean> account_list;

    public int getHave() {
        return have;
    }

    public void setHave(int have) {
        this.have = have;
    }

    public List<AccountListBean> getAccount_list() {
        return account_list;
    }

    public void setAccount_list(List<AccountListBean> account_list) {
        this.account_list = account_list;
    }

    public static class AccountListBean {

        private String account_id;
        private String account_type_name;
        private String account_code;
        private int account_type;

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public String getAccount_type_name() {
            return account_type_name;
        }

        public void setAccount_type_name(String account_type_name) {
            this.account_type_name = account_type_name;
        }

        public String getAccount_code() {
            return account_code;
        }

        public void setAccount_code(String account_code) {
            this.account_code = account_code;
        }

        public int getAccount_type() {
            return account_type;
        }

        public void setAccount_type(int account_type) {
            this.account_type = account_type;
        }
    }
}
