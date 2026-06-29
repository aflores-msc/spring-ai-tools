package me.afibarra.springaitools.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NewsTools {

    private final RestClient restClient;
    private final String newsApiKey;

    public NewsTools(@Value("${news.api.key}") String newsApiKey) {
        this.newsApiKey = newsApiKey;
        this.restClient = RestClient.builder()
                .baseUrl("https://newsapi.org/v2")
                .build();
    }

    @Tool(description = "Fetch real-time news articles about a given topic or query. " +
            "Use this when the user asks about current events, latest news, or recent happenings.")
    public String fetchNews(String topic) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/everything")
                        .queryParam("q", topic)
                        .queryParam("sortBy", "publishedAt")
                        .queryParam("pageSize", "5")
                        .queryParam("apiKey", newsApiKey)
                        .build())
                .retrieve()
                .body(String.class);
    }
}
