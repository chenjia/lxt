package com.lxt.ms.workflow.mapper.ext;


import com.lxt.ms.workflow.model.Process;

import java.util.Map;

public interface ProcessExtMapper {

    Process queryGraphXmlByTaskId(Map<String, Object> params);

}