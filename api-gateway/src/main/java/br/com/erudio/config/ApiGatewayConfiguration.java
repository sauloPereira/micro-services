package br.com.erudio.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p.path("/get")     //intercepta os "/get" para direcionar para ".uri("
                        .filters(f -> f.addRequestHeader("Hello", "world")      //add dados no header
                                        .addRequestParameter("Hello", "Mundo!"))    //add parametros no "args"
                        .uri("http://httpbin.org:80"))      //ferramenta de diagnostico que converte chamadas "http" para "json"
                .route(p -> p.path("/cambio-service/**")
                        .uri("lb://cambio-service"))
                .route(p -> p.path("/book-service/**")
                        .uri("lb://book-service"))
                .build();
    }

}

/*
Function<PredicateSpec, Buildable<Route>> function =
                p -> p.path("/get")     //intercepta os "/get" para direcionar para ".uri("
                        .filters(f -> f.addRequestHeader("Hello", "world")  //add dados no header
                                        .addRequestParameter("Hello", "Mundo!")) //add parametros no "args"
                        .uri("http://httpbin.org:80");  //ferramenta de diagnostico que converte chamadas "http" para "json"

        return builder.routes()
                .route(function)
                .build();
 */