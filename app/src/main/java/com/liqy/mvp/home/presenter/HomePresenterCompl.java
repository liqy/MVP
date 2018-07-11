package com.liqy.mvp.home.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.liqy.mvp.home.util.ActivityHolder;
import com.liqy.mvp.home.view.IHomeView;
import com.liqy.mvp.login.LoginActivity;
import com.liqy.mvp.loginoptimized.LoginOptimizedActivity;
import com.liqy.mvp.outteradapter.AdapterActivityA;
import com.liqy.mvp.outteradapter.AdapterActivityB;

/**
 * Created by kaede on 2015/5/19.
 */
public class HomePresenterCompl implements IHomePresenter {
    public static ActivityHolder activityHolder;

    static {
        activityHolder = new ActivityHolder();
        activityHolder.addActivity("MVP with Login Showcase", LoginActivity.class);
        activityHolder.addActivity("Optimized MVP with Login Showcase", LoginOptimizedActivity.class);
        activityHolder.addActivity("MVP in Adapter A", AdapterActivityA.class);
        activityHolder.addActivity("MVP in Adapter B", AdapterActivityB.class);
//		activityHolder.addActivity("MVP with EventBus", EventBusActivity.class);
//		activityHolder.addActivity("MVP with EventBus in Fragments", FragmentsActivity.class);
    }

    Context context;
    IHomeView homeView;

    public HomePresenterCompl(Context context, IHomeView homeView) {
        this.context = context;
        this.homeView = homeView;
    }

    @Override
    public void loadDatas() {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeView.onGetDataList(activityHolder.getNameList());
            }
        }, 2000);
    }

    @Override
    public void onItemClick(int position) {
        Class activity = activityHolder.getActivity(activityHolder.getNameList().get(position));
        if (activity != null) {
            context.startActivity(new Intent(context, activity));
        }
    }
}
