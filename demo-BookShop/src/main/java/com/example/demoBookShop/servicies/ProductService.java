package com.example.demoBookShop.servicies;

import com.example.demoBookShop.exceptions.AppException;
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

    public Product create(Product product) throws AppException {
        productValidation.validation(product);
        return productRepository.saveAndFlush(product);
    }

    public Product delete(Long id) throws AppException{
        Product product = findProductById(id);
        if(product==null){
            throw new AppException("There isn't such product with this id"+id);
        }
        //also need to check for children records before deleting.
        productRepository.deleteById(id);
        return product;
    }

    public Product update(Long id, Product product) throws AppException {
        //validation of all atributes
        productValidation.validation(product);
        Product existingProduct = findProductById(id);
        BeanUtils.copyProperties(product, existingProduct, "id_product");
        return productRepository.saveAndFlush(existingProduct);
    }

    public List<Product>  findByPrice(Double price) throws AppException{
        if(price==null){
            throw new AppException("Field price is empty");
        }
        return productRepository.findByPrice(price);
    }

    public List<Product> findByPriceThatAreCheaper(Double price) throws AppException{
        if(price==null){
            throw new AppException("Field price is empty");
        }
        return productRepository.findByPriceThatAreCheaper(price);
    }

    public List<Product> findByPriceThatAreExpensive(Double price) throws AppException{
        if(price==null){
            throw new AppException("Field price is empty");
        }
        return productRepository.findByPriceThatAreExpensive(price);
    }

    public List<Product> publishingHouse(String publishHouse)  throws AppException{
        if(publishHouse.isBlank()){
            throw new AppException("Field publishHouse is Blank");
        }
        return productRepository.findByPublishingHouse(publishHouse);
    }

    public List<Product> findByYear(Integer year) throws AppException{
        if(year==null && year.intValue()>0){
            throw new AppException("Field year is empty");
        }
        return productRepository.findByYear(year);
    }

    public List<Product> findByProductName(String productName) throws AppException{
        if(productName.isBlank()){
            throw new AppException("Field productName is Blank");
        }
        return productRepository.findByProductName(productName);
    }

    public List<Product> findByCategory(String category) throws AppException{
        if(category.isBlank()){
            throw new AppException("Field category is Blank");
        }
        return productRepository.findByCategory(category);
    }

    public List<Product> findByAuthor(String author) throws AppException{
        if(author.isBlank()){
            throw new AppException("Field author is Blank");
        }
        return productRepository.findByAuthor(author);
    }
}