package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private Map<String, Product> idtoProductHashMap = new HashMap<>();

    public Product create(Product product){
        product.setProductID(String.valueOf(UUID.randomUUID()));
        productData.add(product);
        idtoProductHashMap.put(product.getProductID(), product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(Product product) {
        Product oldProduct = idtoProductHashMap.get(product.getProductID());
        if(oldProduct!=null){
            oldProduct.setProductQuantity(product.getProductQuantity());
            oldProduct.setProductName(product.getProductName());
            return product; 
        }else{
            return null;
        }
    }
    
    public void delete(String productId){
        productData.removeIf(product -> product.getProductID().equals(productId));
    }
}
