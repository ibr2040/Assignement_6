import java.util.*;

public class Campaign {
    private String nameOfCampaign;
    private int pricePerInteraction;
    private String budget;

    private  List<Product> products=new ArrayList<>();

    public Campaign(String nameOfCampaign,List<Product> initialProducts) {
        if (nameOfCampaign == null || nameOfCampaign.isBlank()) {
            throw new IllegalArgumentException("Campaign name cannot be empty");
        }

        if (initialProducts == null || initialProducts.isEmpty()) {
            throw new IllegalArgumentException("Campaign must start with at least 1 product (1..*)");
        }

        this.nameOfCampaign = nameOfCampaign;

        for (Product p: initialProducts){
            addProduct(p);
        }
    }

    public void addProduct(Product product){
        if (product==null){
            throw new IllegalArgumentException("Cannot add null product");
        }

        if (products.contains(product)){
            throw new IllegalStateException("Product already added to this campaign");
        }

        if (product.getCampaign() != null) {
            throw new IllegalStateException("Product already belongs to another campaign");
        }

        products.add(product);
        product.setCampaign(this);
    }

    public void removeProduct(Product product){
        if (!products.contains(product)){
            throw new IllegalArgumentException("Product not found in campaign");
        }

        if (products.size() == 1) {
            throw new IllegalStateException("Cannot remove last product");
        }

        products.remove(product);
        product.removeCampaign();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

}
