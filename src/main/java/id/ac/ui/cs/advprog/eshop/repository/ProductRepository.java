package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Repository
public class ProductRepository implements AllRepository<Product> {
    private Map<String, Product> idToProductMap = new HashMap<>();

    @Override
    public Product create(Product product) {
        product.setProductID(UniqueIdGenerator.generate());
        idToProductMap.put(product.getProductID(), product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return idToProductMap.values().iterator();
    }

    @Override
    public Product findById(String id) {
        return idToProductMap.get(id);
    }

    @Override
    public Product update(String id, Product updatedProduct) {
        if (idToProductMap.containsKey(id)) {
            Product product = idToProductMap.get(id);
            product.setProductName(updatedProduct.getProductName());
            product.setProductQuantity(updatedProduct.getProductQuantity());
            return product;
        }
        return null;
    }

    @Override
    public void delete(String id) {
        idToProductMap.remove(id);
    }
}

