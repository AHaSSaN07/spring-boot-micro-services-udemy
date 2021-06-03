package com.PhotoApp.APIGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//@Component
public class PostFilter implements GlobalFilter {

    Logger logger = LoggerFactory.getLogger(PostFilter.class);


    //the only difference from pre filter is the return statement, it completes filter chain then calling a mono instance to execute the post filter
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("running post filter ");
        }));
    }
}
