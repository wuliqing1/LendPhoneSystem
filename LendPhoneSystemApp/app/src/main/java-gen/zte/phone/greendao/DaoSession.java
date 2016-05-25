package zte.phone.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import zte.phone.greendao.PhoneNote;
import zte.phone.greendao.LendPhoneNote;

import zte.phone.greendao.PhoneNoteDao;
import zte.phone.greendao.LendPhoneNoteDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig phoneNoteDaoConfig;
    private final DaoConfig lendPhoneNoteDaoConfig;

    private final PhoneNoteDao phoneNoteDao;
    private final LendPhoneNoteDao lendPhoneNoteDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        phoneNoteDaoConfig = daoConfigMap.get(PhoneNoteDao.class).clone();
        phoneNoteDaoConfig.initIdentityScope(type);

        lendPhoneNoteDaoConfig = daoConfigMap.get(LendPhoneNoteDao.class).clone();
        lendPhoneNoteDaoConfig.initIdentityScope(type);

        phoneNoteDao = new PhoneNoteDao(phoneNoteDaoConfig, this);
        lendPhoneNoteDao = new LendPhoneNoteDao(lendPhoneNoteDaoConfig, this);

        registerDao(PhoneNote.class, phoneNoteDao);
        registerDao(LendPhoneNote.class, lendPhoneNoteDao);
    }
    
    public void clear() {
        phoneNoteDaoConfig.getIdentityScope().clear();
        lendPhoneNoteDaoConfig.getIdentityScope().clear();
    }

    public PhoneNoteDao getPhoneNoteDao() {
        return phoneNoteDao;
    }

    public LendPhoneNoteDao getLendPhoneNoteDao() {
        return lendPhoneNoteDao;
    }

}
