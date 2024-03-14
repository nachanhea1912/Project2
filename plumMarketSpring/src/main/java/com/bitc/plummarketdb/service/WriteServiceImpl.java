package com.bitc.plummarketdb.service;

import com.bitc.plummarketdb.DTO.ListDTO;
import com.bitc.plummarketdb.DTO.ListImageDTO;
import com.bitc.plummarketdb.mapper.WriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriteServiceImpl implements WriteService {
    @Autowired
    WriteMapper writeMapper;

    @Override
    public void InsertList(ListDTO list) throws Exception {
        writeMapper.InsertList(list);
    }

    @Override
    public List<ListDTO> SelectList() throws Exception {
        return writeMapper.SelectList();
    }

    @Override
    public List<ListDTO> PanmaeSelectList(String userNick) throws Exception {
        return writeMapper.PanmaeSelectList(userNick);
    }
    @Override
    public List<ListDTO> PanmaeSelectCompleteList(String userNick)throws Exception {
        return writeMapper.PanmaeSelectCompleteList(userNick);
    }
    @Override
    public List<ListDTO> PanmaeSelectHideList(String userNick)throws Exception {
        return writeMapper.PanmaeSelectHideList(userNick);
    }

    @Override
    public void updateSellReservation(ListDTO list) throws Exception {
        writeMapper.updateSellReservation(list);
    }

    @Override
    public void updateSellComplete(ListDTO list) throws Exception {
        writeMapper.updateSellComplete(list);
    }
    @Override
    public void updateSellHide(ListDTO list) throws Exception {
        writeMapper.updateSellHide(list);
    }
    @Override
    public void updateSellDelete(ListDTO list) throws Exception {
        writeMapper.updateSellDelete(list);
    }
    @Override
    public void updateSellOngoing(ListDTO list) throws Exception {
        writeMapper.updateSellOngoing(list);
    }
    @Override
    public void InsertImageList(ListImageDTO item) throws Exception {
        writeMapper.InsertImageList(item);
    }

    @Override
    public List<ListDTO> SearchListTitle(String search) throws Exception {
        return writeMapper.SearchListTitle(search);
    }

    @Override
    public void updateSellHideRemove(ListDTO list) throws Exception {
        writeMapper.updateSellHideRemove(list);
    }

    @Override
    public void updateSellupdate(ListDTO list) throws Exception {
        writeMapper.updateSellupdate(list);
    }

    @Override
    public void updateSellRervation(ListDTO list) throws Exception {
        writeMapper.updateSellRervation(list);
    }

    @Override
    public void updateSellRervationDelete(ListDTO list) throws Exception {
        writeMapper.updateSellRervationDelete(list);
    }

    @Override
    public ListDTO selectSellState(ListDTO list) throws Exception {
        return  writeMapper.selectSellState(list);
    }

    @Override
    public void Modify(ListDTO list) throws Exception {
        writeMapper.Modify(list);
    }

    @Override
    public List<ListDTO> getListGumaeList(String userNick) throws Exception {
        return writeMapper.getListGumaeList(userNick);
    }
}
