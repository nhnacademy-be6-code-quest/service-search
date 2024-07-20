package com.nhnacademy.search.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ElasticsearchConfigTest {

    private ElasticsearchConfig elasticsearchConfig;

    @BeforeEach
    void setUp() {
        elasticsearchConfig = new ElasticsearchConfig();
        ReflectionTestUtils.setField(elasticsearchConfig, "username", "testUser");
        ReflectionTestUtils.setField(elasticsearchConfig, "password", "testPassword");
        ReflectionTestUtils.setField(elasticsearchConfig, "esHost", new String[]{"localhost:9200"});
    }

    @Test
    void testClientConfiguration() {
        ClientConfiguration clientConfiguration = elasticsearchConfig.clientConfiguration();

        assertNotNull(clientConfiguration);
        // We can't directly access the hosts, so we'll check if the configuration is not null
        assertNotNull(clientConfiguration.getClientConfigurationCallback());
        // Check if basic auth is set
        assertTrue(clientConfiguration.getDefaultHeaders().containsKey("Authorization"));
    }

    @Test
    void testElasticsearchCustomConversions() {
        ElasticsearchCustomConversions conversions = elasticsearchConfig.elasticsearchCustomConversions();

        assertNotNull(conversions);
        assertTrue(conversions.hasCustomReadTarget(Long.class, LocalDateTime.class));
    }

    @Test
    void testLongToLocalDateTimeConverter() {
        var converter = new ElasticsearchConfig.LongToLocalDateTimeConverter();

        long timestamp = 1609459200000L; // 2021-01-01 00:00:00
        LocalDateTime dateTime = converter.convert(timestamp);

        assertNotNull(dateTime);
        assertEquals(2021, dateTime.getYear());
        assertEquals(1, dateTime.getMonthValue());
        assertEquals(1, dateTime.getDayOfMonth());
    }
}