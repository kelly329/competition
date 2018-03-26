package com.example.competition.service;

import com.example.competition.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    //新增
    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    //分页查询
    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    //获取指定条数的数据
    List<Type> listTypeTop(Integer size);

    //修改
    Type updateType(Long id, Type type);

    //删除
    void deleteType(Long id);



}
