public class Product {

    private String image;
    private double price;
    private String title;
    private String category;
    private String description;
    private double advertisementFee;
    private boolean availability;

    private Campaign campaign;

    public Product(String image,
                   double price,
                   String title,
                   String category,
                   String description,
                   double advertisementFee,
                   boolean availability,
                   Campaign campaign) {

        if (campaign == null) {
            throw new IllegalArgumentException("Product must belong to a Campaign (multiplicity 1..1)");
        }

        this.image = image;
        this.price = price;
        this.title = title;
        this.category = category;
        this.description = description;
        this.advertisementFee = advertisementFee;
        this.availability = availability;

        campaign.addProduct(this);
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

        if (this.campaign == newCampaign) return;

        this.campaign.removeProduct(this);
        newCampaign.addProduct(this);
    }

    public double getAdvertisementFee() {
        return advertisementFee;
    }
}
