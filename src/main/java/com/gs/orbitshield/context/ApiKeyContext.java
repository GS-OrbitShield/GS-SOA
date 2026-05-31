package com.gs.orbitshield.context;

import com.gs.orbitshield.model.ApiKey;

public class ApiKeyContext {
    private static final ThreadLocal<ApiKey> context = new ThreadLocal<>();

    public static void set(ApiKey apiKey) {
        context.set(apiKey);
    }

    public static ApiKey get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}

