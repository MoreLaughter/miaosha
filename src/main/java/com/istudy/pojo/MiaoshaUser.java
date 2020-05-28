package com.istudy.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "miaosha_user")
public class MiaoshaUser implements Serializable {
    /**
     * 用户id,手机号码
     */
    @Id
    private Long id;

    /**
     * 登录昵称
     */
    private String nickname;

    /**
     * MD5(MD5(pass明文+固定salt)+salt)
     */
    private String password;

    private String salt;

    /**
     * 头像，云存储的id
     */
    private String head;

    /**
     * 注册时间
     */
    @Column(name = "register_date")
    private Date registerDate;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    /**
     * 获取用户id,手机号码
     *
     * @return id - 用户id,手机号码
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户id,手机号码
     *
     * @param id 用户id,手机号码
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取登录昵称
     *
     * @return nickname - 登录昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置登录昵称
     *
     * @param nickname 登录昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取MD5(MD5(pass明文+固定salt)+salt)
     *
     * @return password - MD5(MD5(pass明文+固定salt)+salt)
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置MD5(MD5(pass明文+固定salt)+salt)
     *
     * @param password MD5(MD5(pass明文+固定salt)+salt)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取头像，云存储的id
     *
     * @return head - 头像，云存储的id
     */
    public String getHead() {
        return head;
    }

    /**
     * 设置头像，云存储的id
     *
     * @param head 头像，云存储的id
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * 获取注册时间
     *
     * @return register_date - 注册时间
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 设置注册时间
     *
     * @param registerDate 注册时间
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 获取上次登录时间
     *
     * @return last_login_date - 上次登录时间
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置上次登录时间
     *
     * @param lastLoginDate 上次登录时间
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取登录次数
     *
     * @return login_count - 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登录次数
     *
     * @param loginCount 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}