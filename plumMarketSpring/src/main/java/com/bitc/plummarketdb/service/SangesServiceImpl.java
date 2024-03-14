package com.bitc.plummarketdb.service;

import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.mapper.SangesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SangesServiceImpl implements SangesService{
    @Autowired
    SangesMapper sangesMapper;

    @Override
    public ListDTO SangesList(ListDTO list) throws Exception {
        return sangesMapper.SangesList(list);
    }
}