package com.chrisbees.e_commerce.property.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import com.chrisbees.e_commerce.category.domain.Category;
import com.chrisbees.e_commerce.category.repositiory.CategoryRepository;
import com.chrisbees.e_commerce.images.domain.PropertyImage;
import com.chrisbees.e_commerce.images.dto.ImageResponse;
import com.chrisbees.e_commerce.images.dto.ImageResponseMapper;
import com.chrisbees.e_commerce.property.domain.Property;
import com.chrisbees.e_commerce.property.domain.dto.mapper.PropertyRequestMapper;
import com.chrisbees.e_commerce.property.domain.dto.mapper.PropertyResponseMapper;
import com.chrisbees.e_commerce.property.domain.dto.mapper.PropertyWithImagesResponseMapper;
import com.chrisbees.e_commerce.property.domain.dto.mapper.UpdateToPropertyMapper;
import com.chrisbees.e_commerce.property.domain.dto.request.PropertyRequest;
import com.chrisbees.e_commerce.property.domain.dto.request.UpdatePropertyRequest;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertyResponse;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertyWithImagesResponse;
import com.chrisbees.e_commerce.property.exception.PropertyNotFoundException;
import com.chrisbees.e_commerce.property.repositiory.PropertyRepository;

import jakarta.transaction.Transactional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRequestMapper propertyRequestMapper;
    private final PropertyResponseMapper propertyResponseMapper;
    private final ImageResponseMapper imageResponseMapper;
    private final PropertyWithImagesResponseMapper propertyWithImagesResponseMapper;
    private final PropertyRepository propertyRepository;
    private final CategoryRepository categoryRepository;
    private final UpdateToPropertyMapper updateToPropertyMapper;

    public PropertyServiceImpl(PropertyRequestMapper propertyRequestMapper,
            PropertyResponseMapper propertyResponseMapper,
            PropertyRepository propertyRepository, CategoryRepository categoryRepository,
            ImageResponseMapper imageResponseMapper,
            PropertyWithImagesResponseMapper propertyWithImagesResponseMapper, UpdateToPropertyMapper updateToPropertyMapper) {
        this.propertyRequestMapper = propertyRequestMapper;
        this.propertyResponseMapper = propertyResponseMapper;
        this.imageResponseMapper = imageResponseMapper;
        this.propertyWithImagesResponseMapper = propertyWithImagesResponseMapper;
        this.propertyRepository = propertyRepository;
        this.categoryRepository = categoryRepository;
        this.updateToPropertyMapper = updateToPropertyMapper;
    }

    @Override
    @Transactional
    public PropertyResponse saveProperty(PropertyRequest propertyRequest) {
        Property property = propertyRequestMapper.fromDto(propertyRequest);
        Optional<Category> category = categoryRepository.findByname(propertyRequest.category().toLowerCase());
        if (category.isPresent()) {
            property.setCategory(category.get());
        } else {
            System.out.println("Category does not exits");
        }
        property.setSlug(property.getName()
                .replace(" ", "-")
                .toLowerCase());
        property.setUuid(UUID.randomUUID().toString());
        Property createdProperty = propertyRepository.save(property);
        return propertyResponseMapper.toDto(createdProperty, List.of());
    }

    @Override
    public PropertyWithImagesResponse getPropertyByUUID(String uuid) {
        Property property = propertyRepository.findByUuidAndDeletedFalse(uuid)
                .orElseThrow(() -> new PropertyNotFoundException("Property does not exist"));
        System.out.println("NEW PROPERTY: " + property);
        List<PropertyImage> propertyImages = property.getPropertyImages();
        List<ImageResponse> imageResponses = imageResponseMapper.toDto(propertyImages);
        return propertyWithImagesResponseMapper.toDto(property, imageResponses);
    }

    @Override
    public PagedModel<PropertyResponse> returnAllProperties(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "createdAt"));
        Page<Property> allByOrderByCreatedAtDesc = propertyRepository.findAllByDeletedFalse(pageRequest);
        Page<PropertyResponse> propertyList = allByOrderByCreatedAtDesc.map(property -> {
            return propertyResponseMapper.toDto(property, property.getPropertyImages());
        });
        return new PagedModel<>(propertyList);
    }


    @Override
    @Transactional
    public PropertyResponse updateProperty(String uuid, UpdatePropertyRequest propertyRequest) {
        Property property = propertyRepository.findByUuidAndDeletedFalse(uuid)
                .orElseThrow(() -> new PropertyNotFoundException("property not found"));
        updateToPropertyMapper.mapUpdateRequestToProperty(propertyRequest, property);

        if (property.getPropertyImages() != null) {
            List<PropertyImage> propertyImages = property.getPropertyImages();
            return propertyResponseMapper.toDto(property, propertyImages);

        } else
            return propertyResponseMapper.toDto(property, List.of());
    }

    

    @Override
    public void deleteProperty(String uuid) {
        Property property = propertyRepository.findByUuidAndDeletedFalse(uuid)
                .orElseThrow(() -> new PropertyNotFoundException("property not found"));
                property.setDeleted(true);
                property.setDeletedAt(Instant.now());
        propertyRepository.save(property);
    }

}
