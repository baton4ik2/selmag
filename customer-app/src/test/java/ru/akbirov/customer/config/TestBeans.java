package ru.akbirov.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;
import ru.akbirov.customer.client.WebClientFavouriteProductsClient;
import ru.akbirov.customer.client.WebClientProductReviewsClient;
import ru.akbirov.customer.client.WebClientProductsClient;

import static org.mockito.Mockito.mock;

@Configuration
public class TestBeans {

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
        return mock();
    }

    @Bean
    public ServerOAuth2AuthorizedClientRepository authorizedClientRepository() {
        return mock();
    }

    @Bean
    @Primary
    public WebClientProductsClient mockWebClientProductsClient() {
        return new WebClientProductsClient(WebClient.builder()
                .baseUrl("http://localhost:54321")
                .build());
    }

    @Bean
    @Primary
    public WebClientFavouriteProductsClient mockWebClientFavouriteProductsClient() {
        return new WebClientFavouriteProductsClient(WebClient.builder()
                .baseUrl("http://localhost:54321")
                .build());
    }

    @Bean
    @Primary
    public WebClientProductReviewsClient mockWebClientProductReviewsClient() {
        return new WebClientProductReviewsClient(WebClient.builder()
                .baseUrl("http://localhost:54321")
                .build());
    }
}
