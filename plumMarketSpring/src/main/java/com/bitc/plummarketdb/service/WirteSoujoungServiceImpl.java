package com.bitc.plummarketdb.service;

import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.mapper.WriteSujoungMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WirteSoujoungServiceImpl implements WirteSoujoungService{

    @Autowired
    WriteSujoungMapper writeSujoungMapper;


    @Override
    public ListDTO selectList(String idx) throws Exception {
        return writeSujoungMapper.selectList(idx);
    }

    @Override
    public void writeList(ListDTO list) throws Exception {
        writeSujoungMapper.writeList(list);
    }

    @Override
    public void UpdateList(ListDTO list) throws Exception {
        writeSujoungMapper.UpdateList(list);
    }

}
