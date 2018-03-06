package com.miner.out.jielin_fast;

import com.alipay.demo.trade.config.Configs;
import com.miner.out.jielin_fast.common.utils.ConfigUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ClassUtils;

@SpringBootApplication
public class JieLinFast {

    public static void main(String[] args) {

        SpringApplication.run(JieLinFast.class, args);
        //初始化 支付宝-微信-银联相关参数,涉及机密,此文件不会提交,请自行配置相关参数并加载
        Configs.init("zfbinfo.properties");//支付宝
        ConfigUtil.init("wxinfo.properties");//微信
        //SDKConfig.getConfig().loadPropertiesFromSrc();//银联
    }
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(YinliuApplication.class);
	}*/
}
