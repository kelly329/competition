package com.example.competition.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="c_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    // 比赛和类型是多对一  n:1
    // 是多的一端  是关系维护端
    @ManyToOne
    private Type typeids;

    @ManyToOne
    private User userids;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private Integer days;

    private String picurl;

    public Group(){}

    public Group(String content, Type typeids, User userids, Date createTime, Integer days, String picurl) {
        this.content = content;
        this.typeids = typeids;
        this.userids = userids;
        this.createTime = createTime;
        this.days = days;
        this.picurl = picurl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getTypeids() {
        return typeids;
    }

    public void setTypeids(Type typeids) {
        this.typeids = typeids;
    }

    public User getUserids() {
        return userids;
    }

    public void setUserids(User userids) {
        this.userids = userids;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", typeids=" + typeids +
                ", userids=" + userids +
                ", createTime=" + createTime +
                ", days=" + days +
                ", picurl='" + picurl + '\'' +
                '}';
    }
}
