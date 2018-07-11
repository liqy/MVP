package com.liqy.mvp.login.presenter;

/**
 * Created by kaede on 2015/10/12.
 */
public interface ILoginPresenter {
    /**
     * 清除输入
     */
    void clear();

    /**
     * 执行登录接口
     *
     * @param name
     * @param passwd
     */
    void doLogin(String name, String passwd);

    /**
     * 登录过程中显示隐藏进度条
     *
     * @param visiblity
     */
    void setProgressBarVisiblity(int visiblity);
}
