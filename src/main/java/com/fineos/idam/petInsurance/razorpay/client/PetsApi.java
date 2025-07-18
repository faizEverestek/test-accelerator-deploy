/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.razorpay.client;

import com.fineos.idam.petInsurance.razorpay.dto.Error;
import com.fineos.idam.petInsurance.razorpay.dto.Pet;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated
   ("v1.0.0")

@Validated
@Tag(name = "pets", description = "the pets API")
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public interface PetsApi {

    /**
     * POST /pets : Create a pet.
     * Create a pet in the store with the given details.
     *
     * @param pet Pet object that needs to be added to the store. (required)
     * @param acceptHeader The value of the "Accept" header sent with the request
     * @return Null response. (status code 201)
     *         or unexpected error, (status code 200)
     */
    @Operation(
        operationId = "createPets",
        summary = "Create a pet.",
        description = "Create a pet in the store with the given details.",
        tags = { "pets" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Null response."),
            @ApiResponse(responseCode = "default", description = "unexpected error,", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/pets",
        consumes = { "application/json" }
    )
    
    ResponseEntity<Void> createPets(
        @Parameter(name = "Pet", description = "Pet object that needs to be added to the store.", required = true) @Valid @RequestBody Pet pet,
        @RequestHeader(value = "Accept", defaultValue = "*/*") String acceptHeader
    );


    /**
     * GET /pets : List all pets.
     * Get a paged list of pets.
     *
     * @param limit How many items to return at one time (max 100). (optional)
     * @param acceptHeader The value of the "Accept" header sent with the request
     * @return A paged array of pets. (status code 200)
     *         or unexpected error. (status code 200)
     */
    @Operation(
        operationId = "listPets",
        summary = "List all pets.",
        description = "Get a paged list of pets.",
        tags = { "pets" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of pets.", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Pet.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error.", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/pets",
        consumes = { "application/json" }
    )
    
    ResponseEntity<List<Pet>> listPets(
        @Parameter(name = "limit", description = "How many items to return at one time (max 100).", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit,
        @RequestHeader(value = "Accept", defaultValue = "*/*") String acceptHeader
    );


    /**
     * GET /pets/{petId} : Info for a specific pet.
     * Get a specific pet by ID.
     *
     * @param petId The id of the pet to retrieve. (required)
     * @param acceptHeader The value of the "Accept" header sent with the request
     * @return Expected response to a valid request. (status code 200)
     *         or unexpected error. (status code 200)
     */
    @Operation(
        operationId = "showPetById",
        summary = "Info for a specific pet.",
        description = "Get a specific pet by ID.",
        tags = { "pets" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request.", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pet.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error.", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/pets/{petId}",
        consumes = { "application/json" }
    )
    
    ResponseEntity<Pet> showPetById(
        @Parameter(name = "petId", description = "The id of the pet to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("petId") String petId,
        @RequestHeader(value = "Accept", defaultValue = "*/*") String acceptHeader
    );

}
