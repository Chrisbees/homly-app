package com.chrisbees.e_commerce.property.domain.dto.request;

public record UpdatePropertyDetailsRequest(
        String propertyType,
        Integer bedrooms,
        Integer bathrooms,
        Integer toilets,
        Boolean parkingIncluded,
        Boolean petsAllowed,
        Integer yearBuilt,
        String furnishingLevel) {

}
