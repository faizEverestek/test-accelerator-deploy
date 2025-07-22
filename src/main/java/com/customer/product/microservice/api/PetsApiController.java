/*---------------------------------------------------------------------------------------------
CUSTOMER

(c) Copyright CUSTOMER.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.customer.product.microservice.api;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Generated
   ("v1.0.0")

@Controller
@RequestMapping("${openapi.swaggerPetstore.base-path:/v1}")
public class PetsApiController implements PetsApi {

    /**
    * The {@link NativeWebRequest} instance used for handling the current web request.
    */
    private final NativeWebRequest request;

    /**
    * Constructs a new PetsApiController with the specified {@link NativeWebRequest}.
    *
    * @param request The web request for handling requests
    */
    @Autowired
    public PetsApiController(final NativeWebRequest request) {
        this.request = request;
    }

    /**
    * Returns an {@link Optional} containing the current {@link NativeWebRequest}.
    *
    * @return an {@link Optional} containing the current {@link NativeWebRequest} if available, otherwise an empty {@link Optional}
    */
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
