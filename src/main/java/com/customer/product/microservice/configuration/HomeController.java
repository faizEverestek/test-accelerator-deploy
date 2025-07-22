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

package com.customer.product.microservice.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Home redirection to OpenAPI api documentation. */
@Controller
public class HomeController {

    /**
     * Handles HTTP GET requests for the root URL ("/").
     *
     * <p>This method redirects the client to the Swagger UI page. When a user navigates to the root of the application,
     * they are automatically redirected to the Swagger documentation page located at "swagger-ui.html".
     *
     * @return a string representing the redirection URL to the Swagger UI page.
     */
    @GetMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
