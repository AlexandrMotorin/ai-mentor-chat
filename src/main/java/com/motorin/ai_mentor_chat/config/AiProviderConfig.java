package com.motorin.ai_mentor_chat.config;

import chat.giga.springai.GigaChatModel;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AiProviderConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "ai", name = "provider", havingValue = "OLLAMA")
    public ChatModel ollamaChatModel(){
        return OllamaChatModel.builder()
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "ai", name = "provider", havingValue = "GIGACHAT")
    public ChatModel gigaChatModel(){
        return GigaChatModel.builder()
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "ai", name = "provider", havingValue = "OPENAI")
    public ChatModel openAiChatModel(){
        return OpenAiChatModel.builder()
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "ai", name = "provider", havingValue = "ANTHROPIC")
    public ChatModel anthropicChatModel(){
        return AnthropicChatModel.builder()
                .build();
    }

}
