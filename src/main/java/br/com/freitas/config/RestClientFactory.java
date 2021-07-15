package br.com.freitas.config;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

@Factory
public class RestClientFactory {

    @Bean
    public RestHighLevelClient builderHighLevel() {
        final var credentialsProvider = this.getBasicCredentialsProvider();

        var restClientBuilder = this.buildRestClient(credentialsProvider);

        return new RestHighLevelClient(restClientBuilder);
    }

    @Bean
    public RestClient builderLowLevel() {
        final var credentialsProvider = this.getBasicCredentialsProvider();

        return this.buildRestClient(credentialsProvider).build();
    }

    private RestClientBuilder buildRestClient(BasicCredentialsProvider credentialsProvider) {
        var host1 = new HttpHost("localhost", 9200, "http");
        var host2 = new HttpHost("localhost", 9201, "http");

        var restClientBuilder = RestClient.builder(host1, host2);

        restClientBuilder.setHttpClientConfigCallback(httpClient -> httpClient.setDefaultCredentialsProvider(credentialsProvider));

        return restClientBuilder;
    }

    private BasicCredentialsProvider getBasicCredentialsProvider() {
        final var credentialsProvider = new BasicCredentialsProvider();

        final var credential = new UsernamePasswordCredentials("elastic", "changeme");

        credentialsProvider.setCredentials(AuthScope.ANY, credential);

        return credentialsProvider;
    }
}