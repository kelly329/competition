package com.example.competition.service;

import com.example.competition.NotFoundException;
import com.example.competition.dao.ContestRepository;
import com.example.competition.po.Contest;
import com.example.competition.po.Type;
import com.example.competition.util.MyBeanUtils;
import com.example.competition.vo.ContestQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContestServiceImpl implements ContestService{
    @Autowired
    private ContestRepository contestRepository;

    @Override
    public Contest getConest(Long id) {
        return contestRepository.findOne(id);
    }

    /**
     * 动态组合查询的功能
     * @param pageable
     * @param contest
     * @return
     */
    @Override
    public Page<Contest> listContest(Pageable pageable, ContestQuery contest) {
        return contestRepository.findAll(new Specification<Contest>() {
            //cq 查询的容器 条件的容器  cb设置具体的查询 like
            @Override
            public Predicate toPredicate(Root<Contest> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(contest.getTitle()) && contest.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + contest.getTitle() + "%"));
                }
                if (contest.getTypeId()!= null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), contest.getTypeId()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Transactional
    @Override
    public Contest saveContest(Contest contest) {
        if(contest.getId()==null){
            contest.setCreateTime(new Date());
            contest.setUpdateTime(new Date());
//            contest.getViews(0);
        }else{
            contest.setUpdateTime(new Date());
        }
        return contestRepository.save(contest);
    }

    @Transactional
    @Override
    public Contest updateContest(Long id, Contest contest) {
        Contest c = contestRepository.findOne(id);
        if(c == null) {
            throw new NotFoundException("该比赛信息不存在");
        }
        BeanUtils.copyProperties(contest,c, MyBeanUtils.getNullPropertyNames(contest));
        c.setUpdateTime(new Date());
        return contestRepository.save(c);
    }

    @Transactional
    @Override
    public void deleteContest(Long id) {
        contestRepository.delete(id);
    }

    /**
     * 获取最新的比赛
     * @param size
     * @return
     */
    @Override
    public List<Contest> listContestTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = new PageRequest(0,size,sort);
        return contestRepository.findTop(pageable);
    }

    /**
     * 获取最热的比赛
     * @param size
     * @return
     */
    @Override
    public List<Contest> listHotContestTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"likenum");
        Pageable pageable = new PageRequest(0,size,sort);
        return contestRepository.findTop(pageable);
    }

    /**
     * 获取指定条数的最新推荐比赛
     * @param size
     * @return
     */
    @Override
    public List<Contest> listRecommendContestTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0,size,sort);
        return contestRepository.findTopByIsrecommend(pageable);
    }

    @Override
    public Page<Contest> listContest(String query, Pageable pageable) {
        return contestRepository.findByQuery(query,pageable);
    }

    //校外比赛信息
    @Override
    public Page<Contest> listOffContest(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0,size,sort);
        return contestRepository.findOffQuery(pageable);
    }

    @Override
    public Page<Contest> listInContest(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0,size,sort);
        return contestRepository.findInQuery(pageable);
    }
}
