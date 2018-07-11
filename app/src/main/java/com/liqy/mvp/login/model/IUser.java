package com.liqy.mvp.login.model;

/**
 *
 */
public interface IUser {

    /**
     * 获取用户名
     *
     * @return
     */
    String getName();

    /**
     * 获取密码
     *
     * @return
     */
    String getPasswd();

    /**
     * 检查用户名密码
     *
     * @param name
     * @param passwd
     * @return
     */
    int checkUserValidity(String name, String passwd);
}
