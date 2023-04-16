package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.exam.Category;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//adding
	//ResponseEntity represents the whole HTTP response: status code, headers, and body.
	//By using @RequestBody annotation you will get your values mapped with the model you created in your system for handling any specific call.
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category category2 = this.categoryService.addCategory(category);
		return ResponseEntity.ok(category2);
	}
	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") int categoryId) {
		return this.categoryService.getCategory(categoryId);
	}
	
	//get all categories
	@GetMapping("/")
	public ResponseEntity<?> getCategories(){
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
	}
	
	//delete mapping
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") int categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}
	
	
}

