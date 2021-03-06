package zte.phone.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "LendPhoneNoteTable".
 */
public class LendPhoneNote {

    private Long id;
    private String bmob_lend_phone_id;
    private String attach_bmob_phone_id;
    private String lend_phone_name;
    private String lend_phone_time;
    private String lend_phone_photo_url;
    private Long lend_phone_number;
    private Long lend_phone_status;

    public LendPhoneNote() {
    }

    public LendPhoneNote(Long id) {
        this.id = id;
    }

    public LendPhoneNote(Long id, String bmob_lend_phone_id, String attach_bmob_phone_id, String lend_phone_name, String lend_phone_time, String lend_phone_photo_url, Long lend_phone_number, Long lend_phone_status) {
        this.id = id;
        this.bmob_lend_phone_id = bmob_lend_phone_id;
        this.attach_bmob_phone_id = attach_bmob_phone_id;
        this.lend_phone_name = lend_phone_name;
        this.lend_phone_time = lend_phone_time;
        this.lend_phone_photo_url = lend_phone_photo_url;
        this.lend_phone_number = lend_phone_number;
        this.lend_phone_status = lend_phone_status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBmob_lend_phone_id() {
        return bmob_lend_phone_id;
    }

    public void setBmob_lend_phone_id(String bmob_lend_phone_id) {
        this.bmob_lend_phone_id = bmob_lend_phone_id;
    }

    public String getAttach_bmob_phone_id() {
        return attach_bmob_phone_id;
    }

    public void setAttach_bmob_phone_id(String attach_bmob_phone_id) {
        this.attach_bmob_phone_id = attach_bmob_phone_id;
    }

    public String getLend_phone_name() {
        return lend_phone_name;
    }

    public void setLend_phone_name(String lend_phone_name) {
        this.lend_phone_name = lend_phone_name;
    }

    public String getLend_phone_time() {
        return lend_phone_time;
    }

    public void setLend_phone_time(String lend_phone_time) {
        this.lend_phone_time = lend_phone_time;
    }

    public String getLend_phone_photo_url() {
        return lend_phone_photo_url;
    }

    public void setLend_phone_photo_url(String lend_phone_photo_url) {
        this.lend_phone_photo_url = lend_phone_photo_url;
    }

    public Long getLend_phone_number() {
        return lend_phone_number;
    }

    public void setLend_phone_number(Long lend_phone_number) {
        this.lend_phone_number = lend_phone_number;
    }

    public Long getLend_phone_status() {
        return lend_phone_status;
    }

    public void setLend_phone_status(Long lend_phone_status) {
        this.lend_phone_status = lend_phone_status;
    }

}
