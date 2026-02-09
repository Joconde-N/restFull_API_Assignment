package auca.ac.rw.question4_ecommerce_product.controller;

import auca.ac.rw.question4_ecommerce_product.model.Product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 11L;

    public ProductController(){
        products.add(new Product(1L, "Laptop Pro 14", "Laptop for coding and design", 1200.0, "Electronics", 5, "TechBrand"));
        products.add(new Product(2L, "Wireless Mouse", "Silent mouse for office work", 25.0, "Electronics", 50, "LogiX"));
        products.add(new Product(3L, "Mechanical Keyboard", "RGB mechanical keyboard", 70.0, "Electronics", 0, "KeyPro"));
        products.add(new Product(4L, "Smartphone X", "Great camera and battery", 650.0, "Electronics", 12, "PhoneMax"));
        products.add(new Product(5L, "Office Chair", "Ergonomic chair for long hours", 150.0, "Home", 7, "FurniCo"));
        products.add(new Product(6L, "Coffee Maker", "Fast coffee machine", 90.0, "Home", 3, "BrewIt"));
        products.add(new Product(7L, "Running Shoes", "Comfort shoes for sport", 80.0, "Fashion", 0, "Sporty"));
        products.add(new Product(8L, "Backpack", "Water-resistant laptop backpack", 40.0, "Fashion", 20, "UrbanCarry"));
        products.add(new Product(9L, "T-Shirt", "Cotton t-shirt", 15.0, "Fashion", 100, "UrbanCarry"));
        products.add(new Product(10L, "Blender", "Kitchen blender for smoothies", 60.0, "Home", 10, "HomeMix"));
    }

     @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit
    ) {
        if (page == null || limit == null) {
            return ResponseEntity.ok(products); // 200
        }

        if (page < 1 || limit < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
        }

        int start = (page - 1) * limit;
        int end = Math.min(start + limit, products.size());

        if (start >= products.size()) {
            return ResponseEntity.ok(new ArrayList<>()); // 200 empty list
        }

        return ResponseEntity.ok(products.subList(start, end)); // 200
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product prod : products) {
            if (prod.getProductId().equals(productId)) {
                return ResponseEntity.ok(prod); // 200
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getCategory() != null && prod.getCategory().equalsIgnoreCase(category)) {
                result.add(prod);
            }
        }
        return ResponseEntity.ok(result); // 200
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getBrand() != null && prod.getBrand().equalsIgnoreCase(brand)) {
                result.add(prod);
            }
        }
        return ResponseEntity.ok(result); // 200
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        String k = keyword.toLowerCase();

        for (Product prod : products) {
            String name = (prod.getName() == null) ? "" : prod.getName().toLowerCase();
            String descr = (prod.getDescription() == null) ? "" : prod.getDescription().toLowerCase();

            if (name.contains(k) || descr.contains(k)) {
                result.add(prod);
            }
        }
        return ResponseEntity.ok(result); // 200
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        if (min > max) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
        }

        List<Product> result = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getPrice() != null && prod.getPrice() >= min && prod.getPrice() <= max) {
                result.add(prod);
            }
        }
        return ResponseEntity.ok(result); // 200
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> inStock() {
        List<Product> result = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getStockQuantity() > 0) {
                result.add(prod);
            }
        }
        return ResponseEntity.ok(result); // 200
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setProductId(nextId++);
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product); // 201
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product p) {
        for (Product prod : products) {
            if (prod.getProductId().equals(productId)) {
                prod.setName(p.getName());
                prod.setDescription(p.getDescription());
                prod.setPrice(p.getPrice());
                prod.setCategory(p.getCategory());
                prod.setStockQuantity(p.getStockQuantity());
                prod.setBrand(p.getBrand());
                return ResponseEntity.ok(prod); // 200
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        if (quantity < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
        }

        for (Product prod : products) {
            if (prod.getProductId().equals(productId)) {
                prod.setStockQuantity(quantity);
                return ResponseEntity.ok(prod); // 200
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                products.remove(i);
                return ResponseEntity.noContent().build(); // 204
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
    }
    
}
