package com.lkd.note.model;

/**
 * 实体类，用于封装用户
 */
public class User {
    private String nickname;
    private String phone;
    private String password;
    private int six;

    public User() {
    }

    public User(String nickname, String phone, String password, int six) {
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.six = six;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }


}
