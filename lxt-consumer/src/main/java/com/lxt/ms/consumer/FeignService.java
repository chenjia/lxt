package com.lxt.ms.consumer;

import com.lxt.ms.common.bean.web.Packages;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "lxt-manage")
public interface FeignService {

    @RequestMapping("/lxt-manage/testconfig")
    public Packages callService();
}
