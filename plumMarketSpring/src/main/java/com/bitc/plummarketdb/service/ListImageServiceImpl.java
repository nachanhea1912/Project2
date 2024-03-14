package com.bitc.plummarketdb.service;


import com.bitc.plummarketdb.mapper.ListImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListImageServiceImpl implements ListImageService {
    @Autowired
    private ListImageMapper listImageMapper;
}
