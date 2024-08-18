package org.anurag.movie_catalog_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        /*RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1_000, TimeUnit.MILLISECONDS)
                .setResponseTimeout(1000, TimeUnit.MILLISECONDS)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient); */
        /*HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(1000);
        return new RestTemplate(clientHttpRequestFactory);*/
        return new RestTemplate();
    }

}
