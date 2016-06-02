package android.wuliqing.com.lendphonesystemapp.dataBase;

import java.util.Date;
import java.util.List;

import zte.phone.greendao.LendPhoneNote;
import zte.phone.greendao.LendPhoneNoteDao;

/**
 * Created by 10172915 on 2016/5/26.
 */
public class LendPhoneTableAction implements DataBaseAction<LendPhoneNote> {
    private LendPhoneNoteDao lendPhoneNoteDao;
    private long phone_id;

    public LendPhoneTableAction(long phone_id) {
        lendPhoneNoteDao = DBHelper.getInstance().getLendPhoneNoteDao();
        this.phone_id = phone_id;
    }

    @Override
    public List<LendPhoneNote> query() {
        if (phone_id <= 0) {
            throw new IllegalArgumentException();
        }
        List<LendPhoneNote> list = lendPhoneNoteDao.queryBuilder()
                .where(LendPhoneNoteDao.Properties.Phone_id.eq(phone_id)).build().list();
        return list;
    }

    @Override
    public void update(LendPhoneNote note) {
        if (note == null || note.getId() <= 0) {
            throw new IllegalArgumentException();
        }
        note.setLend_phone_time(new Date());
        lendPhoneNoteDao.update(note);
    }

    @Override
    public void remove(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        List<LendPhoneNote> list = lendPhoneNoteDao.queryBuilder()
                .where(LendPhoneNoteDao.Properties.Id.eq(id)).build().list();
        if (list.size() <= 0) {
            return;
        }
        for (LendPhoneNote lendPhoneNote : list) {
            lendPhoneNoteDao.delete(lendPhoneNote);
        }
    }

    @Override
    public void add(LendPhoneNote note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }
        note.setLend_phone_time(new Date());
        lendPhoneNoteDao.insert(note);
    }

    @Override
    public List<LendPhoneNote> queryWithKey(String column, String key) {
        String[] columns = lendPhoneNoteDao.getAllColumns();
        boolean isColumnExist = false;
        for (String column_temp :
                columns) {
            if (column_temp.equalsIgnoreCase(column)) {
                isColumnExist = true;
            }
        }
        if(!isColumnExist || key == null){
            throw new IllegalArgumentException();
        }
        //需要优化，目前只支持lend_phone_name字段查询
        List<LendPhoneNote> list = lendPhoneNoteDao.queryBuilder()
                .where(LendPhoneNoteDao.Properties.Lend_phone_name.like(key))
                .orderAsc(LendPhoneNoteDao.Properties.Lend_phone_time).build().list();
        return list;
    }

    @Override
    public void clearData() {
        lendPhoneNoteDao.deleteAll();
    }

}