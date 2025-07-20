/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.pet.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
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
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.List;
import jakarta.annotation.Generated;

/**
 * Error.
 */

@JacksonXmlRootElement(localName = "Error")

@Generated
   ("v1.0.0")

@lombok.ToString
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
@SuppressWarnings("all")
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Error {

    /**
    * Error code.
    
    * This field is required.
    
    
    */
    @NotNull 
    @Schema(name = "code", description = "Error code.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("code")
    @JacksonXmlProperty(localName = "code")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Integer code;
    /**
    * Error message.
    
    * This field is required.
    
    
    */
    @NotNull 
    @Schema(name = "message", description = "Error message.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("message")
    @JacksonXmlProperty(localName = "message")
    @JacksonXmlElementWrapper(useWrapping = false)
    private String message;
    
    /**
    * Compares this object to the specified object for equality.
    *
    * This method returns {@code true} if and only if:
    * <ul>
        *     <li>The specified object is the same instance as this object</li>
        *     <li>The specified object is not null and is of the same class as this object</li>
        *     <li>All member variables of this object and the specified object are equal</li>
        *     <li>For variables marked as Jackson optional nullable, {@link #equalsNullable(JsonNullable, JsonNullable)} is used to compare the variables</li>
        *     <li>For other variables, {@link Objects#equals} is used to compare the variables</li>
        *     <li>If this object has additional properties, they are compared using {@link Objects#equals}</li>
        *     <li>If this object has a parent class, the parent class's {@code equals} method is also called for comparison</li>
        * </ul>
    *
    * @param o The object to be compared for equality with this object
    * @return {@code true} if the specified object is equal to this object, {@code false} otherwise
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
        if (o == null || getClass() != o.getClass()) {
          return false;
        }
        Error error = (Error) o;
        return Objects.equals(this.code, error.code) &&
            Objects.equals(this.message, error.message);
    }

    /**
    * Generates a hash code for the object using its member variables.
    * The hash code is computed based on the following criteria:
    *
    * - For variables marked as Jackson optional nullable, the hash code is computed using the `hashCodeNullable` method.
    * - For other variables, the hash code is computed using their default `hashCode` implementation.
    * - If the object has a parent class, the parent class's `hashCode` is included.
    * - If the object has additional properties, their hash code is also included.
    *
    * @return The computed hash code.
    */
    @Override
    public int hashCode() {
      return Objects.hash(code, message);
    }
  
}

