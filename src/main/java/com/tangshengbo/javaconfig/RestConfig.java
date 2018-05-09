package com.tangshengbo.javaconfig;

import com.tangshengbo.interceptor.RestTemplateInterceptor;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tangshengbo on 2016/12/26.
 */
@Configuration
public class RestConfig {


//    @Value("${custom.rest.template.connect-timeout}")
//    private int connectTimeout;
//    @Value("${custom.rest.template.read-timeout}")
//    private int readTimeout;

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(100);

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(connectionManager);

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());

        clientHttpRequestFactory.setConnectTimeout(5000);
        clientHttpRequestFactory.setReadTimeout(5000);
        clientHttpRequestFactory.setConnectionRequestTimeout(2000);

        return clientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());

        this.setMessageConverter(restTemplate);
        this.addInterceptor(restTemplate);

        return restTemplate;
    }

    /**
     * 设置消息转换格式
     *
     * @param restTemplate
     */
    private void setMessageConverter(RestTemplate restTemplate) {

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new SourceHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());

        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);

        jackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        messageConverters.add(jackson2HttpMessageConverter);

        restTemplate.setMessageConverters(messageConverters);
    }

    private void addInterceptor(RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(new RestTemplateInterceptor());
    }
}
