package com.lanwon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAsync//@EnableAsync注解开启异步方法的支持。
//@EnableScheduling//@EnableScheduling注解开启计划任务的支持。也就是字面上的意思，开启计划任务的支持！ 一般都需要@Scheduled注解的配合。
@ServletComponentScan("com.lanwon")
@EnableTransactionManagement(proxyTargetClass=true)
@SpringBootApplication
@ImportResource(locations = { "classpath:druid-bean.xml" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
