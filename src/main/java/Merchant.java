import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Merchant {
    private String bankAccountNumber;

    private final List<Product> products=new ArrayList<>();

    public Merchant(String bankAccountNumber) {
        if (bankAccountNumber == null || bankAccountNumber.isBlank()) {
            throw new IllegalArgumentException("Bank account number cannot be empty");
        }
        this.bankAccountNumber = bankAccountNumber;
    }

    public Product createProduct(String title,double price){
        Product p=new Product(this,title,price);
        products.add(p);
        return p;
    }

    public void addExistingProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot add null product");
        }

        if (products.contains(product)) {
            throw new IllegalStateException("Product already belongs to this merchant");
        }

        if (product.getMerchant() != null) {
            throw new IllegalStateException("Product already belongs to another merchant");
        }

        product.setMerchant(this);
        products.add(product);
    }

    public void removeProduct (Product product){
        if (product == null) {
            throw new IllegalArgumentException("Cannot remove null product");
        }

        if (!products.contains(product)) {
            throw new IllegalArgumentException("This product does not belong to this merchant");
        }

        product.removeMerchant();
        products.remove(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public String getBankAccountNumber () {
        return bankAccountNumber;
    }
}
