package com.lxt.ms.manage.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.manage.model.Feedback;
import com.lxt.ms.manage.model.FeedbackExample;
import com.lxt.ms.manage.model.FeedbackReply;

public interface FeedbackService {

    public abstract Packages list(FeedbackExample example, PageData pageData) throws APIException;

    public abstract Packages save(Feedback feedback, String $userId) throws APIException;

    public abstract Packages reply(FeedbackReply feedbackReply, String $userId) throws APIException;

    public abstract Packages details(String feedbackId) throws APIException;
}
