package com.bitc.plummarketdb.controller;


import com.bitc.plummarketdb.service.ListImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ListImageController {
    @Autowired
    private ListImageService listImageService;
}
