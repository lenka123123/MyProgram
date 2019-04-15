package com.wokun.tysl.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public class HomeAddBean implements Serializable {


    /**
     * msg : 成功
     * data : {"naojiankang":[{"goods_name":"脑康键","goods_pic":"http://img.tyitop.com/m_tyitop_com/goods/goods_d71c503d5e15c243_360.jpeg","goods_id":"490","goods_desc":"守护脑健康"},{"goods_name":"脑动力","goods_pic":"http://img.tyitop.com/m_tyitop_com/goods/goods_d71c503d5e15c243_360.jpeg","goods_id":"487","goods_desc":"唤醒脑动力"},{"goods_name":"脑优能","goods_pic":"http://img.tyitop.com/m_tyitop_com/goods/goods_d71c503d5e15c243_360.jpeg","goods_id":"488","goods_desc":"调动脑活力"}],"ad":{"image":"http://weixin.tyitop.com/wx/taiyi_shouye/img/shouye-advertise.jpg","url":"http://weixin.tyitop.com/wx/taiyi_shouye/haishang-zhongyi.html?source=app"},"banner":[{"pic_id":"13","type":"0","picture":"http://api.tyitop.com/uploads/picture/banner_new3.png","url":null},{"pic_id":"5","type":"0","picture":"http://api.tyitop.com/uploads/picture/shouye-banner2.jpg","url":"http://weixin.tyitop.com/wx/taiyi_shouye/banner_jiaosu.html?source=app"},{"pic_id":"1","type":"0","picture":"http://api.tyitop.com/uploads/picture/shouye-banner1.jpg","url":"http://weixin.tyitop.com/wx/taiyi_shouye/banner_yun.html?source=app"}],"serviceStar":[{"dietitian_id":"60","truename":"程路","head_logo":"http://img.tyitop.com/m_tyitop_com/avatar/user_1465273609116.jpeg","jobtype":"公共营养师"},{"dietitian_id":"61","truename":"张学平","head_logo":"http://img.tyitop.com/m_tyitop_com/avatar/user_1464569099612.jpg","jobtype":"临床营养师"},{"dietitian_id":"62","truename":"沈夏冰","head_logo":"http://img.tyitop.com/m_tyitop_com/avatar/user_1464569631755.jpg","jobtype":"公共营养师"},{"dietitian_id":"74","truename":"徐小宁","head_logo":"http://img.tyitop.com/m_tyitop_com/avatar/user_1464683562119.jpeg","jobtype":"食疗调理"}],"jigou":[{"imgurl":"http://weixin.tyitop.com/images/jigou1.jpg","title":"安徽宜经堂健康管理有限公司","url":"http://weixin.tyitop.com/wx/taiyi_shouye/fuwu_anhui.html?source=app","desc":"宜经堂是全国首家运用传统中医经络学..."},{"imgurl":"http://weixin.tyitop.com/images/jigou2.jpg","title":"苏州月子煲姆健康科技有限公司","url":"http://weixin.tyitop.com/wx/taiyi_shouye/fuwu_suzhou.html?source=app","desc":"成立于2011年，总部设立在国际化时尚..."},{"imgurl":"http://weixin.tyitop.com/images/jigou3.jpg","title":"新视野商业运营管理有限公司","url":"http://weixin.tyitop.com/wx/taiyi_shouye/fuwu_xinye.html?source=app","desc":"专注于日常生活饮食，对健康问题有深入..."}],"article":[{"article_id":"365","good_num":"356","article_image":"http://img.tyitop.com/m_tyitop_com/article/575e148a36710.png","click_num":"3166","title":"脾有病，人就废一半！脾衰比肾虚更可怕，99%的人不知道！","add_time":"2019/03/27"},{"article_id":"364","good_num":"544","article_image":"http://img.tyitop.com/m_tyitop_com/article/微信图片_20190325104919.jpg","click_num":"7359","title":"这些习惯让人越睡越累！最后一个很多人都有","add_time":"2019/03/25"},{"article_id":"363","good_num":"799","article_image":"http://img.tyitop.com/m_tyitop_com/article/微信图片_20190318102340.jpg","click_num":"9250","title":"手掌上这块肉，一看就知道你身体好不好！","add_time":"2019/03/18"},{"article_id":"362","good_num":"431","article_image":"http://img.tyitop.com/m_tyitop_com/article/201505291653265233914.jpg","click_num":"5284","title":"这种水果春天吃是\u201c毒\u201d，夏天吃是\u201c宝\u201d！你可千万别吃错了","add_time":"2019/03/15"},{"article_id":"361","good_num":"456","article_image":"http://img.tyitop.com/m_tyitop_com/article/微信图片_20190313111344.jpg","click_num":"6239","title":"有些病不要急于施治！横跨2千年的长寿五大秘密，震惊世界~","add_time":"2019/03/13"}],"retail":{"image3":"http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-3.png","url3":"http://weixin.tyitop.com/wx/taiyi_shouye/zhls_linshougui.html?source=app","image4":"http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-4.png","url4":"http://weixin.tyitop.com/wx/taiyi_shouye/zhls_bfsw.html?source=app","url1":"http://weixin.tyitop.com/wx/join/index.html?source=app","url2":"http://weixin.tyitop.com/wx/taiyi_shouye/zhls_info.html?source=app","image1":"http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-1.png","image2":"http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-2.png"},"notice":[{"title":"2.1-2.11放假期间正常下单，节后依次发货。","notice_id":"116","content":"太宜食聊专注营养健康服务"},{"title":"太宜食聊专注营养健康服务","notice_id":"1","content":"太宜食聊专注营养健康服务"}],"njk_ad":"http://weixin.tyitop.com/wx/taiyi_shouye/img/brain-adNew.png"}
     * stateCode : 00001
     * state : true
     */
    private String msg;
    private DataEntity data;
    private String stateCode;
    private boolean state;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getStateCode() {
        return stateCode;
    }

    public boolean isState() {
        return state;
    }

    public class DataEntity {
        /**
         * naojiankang : [{"goods_name":"脑康键","goods_pic":"http://img.tyitop.com/m_tyitop_com/goods/goods_d71c503d5e15c243_360.jpeg","goods_id":"490","goods_desc":"守护脑健康"},{"goods_name":"脑动力","goods_pic":"http://img.tyitop.com/m_tyitop_com/goods/goods_d71c503d5e15c243_360.jpeg","goods_id":"487","goods_desc":"唤醒脑动力"},{"goods_name":"脑优能","goods_pic":"http://img.tyitop.com/m_tyitop_com/goods/goods_d71c503d5e15c243_360.jpeg","goods_id":"488","goods_desc":"调动脑活力"}]
         * ad : {"image":"http://weixin.tyitop.com/wx/taiyi_shouye/img/shouye-advertise.jpg","url":"http://weixin.tyitop.com/wx/taiyi_shouye/haishang-zhongyi.html?source=app"}
         * banner : [{"pic_id":"13","type":"0","picture":"http://api.tyitop.com/uploads/picture/banner_new3.png","url":null},{"pic_id":"5","type":"0","picture":"http://api.tyitop.com/uploads/picture/shouye-banner2.jpg","url":"http://weixin.tyitop.com/wx/taiyi_shouye/banner_jiaosu.html?source=app"},{"pic_id":"1","type":"0","picture":"http://api.tyitop.com/uploads/picture/shouye-banner1.jpg","url":"http://weixin.tyitop.com/wx/taiyi_shouye/banner_yun.html?source=app"}]
         * serviceStar : [{"dietitian_id":"60","truename":"程路","head_logo":"http://img.tyitop.com/m_tyitop_com/avatar/user_1465273609116.jpeg","jobtype":"公共营养师"},{"dietitian_id":"61","truename":"张学平","head_logo":"http://img.tyitop.com/m_tyitop_com/avatar/user_1464569099612.jpg","jobtype":"临床营养师"},{"dietitian_id":"62","truename":"沈夏冰","head_logo":"http://img.tyitop.com/m_tyitop_com/avatar/user_1464569631755.jpg","jobtype":"公共营养师"},{"dietitian_id":"74","truename":"徐小宁","head_logo":"http://img.tyitop.com/m_tyitop_com/avatar/user_1464683562119.jpeg","jobtype":"食疗调理"}]
         * jigou : [{"imgurl":"http://weixin.tyitop.com/images/jigou1.jpg","title":"安徽宜经堂健康管理有限公司","url":"http://weixin.tyitop.com/wx/taiyi_shouye/fuwu_anhui.html?source=app","desc":"宜经堂是全国首家运用传统中医经络学..."},{"imgurl":"http://weixin.tyitop.com/images/jigou2.jpg","title":"苏州月子煲姆健康科技有限公司","url":"http://weixin.tyitop.com/wx/taiyi_shouye/fuwu_suzhou.html?source=app","desc":"成立于2011年，总部设立在国际化时尚..."},{"imgurl":"http://weixin.tyitop.com/images/jigou3.jpg","title":"新视野商业运营管理有限公司","url":"http://weixin.tyitop.com/wx/taiyi_shouye/fuwu_xinye.html?source=app","desc":"专注于日常生活饮食，对健康问题有深入..."}]
         * article : [{"article_id":"365","good_num":"356","article_image":"http://img.tyitop.com/m_tyitop_com/article/575e148a36710.png","click_num":"3166","title":"脾有病，人就废一半！脾衰比肾虚更可怕，99%的人不知道！","add_time":"2019/03/27"},{"article_id":"364","good_num":"544","article_image":"http://img.tyitop.com/m_tyitop_com/article/微信图片_20190325104919.jpg","click_num":"7359","title":"这些习惯让人越睡越累！最后一个很多人都有","add_time":"2019/03/25"},{"article_id":"363","good_num":"799","article_image":"http://img.tyitop.com/m_tyitop_com/article/微信图片_20190318102340.jpg","click_num":"9250","title":"手掌上这块肉，一看就知道你身体好不好！","add_time":"2019/03/18"},{"article_id":"362","good_num":"431","article_image":"http://img.tyitop.com/m_tyitop_com/article/201505291653265233914.jpg","click_num":"5284","title":"这种水果春天吃是\u201c毒\u201d，夏天吃是\u201c宝\u201d！你可千万别吃错了","add_time":"2019/03/15"},{"article_id":"361","good_num":"456","article_image":"http://img.tyitop.com/m_tyitop_com/article/微信图片_20190313111344.jpg","click_num":"6239","title":"有些病不要急于施治！横跨2千年的长寿五大秘密，震惊世界~","add_time":"2019/03/13"}]
         * retail : {"image3":"http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-3.png","url3":"http://weixin.tyitop.com/wx/taiyi_shouye/zhls_linshougui.html?source=app","image4":"http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-4.png","url4":"http://weixin.tyitop.com/wx/taiyi_shouye/zhls_bfsw.html?source=app","url1":"http://weixin.tyitop.com/wx/join/index.html?source=app","url2":"http://weixin.tyitop.com/wx/taiyi_shouye/zhls_info.html?source=app","image1":"http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-1.png","image2":"http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-2.png"}
         * notice : [{"title":"2.1-2.11放假期间正常下单，节后依次发货。","notice_id":"116","content":"太宜食聊专注营养健康服务"},{"title":"太宜食聊专注营养健康服务","notice_id":"1","content":"太宜食聊专注营养健康服务"}]
         * njk_ad : http://weixin.tyitop.com/wx/taiyi_shouye/img/brain-adNew.png
         */
        private List<NaojiankangEntity> naojiankang;
        private AdEntity ad;
        private List<BannerEntity> banner;
        private List<ServiceStarEntity> serviceStar;
        private List<JigouEntity> jigou;
        private List<ArticleEntity> article;
        private RetailEntity retail;
        private List<NoticeEntity> notice;
        private String njk_ad;

        public void setNaojiankang(List<NaojiankangEntity> naojiankang) {
            this.naojiankang = naojiankang;
        }

        public void setAd(AdEntity ad) {
            this.ad = ad;
        }

        public void setBanner(List<BannerEntity> banner) {
            this.banner = banner;
        }

        public void setServiceStar(List<ServiceStarEntity> serviceStar) {
            this.serviceStar = serviceStar;
        }

        public void setJigou(List<JigouEntity> jigou) {
            this.jigou = jigou;
        }

        public void setArticle(List<ArticleEntity> article) {
            this.article = article;
        }

        public void setRetail(RetailEntity retail) {
            this.retail = retail;
        }

        public void setNotice(List<NoticeEntity> notice) {
            this.notice = notice;
        }

        public void setNjk_ad(String njk_ad) {
            this.njk_ad = njk_ad;
        }

        public List<NaojiankangEntity> getNaojiankang() {
            return naojiankang;
        }

        public AdEntity getAd() {
            return ad;
        }

        public List<BannerEntity> getBanner() {
            return banner;
        }

        public List<ServiceStarEntity> getServiceStar() {
            return serviceStar;
        }

        public List<JigouEntity> getJigou() {
            return jigou;
        }

        public List<ArticleEntity> getArticle() {
            return article;
        }

        public RetailEntity getRetail() {
            return retail;
        }

        public List<NoticeEntity> getNotice() {
            return notice;
        }

        public String getNjk_ad() {
            return njk_ad;
        }

        public class NaojiankangEntity {
            /**
             * goods_name : 脑康键
             * goods_pic : http://img.tyitop.com/m_tyitop_com/goods/goods_d71c503d5e15c243_360.jpeg
             * goods_id : 490
             * goods_desc : 守护脑健康
             */
            private String goods_name;
            private String goods_pic;
            private String goods_id;
            private String goods_desc;

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setGoods_pic(String goods_pic) {
                this.goods_pic = goods_pic;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getGoods_pic() {
                return goods_pic;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getGoods_desc() {
                return goods_desc;
            }
        }

        public class AdEntity {
            /**
             * image : http://weixin.tyitop.com/wx/taiyi_shouye/img/shouye-advertise.jpg
             * url : http://weixin.tyitop.com/wx/taiyi_shouye/haishang-zhongyi.html?source=app
             */
            private String image;
            private String url;

            public void setImage(String image) {
                this.image = image;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImage() {
                return image;
            }

            public String getUrl() {
                return url;
            }
        }

        public class BannerEntity {
            /**
             * pic_id : 13
             * type : 0
             * picture : http://api.tyitop.com/uploads/picture/banner_new3.png
             * url : null
             */
            private String pic_id;
            private String type;
            private String picture;
            private String url;

            public void setPic_id(String pic_id) {
                this.pic_id = pic_id;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPic_id() {
                return pic_id;
            }

            public String getType() {
                return type;
            }

            public String getPicture() {
                return picture;
            }

            public String getUrl() {
                return url;
            }
        }

        public class ServiceStarEntity {
            /**
             * dietitian_id : 60
             * truename : 程路
             * head_logo : http://img.tyitop.com/m_tyitop_com/avatar/user_1465273609116.jpeg
             * jobtype : 公共营养师
             */
            private String dietitian_id;
            private String truename;
            private String head_logo;
            private String jobtype;

            public void setDietitian_id(String dietitian_id) {
                this.dietitian_id = dietitian_id;
            }

            public void setTruename(String truename) {
                this.truename = truename;
            }

            public void setHead_logo(String head_logo) {
                this.head_logo = head_logo;
            }

            public void setJobtype(String jobtype) {
                this.jobtype = jobtype;
            }

            public String getDietitian_id() {
                return dietitian_id;
            }

            public String getTruename() {
                return truename;
            }

            public String getHead_logo() {
                return head_logo;
            }

            public String getJobtype() {
                return jobtype;
            }
        }

        public class JigouEntity {
            /**
             * imgurl : http://weixin.tyitop.com/images/jigou1.jpg
             * title : 安徽宜经堂健康管理有限公司
             * url : http://weixin.tyitop.com/wx/taiyi_shouye/fuwu_anhui.html?source=app
             * desc : 宜经堂是全国首家运用传统中医经络学...
             */
            private String imgurl;
            private String title;
            private String url;
            private String desc;

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getImgurl() {
                return imgurl;
            }

            public String getTitle() {
                return title;
            }

            public String getUrl() {
                return url;
            }

            public String getDesc() {
                return desc;
            }
        }

        public class ArticleEntity {
            /**
             * article_id : 365
             * good_num : 356
             * article_image : http://img.tyitop.com/m_tyitop_com/article/575e148a36710.png
             * click_num : 3166
             * title : 脾有病，人就废一半！脾衰比肾虚更可怕，99%的人不知道！
             * add_time : 2019/03/27
             */
            private String article_id;
            private String good_num;
            private String article_image;
            private String click_num;
            private String title;
            private String add_time;

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            public void setGood_num(String good_num) {
                this.good_num = good_num;
            }

            public void setArticle_image(String article_image) {
                this.article_image = article_image;
            }

            public void setClick_num(String click_num) {
                this.click_num = click_num;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getArticle_id() {
                return article_id;
            }

            public String getGood_num() {
                return good_num;
            }

            public String getArticle_image() {
                return article_image;
            }

            public String getClick_num() {
                return click_num;
            }

            public String getTitle() {
                return title;
            }

            public String getAdd_time() {
                return add_time;
            }
        }

        public class RetailEntity {
            /**
             * image3 : http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-3.png
             * url3 : http://weixin.tyitop.com/wx/taiyi_shouye/zhls_linshougui.html?source=app
             * image4 : http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-4.png
             * url4 : http://weixin.tyitop.com/wx/taiyi_shouye/zhls_bfsw.html?source=app
             * url1 : http://weixin.tyitop.com/wx/join/index.html?source=app
             * url2 : http://weixin.tyitop.com/wx/taiyi_shouye/zhls_info.html?source=app
             * image1 : http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-1.png
             * image2 : http://weixin.tyitop.com/wx/taiyi_shouye/img/zhls-2.png
             */
            private String image3;
            private String url3;
            private String image4;
            private String url4;
            private String url1;
            private String url2;
            private String image1;
            private String image2;

            public void setImage3(String image3) {
                this.image3 = image3;
            }

            public void setUrl3(String url3) {
                this.url3 = url3;
            }

            public void setImage4(String image4) {
                this.image4 = image4;
            }

            public void setUrl4(String url4) {
                this.url4 = url4;
            }

            public void setUrl1(String url1) {
                this.url1 = url1;
            }

            public void setUrl2(String url2) {
                this.url2 = url2;
            }

            public void setImage1(String image1) {
                this.image1 = image1;
            }

            public void setImage2(String image2) {
                this.image2 = image2;
            }

            public String getImage3() {
                return image3;
            }

            public String getUrl3() {
                return url3;
            }

            public String getImage4() {
                return image4;
            }

            public String getUrl4() {
                return url4;
            }

            public String getUrl1() {
                return url1;
            }

            public String getUrl2() {
                return url2;
            }

            public String getImage1() {
                return image1;
            }

            public String getImage2() {
                return image2;
            }
        }

        public class NoticeEntity {
            /**
             * title : 2.1-2.11放假期间正常下单，节后依次发货。
             * notice_id : 116
             * content : 太宜食聊专注营养健康服务
             */
            private String title;
            private String notice_id;
            private String content;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setNotice_id(String notice_id) {
                this.notice_id = notice_id;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public String getNotice_id() {
                return notice_id;
            }

            public String getContent() {
                return content;
            }
        }
    }
}
