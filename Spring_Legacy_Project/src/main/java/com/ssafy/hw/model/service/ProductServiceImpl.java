package com.ssafy.hw.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.hw.model.dao.ProductDao;
import com.ssafy.hw.model.dto.Product;
import com.ssafy.hw.model.dto.SearchCondition;
import com.ssafy.hw.util.PageNavigation;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public void insertProduct(Product product) {
		productDao.insertProduct(product);
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(String pCode) {
		productDao.deleteProduct(pCode);
	}

	@Override
	public Product selectProductBypCode(String pCode) {
		return productDao.selectProductBypCode(pCode);
	}

	@Override
	public List<Product> selectProductBySearchConditionWithPaging(SearchCondition con) {
		// 네비게이션을 활용해서 con의 조건들을 세팅해 줘야 한다.
		int totalCount = productDao.numberOfSearchConditionWithPaging(con);
		PageNavigation nav = new PageNavigation(con.getCurrentPage(), totalCount);
		con.setLimit(10);
		con.setOffSet(nav.getStartPage());
		return productDao.selectProductBySearchConditionWithPaging(con);
	}

}
