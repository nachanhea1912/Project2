package com.bitc.plummarketdb.service;


import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.mapper.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceImpl implements ListService {
    @Autowired
    private ListMapper listMapper;
    @Override
    public ListDTO selectListInfo() throws Exception {
        return null;
    }

    @Override
    public void updateHit(String idx) throws Exception {
        listMapper.updateHit(idx);
    }

    @Override
    public List<ListDTO> DetailPageInfo(String listIdx) throws Exception {
        return listMapper.DetailPageInfo(listIdx);

    }

    @Override
    public ListDTO NoImageDetail(String listIdx) throws Exception {
        return listMapper.NoImageDetail(listIdx);
    }

    @Override
    public void UpdateBuy(String nick, String idx) throws Exception {
        listMapper.UpdateBuy(nick,idx);
    }
}
