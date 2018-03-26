package com.example.competition.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "c_contest")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    private String picurl;

    //浏览次数
    private Integer views;

    //是否推荐
    private boolean isrecommend;

    // 管理员添加比赛时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //修改比赛时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //比赛开始时间
    @Temporal(TemporalType.DATE)
    private Date startTime;
    //比赛结束时间
    @Temporal(TemporalType.DATE)
    private Date endTime;

    // 比赛主分类：1：校内 2：校外
    private Integer maintype;
    private boolean delStatus;
    private String sourceLink;
    private Integer likenum;
    private Integer postnum;

    // 比赛信息摘要
    private String shortdesc;

    // 比赛和类型是多对一  n:1
    // 是多的一端  是关系维护端
    @ManyToOne
    private Type type;

    // 比赛奖项内容
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String awards;

    // 来源组织 / 网站等名称
    private String sourcename;
    @OneToMany(mappedBy = "contest")
    private List<FocusContest> focusContests = new ArrayList<>();

    public Contest() {

    }

    public Contest(String title, String content, String picurl, Integer views, boolean isrecommend, Date createTime, Date updateTime, Date startTime, Date endTime, Integer maintype, boolean delStatus, String sourceLink, Integer likenum, Integer postnum, String shortdesc, Type type, String awards, String sourcename, List<FocusContest> focusContests) {
        this.title = title;
        this.content = content;
        this.picurl = picurl;
        this.views = views;
        this.isrecommend = isrecommend;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maintype = maintype;
        this.delStatus = delStatus;
        this.sourceLink = sourceLink;
        this.likenum = likenum;
        this.postnum = postnum;
        this.shortdesc = shortdesc;
        this.type = type;
        this.awards = awards;
        this.sourcename = sourcename;
        this.focusContests = focusContests;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(boolean isrecommend) {
        this.isrecommend = isrecommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getMaintype() {
        return maintype;
    }

    public void setMaintype(Integer maintype) {
        this.maintype = maintype;
    }

    public boolean isDelStatus() {
        return delStatus;
    }

    public void setDelStatus(boolean delStatus) {
        this.delStatus = delStatus;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public List<FocusContest> getFocusContests() {
        return focusContests;
    }

    public void setFocusContests(List<FocusContest> focusContests) {
        this.focusContests = focusContests;
    }

    public Integer getPostnum() {
        return postnum;
    }

    public void setPostnum(Integer postnum) {
        this.postnum = postnum;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picurl='" + picurl + '\'' +
                ", views=" + views +
                ", isrecommend=" + isrecommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", maintype=" + maintype +
                ", delStatus=" + delStatus +
                ", sourceLink='" + sourceLink + '\'' +
                ", likenum=" + likenum +
                ", postnum=" + postnum +
                ", shortdesc='" + shortdesc + '\'' +
                ", type=" + type +
                ", awards='" + awards + '\'' +
                ", sourcename='" + sourcename + '\'' +
                ", focusContests=" + focusContests +
                '}';
    }
}
