/*
---------------------------------------------------------------------------------------------
 EVERESTEK
 Herndon, VA

 (c) Copyright EVERESTEK Corporation.
 ALL RIGHTS RESERVED

 The software and information contained herein are proprietary to, and comprise valuable
 trade secrets of, EVERESTEK Corporation, which intends to preserve as trade secrets such
 software and information. This software should only be furnished subject to a written
 license agreement and may only be used, copied, transmitted, and stored in accordance
 with the terms of such license and with the inclusion of the above copyright notice.
 If there is no written License Agreement between you and EVERESTEK Corporation, then you
 have received this software in error and should be returned to EVERESTEK Corporation or
 destroyed immediately, and you should also notify EVERESTEK Corporation. This software and
 information or any other copies thereof may not be provided or otherwise made available
 to any person who is not authorized to receive it pursuant to a written license Agreement
 executed with EVERESTEK Corporation.
---------------------------------------------------------------------------------------------
*/

package com.everestek.hub.evhub.api;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * A Spring Boot {@link InfoContributor} implementation to provide additional information about the application,
 * including version and service name, by reading the `META-INF/MANIFEST.MF` file.
 */
@Component
public class InfoEndPointApi implements InfoContributor {

    /** ServletContext for accessing application level parameters and resources. */
    private final ServletContext servletContext;

    /**
     * Constructor for InfoEndPointApi.
     *
     * @param servletContext the ServletContext to be injected.
     */
    public InfoEndPointApi(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * Contributes additional details using the specified builder.
     *
     * @param builder The builder to use.
     */
    @Override
    public void contribute(Info.Builder builder) {
        try {
            InputStream manifestStream = servletContext.getResourceAsStream("META-INF/MANIFEST.MF");

            if (manifestStream == null) {
                throw new IllegalStateException("MANIFEST.MF is missing in the war");
            }

            Manifest manifest = new Manifest(manifestStream);
            Attributes attributes = manifest.getMainAttributes();
            String version = attributes.getValue("Implementation-Version");
            String serviceName = attributes.getValue("Implementation-Title");

            builder.withDetail("version", version);
            builder.withDetail("description", "The evhub micro-service for EVERESTEK.");
            builder.withDetail("serviceName", serviceName);

        } catch (IOException e) {
            throw new IllegalStateException("MANIFEST.MF is missing in the war", e);
        }
    }
}
