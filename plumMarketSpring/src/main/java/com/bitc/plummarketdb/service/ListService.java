package com.bitc.plummarketdb.service;


import com.bitc.plummarketdb.DTO.ListDTO;

import java.util.List;

public interface ListService {

    ListDTO selectListInfo()throws Exception;

    void updateHit(String idx)throws Exception;

    List<ListDTO> DetailPageInfo(String listIdx)throws Exception;

    ListDTO NoImageDetail(String listIdx)throws Exception;

    void UpdateBuy(String nick, String idx)throws Exception;
}
