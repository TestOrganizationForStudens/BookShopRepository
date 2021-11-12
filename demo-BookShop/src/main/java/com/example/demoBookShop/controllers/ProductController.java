package com.example.demoBookShop.controllers;

import com.example.demoBookShop.models.Product;
import com.example.demoBookShop.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Product findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Product product){
        if (productService.create(product)==null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(product);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Product product= findProductById(id);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(product);
        }
        //also need to check for children records before deleting.
        productService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).body(product);
    }

        @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Product product){
        if (productService.update(id, product)==null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(product);
        }
            return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
