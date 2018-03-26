package com.example.competition.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "c_admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    private String username;
    private String nickname;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastime;

    private Integer isban;

    public Admin() {

    }

    public Admin(String password, String username, String nickname, Date lastime, Integer isban) {
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.lastime = lastime;
        this.isban = isban;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getLastime() {
        return lastime;
    }

    public void setLastime(Date lastime) {
        this.lastime = lastime;
    }

    public Integer getIsban() {
        return isban;
    }

    public void setIsban(Integer isban) {
        this.isban = isban;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", lastime=" + lastime +
                ", isban=" + isban +
                '}';
    }
}
