package com.example.demoBookShop.servicies;

import com.example.demoBookShop.models.Product;
import com.example.demoBookShop.repositories.ProductRepository;
import com.example.demoBookShop.validators.ProductValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    private final ProductValidation productValidation=new ProductValidation();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.getById(id);
    }

    public Product create(Product product) {
        if(!productValidation.validation(product)){
            return null;//error
        }
        return productRepository.saveAndFlush(product);
    }

    public void delete(Long id) {
        //also need to check for children records before deleting.
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product product) {
        //validation of all atributes
        if(!productValidation.validation(product)){
            return null;//error
        }
        Product existingProduct = findProductById(id);
        BeanUtils.copyProperties(product, existingProduct, "id_product");
        return productRepository.saveAndFlush(existingProduct);
    }
}
