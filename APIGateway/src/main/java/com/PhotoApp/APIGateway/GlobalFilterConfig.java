package com.PhotoApp.APIGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;


@Component
public class GlobalFilterConfig   {
    Logger logger = LoggerFactory.getLogger(GlobalFilterConfig.class);
    @Bean
    public GlobalFilter preFilter(){
        return ((exchange, chain) -> {
            logger.info("running pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("running post filter");
            }));
        });
    }
}
