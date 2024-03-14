package com.bitc.plummarketdb.mapper;

import com.bitc.plummarketdb.DTO.GansimDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GansimMapper {
    List<GansimDTO> selectGansimList(String id) throws Exception;

    void insertGansim(@Param("id") String id, @Param("listIdx") String listIdx)throws Exception;

    int gansimChk(@Param("id") String id, @Param("listIdx") String listIdx)throws Exception;
}
