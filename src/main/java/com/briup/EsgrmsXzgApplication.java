package com.briup;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.briup.big.BigMain;

@SpringBootApplication
@ServletComponentScan
public class EsgrmsXzgApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(EsgrmsXzgApplication.class, args);
		//启动推荐引擎代码
		BigMain.start();
	}

}
