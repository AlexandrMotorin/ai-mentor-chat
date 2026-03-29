package com.motorin.ai_mentor_chat;

import com.motorin.ai_mentor_chat.config.AiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AiProperties.class)
public class AiMentorChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiMentorChatApplication.class, args);
	}

}
