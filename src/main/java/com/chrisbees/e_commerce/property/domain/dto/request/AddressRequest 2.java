package com.chrisbees.e_commerce.property.domain.dto.request;

public record AddressRequest (
        String streetAddress,
        String city,
        String state,
        String postalCode,
        String landmark
 ) {}
