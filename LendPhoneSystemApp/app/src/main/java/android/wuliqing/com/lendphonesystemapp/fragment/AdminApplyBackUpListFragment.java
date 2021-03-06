package android.wuliqing.com.lendphonesystemapp.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.ViewGroup;
import android.wuliqing.com.lendphonesystemapp.PhoneDetailActivity;
import android.wuliqing.com.lendphonesystemapp.R;
import android.wuliqing.com.lendphonesystemapp.adapter.AdminApplyBackUpListAdapter;
import android.wuliqing.com.lendphonesystemapp.adapter.BasePullListAdapter;
import android.wuliqing.com.lendphonesystemapp.adapter.recyclerview.OnItemClickListener;
import android.wuliqing.com.lendphonesystemapp.model.AdminPhoneDetailNote;
import android.wuliqing.com.lendphonesystemapp.mvpview.AdminApplyBackUpListView;
import android.wuliqing.com.lendphonesystemapp.presenter.AdminApplyBackUpListPresenter;
import android.wuliqing.com.lendphonesystemapp.utils.ProgressDialogHelper;
import android.wuliqing.com.lendphonesystemapp.widgets.PullRecycler;

import java.util.ArrayList;
import java.util.List;

import zte.phone.greendao.LendPhoneNote;

/**
 * Created by 10172915 on 2016/6/1.
 */
@SuppressWarnings("ALL")
public class AdminApplyBackUpListFragment extends BaseListFragment<LendPhoneNote> implements AdminApplyBackUpListView {
    private AdminApplyBackUpListPresenter mAdminPhoneListPresenter;
    private final List<AdminPhoneDetailNote> adminPhoneDetailNotes = new ArrayList<>();
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (PhoneDetailActivity.LEND_PHONE_NOTE_CHANGE_ACTION.equals(intent.getAction())) {
                updateData();
            }
        }
    };
    private ProgressDialog mProgressDialog;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProgressDialog = ProgressDialogHelper.initProgressDialog(ProgressDialog.STYLE_SPINNER, getActivity(),
                getString(R.string.agree_message));
        recycler.setRefreshing();
    }

    private void updateData() {
        if (recycler != null) {
            recycler.setRefreshing();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void detachPresenter() {
        mAdminPhoneListPresenter.detach();
    }

    @Override
    protected void initParamData() {
    }

    @Override
    protected void createPresenter() {
        mAdminPhoneListPresenter = new AdminApplyBackUpListPresenter();
        mAdminPhoneListPresenter.attach(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(PhoneDetailActivity.LEND_PHONE_NOTE_CHANGE_ACTION);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, filter);
    }


    @Override
    protected BasePullListAdapter createAdapter() {
        BasePullListAdapter<AdminPhoneDetailNote> basePullListAdapter = new AdminApplyBackUpListAdapter(getActivity(),
                adminPhoneDetailNotes);
        basePullListAdapter.setOnItemClickListener(new OnItemClickListener<AdminPhoneDetailNote>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, AdminPhoneDetailNote adminPhoneDetailNote, int position) {
                showApplyBackUpDialog(adminPhoneDetailNote);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, AdminPhoneDetailNote adminPhoneDetailNote, int position) {
                return true;
            }
        });
        return basePullListAdapter;
    }

    private void showApplyBackUpDialog(final AdminPhoneDetailNote adminPhoneDetailNote) {
        MyDialogFragment.newInstance("", getString(R.string.apply_backup_dialog_msg, adminPhoneDetailNote.getPhone_name()),
                new MyDialogFragment.DialogListener() {
                    @Override
                    public void onClickDialogOk() {
                        mProgressDialog.show();
                        mAdminPhoneListPresenter.agreeBackUpApply(adminPhoneDetailNote);
                    }

                    @Override
                    public void onClickDialogCancel() {

                    }
                }).show(getActivity().getFragmentManager(), "");
    }

    @Override
    public void onRefresh(int action) {
        if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
            page = 1;
        }
        mAdminPhoneListPresenter.queryApplyDataBaseAll();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onFetchedLendPhones(List<AdminPhoneDetailNote> lendPhoneNotes) {
        adapter.setData(lendPhoneNotes);
        recycler.onRefreshCompleted();
    }

    @Override
    public void onBackUpResult(boolean result) {
        if (mProgressDialog.isShowing() && !getActivity().isFinishing()) {
            mProgressDialog.dismiss();
        }
    }

}
