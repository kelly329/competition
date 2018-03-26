package com.example.competition.dao;

import com.example.competition.po.Contest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContestRepository extends JpaRepository<Contest,Long>, JpaSpecificationExecutor<Contest> {
    @Transactional
    @Modifying
    @Query("update Contest c set c.views=c.views+1 where c.id = ?1")
    int updateViews(Long id);

    @Query("select t from Contest t")
    List<Contest> findTop(Pageable pageable);

    /**
     * 自定义查询推荐的比赛信息
     * @return
     */
    @Query("select c from Contest c where c.isrecommend=true")
    List<Contest> findTopByIsrecommend(Pageable pageable);

    // select * from c_contest where title like '%内容%'
    @Query("select c from Contest c where c.title like ?1 or c.shortdesc like ?1")
    Page<Contest> findByQuery(String query, Pageable pageable);


    // select * from c_contest where c.maintype=2' 查询校外比赛信息
    @Query("select c from Contest c where c.maintype=2")
    Page<Contest> findOffQuery(Pageable pageable);

    @Query("select c from Contest c where c.maintype=1")
    Page<Contest> findInQuery(Pageable pageable);

}
