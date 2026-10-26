package com.chrisbees.e_commerce.property.api;

import java.io.IOException;
import java.util.List;

import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chrisbees.e_commerce.images.dto.ImageResponse;
import com.chrisbees.e_commerce.images.service.ImageStorageService;
import com.chrisbees.e_commerce.property.domain.dto.request.PropertyRequest;
import com.chrisbees.e_commerce.property.domain.dto.request.UpdatePropertyRequest;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertyResponse;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertyWithImagesResponse;
import com.chrisbees.e_commerce.property.service.PropertyService;
import com.chrisbees.e_commerce.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final ImageStorageService imageStorageService;

    public PropertyController(PropertyService propertyService, ImageStorageService imageStorageService) {
        this.propertyService = propertyService;
        this.imageStorageService = imageStorageService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PropertyResponse>> createProperty(
            @Valid @RequestBody PropertyRequest propertyRequest, HttpServletRequest request) {
        var propertyResponse = propertyService.saveProperty(propertyRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(propertyResponse, "property created successfully", request));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse<PropertyWithImagesResponse>> getProperty(@PathVariable String uuid,
            HttpServletRequest request) {
        PropertyWithImagesResponse propertyResponse = propertyService.getPropertyByUUID(uuid);
        System.out.println("PROPERTY: " + propertyResponse);
        return ResponseEntity
                .ok(ApiResponse.success(propertyResponse, "property retrieved successfully", request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedModel<PropertyResponse>>> getAllProperty(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, HttpServletRequest request) {
        PagedModel<PropertyResponse> allProperties = propertyService.returnAllProperties(page, size);
        return ResponseEntity.ok(ApiResponse.success(allProperties, "success", request));
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<ApiResponse<PropertyResponse>> updateProduct(@PathVariable String uuid,
            @Valid @RequestBody UpdatePropertyRequest propertyRequest, HttpServletRequest request) {
        var propertyResponse = propertyService.updateProperty(uuid, propertyRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(propertyResponse, "property updated successfully", request));
    }

    @PostMapping("/{uuid}/images")
    public ResponseEntity<ApiResponse<List<ImageResponse>>> uploadImage(
            @RequestParam("file") List<MultipartFile> multipartFile, @PathVariable String uuid,
            HttpServletRequest request) throws IOException {
        var result = imageStorageService.storeImageFile(multipartFile, uuid);
        return ResponseEntity.ok(ApiResponse.success(result, "image uploaded successfully", request));
    }

    @PatchMapping("delete/{uuid}")
    public ResponseEntity<ApiResponse<?>> deleteProperty(@PathVariable String uuid, HttpServletRequest request) {
        propertyService.deleteProperty(uuid);
        return ResponseEntity.ok(ApiResponse.success("", "property deleled successfully", request));

    }
}
