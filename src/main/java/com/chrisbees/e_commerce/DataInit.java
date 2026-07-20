// package com.chrisbees.e_commerce;

// import java.math.BigDecimal;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.chrisbees.e_commerce.category.api.CategoryController;
// import com.chrisbees.e_commerce.category.domain.dto.CategoryRequest;
// import com.chrisbees.e_commerce.property.api.PropertyController;
// import com.chrisbees.e_commerce.property.domain.dto.AddressRequest;
// import com.chrisbees.e_commerce.property.domain.dto.PropertyDetailsRequest;
// import com.chrisbees.e_commerce.property.domain.dto.PropertyRequest;
// import com.chrisbees.e_commerce.property.domain.enums.FurnishingLevel;
// import com.chrisbees.e_commerce.property.domain.enums.PaymentFrequency;
// import com.chrisbees.e_commerce.property.domain.enums.PropertyType;
// import com.chrisbees.e_commerce.property.domain.enums.StatusEnum;

// import jakarta.servlet.http.HttpServletRequest;
// import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// public class DataInit {

//     private final PropertyController propertyController;
//     private final CategoryController categoryController;
//     private final HttpServletRequest request;

   
//     @Bean
//     CommandLineRunner runner() {
//         return args -> {

//             // Categories
//             categoryController.createCategory(
//                     new CategoryRequest("Apartment", "Modern apartment units"));

//             categoryController.createCategory(
//                     new CategoryRequest("Duplex", "Luxury duplex properties"));

//             categoryController.createCategory(
//                     new CategoryRequest("Commercial", "Commercial buildings and offices"));

//             // Property 1
//             propertyController.createProperty(
//                     new PropertyRequest(
//                             "Luxury Apartment Ikoyi",
//                             "Luxury waterfront apartment",
//                             "Beautiful waterfront apartment with ocean view in Banana Island.",
//                             new BigDecimal("1200000"),
//                             PaymentFrequency.ANUALLY.name(),
//                             StatusEnum.AVAILABLE.name(),

//                             new AddressRequest(
//                                     "12 Banana Island Road",
//                                     "Ikoyi",
//                                     "Lagos",
//                                     "101233",
//                                     "Near Foreshore Estate"),

                        

//                             new PropertyDetailsRequest(
//                                     PropertyType.APARTMENT.name(),
//                                     3,
//                                     4,
//                                     4,
//                                     true,
//                                     true,
//                                     2021,
//                                     FurnishingLevel.FURNISHED.name()),

//                             "Apartment"), request);

//             // Property 2
//             propertyController.createProperty(
//                     new PropertyRequest(
//                             "Lekki Studio Apartment",
//                             "Affordable studio apartment",
//                             "Modern studio apartment located in Lekki Phase 1.",
//                             new BigDecimal("450000"),
//                             PaymentFrequency.ANUALLY.name(),
//                             StatusEnum.AVAILABLE.name(),

//                             new AddressRequest(
//                                     "21 Admiralty Way",
//                                     "Lekki",
//                                     "Lagos",
//                                     "105102",
//                                     "Near Circle Mall"),

//                             new PropertyDetailsRequest(
//                                     PropertyType.FLAT.name(),
//                                     1,
//                                     1,
//                                     1,
//                                     true,
//                                     false,
//                                     2023,
//                                     FurnishingLevel.SEMI_FURNISHED.name()),

//                             "Apartment"), request);

//             // Property 3
//             propertyController.createProperty(
//                     new PropertyRequest(
//                             "Abuja Executive Duplex",
//                             "Luxury duplex",
//                             "Spacious executive duplex in Maitama.",
//                             new BigDecimal("2500000"),
//                             PaymentFrequency.QUATERLY.name(),
//                             StatusEnum.AVAILABLE.name(),

//                             new AddressRequest(
//                                     "10 Gana Street",
//                                     "Maitama",
//                                     "FCT",
//                                     "900271",
//                                     "Near Transcorp Hilton"),

//                             new PropertyDetailsRequest(
//                                     PropertyType.APARTMENT.name(),
//                                     5,
//                                     6,
//                                     6,
//                                     true,
//                                     true,
//                                     2022,
//                                     FurnishingLevel.FURNISHED.name()),

//                             "Duplex"), request);

//         };
//     }

// }
