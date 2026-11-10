package com.portfolio.qa.api;

public final class ApiConfig {
    private ApiConfig() {}
    public static final String BASE_URL =
        System.getProperty("baseUrl", System.getenv().getOrDefault("API_BASE_URL", "https://jsonplaceholder.typicode.com"));
}
