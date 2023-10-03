package com.company.apigateway.security;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("fakeApiAuthorizationChecker")
public class FakeApiAuthorizationChecker implements ApiKeyAuthorizationChecker {
    private final static Map<String, List<String>> apiKeys = Map.of("ab1234cd5678ef90ghij1234kl5678mn", List.of("customer"));

    @Override
    public boolean isAuthorized(String apiKey, String application) {
        return apiKeys.getOrDefault(apiKey, List.of()).stream().anyMatch(k -> k.contains(application));
    }
}
