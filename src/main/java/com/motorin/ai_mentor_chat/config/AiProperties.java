package com.motorin.ai_mentor_chat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "ai")
public class AiProperties {

    private Provider provider = Provider.OLLAMA;

    private String defaultSystemPrompt;

    private OpenAi openai = new OpenAi();
    private Ollama ollama = new Ollama();
    private Anthropic anthropic = new Anthropic();
    private GigaChat gigaChat = new GigaChat();

    @Getter
    @Setter
    public static class OpenAi {
        private String apiKey;           // обязательно для OpenAI
        private String model = "gpt-4o"; // или gpt-4o-mini, o1 и т.д.
        private String baseUrl;          // если используешь прокси или Azure OpenAI
        private Double temperature = 0.7;
        private Integer maxTokens = 2048;
    }

    @Getter
    @Setter
    public static class Ollama {
        private String url = "http://localhost:11431";
        private String model = "gemma3:4b-it-q4_K_M";        // здесь удобно указывать русские модели
        private Double temperature = 0.7;
        private Double topP = 0.7;
        private Integer maxTokens = 4096;
        private Boolean formatJson = false;        // если нужно принудительно JSON
    }

    @Getter
    @Setter
    public static class Anthropic {
        private String apiKey;                     // обязательно
        private String model = "claude-3-5-sonnet-20241022";
        private Double temperature = 0.7;
        private Integer maxTokens = 4096;
    }

    @Getter
    @Setter
    public static class GigaChat {
        private String model = "GigaChat";        // GigaChat по дефолту, список доступных моделей - https://developers.sber.ru/docs/ru/gigachat/models
        private String apiKey;
        private Double temperature =  0.5;       
        private Double topP = 0.5;             
        private int maxTokens = 200;
        private Double repetitionPenalty = 1D;
        private Double updateInterval = 0D;
    }
}
