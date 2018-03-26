package com.example.competition.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "c_type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    private  String name;



    // 类型与比赛是一对多的关系  n:1
    // 初始化     是一的一端  是关系被维护端  type是contest中与Type关联的type   被维护比赛和类型的关系
    @OneToMany(mappedBy = "type")
    private List<Contest> contests = new ArrayList<>();

    @OneToMany(mappedBy = "typeid")
    private List<Recommend> recommends = new ArrayList<>();

    @OneToMany(mappedBy = "typeids")
    private List<Group> groups = new ArrayList<>();

    //被维护关系
    @ManyToMany(mappedBy = "types")
    private List<User> users = new ArrayList<>();

    public Type() {}

    public Type(String name, List<Contest> contests, List<Recommend> recommends, List<Group> groups, List<User> users) {
        this.name = name;
        this.contests = contests;
        this.recommends = recommends;
        this.groups = groups;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    public List<Recommend> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<Recommend> recommends) {
        this.recommends = recommends;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contests=" + contests +
                ", recommends=" + recommends +
                ", groups=" + groups +
                ", users=" + users +
                '}';
    }
}
