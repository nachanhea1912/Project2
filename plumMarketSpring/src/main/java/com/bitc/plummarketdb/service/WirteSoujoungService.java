package com.bitc.plummarketdb.service;

import com.bitc.plummarketdb.DTO.ListDTO;

public interface WirteSoujoungService {


     ListDTO selectList(String idx)throws Exception;

    void writeList(ListDTO list)throws Exception;


    void UpdateList(ListDTO list)throws Exception;
}
