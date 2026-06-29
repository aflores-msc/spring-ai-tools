package me.afibarra.springaitools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.ai.google.genai.api-key=test-key",
    "news.api.key=test-news-key",
    "spring.ai.model.chat=none"
})
class SpringAiToolsApplicationTests {

    @Test
    void contextLoads() {
    }
}
