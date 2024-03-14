package com.bitc.plummarketdb.mapper;

import com.bitc.plummarketdb.DTO.ListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ListMapper {
    void updateHit(@Param("idx") String idx)throws Exception;

    List<ListDTO> DetailPageInfo(String listIdx)throws Exception;

    ListDTO NoImageDetail(String listIdx)throws Exception;

    void UpdateBuy(@Param("nick") String nick, @Param("idx") String idx)throws Exception;
}
