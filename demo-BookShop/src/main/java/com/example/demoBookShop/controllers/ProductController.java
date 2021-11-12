package com.example.demoBookShop.controllers;

import com.example.demoBookShop.models.Product;
import com.example.demoBookShop.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //also need to check for children records before deleting.
        productService.delete(id);
    }

        @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product update(@PathVariable Long id, @RequestBody Product product){
    return  productService.update(id, product);
    }
}
