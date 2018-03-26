package com.example.competition.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "c_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String userName;
    private String nickname;
    private String image;

    @Temporal(TemporalType.DATE)
    private Date createTime;
    // 一句话简介
    private  String protrait;
    private String sex;
    // 最后的登录时间
    @Temporal(TemporalType.DATE)
    private Date lastTime;
    // 注册IP
    private String regIp;
    private String college;
    private String major;
    private String grade;
    private Integer delStatus;

    @OneToMany(mappedBy = "userid")
    private List<Recommend> recommends = new ArrayList<>();

    @OneToMany(mappedBy = "userids")
    private List<Group> groups = new ArrayList<>();

    // 感兴趣的比赛类型
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Type> types = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FocusContest> focusContests = new ArrayList<>();

    public User() {
    }

    public User(String email, String password, String userName, String nickname, String image, Date createTime, String protrait, String sex, Date lastTime, String regIp, String college, String major, String grade,  Integer delStatus, List<Recommend> recommends, List<Group> groups, List<Type> types, List<FocusContest> focusContests) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.nickname = nickname;
        this.image = image;
        this.createTime = createTime;
        this.protrait = protrait;
        this.sex = sex;
        this.lastTime = lastTime;
        this.regIp = regIp;
        this.college = college;
        this.major = major;
        this.grade = grade;
        this.delStatus = delStatus;
        this.recommends = recommends;
        this.groups = groups;
        this.types = types;
        this.focusContests = focusContests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProtrait() {
        return protrait;
    }

    public void setProtrait(String protrait) {
        this.protrait = protrait;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public List<Recommend> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<Recommend> recommends) {
        this.recommends = recommends;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<FocusContest> getFocusContests() {
        return focusContests;
    }

    public void setFocusContests(List<FocusContest> focusContests)
    {
        this.focusContests = focusContests;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", image='" + image + '\'' +
                ", createTime=" + createTime +
                ", protrait='" + protrait + '\'' +
                ", sex='" + sex + '\'' +
                ", lastTime=" + lastTime +
                ", regIp='" + regIp + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", delStatus=" + delStatus +
                ", recommends=" + recommends +
                ", groups=" + groups +
                ", types=" + types +
                ", focusContests=" + focusContests +
                '}';
    }
}
