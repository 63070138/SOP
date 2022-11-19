package com.example.premidterm2;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Route(value = "index")
public class ProductView extends VerticalLayout {
    private ComboBox<String> listcombo;
    private TextField namefield;
    private NumberField costfield, profitfield, pricefield;
    private HorizontalLayout btngroup;
    private Button add, update, delete, clear;
    private ArrayList<Product> allProduct;
    private Product oldProduct;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public ProductView(){
        rabbitTemplate = new RabbitTemplate();
        listcombo = new ComboBox<String>();
        namefield = new TextField("");
        costfield = new NumberField();
        profitfield = new NumberField();
        pricefield = new NumberField();
        costfield.setValue(0.0);
        profitfield.setValue(0.0);
        pricefield.setValue(0.0);
        add = new Button("Add Product");
        update = new Button("Update Product");
        delete = new Button("Delete Product");
        clear = new Button("Clear Product");
        allProduct = new ArrayList<Product>();
        oldProduct = new Product();
        listcombo.setLabel("Product List");
        namefield.setLabel("Product Name:");
        costfield.setLabel("Product Cost:");
        profitfield.setLabel("Product Profit:");
        pricefield.setLabel("Product Price");
        pricefield.setEnabled(false);
        listcombo.setAllowCustomValue(true);
        btngroup = new HorizontalLayout();
        btngroup.add(add, update, delete, clear);
        System.out.println(allProduct);
        this.setWidth("400px");
        this.add(listcombo, namefield, costfield, profitfield, pricefield, btngroup);

        costfield.addValueChangeListener(event -> {
            double cost = costfield.getValue();
            double profit = profitfield.getValue();
            double price = WebClient.create().get().uri("http://localhost:8080/getPrice/"+cost+"/"+profit).retrieve().bodyToMono(Double.class).block();
            pricefield.setValue(price);
        });
        profitfield.addValueChangeListener(event -> {
            double cost = costfield.getValue();
            double profit = profitfield.getValue();
            double price = WebClient.create().get().uri("http://localhost:8080/getPrice/"+cost+"/"+profit).retrieve().bodyToMono(Double.class).block();
            pricefield.setValue(price);
        });
        add.addClickListener(event -> {
            double cost = costfield.getValue();
            double profit = profitfield.getValue();
            double price = WebClient.create().get().uri("http://localhost:8080/getPrice/"+cost+"/"+profit).retrieve().bodyToMono(Double.class).block();
            pricefield.setValue(price);
            Product product = new Product(null, namefield.getValue(), costfield.getValue(), profitfield.getValue(), pricefield.getValue());
            rabbitTemplate.convertAndSend("ProductExchange", "add", product);
            Notification notification = Notification.show("Product has been added");
        });
        update.addClickListener(event -> {
            double cost = costfield.getValue();
            double profit = profitfield.getValue();
            double price = WebClient.create().get().uri("http://localhost:8080/getPrice/"+cost+"/"+profit).retrieve().bodyToMono(Double.class).block();
            pricefield.setValue(price);
            Product product = new Product(oldProduct.get_id(), namefield.getValue(), costfield.getValue(), profitfield.getValue(), pricefield.getValue());
            rabbitTemplate.convertAndSend("ProductExchange", "update", product);
            Notification notification = Notification.show("Product has been updated");
        });
        delete.addClickListener(event -> {
            rabbitTemplate.convertAndSend("ProductExchange", "delete", oldProduct);
            Notification notification = Notification.show("Product has been deleted");
        });
        listcombo.addFocusListener(event -> {
            allProduct = (ArrayList<Product>) rabbitTemplate.convertSendAndReceive("ProductExchange", "getall", "");
            ArrayList<String> productName = new ArrayList<String>();
            for (int i = 0 ; i < allProduct.size() ; i++){
                productName.add(allProduct.get(i).getProductName());
            }
            listcombo.setItems(productName);
        });
        listcombo.addValueChangeListener(event -> {
            Product product = new Product();
            product = (Product)rabbitTemplate.convertSendAndReceive("ProductExchange", "getname", listcombo.getValue());
            oldProduct = product;
            namefield.setValue(product.getProductName());
            costfield.setValue(product.getProductCost());
            profitfield.setValue(product.getProductProfit());
            pricefield.setValue(product.getProductPrice());
        });
        clear.addClickListener(event -> {
            namefield.setValue("");
            profitfield.setValue(0.0);
            costfield.setValue(0.0);
            pricefield.setValue(0.0);
        });

    }
}
