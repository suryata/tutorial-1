package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public void update(Product product) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductID().equals(product.getProductID())) {
                productData.set(i, product);
                break;
            }
        }
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductID().equals(productId)) {
                return product;
            }
        }
        return null; 
    }
}
