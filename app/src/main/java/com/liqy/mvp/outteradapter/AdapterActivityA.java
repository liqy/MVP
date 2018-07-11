package com.liqy.mvp.outteradapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.liqy.mvp.R;
import com.liqy.mvp.outteradapter.adapter.CustomAdapter;
import com.liqy.mvp.outteradapter.presenter.AdapterPresenterCompl;
import com.liqy.mvp.outteradapter.presenter.IAdapterPresenter;
import com.liqy.mvp.outteradapter.view.IAdapterView;

import java.util.List;

public class AdapterActivityA extends AppCompatActivity implements IAdapterView, AdapterView.OnItemClickListener {

    private ListView listView;
    private View footerViewVisible;
    private IAdapterPresenter iAdapterPresenter;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //find view
        listView = (ListView) this.findViewById(R.id.list_home);

        //set listener
        listView.setOnItemClickListener(this);

        //init
        listView.setDivider(null);
        View loadingView = LayoutInflater.from(this).inflate(R.layout.item_empty_view, null);
        ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.layout_home);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewGroup.addView(loadingView, layoutParams);
        listView.setEmptyView(loadingView);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, null);
        listView.addFooterView(footerView);
        footerViewVisible = footerView.findViewById(R.id.layout_visible);
        iAdapterPresenter = new AdapterPresenterCompl(this);
        iAdapterPresenter.showFooterView(false);

        adapter = new CustomAdapter(iAdapterPresenter);
        listView.setAdapter(adapter);
        iAdapterPresenter.loadDatas();
    }

    @Override
    public void onGetDataList(List<String> datas) {
        adapter.setDatas(datas);
    }

    @Override
    public void onLoadMoreData(String item) {
        adapter.addItem(item);
        iAdapterPresenter.showFooterView(false);
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, "AdapterActivityA : " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Activity onGetActivity() {
        return this;
    }

    @Override
    public void onShowFooterView(Boolean isShow) {
        if (isShow) {
            footerViewVisible.setVisibility(View.VISIBLE);
        } else {
            footerViewVisible.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toast(adapter.getItem(position));
    }
}
