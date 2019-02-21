package com.lxt.ms.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.UUIDUtils;
import com.lxt.ms.manage.mapper.FeedbackMapper;
import com.lxt.ms.manage.mapper.FeedbackReplyMapper;
import com.lxt.ms.manage.model.Feedback;
import com.lxt.ms.manage.model.FeedbackExample;
import com.lxt.ms.manage.model.FeedbackReply;
import com.lxt.ms.manage.model.FeedbackReplyExample;
import com.lxt.ms.manage.service.api.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private FeedbackReplyMapper feedbackReplyMapper;

    @Override
    public Packages list(FeedbackExample example, PageData pageData) throws APIException {
        Packages pkg = new Packages();

        Page page = PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize(), true);
        List<Feedback> list = feedbackMapper.selectByExample(example);
        pkg.getBody().setData(list);

        return pkg;
    }

    @Override
    public Packages save(Feedback feedback, String $userId) throws APIException {
        Packages pkg = new Packages();

        Date date = new Date();

        feedback.setId(UUIDUtils.UUID());
        feedback.setUserId($userId);
        feedback.setInsertTime(date);
        feedback.setUpdateTime(date);
        feedback.setStatus(0);
        feedbackMapper.insert(feedback);

        return pkg;
    }

    @Override
    public Packages reply(FeedbackReply feedbackReply, String $userId) throws APIException {
        Packages pkg = new Packages();

        feedbackReply.setId(UUIDUtils.UUID());
        feedbackReply.setUserId($userId);
        feedbackReplyMapper.insert(feedbackReply);

        return pkg;
    }

    @Override
    public Packages details(String feedbackId) throws APIException {
        Packages pkg = new Packages();

        Map<String, Object> result = new HashMap<String, Object>();

        Feedback feedback = feedbackMapper.selectByPrimaryKey(feedbackId);
        FeedbackReplyExample example = new FeedbackReplyExample();
        example.createCriteria().andFeedbackIdEqualTo(feedbackId);
        example.setOrderByClause("INSERT_TIME ASC");
        List<FeedbackReply> list = feedbackReplyMapper.selectByExample(example);

        result.put("feedback", feedback);
        result.put("replies", list);

        pkg.getBody().setData(result);

        return pkg;
    }
}
