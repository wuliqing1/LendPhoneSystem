package zte.phone.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PhoneNoteTable".
 */
public class PhoneNote {

    private Long id;
    private String phone_name;
    private Integer phone_number;
    private String project_name;
    private java.util.Date phone_time;
    private byte[] phone_photo;

    public PhoneNote() {
    }

    public PhoneNote(Long id) {
        this.id = id;
    }

    public PhoneNote(Long id, String phone_name, Integer phone_number, String project_name, java.util.Date phone_time, byte[] phone_photo) {
        this.id = id;
        this.phone_name = phone_name;
        this.phone_number = phone_number;
        this.project_name = project_name;
        this.phone_time = phone_time;
        this.phone_photo = phone_photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public java.util.Date getPhone_time() {
        return phone_time;
    }

    public void setPhone_time(java.util.Date phone_time) {
        this.phone_time = phone_time;
    }

    public byte[] getPhone_photo() {
        return phone_photo;
    }

    public void setPhone_photo(byte[] phone_photo) {
        this.phone_photo = phone_photo;
    }

}
