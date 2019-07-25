package forkjoinpool;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {
    public List<Product> generate(int size){
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product "+i);
            product.setPrice(10);
            result.add(product);
        }
        return result;
    }
}
