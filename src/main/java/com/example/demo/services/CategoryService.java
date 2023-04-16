package com.example.demo.services;

import java.util.Set;

import com.example.demo.model.exam.Category;

public interface CategoryService {
	public Category addCategory(Category category);
	public Category updateCategory(Category category);
	public Set<Category> getCategories();
	public Category getCategory(int cateId);
	public void deleteCategory(int cateId);
}
