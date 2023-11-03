package com.rohit.ElectronicStore.service;

import com.rohit.ElectronicStore.dtos.CategoryDto;
import com.rohit.ElectronicStore.dtos.PageableResponse;
import com.rohit.ElectronicStore.dtos.ProductDto;

public interface CategoryService
{


    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete

    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single category detail
    CategoryDto get(String categoryId);



    //search:
}

