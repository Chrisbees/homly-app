package com.chrisbees.e_commerce.property.domain;

import com.chrisbees.e_commerce.property.domain.enums.FurnishingLevel;
import com.chrisbees.e_commerce.property.domain.enums.PropertyType;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PropertyDetails {

    @Column(name = "property_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Column(name = "bedrooms", nullable = false)
    private Integer bedrooms;

    @Column(name = "bathrooms", nullable = false)
    private Integer bathrooms;

    @Column(name = "toilets", nullable = false)
    private Integer toilets;

    @Column(name = "parking", nullable = false)
    private Boolean parkingIncluded;

    @Column(name = "pets_allowed", nullable = false)
    private Boolean petsAllowed;

    @Column(name = "year_built", nullable = false)
    private Integer yearBuilt;

    @Enumerated(EnumType.STRING)
    @Column(name = "furnishing_level", nullable = false)
    private FurnishingLevel FurnishingLevel;
    
}
