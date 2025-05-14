package org.mysite.mysitebackend.Service;

import org.mysite.mysitebackend.entity.Category;
import org.mysite.mysitebackend.entity.Result;

import java.util.List;

public interface CategoryService {
    Result<List<Category>> getList();

    Result add(Category category);

    Result delete(Integer categoryId);

    Result update(Category category);
}
