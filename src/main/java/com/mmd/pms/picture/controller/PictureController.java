package com.mmd.pms.picture.controller;

import com.mmd.pms.common.controller.BaseController;
import com.mmd.pms.common.entity.BaseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片基本Controller类
 */
@Controller
@RequestMapping(value = "/picture")
public class PictureController extends BaseController{


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> test(){
        logger.info("进入到测试方法...");

        Map<String, Object> resData = new HashMap<String, Object>();

        resData.put("title", "这是标题");
        resData.put("name", "这是姓名");
        resData.put("age", "这是年龄");

        BaseEntity entity = new BaseEntity();

        entity.setId("123");
        entity.setCreateDate(new Date());
        resData.put("user", entity);
        resData.put("user2", null);
        resData.put("user3", new BaseEntity());
        resData.put("birthday", new Date());
        return resData;
    }

}
