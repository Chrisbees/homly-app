package com.chrisbees.e_commerce.property.domain.dto.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.chrisbees.e_commerce.property.domain.Address;
import com.chrisbees.e_commerce.property.domain.Property;
import com.chrisbees.e_commerce.property.domain.PropertyDetails;
import com.chrisbees.e_commerce.property.domain.dto.request.PropertyRequest;
import com.chrisbees.e_commerce.property.domain.enums.FurnishingLevel;
import com.chrisbees.e_commerce.property.domain.enums.PaymentFrequency;
import com.chrisbees.e_commerce.property.domain.enums.PropertyType;
import com.chrisbees.e_commerce.property.domain.enums.StatusEnum;

@Component
public class PropertyRequestMapper {
   public Property fromDto(PropertyRequest request) {

    Property property = new Property();

    if (StatusEnum.AVAILABLE.name()
        .equalsIgnoreCase(request.status())) {
    property.setAvailableFrom(LocalDateTime.now());
}

    property.setName(request.name());
    property.setShortDesc(request.shortDesc());
    property.setLongDesc(request.longDesc());
    property.setPricePerMonth(request.pricePerMonth());
    property.setPaymentFrequency(PaymentFrequency.valueOf(request.paymentFrequency().toUpperCase()));

    Address address = new Address();
    address.setStreetAddress(request.address().streetAddress());
    address.setCity(request.address().city());
    address.setState(request.address().state());
    address.setPostalCode(request.address().postalCode());
    address.setLandmark(request.address().landmark());
    property.setAddress(address);

    property.setStatus(StatusEnum.valueOf(request.status()));

    PropertyDetails details = new PropertyDetails();
    details.setPropertyType(
        PropertyType.valueOf(
            request.propertyDetails().propertyType().toUpperCase()
        )
    );
    details.setBedrooms(request.propertyDetails().bedrooms());
    details.setBathrooms(request.propertyDetails().bathrooms());
    details.setToilets(request.propertyDetails().toilets());
    details.setParkingIncluded(request.propertyDetails().parkingIncluded());
    details.setPetsAllowed(request.propertyDetails().petsAllowed());
    details.setYearBuilt(request.propertyDetails().yearBuilt());
    details.setFurnishingLevel(FurnishingLevel.valueOf(request.propertyDetails().furnishingLevel()));
    property.setPropertyDetails(details);

    return property;
}
}
