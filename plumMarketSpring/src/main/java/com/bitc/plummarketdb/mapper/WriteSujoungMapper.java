package com.bitc.plummarketdb.mapper;

import com.bitc.plummarketdb.DTO.ListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WriteSujoungMapper {

    void writeList(ListDTO list)throws Exception;

    void UpdateList(ListDTO list)throws Exception;

    ListDTO selectList(@Param("listIdx") String idx)throws Exception;
}
