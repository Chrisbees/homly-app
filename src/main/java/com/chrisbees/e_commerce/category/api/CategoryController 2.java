package com.chrisbees.e_commerce.category.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chrisbees.e_commerce.category.domain.dto.CategoryRequest;
import com.chrisbees.e_commerce.category.domain.dto.CategoryResponse;
import com.chrisbees.e_commerce.category.domain.dto.CategoryResponseWithProperties;
import com.chrisbees.e_commerce.category.service.CategoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    
    private final CategoryService catgoryService;

    public CategoryController (CategoryService catgoryService){
        this.catgoryService = catgoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        var categoryResponse = catgoryService.saveCategory(categoryRequest);
        return ResponseEntity.ok(categoryResponse);
    }
    
     @GetMapping("/{uuid}")
     public ResponseEntity<CategoryResponse> getCategory(@PathVariable String uuid) {
        var categoryResponse = catgoryService.getCategoryByUUID(uuid);
         return ResponseEntity.ok(categoryResponse);
     }

     @PatchMapping("/{uuid}")
     public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String uuid, @RequestBody CategoryRequest productRequest){
        var categoryResponse = catgoryService.updateCategory(uuid, productRequest);
        return ResponseEntity.ok(categoryResponse);
     }


      @GetMapping("/products/{uuid}")
        public ResponseEntity<CategoryResponseWithProperties> getCategoryWithProducts(@PathVariable String uuid) {
        var categoryWithcategoryResponse = catgoryService.getCategoryWithProducts(uuid);
        return ResponseEntity.ok(categoryWithcategoryResponse);
     }
     

}
