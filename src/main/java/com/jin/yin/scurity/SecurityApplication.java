package com.jin.yin.scurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: liangjinyin
 * @date:  2018/8/22 20:02
 * @description: 启动类
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
}
