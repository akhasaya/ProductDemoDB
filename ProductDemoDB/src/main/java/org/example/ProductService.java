package org.example;

import java.sql.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProductService {
    ProductDB db = new ProductDB();

    public void addProduct(Product p){
        db.save(p);
    }

    public Stream<Product> getAllProducts(){
        List<Product> prods = db.getAll();
        Stream<Product> productStream = prods.stream();
        return productStream;
    }

    public Product getProduct(String name){
        Product p = db.getAProduct(name);
        return p;
    }

    public Stream<Product> getProductWithText(String text) {
        String str = text.toLowerCase();
        List<Product> prods = db.getProductWithText(text);
        Stream<Product> products = prods.stream();
        return products;
    }

    public Stream<Product> getProductInPlace(String text) {
        String str = text.toLowerCase();
        List<Product> prods = db.getProductInPlace(text);
        Stream<Product> products = prods.stream();
        return products;
    }

    public Stream<Product> getProductsWithoutWarrenty() {
        int year = Year.now().getValue();
        List<Product> products = db.getProductsWithoutWarrenty(year);
        Stream<Product> prods = products.stream();
         return prods;
    }

}
