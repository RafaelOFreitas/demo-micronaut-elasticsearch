package br.com.freitas.config;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@Factory
public class RestHighLevelClientFactory {

    @Bean
    public RestHighLevelClient builder() {
        final var credentialsProvider = new BasicCredentialsProvider();
        final var credential = new UsernamePasswordCredentials("elastic", "changeme");

        credentialsProvider.setCredentials(AuthScope.ANY, credential);

        var host1 = new HttpHost("localhost", 9200, "http");
        var host2 = new HttpHost("localhost", 9201, "http");

        var restClientBuilder = RestClient.builder(host1, host2);

        restClientBuilder.setHttpClientConfigCallback(httpClient -> httpClient.setDefaultCredentialsProvider(credentialsProvider));

        return new RestHighLevelClient(restClientBuilder);
    }
}