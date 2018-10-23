package me.mikasa.wandoujia.mvp.model;

import android.app.Activity;

import me.mikasa.wandoujia.bean.ApkContent;
import me.mikasa.wandoujia.mvp.contract.GetApkDetailContract;
import me.mikasa.wandoujia.utils.HttpUrlConnUtil;
import me.mikasa.wandoujia.utils.JsoupUtil;

public class GetApkDetailModelImpl implements GetApkDetailContract.Model {
    private GetApkDetailContract.Presenter mPresenter;
    private static ApkContent apkContent;
    public GetApkDetailModelImpl(GetApkDetailContract.Presenter presenter){
        this.mPresenter=presenter;
    }

    @Override
    public void getApkDetail(final Activity activity, final String link) {
        new Thread(new Runnable() {//网络请求开启异步线程
            @Override
            public void run() {
                String html=HttpUrlConnUtil.doGet(link,"utf-8");//获取html
                apkContent=JsoupUtil.getInstance().getApkContent(html);//解析html
                activity.runOnUiThread(new Runnable() {//返回UI线程更新UI
                    @Override
                    public void run() {
                        if (apkContent!=null){
                            mPresenter.getDetailSuccess(apkContent);
                        }else {
                            mPresenter.getDetailError("获取网络数据失败");
                        }
                    }
                });
            }
        }).start();//start()
    }
}
