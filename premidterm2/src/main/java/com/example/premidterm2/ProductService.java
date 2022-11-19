package com.example.premidterm2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    public boolean addProduct(Product product){
            repository.insert(product);
            return true;
    }
    public boolean updateProduct(Product product){
        if (product !=null){
            repository.save(product);
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteProduct(Product product){
        try{
            repository.delete(product);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public List<Product> getAllProduct(){
        try{
            return repository.findAll();
        } catch (Exception e){
            return null;
        }
    }
    public Product getProductByName(String name){
        try {
            return repository.findByName(name);
        } catch (Exception e){
            return null;
        }
    }

}
