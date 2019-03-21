package com.lxt.ms.chat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxt.ms.chat.mapper.ChatRecordMapper;
import com.lxt.ms.chat.model.ChatRecord;
import com.lxt.ms.chat.model.ChatRecordExample;
import com.lxt.ms.chat.service.api.RecordService;
import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Override
    public Packages list(HashMap<String, Object> example, PageData pageData) throws APIException {
        Packages pkg = new Packages();

        System.out.println("example:"+example);
        System.out.println("pagedata:"+pageData);
        Page page = PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize(), true);

        long time = (Long) example.get("beforeDate");
        String sendId = (String) example.get("sendId");
        String receiveId = (String) example.get("receiveId");
        ChatRecordExample chatRecordExample = new ChatRecordExample();

        chatRecordExample.createCriteria().andSendIdEqualTo(sendId).andReceiveIdEqualTo(receiveId);
        chatRecordExample.or(chatRecordExample.createCriteria().andSendIdEqualTo(receiveId).andReceiveIdEqualTo(sendId));
        chatRecordExample.createCriteria().andInsertTimeLessThan(new Date(time));
        chatRecordExample.setOrderByClause("INSERT_TIME desc");
        List<ChatRecord> list = chatRecordMapper.selectByExample(chatRecordExample);
        pageData.setData(list);
        pageData.setTotal(page.getTotal());
        pkg.getBody().setData(pageData);

        return pkg;
    }
}
