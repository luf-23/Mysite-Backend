package org.mysite.mysitebackend.controller;

import org.mysite.mysitebackend.Service.CategoryService;
import org.mysite.mysitebackend.entity.Category;
import org.mysite.mysitebackend.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result<List<Category>> getList(){
        return categoryService.getList();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        return categoryService.add(category);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer categoryId){
        return categoryService.delete(categoryId);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Category category){
        return categoryService.update(category);
    }
}
