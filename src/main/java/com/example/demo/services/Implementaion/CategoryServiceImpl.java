package com.example.demo.services.Implementaion;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.exam.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return this.categoryRepo.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>( this.categoryRepo.findAll());
	}

	@Override
	public Category getCategory(int cateId) {
		// TODO Auto-generated method stub
		return this.categoryRepo.findById(cateId).get();
	}

	@Override
	public void deleteCategory(int cateId) {
		// TODO Auto-generated method stub
		this.categoryRepo.deleteById(cateId);
	}

}
