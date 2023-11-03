package com.rohit.ElectronicStore.service;

import com.rohit.ElectronicStore.dtos.PageableResponse;
import com.rohit.ElectronicStore.dtos.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);

    //create product with its category
    ProductDto createProductInCategory(ProductDto productDto,String categoryId);


    //update
    ProductDto update(ProductDto productDto, String productId);

    //
    ProductDto updateProductCategory(String productId,String categoryId);

    //delete
    void delete(String productId);

    //get single

    ProductDto get(String productId);

    //get all
    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get all : live
    PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir);

    //search product
    PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir);

    PageableResponse<ProductDto> getAllOfCategory(String categoryId,int pageNumber,int pageSize,String sortBy, String sortDir);

}
