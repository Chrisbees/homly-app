package com.chrisbees.e_commerce.property.domain.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public record UpdatePropertyRequest(

        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name,
        @Size(max = 100, message = "Short description cannot exceed 100 characters")
        String shortDesc,
        String longDesc,
        @DecimalMin(value = "0.0", inclusive = false, message = "price per month must be positive")
        BigDecimal pricePerMonth,
        String paymentFrequency,
        String status,
        UpdateAddressRequest address,
        UpdatePropertyDetailsRequest propertyDetails,
        String category

) {
    
}
