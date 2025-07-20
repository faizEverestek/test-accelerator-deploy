/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.razorpay.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.List;
import jakarta.annotation.Generated;
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

/**
 * Pet.
 */

@JacksonXmlRootElement(localName = "Pet")

@Generated
   ("v1.0.0")

@lombok.ToString
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
@SuppressWarnings("all")
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Pet {

    /**
    * The unique identifier for a pet.
    
    * This field is required.
    
    
    */
    @NotNull 
    @Schema(name = "id", description = "The unique identifier for a pet.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("id")
    @JacksonXmlProperty(localName = "id")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Long id;
    /**
    * The name of the pet.
    
    * This field is required.
    
    
    */
    @NotNull 
    @Schema(name = "name", description = "The name of the pet.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    @JacksonXmlProperty(localName = "name")
    @JacksonXmlElementWrapper(useWrapping = false)
    private String name;
    /**
    * A tag associated with the pet.
    
    
    
    
    */
    
    @Schema(name = "tag", description = "A tag associated with the pet.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("tag")
    @JacksonXmlProperty(localName = "tag")
    @JacksonXmlElementWrapper(useWrapping = false)
    private String tag;
    
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
        Pet pet = (Pet) o;
        return Objects.equals(this.id, pet.id) &&
            Objects.equals(this.name, pet.name) &&
            Objects.equals(this.tag, pet.tag);
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
      return Objects.hash(id, name, tag);
    }
  
}

