package zte.phone.greendao;

import zte.phone.greendao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "LendPhoneNoteTable".
 */
public class LendPhoneNote {

    private Long id;
    private long phone_id;
    private String lend_phone_name;
    private java.util.Date lend_phone_time;
    private Integer lend_phone_number;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient LendPhoneNoteDao myDao;

    private PhoneNote phoneNote;
    private Long phoneNote__resolvedKey;


    public LendPhoneNote() {
    }

    public LendPhoneNote(Long id) {
        this.id = id;
    }

    public LendPhoneNote(Long id, long phone_id, String lend_phone_name, java.util.Date lend_phone_time, Integer lend_phone_number) {
        this.id = id;
        this.phone_id = phone_id;
        this.lend_phone_name = lend_phone_name;
        this.lend_phone_time = lend_phone_time;
        this.lend_phone_number = lend_phone_number;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLendPhoneNoteDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(long phone_id) {
        this.phone_id = phone_id;
    }

    public String getLend_phone_name() {
        return lend_phone_name;
    }

    public void setLend_phone_name(String lend_phone_name) {
        this.lend_phone_name = lend_phone_name;
    }

    public java.util.Date getLend_phone_time() {
        return lend_phone_time;
    }

    public void setLend_phone_time(java.util.Date lend_phone_time) {
        this.lend_phone_time = lend_phone_time;
    }

    public Integer getLend_phone_number() {
        return lend_phone_number;
    }

    public void setLend_phone_number(Integer lend_phone_number) {
        this.lend_phone_number = lend_phone_number;
    }

    /** To-one relationship, resolved on first access. */
    public PhoneNote getPhoneNote() {
        long __key = this.phone_id;
        if (phoneNote__resolvedKey == null || !phoneNote__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhoneNoteDao targetDao = daoSession.getPhoneNoteDao();
            PhoneNote phoneNoteNew = targetDao.load(__key);
            synchronized (this) {
                phoneNote = phoneNoteNew;
            	phoneNote__resolvedKey = __key;
            }
        }
        return phoneNote;
    }

    public void setPhoneNote(PhoneNote phoneNote) {
        if (phoneNote == null) {
            throw new DaoException("To-one property 'phone_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.phoneNote = phoneNote;
            phone_id = phoneNote.getId();
            phoneNote__resolvedKey = phone_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
