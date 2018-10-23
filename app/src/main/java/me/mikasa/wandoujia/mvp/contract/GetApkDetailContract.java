package me.mikasa.wandoujia.mvp.contract;

import android.app.Activity;

import me.mikasa.wandoujia.bean.ApkContent;

public interface GetApkDetailContract {
    interface View{
        void getDetailSuccess(ApkContent apkContent);
        void getDetailError(String s);
    }
    interface Model{
        void getApkDetail(Activity activity,String link);//没有getError,getSuccess
    }
    interface Presenter{
        void getApkDetail(Activity activity,String link);
        void getDetailSuccess(ApkContent apkContent);
        void getDetailError(String s);
    }
}
