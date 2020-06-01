package com.istudy.Access;

import com.istudy.pojo.MiaoshaUser;

public class UserContext {

    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<>();

    public MiaoshaUser getUser() {
        return userHolder.get();
    }

    public static void setUser(MiaoshaUser user) {
        userHolder.set(user);
    }
}
