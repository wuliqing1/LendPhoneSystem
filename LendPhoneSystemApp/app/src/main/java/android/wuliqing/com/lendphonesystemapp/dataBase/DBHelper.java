package android.wuliqing.com.lendphonesystemapp.dataBase;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zte.phone.greendao.DaoMaster;
import zte.phone.greendao.DaoSession;
import zte.phone.greendao.LendPhoneNote;
import zte.phone.greendao.LendPhoneNoteDao;
import zte.phone.greendao.PhoneNote;
import zte.phone.greendao.PhoneNoteDao;

/**
 * Created by 10172915 on 2016/5/25.
 */
public class DBHelper {
    private static DBHelper instance;
//    private static Context mContext;

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private PhoneNoteDao phoneNoteDao;
    private LendPhoneNoteDao lendPhoneNoteDao;

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    "lend_phone.db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    private DBHelper() {
    }

    public static void init(Context context) {
        Context mContext = context;
        instance = new DBHelper();
        // 数据库对象
        DaoSession daoSession = getDaoSession(mContext);
        instance.setPhoneNoteDao(daoSession.getPhoneNoteDao());
        instance.setLendPhoneNoteDao(daoSession.getLendPhoneNoteDao());
    }

    private void setPhoneNoteDao(PhoneNoteDao phoneNoteDao) {
        this.phoneNoteDao = phoneNoteDao;
    }

    private void setLendPhoneNoteDao(LendPhoneNoteDao lendPhoneNoteDao) {
        this.lendPhoneNoteDao = lendPhoneNoteDao;
    }

    public PhoneNoteDao getPhoneNoteDao() {
        return phoneNoteDao;
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    public LendPhoneNoteDao getLendPhoneNoteDao() {
        return lendPhoneNoteDao;
    }

    public void PhoneTableAdd(String phone_name, long phone_number, String project_name, String url) {
        PhoneNote phoneNote = new PhoneNote();
        phoneNote.setPhone_name(phone_name);
        phoneNote.setPhone_number(phone_number);
        phoneNote.setProject_name(project_name);
        phoneNote.setPhone_time(new Date().toString());
        phoneNote.setPhone_photo_url(url);
        new PhoneTableAction().add(phoneNote);
    }

    public void PhoneTableRemove(String phone_id) {
        new PhoneTableAction().remove(phone_id);
    }

    public void PhoneTableUpdate(PhoneNote phoneNote) {
        new PhoneTableAction().update(phoneNote);
    }

    public List<PhoneNote> PhoneTableQuery() {
        return new PhoneTableAction().queryAll();
    }

    public void LendPhoneTableAdd(String lend_phone_name, long lend_phone_number, String phone_id) {
        if (phone_id == null) {
            throw new IllegalArgumentException();
        }
        LendPhoneNote lendPhoneNote = new LendPhoneNote();
        lendPhoneNote.setLend_phone_name(lend_phone_name);
        lendPhoneNote.setLend_phone_number(lend_phone_number);
//        lendPhoneNote.setLend_phone_time(new Date().toString());
        lendPhoneNote.setAttach_bmob_phone_id(phone_id);
        new LendPhoneTableAction().add(lendPhoneNote);
    }

    public void LendPhoneTableRemove(String id) {
        new LendPhoneTableAction().remove(id);
    }

    public void LendPhoneTableUpdate(LendPhoneNote lendPhoneNote) {
        new LendPhoneTableAction().update(lendPhoneNote);
    }

    public List<LendPhoneNote> LendPhoneTableQuery(String phone_id) {
        return new LendPhoneTableAction().queryAll();
    }

    public String lendPhoneNames(String phone_id) {
        if (phone_id == null) {
            throw new IllegalArgumentException();
        }
        List<String> names = new ArrayList<>();
        List<LendPhoneNote> list = lendPhoneNoteDao.queryBuilder()
                .where(LendPhoneNoteDao.Properties.Attach_bmob_phone_id.eq(phone_id)).build().list();
        for (LendPhoneNote lendPhoneNote : list) {
            if (!names.contains(lendPhoneNote.getLend_phone_name())) {
                names.add(lendPhoneNote.getLend_phone_name());
            }
        }
        list = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (i != names.size() - 1) {
                stringBuilder.append(name).append(',');
            } else {
                stringBuilder.append(name);
            }
        }
        names = null;
        return stringBuilder.toString();
    }

    public int LendPhoneNumber(String phone_id) {//借手机数量
        if (phone_id == null) {
            throw new IllegalArgumentException();
        }
        int number = 0;
        List<LendPhoneNote> list = lendPhoneNoteDao.queryBuilder()
                .where(LendPhoneNoteDao.Properties.Attach_bmob_phone_id.eq(phone_id)).build().list();
        for (LendPhoneNote lendPhoneNote : list) {
            number += lendPhoneNote.getLend_phone_number();
        }
        list = null;
        return number;
    }

    public int LeftPhoneNumber(String phone_id) {//剩余手机数量
        if (TextUtils.isEmpty(phone_id)) {
            throw new IllegalArgumentException();
        }
        int lend_number = 0;
        List<PhoneNote> phoneNotes = phoneNoteDao.queryBuilder()
                .where(PhoneNoteDao.Properties.Bmob_phone_id.eq(phone_id)).build().list();
        int total_number = 0;
        for (PhoneNote phoneNote : phoneNotes) {
            total_number += phoneNote.getPhone_number();
        }
        List<LendPhoneNote> lendPhoneNotes = lendPhoneNoteDao.queryBuilder()
                .where(LendPhoneNoteDao.Properties.Attach_bmob_phone_id.eq(phone_id)).build().list();
        for (LendPhoneNote lendPhoneNote : lendPhoneNotes) {
            lend_number += lendPhoneNote.getLend_phone_number();
        }
//        if (lend_number > total_number){
//            throw new IndexOutOfBoundsException();
//        }
        phoneNotes = null;
        lendPhoneNotes = null;
        int left_num = total_number - lend_number;
        return left_num < 0 ? 0 : left_num;
    }
}
