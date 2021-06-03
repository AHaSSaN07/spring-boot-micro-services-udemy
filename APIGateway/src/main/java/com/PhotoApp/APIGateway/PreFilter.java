package com.PhotoApp.APIGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;


@Component
public class PreFilter implements GlobalFilter {

    Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("running pre filter");;
        logger.info(exchange.getRequest().getPath().toString());
        Set<String> set = exchange.getRequest().getHeaders().keySet();
        for (var header : set){
            logger.info(header + " : " +  exchange.getRequest().getHeaders().getFirst(header));//header value by key
        }
        return chain.filter(exchange);
    }
}
