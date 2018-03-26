package com.example.competition.service;

import com.example.competition.NotFoundException;
import com.example.competition.dao.RecommendRepository;
import com.example.competition.po.Recommend;
import com.example.competition.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RecommendServiceImpl implements RecommendService{

    @Autowired
    private RecommendRepository recommendRepository;

    @Transactional
    @Override
    public Recommend saveRecommend(Recommend recommend) {
        if(recommend.getId()==null){
            recommend.setCreateTime(new Date());
        }
        return recommendRepository.save(recommend);
    }

    @Override
    public Page<Recommend> listRecommend(Pageable pageable) {
        return recommendRepository.findAll(pageable);
    }


    @Override
    public void deleteRecommend(Long id) {
        recommendRepository.delete(id);
    }

    @Override
    public Recommend getRecommend(Long id) {
        return recommendRepository.findOne(id);
    }

    @Override
    public Recommend updateRecommed(Long id, Recommend recommend) {
        Recommend r = recommendRepository.findOne(id);
        if(r==null){
            throw new NotFoundException("该推荐不存在");
        }
        //获取type查询到的内容 type复制到t
        BeanUtils.copyProperties(recommend,r);
        return recommendRepository.save(r);
    }
}
