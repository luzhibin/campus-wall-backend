package com.sqx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class SqxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqxApplication.class, args);
		log.info("(♥◠‿◠)ﾉﾞ  项目启动成功   ლ(´ڡ`ლ)ﾞ  \n"+
							"       _    \n" +
							"      | |   \n" +
							"  ___ | | __\n" +
							" / _ \\| |/ /\n" +
							"| (_) |   < \n" +
							" \\___/|_|\\_\\");

	}

}