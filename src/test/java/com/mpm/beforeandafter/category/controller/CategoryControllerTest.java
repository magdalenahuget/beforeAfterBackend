package com.mpm.beforeandafter.category.controller;

import com.mpm.beforeandafter.category.dto.CategoryResponseDto;
import com.mpm.beforeandafter.category.model.Category;
import com.mpm.beforeandafter.category.service.CategoryMapper;
import com.mpm.beforeandafter.category.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;

    private CategoryMapper categoryMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        categoryMapper = new CategoryMapper();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Should return all categories")
    public void getCategoriesShouldReturnCategories() throws Exception {
        //GIVEN
        Category beauty = new Category();
        beauty.setName("beauty");
        Category moto = new Category();
        moto.setName("moto");
        CategoryResponseDto category1Dto = categoryMapper.mapToCategoryResponseDto(beauty);
        CategoryResponseDto category2Dto = categoryMapper.mapToCategoryResponseDto(moto);

        // WHEN
        when(categoryService.getCategories()).thenReturn(List.of(category1Dto, category2Dto));

        // THEN
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
//                .andExpect(jsonPath("$[0].name", is("beauty")))
//                .andExpect(jsonPath("$[1].name", is("moto")));
    }
}