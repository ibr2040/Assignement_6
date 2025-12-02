import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Campaign {
    private String nameOfCampaign;
    private int pricePerInteraction;
    private String budget;

    public Set<Product> products=new HashSet<>();

    public Campaign(String nameOfCampaign, int pricePerInteraction, String budget) {
        if (nameOfCampaign==null || nameOfCampaign.isBlank()){
            throw new IllegalArgumentException("Campaign name cannot be null");
        }
        this.nameOfCampaign = nameOfCampaign;
        this.pricePerInteraction = pricePerInteraction;
        this.budget = budget;
    }

    public void removeProduct(Product product) {
        if (product == null) {
            return;
        }

        if (!products.contains(product)) {
            throw new IllegalStateException("Product not found in this campaign");
        }

        products.remove(product);
        product.setCampaignInternal(null);
    }

    public void addProduct(Product product){
        if (product==null){
            throw new IllegalArgumentException("Cannot add null product");
        }

        if (products.contains(product)){
            throw new IllegalStateException("Product already assigned to this campaign");
        }

        if (product.getCampaign() != null && product.getCampaign() != this) {
            product.getCampaign().removeProduct(product);
        }

        products.add(product);
        product.setCampaignInternal(this);
    }

    public Set<Product> getProducts(){
        return Collections.unmodifiableSet(products);
    }

    public double getCampaignFee() {
        return products.stream()
                .mapToDouble(Product::getAdvertisementFee)
                .sum();
    }
}
