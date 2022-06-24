package com.autobots.automanager.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.controller.dtos.CreateProductDTO;
import com.autobots.automanager.entity.Company;
import com.autobots.automanager.entity.Product;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.service.CompanyService;
import com.autobots.automanager.service.ProductService;
import com.autobots.automanager.service.UserService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	public ProductService _productService;

	@Autowired
	public CompanyService _companyService;

	@Autowired
	public UserService _userService;

	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductDTO product) {
		try {
			if (product.get_socialReason() == null) {
				_productService.createProduct(product, product.get_userId());
			} else {
				_productService.createProduct(product, product.get_socialReason());
			}

			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		Product product = _productService.findProduct(id);

		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Product>(product, HttpStatus.FOUND);
	}

	@GetMapping("/company/{companyId}")
	public ResponseEntity<Set<Product>> getProducts(@PathVariable Long companyId) {
		Company company = _companyService.findCompany(companyId);

		if (company == null || company.get_products().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Set<Product>>(company.get_products(), HttpStatus.FOUND);
	}

	@GetMapping("/owner/{ownerId}")
	public ResponseEntity<Set<Product>> GetProductByOwner(@PathVariable Long ownerId) {
		User user = _userService.findUser(ownerId);

		if (user == null || user.get_products().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Set<Product>>(user.get_products(), HttpStatus.FOUND);
	}

	@DeleteMapping("company/delete/{socialReason}/{companyId}")
	public ResponseEntity<Product> deleteCompanyProduct(@PathVariable String socialReason,
			@PathVariable Long companyId) {

		try {
			_productService.deleteProduct(socialReason, companyId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("owner/delete/{companyId}/{productId}")
	public ResponseEntity<Product> deleteProviderProduct(
			@PathVariable Long providerId,
			@PathVariable Long productId) {

		try {
			_productService.deleteProduct(providerId, productId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}