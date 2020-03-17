package com.danilopereira.remidme.repo.config;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Value("${httpConnectionPool.timeout}")
    private int TIMEOUT;

    @Value("${httpConnectionPool.maxConnections}")
    private int MAX_CONNECTIONS;

    @Value("${httpConnectionPool.keepAliveTime}")
    private int DEFAULT_KEEP_ALIVE_TIME;

    @Bean
    public RestTemplate getRestTemplate(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(TIMEOUT);
        httpRequestFactory.setConnectTimeout(TIMEOUT);
        httpRequestFactory.setReadTimeout(TIMEOUT);
        httpRequestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        return restTemplate;
    }

    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager,
                                          ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        CloseableHttpClient result = HttpClientBuilder
                .create()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setKeepAliveStrategy(connectionKeepAliveStrategy)
                .build();
        return result;
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
        result.setMaxTotal(MAX_CONNECTIONS);
        return result;
    }

    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (httpResponse, httpContext) -> {
            HeaderIterator headerIterator = httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE);
            HeaderElementIterator elementIterator = new BasicHeaderElementIterator(headerIterator);

            while (elementIterator.hasNext()) {
                HeaderElement element = elementIterator.nextElement();
                String param = element.getName();
                String value = element.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    return Long.parseLong(value) * 1000; // convert to ms
                }
            }

            return DEFAULT_KEEP_ALIVE_TIME;
        };
    }

}
