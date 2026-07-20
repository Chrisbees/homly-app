package com.chrisbees.e_commerce.property.domain.dto.mapper;

import org.springframework.stereotype.Component;

import com.chrisbees.e_commerce.category.domain.Category;
import com.chrisbees.e_commerce.property.domain.Address;
import com.chrisbees.e_commerce.property.domain.Property;
import com.chrisbees.e_commerce.property.domain.PropertyDetails;
import com.chrisbees.e_commerce.property.domain.dto.request.UpdateAddressRequest;
import com.chrisbees.e_commerce.property.domain.dto.request.UpdatePropertyDetailsRequest;
import com.chrisbees.e_commerce.property.domain.dto.request.UpdatePropertyRequest;
import com.chrisbees.e_commerce.property.domain.enums.FurnishingLevel;
import com.chrisbees.e_commerce.property.domain.enums.PaymentFrequency;
import com.chrisbees.e_commerce.property.domain.enums.PropertyType;
import com.chrisbees.e_commerce.property.domain.enums.StatusEnum;

@Component
public class UpdateToPropertyMapper {
    
    public void mapUpdateRequestToProperty(UpdatePropertyRequest propertyRequest, Property property) {
        if (propertyRequest.name() != null) {
            property.setName(propertyRequest.name());
            property.setSlug(property.getName()
                    .toLowerCase()
                    .replace(" ", "-"));
        }
        if (propertyRequest.shortDesc() != null) {
            property.setShortDesc(propertyRequest.shortDesc());
        }
        if (propertyRequest.longDesc() != null) {
            property.setLongDesc(propertyRequest.longDesc());
        }
        if (propertyRequest.pricePerMonth() != null) {
            property.setPricePerMonth(propertyRequest.pricePerMonth());
        }
        if (propertyRequest.paymentFrequency() != null) {
            property.setPaymentFrequency(PaymentFrequency.valueOf(propertyRequest.paymentFrequency()));
        }
        if (propertyRequest.status() != null) {
            property.setStatus(StatusEnum.valueOf(propertyRequest.status()));
        }
        if (propertyRequest.address() != null) {
            UpdateAddressRequest addressRequest = propertyRequest.address();
            Address address = property.getAddress();
            if (addressRequest.city() != null) {
                address.setCity(addressRequest.city());
            }
            if (addressRequest.landmark() != null) {
                address.setLandmark(addressRequest.landmark());
            }
            if (addressRequest.postalCode() != null) {
                address.setPostalCode(addressRequest.postalCode());
            }
            if (addressRequest.state() != null) {
                address.setState(addressRequest.state());
            }
            if (addressRequest.streetAddress() != null) {
                address.setStreetAddress(addressRequest.streetAddress());
            }
        }
        if (propertyRequest.propertyDetails() != null) {
            UpdatePropertyDetailsRequest propertyDetailsRequest = propertyRequest.propertyDetails();
            PropertyDetails propertyDetails = property.getPropertyDetails();
            if (propertyDetailsRequest.bathrooms() != null) {
                propertyDetails.setBathrooms(propertyDetailsRequest.bathrooms());
            }
            if (propertyDetailsRequest.bedrooms() != null) {
                propertyDetails.setBedrooms(propertyDetailsRequest.bedrooms());
            }
            if (propertyDetailsRequest.furnishingLevel() != null) {
                propertyDetails.setFurnishingLevel(FurnishingLevel.valueOf(propertyDetailsRequest.furnishingLevel()));
            }
            if (propertyDetailsRequest.parkingIncluded() != null) {
                propertyDetails.setParkingIncluded(propertyDetailsRequest.parkingIncluded());
            }
            if (propertyDetailsRequest.petsAllowed() != null) {
                propertyDetails.setPetsAllowed(propertyDetailsRequest.petsAllowed());
            }
            if (propertyDetailsRequest.propertyType() != null) {
                propertyDetails.setPropertyType(PropertyType.valueOf(propertyDetailsRequest.propertyType()));
            }
            if (propertyDetailsRequest.toilets() != null) {
                propertyDetails.setToilets(propertyDetailsRequest.toilets());
            }
            if (propertyDetailsRequest.yearBuilt() != null) {
                propertyDetails.setYearBuilt(propertyDetailsRequest.yearBuilt());
            }
        }
        if (propertyRequest.category() != null) {
            Category category = property.getCategory();
            category.setName(propertyRequest.category());
        }
    }
}
