package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        product.setProductID(String.valueOf(UUID.randomUUID()));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(Product product){
        for (Product thisProduct: productData) {
            if (thisProduct.getProductID().equals(product.getProductID())){
                thisProduct.setProductQuantity(product.getProductQuantity());
                thisProduct.setProductName(product.getProductName());
                return thisProduct;
            }  
        }
        return null;
    }

    public void delete(String productId){
        productData.removeIf(product -> product.getProductID().equals(productId));
    }
}
