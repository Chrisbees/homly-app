package com.chrisbees.e_commerce.property.domain.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PropertyRequest(
    
        @NotBlank(message = "Property name is required")
        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name,
        @Size(max = 100, message = "Short description cannot exceed 100 characters")
        String shortDesc,
        String longDesc,
        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "price per month must be positive")
        BigDecimal pricePerMonth,
        @NotNull(message = "payment frequency cannot be null")
        String paymentFrequency,
        @NotNull(message = "Status is required")
        String status,
        @NotNull(message = "Address is required")
        AddressRequest address,
        @NotNull(message = "Property Details is required")
        PropertyDetailsRequest propertyDetails,
        String category

) {

}
