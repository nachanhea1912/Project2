package com.bitc.plummarketdb.mapper;

import com.bitc.plummarketdb.DTO.ListDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SangesMapper {

    ListDTO SangesList(ListDTO list)throws Exception;
}