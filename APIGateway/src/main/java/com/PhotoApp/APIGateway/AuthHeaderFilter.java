package com.PhotoApp.APIGateway;


import com.thoughtworks.xstream.io.json.JettisonStaxWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.ReactiveStreamsMongoClientDependsOnBeanFactoryPostProcessor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.*;


@Component
public class AuthHeaderFilter extends AbstractGatewayFilterFactory {

    @Autowired
    Environment environment;

    public static class Config {

    }

    public AuthHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no Authorization header", HttpStatus.UNAUTHORIZED);
            }
            String autHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = autHeader.replace("Bear", "");
            if (!isJwtValid(jwt)) {
                return onError(exchange, "invalid jwt token", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String no_authorization_header, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private Boolean isJwtValid(String jwt) {
        String subject;
        try {
            subject = Jwts.parser().
                    setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(jwt)
                    .getBody().getSubject();
        } catch (Exception e) {
                return false;
        }
        return (subject == null || subject.isEmpty()) ? false : true;
    }
}
