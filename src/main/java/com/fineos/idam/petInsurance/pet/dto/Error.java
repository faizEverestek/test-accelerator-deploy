/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.pet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

/** Error. */
@JacksonXmlRootElement(localName = "Error")
@Generated("v1.0.0")
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
     *
     * <p>This field is required.
     */
    @NotNull
    @Schema(name = "code", description = "Error code.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("code")
    @JacksonXmlProperty(localName = "code")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Integer code;

    /**
     * Error message.
     *
     * <p>This field is required.
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
     * <p>This method returns {@code true} if and only if:
     *
     * <ul>
     *   <li>The specified object is the same instance as this object
     *   <li>The specified object is not null and is of the same class as this object
     *   <li>All member variables of this object and the specified object are equal
     *   <li>For variables marked as Jackson optional nullable, {@link #equalsNullable(JsonNullable, JsonNullable)} is
     *       used to compare the variables
     *   <li>For other variables, {@link Objects#equals} is used to compare the variables
     *   <li>If this object has additional properties, they are compared using {@link Objects#equals}
     *   <li>If this object has a parent class, the parent class's {@code equals} method is also called for comparison
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
        return Objects.equals(this.code, error.code) && Objects.equals(this.message, error.message);
    }

    /**
     * Generates a hash code for the object using its member variables. The hash code is computed based on the following
     * criteria:
     *
     * <p>- For variables marked as Jackson optional nullable, the hash code is computed using the `hashCodeNullable`
     * method. - For other variables, the hash code is computed using their default `hashCode` implementation. - If the
     * object has a parent class, the parent class's `hashCode` is included. - If the object has additional properties,
     * their hash code is also included.
     *
     * @return The computed hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
