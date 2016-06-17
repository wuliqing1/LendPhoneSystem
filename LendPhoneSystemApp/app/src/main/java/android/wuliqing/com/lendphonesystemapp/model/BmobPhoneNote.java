package android.wuliqing.com.lendphonesystemapp.model;

import cn.bmob.v3.BmobObject;
import zte.phone.greendao.PhoneNote;

public class BmobPhoneNote extends BmobObject {
    private String phone_name;
    private Integer phone_number;
    private String project_name;
    private String pic_url;

    public String getPhone_name() {
        return phone_name;
    }

    public void setPhone_name(String phone_name) {
        this.phone_name = phone_name;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public static BmobPhoneNote transformPhoneNote(PhoneNote phoneNote) {
        BmobPhoneNote bmobPhoneNote = new BmobPhoneNote();
        bmobPhoneNote.setPhone_name(phoneNote.getPhone_name());
        bmobPhoneNote.setPhone_number(phoneNote.getPhone_number());
        bmobPhoneNote.setProject_name(phoneNote.getProject_name());
        bmobPhoneNote.setPic_url(phoneNote.getPhone_photo_url());
        return bmobPhoneNote;
    }
}