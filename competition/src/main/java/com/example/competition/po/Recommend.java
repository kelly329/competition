package com.example.competition.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "c_recommend")
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    // 比赛主分类：1：校内 2：校外
    private Integer maintype;
    private String content;
    // 管理员添加比赛时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private String picurl;
    private String texturl;

    private boolean ispass;
    private boolean delStatus;

    @ManyToOne
    private Type typeid;

    @ManyToOne
    private User userid;

    public Recommend() {}

    public Recommend(String title, Integer maintype, String content, Date createTime, String picurl, String texturl, boolean ispass, boolean delStatus, Type typeid, User userid) {
        this.title = title;
        this.maintype = maintype;
        this.content = content;
        this.createTime = createTime;
        this.picurl = picurl;
        this.texturl = texturl;
        this.ispass = ispass;
        this.delStatus = delStatus;
        this.typeid = typeid;
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMaintype() {
        return maintype;
    }

    public void setMaintype(Integer maintype) {
        this.maintype = maintype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getTexturl() {
        return texturl;
    }

    public void setTexturl(String texturl) {
        this.texturl = texturl;
    }

    public boolean isIspass() {
        return ispass;
    }

    public void setIspass(boolean ispass) {
        this.ispass = ispass;
    }

    public boolean isDelStatus() {
        return delStatus;
    }

    public void setDelStatus(boolean delStatus) {
        this.delStatus = delStatus;
    }

    public Type getTypeid() {
        return typeid;
    }

    public void setTypeid(Type typeid) {
        this.typeid = typeid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", maintype=" + maintype +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", picurl='" + picurl + '\'' +
                ", texturl='" + texturl + '\'' +
                ", ispass=" + ispass +
                ", delStatus=" + delStatus +
                ", typeid=" + typeid +
                ", userid=" + userid +
                '}';
    }
}
