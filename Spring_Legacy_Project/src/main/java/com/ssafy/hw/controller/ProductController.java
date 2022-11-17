package com.ssafy.hw.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.hw.model.dto.Product;
import com.ssafy.hw.model.dto.Review;
import com.ssafy.hw.model.dto.SearchCondition;
import com.ssafy.hw.model.service.ProductService;
import com.ssafy.hw.model.service.ReviewService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping({ "/", "/index" })
	public String index() {
		return "index";
	}

	@GetMapping("/product/list")
	public String getProductList(@ModelAttribute SearchCondition condition, Model model) {
		List<Product> productList = productService.selectProductBySearchConditionWithPaging(condition);
		model.addAttribute("productList", productList);
		model.addAttribute("condition", condition);
		return "product/productList";
	}

	@GetMapping("/product/regist")
	public String getProductRegist() {
		return "/product/productRegist";
	}

	@PostMapping("/product/regist")
	public String postProductRegist(Product product, MultipartFile file, Model model) {
		
		String fileName = file.getOriginalFilename();
		String uploadPath = "C:\\temp";
		String saveName = UUID.randomUUID() + "_" + fileName;
		
		File target = new File(uploadPath, saveName);
	
		if(!new File(uploadPath).exists()) {
			new File(uploadPath).mkdirs();
		}
		
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			product.setFileName(saveName);
			product.setFileUri(target.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(file);
		productService.insertProduct(product);
		model.addAttribute("product", product);

		return "/product/productDetail";
	}

	@GetMapping("/product/detail")
	public String getProductDetail(String pCode, Model model) {

		Product product = productService.selectProductBypCode(pCode);
		if (product == null) {
			throw new RuntimeException();
		}
		List<Review> list = reviewService.selectAllReview(pCode);
		model.addAttribute("product", product);
		model.addAttribute("reviews", list);

		return "/product/productDetail";
	}

	@GetMapping("/product/delete")
	public String PostDeleteProduct(String pCode) {
		productService.deleteProduct(pCode);
		
		List<Review> list = reviewService.selectAllReview(pCode);
		for (int i = 0; i < list.size(); i++) {
			reviewService.deleteReview(list.get(i).getReviewId());
		}

		return "redirect:/product/list";
	}

	@GetMapping("/product/update")
	public String getProductUpdate(String pCode, Model model) {
		Product product = productService.selectProductBypCode(pCode);

		if (product == null) {
			throw new RuntimeException();
		}

		model.addAttribute("product", product);

		return "/product/productUpdate";
	}

	@PostMapping("/product/update")
	public String postProductUpdate(Product product, Model model) {
		productService.updateProduct(product);
		List<Review> list = reviewService.selectAllReview(product.getpCode());
		model.addAttribute("product", product);
		model.addAttribute("reviews", list);

		return "/product/productDetail";
	}

	@PostMapping("/review/regist")
	public String postReviewRegist(Review review, Model model) {
		reviewService.insertReview(review);

		Product product = productService.selectProductBypCode(review.getpCode());;
		List<Review> list = reviewService.selectAllReview(review.getpCode());
		
		model.addAttribute("product", product);
		model.addAttribute("reviews", list);

		return "/product/productDetail";
	}

	@GetMapping("/review/delete")
	public String getReviewDelete(Integer reviewId, String pCode, Model model) {

		reviewService.deleteReview(reviewId);
		Product product = productService.selectProductBypCode(pCode);
		List<Review> list = reviewService.selectAllReview(pCode);
		
		model.addAttribute("product", product);
		model.addAttribute("reviews", list);

		return "/product/productDetail";
	}

	
	@GetMapping(value="/download"
			,produces= MediaType.APPLICATION_OCTET_STREAM_VALUE )
	@ResponseBody
	public ResponseEntity<Resource> getDownLoad(String fileName) {
		
		String filePath = "C:\\temp\\" + fileName;
		
		Resource res = new FileSystemResource(filePath);
		
		String resourceName = res.getFilename();
		
		resourceName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment; filename=" + new String(resourceName.getBytes("UTF-8") , "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(res, headers, HttpStatus.OK);
	}
	
	@GetMapping("error/404")
	public void showError404() {

	}
	
	@GetMapping("/info")
	public String showBreakTimeInfo() {
		return "info";
	}
	
}
