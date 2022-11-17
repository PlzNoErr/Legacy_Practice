package com.ssafy.hw.model.service;

import java.util.List;

import com.ssafy.hw.model.dto.Product;
import com.ssafy.hw.model.dto.SearchCondition;

public interface ProductService {
	
	 void insertProduct(Product product);
	
	 void updateProduct(Product product);
	
	 void deleteProduct(String pCode);
	
	 Product selectProductBypCode(String pCode);
	
	 List<Product> selectProductBySearchConditionWithPaging(SearchCondition con);
	

}
