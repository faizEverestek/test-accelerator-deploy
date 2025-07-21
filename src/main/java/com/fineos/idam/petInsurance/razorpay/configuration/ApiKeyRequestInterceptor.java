/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.razorpay.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Util;

public final class ApiKeyRequestInterceptor implements RequestInterceptor {

    /**
     * A constant for "header".
     */
    private static final String HEADER = "header";

    /**
     * A constant for "query".
     */
    private static final String QUERY = "query";

    /**
     * The location where the API key should be added in the request. It can either
     * be "header" or "query".
     */
    private final String location;

    /**
     * The name of the API key. This is the key that will be used in the header or
     * query parameter.
     */
    private final String name;

    /**
     * The value of the API key. This is the value that will be associated with the
     * key in the request.
     */
    private final String value;

    /**
     * Constructs a new ApiKeyRequestInterceptor.
     *
     * @param location the location to add the API key, either "header" or "query".
     * @param name     the name of the API key.
     * @param value    the value of the API key.
     * @throws NullPointerException if any of the parameters are null.
     */
    public ApiKeyRequestInterceptor(final String location, final String name, final String value) {
        Util.checkNotNull(location, "location");
        Util.checkNotNull(name, "name");
        Util.checkNotNull(value, "value");
        this.location = location;
        this.name = name;
        this.value = value;
    }

    /**
     * Adds the API key to the request based on the specified location.
     *
     * @param requestTemplate the request template to modify.
     */
    @Override
    public void apply(final RequestTemplate requestTemplate) {
        if (HEADER.equals(location)) {
            requestTemplate.header(name, value);
        } else if (QUERY.equals(location)) {
            requestTemplate.query(name, value);
        }
    }

}
