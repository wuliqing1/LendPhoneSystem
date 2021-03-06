package android.wuliqing.com.lendphonesystemapp.dataBase;

import java.util.List;

import de.greenrobot.dao.Property;
import zte.phone.greendao.PhoneNote;
import zte.phone.greendao.PhoneNoteDao;

/**
 * Created by 10172915 on 2016/5/26.
 */
public class PhoneTableAction implements DataBaseAction<PhoneNote> {
    private PhoneNoteDao phoneNoteDao;

    public PhoneTableAction() {
        phoneNoteDao = DBHelper.getInstance().getPhoneNoteDao();
    }

    @Override
    public List<PhoneNote> queryAll() {
        return phoneNoteDao.queryBuilder().orderDesc(PhoneNoteDao.Properties.Phone_time)
                .build().list();
    }

    @Override
    public List<PhoneNote> query(String id) {
        return null;
    }

    @Override
    public List<PhoneNote> queryWithColumn(Property property, Object column) {
        return phoneNoteDao.queryBuilder().where(property.eq(column))
                .orderAsc(PhoneNoteDao.Properties.Phone_time).build().list();
    }

    @Override
    public void add(PhoneNote phoneNote) {
        if (phoneNote == null) {
            throw new IllegalArgumentException();
        }
        phoneNoteDao.insert(phoneNote);
    }

    public void addCollection(List<PhoneNote> phoneNotes) {
        if (phoneNotes == null) {
            throw new IllegalArgumentException();
        }
        phoneNoteDao.insertInTx(phoneNotes);
    }

    @Override
    public List<PhoneNote> queryWithKey(String column, String key) {
        String[] columns = phoneNoteDao.getAllColumns();
        boolean isColumnExist = false;
        for (String column_temp :
                columns) {
            if (column_temp.equalsIgnoreCase(column)) {
                isColumnExist = true;
            }
        }
        if (!isColumnExist || key == null) {
            throw new IllegalArgumentException();
        }
        //需要优化，目前只支持phone_name字段查询
        return phoneNoteDao.queryBuilder().where(PhoneNoteDao.Properties.Phone_name.like(key))
                .orderAsc(PhoneNoteDao.Properties.Phone_time).build().list();
    }

    @Override
    public void clearData() {
        phoneNoteDao.deleteAll();
    }

    @Override
    public PhoneNote queryOneDataWithID(String phone_id) {
        if (phone_id == null) {
            throw new IllegalArgumentException();
        }
        List<PhoneNote> list = phoneNoteDao.queryBuilder()
                .where(PhoneNoteDao.Properties.Bmob_phone_id.eq(phone_id)).build().list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    @Override
    public void remove(String phone_id) {
        if (phone_id == null) {
            throw new IllegalArgumentException();
        }
        List<PhoneNote> list = phoneNoteDao.queryBuilder()
                .where(PhoneNoteDao.Properties.Bmob_phone_id.eq(phone_id)).build().list();
        if (list.size() <= 0) {
            return;
        }
        for (PhoneNote phoneNote : list) {
            phoneNoteDao.delete(phoneNote);
        }
    }

    @Override
    public void remove(long id) {
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        List<PhoneNote> list = phoneNoteDao.queryBuilder()
                .where(PhoneNoteDao.Properties.Id.eq(id)).build().list();
        if (list.size() <= 0) {
            return;
        }
        for (PhoneNote phoneNote : list) {
            phoneNoteDao.delete(phoneNote);
        }
    }

    @Override
    public void update(PhoneNote phoneNote) {
        if (phoneNote == null || phoneNote.getBmob_phone_id() == null) {
            throw new IllegalArgumentException();
        }
        phoneNoteDao.update(phoneNote);
    }

}
