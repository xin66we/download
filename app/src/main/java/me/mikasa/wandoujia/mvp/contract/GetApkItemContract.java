package me.mikasa.wandoujia.mvp.contract;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import me.mikasa.wandoujia.bean.ApkItem;

public interface GetApkItemContract {
    interface View{
        void getItemSuccess(List<ApkItem> list);
        void getItemError(String msg);
    }
    interface Model{
        void getItem(Activity activity);//没有getError,getSuccess
    }
    interface Presenter{
        void getItem(Activity activity);
        void getItemSuccess(List<ApkItem> list);
        void getItemError(String msg);
    }
}
