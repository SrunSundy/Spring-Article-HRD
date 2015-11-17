package com.hrd.article.services;

import java.util.List;

import com.hrd.article.entities.CategoryDTO;

public interface CategoryService {
	
	public List<CategoryDTO> listCategory();
	public CategoryDTO getCategory(int id);
	public boolean isInsertCategory(CategoryDTO category);
	public boolean isDeleteCategory(int id);
	public boolean isUpdateCategory(CategoryDTO category);
	public List<CategoryDTO> searchCategoryByName(String name);
	public int getTotalCategory();

	
}
