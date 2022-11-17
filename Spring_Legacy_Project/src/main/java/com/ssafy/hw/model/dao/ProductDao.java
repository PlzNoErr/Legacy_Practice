package com.ssafy.hw.model.dao;

import java.util.List;

import com.ssafy.hw.model.dto.Product;
import com.ssafy.hw.model.dto.SearchCondition;

public interface ProductDao {

	void insertProduct(Product product);

	void deleteProduct(String pCode);

	void updateProduct(Product product);

	Product selectProductBypCode(String pCode);

	List<Product> selectProductBySearchConditionWithPaging(SearchCondition con);
	
	int numberOfSearchConditionWithPaging(SearchCondition con);
}
