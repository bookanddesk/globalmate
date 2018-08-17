package com.globalmate.data.dao.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DDLMapper {

    int insertData(Map<String, Object> map);

}
