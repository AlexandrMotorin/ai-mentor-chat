package com.motorin.ai_mentor_chat.config;

import chat.giga.springai.GigaChatModel;
import chat.giga.springai.GigaChatOptions;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class AiProviderConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "ai", name = "provider", havingValue = "OLLAMA")
    public ChatModel ollamaChatModel(AiProperties aiProperties) {
        AiProperties.Ollama ollamaProps = aiProperties.getOllama();
        return OllamaChatModel.builder()
                .ollamaApi(OllamaApi.builder()
                        .baseUrl(ollamaProps.getUrl())
                        .build())
                .defaultOptions(
                        OllamaChatOptions.builder()
                                .model(ollamaProps.getModel())
                                .temperature(ollamaProps.getTemperature())
                                .topP(ollamaProps.getTopP())
                                .maxTokens(ollamaProps.getMaxTokens())
                                .build()
                )
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "ai", name = "provider", havingValue = "GIGACHAT")
    public ChatModel gigaChatModel(AiProperties aiProperties) {
        AiProperties.GigaChat gigaProps = aiProperties.getGigaChat();
        return GigaChatModel.builder()
                .defaultOptions(
                        GigaChatOptions.builder()
                                .model(gigaProps.getModel())
                                .temperature(gigaProps.getTemperature())
                                .topP(gigaProps.getTopP())
                                .maxTokens(gigaProps.getMaxTokens())
                                .repetitionPenalty(gigaProps.getRepetitionPenalty())
                                .updateInterval(gigaProps.getUpdateInterval())
                                .build()
                )
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "ai", name = "provider", havingValue = "OPENAI")
    public ChatModel openAiChatModel(AiProperties aiProperties) {
        AiProperties.OpenAi openAiProps = aiProperties.getOpenai();
        return OpenAiChatModel.builder()
                .openAiApi(OpenAiApi.builder()
                        .apiKey(openAiProps.getApiKey())
                        .baseUrl(openAiProps.getBaseUrl())
                        .build())
                .defaultOptions(
                        OpenAiChatOptions.builder()
                                .temperature(openAiProps.getTemperature())
                                .maxTokens(openAiProps.getMaxTokens())
                                .build()
                )
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "ai", name = "provider", havingValue = "ANTHROPIC")
    public ChatModel anthropicChatModel(AiProperties aiProperties) {
        AiProperties.Anthropic anthropicProps = aiProperties.getAnthropic();
        return AnthropicChatModel.builder()
                .options(
                        AnthropicChatOptions.builder()
                                .temperature(anthropicProps.getTemperature())
                                .maxTokens(anthropicProps.getMaxTokens())
                                .model(anthropicProps.getModel())
                                .apiKey(anthropicProps.getApiKey())
                                .build()
                )
                .build();
    }

}
