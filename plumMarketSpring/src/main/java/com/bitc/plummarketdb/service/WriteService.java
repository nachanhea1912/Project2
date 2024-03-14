package com.bitc.plummarketdb.service;

import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.DTO.ListImageDTO;

import java.util.List;


public interface WriteService {
    void InsertList(ListDTO list)throws Exception;

    List<ListDTO> SelectList()throws Exception;

    List<ListDTO> PanmaeSelectList(String userNick)throws Exception;

    List<ListDTO> PanmaeSelectCompleteList(String userNick)throws Exception;

    List<ListDTO> PanmaeSelectHideList(String userNick)throws Exception;

    void updateSellReservation(ListDTO list)throws Exception;

    void updateSellComplete(ListDTO list)throws Exception;

    void updateSellHide(ListDTO list)throws Exception;

    void updateSellDelete(ListDTO list)throws Exception;

    void InsertImageList(ListImageDTO item)throws Exception;

    void updateSellOngoing(ListDTO list) throws Exception;

    List<ListDTO> SearchListTitle(String search)throws Exception;

    void updateSellHideRemove(ListDTO list) throws Exception;

    void updateSellupdate(ListDTO list) throws Exception;

    void updateSellRervation(ListDTO list)throws Exception;

    void updateSellRervationDelete(ListDTO list)throws Exception;

    ListDTO selectSellState(ListDTO list)throws Exception;

    void Modify(ListDTO list)throws Exception;

    List<ListDTO> getListGumaeList(String userNick)throws Exception;
}
