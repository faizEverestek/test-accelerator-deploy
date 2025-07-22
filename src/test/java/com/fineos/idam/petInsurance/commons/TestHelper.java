/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.commons;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class providing methods to generate HTTP headers used for testing.
 * These headers simulate typical security and caching configurations.
 */
public class TestHelper {

    /**
     * Generates a map of HTTP headers commonly used in general API response testing.
     *
     * @return a {@link Map} containing header names as keys and a list of their corresponding values.
     *         Includes headers such as X-Frame-Options, Cache-Control, X-Content-Type-Options,
     *         Pragma, Expires, X-XSS-Protection, and Content-Type.
     */
    public static Map<String, List<String>> getHeadersForTesting() {
        Map<String, List<String>> headers = new TreeMap<>();

        // Prevents the page from being embedded in frames or iframes
        headers.put("X-Frame-Options", Lists.newArrayList("DENY"));

        // Controls caching behavior on client-side and intermediate proxies
        headers.put("Cache-Control", Lists.newArrayList("no-cache, no-store, max-age=0, must-revalidate"));

        // Prevents MIME-type sniffing
        headers.put("X-Content-Type-Options", Lists.newArrayList("nosniff"));

        // Disables caching in HTTP/1.0 proxies
        headers.put("Pragma", Lists.newArrayList("no-cache"));

        // Expiration date for caching; 0 disables caching
        headers.put("Expires", Lists.newArrayList("0"));

        // Controls XSS protection features in browsers (disabled here)
        headers.put("X-XSS-Protection", Lists.newArrayList("0"));

        // Specifies that the content type is JSON
        headers.put("Content-Type", Lists.newArrayList("application/json"));

        return headers;
    }

    /**
     * Generates a map of HTTP headers used specifically for DELETE operation testing.
     *
     * @return a {@link Map} containing header names and corresponding values relevant to
     *         DELETE operation tests. It excludes the Content-Type header used in general testing.
     */
    public static Map<String, List<String>> getHeadersForDeleteTesting() {
        Map<String, List<String>> headers = new TreeMap<>();

        headers.put("X-Frame-Options", Lists.newArrayList("DENY"));
        headers.put("Cache-Control", Lists.newArrayList("no-cache, no-store, max-age=0, must-revalidate"));
        headers.put("X-Content-Type-Options", Lists.newArrayList("nosniff"));
        headers.put("Pragma", Lists.newArrayList("no-cache"));
        headers.put("Expires", Lists.newArrayList("0"));
        headers.put("X-XSS-Protection", Lists.newArrayList("0"));

        return headers;
    }
}
