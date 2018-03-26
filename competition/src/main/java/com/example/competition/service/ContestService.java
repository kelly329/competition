package com.example.competition.service;

import com.example.competition.po.Contest;
import com.example.competition.vo.ContestQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContestService {
    Contest getConest(Long id);

    //分页查询 带查询参数
    Page<Contest>  listContest(Pageable pageable, ContestQuery contest);

    //新增
    Contest saveContest(Contest contest);
    //修改
    Contest updateContest(Long id,Contest contest);
    //删除
    void deleteContest(Long id);

    List<Contest> listContestTop(Integer size);
    List<Contest> listHotContestTop(Integer size);

    //获得最新推荐的比赛
    List<Contest> listRecommendContestTop(Integer size);

    Page<Contest> listContest(String query, Pageable pageable);

    Page<Contest> listOffContest(Integer size);
    Page<Contest> listInContest(Integer size);
}
