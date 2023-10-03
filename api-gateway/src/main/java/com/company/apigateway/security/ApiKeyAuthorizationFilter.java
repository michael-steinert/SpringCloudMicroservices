package com.company.apigateway.security;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class ApiKeyAuthorizationFilter implements GlobalFilter, Ordered {
    private final FakeApiAuthorizationChecker fakeApiAuthorizationChecker;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route exchangeAttribute = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        assert exchangeAttribute != null;
        String application = exchangeAttribute.getId();
        List<String> apiKey = exchange.getRequest().getHeaders().get("X-API-Key");

        if (application == null || (apiKey == null || apiKey.isEmpty()) || !fakeApiAuthorizationChecker.isAuthorized(apiKey.get(0), application)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized to visit this Route");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // Execute the API Key Filter last to avoid Interfering with other Default Filters in the Filter Chain
        return Ordered.LOWEST_PRECEDENCE;
    }
}
