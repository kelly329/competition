package com.example.competition.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "c_focus_contest")
public class FocusContest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 管理员添加比赛时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date focusTime;

    private boolean delStatus;

    @ManyToOne
    private User user;
    @ManyToOne
    private Contest contest;

    public FocusContest(Date focusTime, boolean delStatus, User user, Contest contest) {
        this.focusTime = focusTime;
        this.delStatus = delStatus;
        this.user = user;
        this.contest = contest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(Date focusTime) {
        this.focusTime = focusTime;
    }

    public boolean isDelStatus() {
        return delStatus;
    }

    public void setDelStatus(boolean delStatus) {
        this.delStatus = delStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    @Override
    public String toString() {
        return "FocusContest{" +
                "id=" + id +
                ", focusTime=" + focusTime +
                ", delStatus=" + delStatus +
                ", user=" + user +
                ", contest=" + contest +
                '}';
    }
}
