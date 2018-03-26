package com.example.competition.dao;

import com.example.competition.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameAndPassword(String username,String password);

    User findByUserName(String username);

    @Transactional
    @Modifying
    @Query("update User u set u.delStatus=0 where u.id = ?1")
    int updateDelStatus(Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.delStatus=1 where u.id = ?1")
    int updateBan(Long id);
}
