package com.atto.server.db.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dhjung on 2017. 11. 7..
 */
@Mapper
public interface CommonMapper {
    List<String> selectTableColumnNames(String tableName);
    Integer selectCount(String tableName);
}
