package com.nhnacademy.search.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ElasticsearchConfig.class)
@TestPropertySource(properties = {
        "spring.elasticsearch.username=testuser",
        "spring.elasticsearch.password=testpass",
        "spring.elasticsearch.uris=http://localhost:9200"
})
class ElasticsearchConfigTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    void testClientConfigurationExists() {
        assertThat(context.containsBean("elasticsearchClientConfiguration")).isTrue();
    }

    @Test
    void testElasticsearchCustomConversionsExists() {
        assertThat(context.containsBean("elasticsearchCustomConversions")).isTrue();
    }

    @Test
    void testElasticsearchConfigurationExists() {
        assertThat(context.getBeansOfType(ElasticsearchConfiguration.class)).isNotEmpty();
    }
}