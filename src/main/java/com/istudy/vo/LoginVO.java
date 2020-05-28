package com.istudy.vo;

import com.istudy.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


public class LoginVO {
    @NotNull
    private String password;

    @IsMobile
    @Length(min = 11)
    private String mobile;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
