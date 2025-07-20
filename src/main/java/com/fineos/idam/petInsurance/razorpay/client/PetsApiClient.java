/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.razorpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import com.fineos.idam.petInsurance.razorpay.configuration.ClientConfiguration;

@FeignClient(name="${pets.name:pets}", url="${pets.url:http://petstore.swagger.io/v1}", configuration = ClientConfiguration.class)
public interface PetsApiClient extends PetsApi {
// PetsApiClient
}
