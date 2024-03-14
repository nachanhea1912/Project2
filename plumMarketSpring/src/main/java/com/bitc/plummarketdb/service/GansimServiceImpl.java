package com.bitc.plummarketdb.service;

import com.bitc.plummarketdb.DTO.GansimDTO;
import com.bitc.plummarketdb.mapper.GansimMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GansimServiceImpl implements GansimService {
    @Autowired
    private GansimMapper gansimMapper;
    @Override
    public List<GansimDTO> selectGansimList(String id) throws Exception {
        return gansimMapper.selectGansimList(id);
    }

    @Override
    public void insertGansim(String id, String listIdx) throws Exception {
        gansimMapper.insertGansim(id, listIdx);
    }

    @Override
    public int gansimChk(String id, String listIdx) throws Exception {
        return gansimMapper.gansimChk(id,listIdx);
    }
}
