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

    public Product(String image,
                   double price,
                   String title,
                   String category,
                   String description,
                   double advertisementFee,
                   boolean availability,
                   Merchant merchant) {

        if (merchant == null) {
            throw new IllegalArgumentException("Product must belong to a Merchant (composition)");
        }

        this.image = image;
        this.price = price;
        this.title = title;
        this.category = category;
        this.description = description;
        this.advertisementFee = advertisementFee;
        this.availability = availability;

        this.merchant=merchant;
        merchant.addExistingProduct(this);
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaignInternal(Campaign campaign) {
        this.campaign = campaign;
    }

    public void changeCampaign(Campaign newCampaign) {
        if (newCampaign == null) {
            throw new IllegalArgumentException("Product must always belong to a campaign");
        }

        if (this.campaign == null) {
            newCampaign.addProduct(this);
            return;
        }


        if (this.campaign == newCampaign) {
            return;
        }

        this.campaign.removeProduct(this);
        newCampaign.addProduct(this);
    }

    public double getAdvertisementFee() {
        return advertisementFee;
    }

    public void changeMerchant(Merchant newMerchant) {
        throw new UnsupportedOperationException("Cannot reassign Product to another Merchant in composition");
    }

    public void internalDelete() {
        this.merchant = null;
    }
}
