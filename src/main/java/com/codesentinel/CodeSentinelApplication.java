package com.codesentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodeSentinelApplication {

	public static void main(String[] args) {
		// Spring 설정을 읽고 내장 웹 서버를 시작하는 애플리케이션 진입점입니다.
		SpringApplication.run(CodeSentinelApplication.class, args);
	}

}
