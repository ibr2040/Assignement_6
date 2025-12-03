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

        if (product.isCompositionProduct()) {
            throw new IllegalStateException("This product cannot be removed individually because it is permanently linked to this merchant."
            );
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

        if (product.isCompositionProduct()) {
            throw new UnsupportedOperationException(
                    "Cannot remove a composition product individually."
            );
        }

        product.removeMerchant();
        products.remove(product);
    }

    public void deleteMerchant(){
        for(Product p:new ArrayList<>(products)){
            p.internalDestroy();
        }
        products.clear();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public String getBankAccountNumber () {
        return bankAccountNumber;
    }
}
