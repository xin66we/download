package me.mikasa.wandoujia.mvp.model;

import android.app.Activity;
import java.util.List;

import me.mikasa.wandoujia.bean.ApkItem;
import me.mikasa.wandoujia.mvp.contract.GetApkItemContract;
import me.mikasa.wandoujia.utils.HttpUrlConnUtil;
import me.mikasa.wandoujia.utils.JsoupUtil;

public class GetApkItemModelImpl implements GetApkItemContract.Model {
    private static final String url="https://sj.qq.com/myapp/category.htm?orgame=1";
    private GetApkItemContract.Presenter mPresenter;
    private static List<ApkItem>itemList=null;
    public GetApkItemModelImpl(GetApkItemContract.Presenter presenter){
        this.mPresenter=presenter;
    }

    @Override
    public void getItem(final Activity activity) {
        new Thread(new Runnable() {//网络请求,开启异步线程
            @Override
            public void run() {
                String html=HttpUrlConnUtil.doGet(url,"utf-8");
                itemList=JsoupUtil.getInstance().getApkItems(html);
                activity.runOnUiThread(new Runnable() {//返回ui线程更新ui
                    @Override
                    public void run() {
                        if (itemList!=null){
                            mPresenter.getItemSuccess(itemList);
                        }else {
                            mPresenter.getItemError("请求网数据失败");
                        }
                    }
                });
            }
        }).start();
    }
}
