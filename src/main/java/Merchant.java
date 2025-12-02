import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Merchant {
    private String bankAccountNumber;
    private Set<Product> products=new HashSet<>();

    public Merchant(String bankAccountNumber) {
        if (bankAccountNumber==null||bankAccountNumber.isBlank()){
            throw new IllegalArgumentException("Bank account cannot be empty");

        }
        this.bankAccountNumber = bankAccountNumber;
    }

    public Product createProduct(String image,
                                 double price,
                                 String title,
                                 String category,
                                 String description,
                                 double advertisementFee,
                                 boolean availability) {

        return new Product(image, price, title, category, description,
                advertisementFee, availability, this);
    }

    public void addExistingProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot add null product");
        }
        products.add(product);
    }


    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void deleteMerchant() {
        for (Product p : products) {
            p.internalDelete();
        }
        products.clear();
    }
}
