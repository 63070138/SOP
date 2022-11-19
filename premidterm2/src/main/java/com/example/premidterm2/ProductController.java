package com.example.premidterm2;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @RabbitListener(queues = "AddProductQueue")
    public void serviceAddProduct(Product product){
            productService.addProduct(product);
    }
    @RabbitListener(queues = "UpdateProductQueue")
    public void serviceUpdateProduct(Product product){
            productService.updateProduct(product);
    }
    @RabbitListener(queues = "DeleteProductQueue")
    public void serviceDeleteProduct(Product product){
            productService.deleteProduct(product);
    }
    @RabbitListener(queues = "GetNameProductQueue")
    public Product serviceGetProductName(String string){
        try{
            return productService.getProductByName(string);
        } catch (Exception e){
            return null;
        }
    }
    @RabbitListener(queues = "GetAllProductQueue")
    public ArrayList<Product> serviceGetAllProduct(){
        try{
            return (ArrayList<Product>) productService.getAllProduct();
        } catch (Exception e){
            return null;
        }
    }
}
