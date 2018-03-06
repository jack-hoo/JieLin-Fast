/**
 * Copyright (C), 2008-2017, 杭州迪火科技有限公司
 * FileName: TestController
 * Author:   shugan
 * Date:     2017/12/14 14:39
 * Description: 测试控制器
 */
package com.miner.out.jielin_fast.controller;

import com.miner.out.jielin_fast.common.utils.Result;
import com.miner.out.jielin_fast.common.utils.ResultUtil;
import com.miner.out.jielin_fast.dto.Product;
import com.miner.out.jielin_fast.service.IAliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * 〈测试控制器〉
 *
 * @author shugan
 * @create 2017/12/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private IAliPayService aliPayService;

    @RequestMapping("/index")
    public Result test() throws IOException {
        //ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        //URL aStatic = defaultClassLoader.getResource("static");
        Product product = new Product();
        product.setBody("body");
        product.setSubject("subject");
        Short type = 1;
        product.setPayType(type);
        product.setFrontUrl("http://www.yinliuren.com");
        product.setAttach("附件数据主要用于商户携带订单的自定义数据");
        product.setOutTradeNo("11111111");
        Short payWay = 1;
        product.setPayWay(payWay);
        product.setProductId("456");
        product.setTotalFee("100");
        product.setSpbillCreateIp("19.199.82.213");
        String s = aliPayService.aliPay(product);

        System.out.println(s);
        return ResultUtil.success(s);
    }

    @RequestMapping(value = "/alipay/mobile",produces = "text/html;charset=UTF-8")
    public String alipayMobile(HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setBody("body");
        product.setSubject("subject");
        Short type = 1;
        product.setPayType(type);
        product.setFrontUrl("http://www.yinliuren.com");
        product.setAttach("附件数据主要用于商户携带订单的自定义数据");
        product.setOutTradeNo("11111111");
        Short payWay = 1;
        product.setPayWay(payWay);
        product.setProductId("456");
        product.setTotalFee("100");
        product.setSpbillCreateIp("19.199.82.213");
        String s = aliPayService.aliPayMobile(product);
        return s;
    }
    @RequestMapping(value = "/alipay/pc",produces = "text/html;charset=UTF-8")
    public String alipayPC(HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setBody("body");
        product.setSubject("subject");
        Short type = 1;
        product.setPayType(type);
        product.setFrontUrl("http://www.yinliuren.com");
        product.setAttach("附件数据主要用于商户携带订单的自定义数据");
        product.setOutTradeNo("11111111");
        Short payWay = 1;
        product.setPayWay(payWay);
        product.setProductId("456");
        product.setTotalFee("100");
        product.setSpbillCreateIp("19.199.82.213");
        String s = aliPayService.aliPayPc(product);
        return s;
        /*response.setContentType("type=text/html;charset=UTF-8");
        response.getWriter().write(s);*/
    }
}