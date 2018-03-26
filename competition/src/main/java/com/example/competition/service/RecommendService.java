package com.example.competition.service;


import com.example.competition.dao.RecommendRepository;
import com.example.competition.po.Admin;
import com.example.competition.po.Recommend;
import com.example.competition.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecommendService {
    //新增
    Recommend saveRecommend(Recommend recommend);

    //分页获取数据
    Page<Recommend> listRecommend(Pageable pageable);

    void deleteRecommend(Long id);

    Recommend getRecommend(Long id);

    //修改
    Recommend updateRecommed(Long id, Recommend recommend);

}
