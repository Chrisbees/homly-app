package com.chrisbees.e_commerce.property.service;


import org.springframework.data.web.PagedModel;

import com.chrisbees.e_commerce.property.domain.dto.request.PropertyRequest;
import com.chrisbees.e_commerce.property.domain.dto.request.UpdatePropertyRequest;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertyResponse;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertyWithImagesResponse;


public interface PropertyService {
    PropertyResponse saveProperty(PropertyRequest propertyRequest);
    PropertyWithImagesResponse getPropertyByUUID (String uuid);
    PagedModel<PropertyResponse> returnAllProperties(int page, int size);
    PropertyResponse updateProperty(String uuid, UpdatePropertyRequest updatePropertyRequest);
    void deleteProperty(String uuid);
}
