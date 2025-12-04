public class Product {

    private String image;
    private double price;
    private String title;
    private String category;
    private String description;
    private double advertisementFee;
    private boolean availability;


    private Campaign campaign;

    private Merchant merchant;

    private final boolean isCompositionProduct;

    public Product(Merchant merchant, String title, double price) {
        if (merchant == null) {
            throw new IllegalArgumentException("Merchant cannot be null for composition constructor");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        this.isCompositionProduct=true;
        this.merchant = merchant;
        this.title = title;
        this.price = price;
    }

    public Product(String title, double price) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        this.title = title;
        this.price = price;
        this.isCompositionProduct=false;
    }

    public void setCampaign(Campaign campaign) {
        if (this.campaign != null && campaign != null) {
            throw new IllegalStateException("Product already assigned to a campaign");
        }
        this.campaign = campaign;
    }

    public boolean isCompositionProduct() {
        return isCompositionProduct;
    }

    public void removeCampaign(){
        this.campaign=null;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setMerchant(Merchant merchant) {
        if (isCompositionProduct) {
            throw new UnsupportedOperationException(
                    "This product is already assigned to a merchant and cannot be reassigned."
            );
        }
        this.merchant = merchant;
    }

    public void removeMerchant() {
        if (isCompositionProduct) {
            throw new UnsupportedOperationException(
                    "This product must always remain linked to its merchant and cannot be detached.");
        }
        this.merchant = null;
    }

    public Merchant getMerchant(){
        return merchant;
    }

    public void internalDestroy(){
        this.merchant=null;
    }


    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getAdvertisementFee() {
        return advertisementFee;
    }

    public boolean isAvailability() {
        return availability;
    }

}

