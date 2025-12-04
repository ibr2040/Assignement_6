import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertiserCampaignTest {
    @Test
    public void testAdvertiserDeleteLastCampaign() {
        Merchant mer = new Merchant("1234");
        Product pro = new Product(mer, "TV", 500);
        Campaign com = new Campaign("ahhhhhh",List.of(pro));
        Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",
                com);
        assertThrows(IllegalArgumentException.class, () -> {adv.cancelCampaign(com);});
    }
    @Test
    public void testAdvertiserAddCampaign() {
        assertThrows(IllegalArgumentException.class, () ->{Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",null);});
    }
    @Test
    public void testAdvertiserGetCampaigns() {
        Merchant mer = new Merchant("1234");
        Product pro = new Product(mer, "TV", 500);
        Campaign com = new Campaign("ahhhhhh",List.of(pro));
        Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",
                com);
        assertThrows(IllegalArgumentException.class, () -> {adv.addCampaign(com);});
    }
    @Test
    public void testAdvertiserGetCampaign() {
        Merchant mer = new Merchant("1234");
        Product pro = new Product(mer, "TV", 500);
        Product pro1 = new Product(mer, "TV", 500);
        Campaign com = new Campaign("ahhhhhh",List.of(pro));
        Campaign com1 = new Campaign("ahhhhhh",List.of(pro1));
        Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",
                com);
        assertDoesNotThrow(() -> {adv.addCampaign(com1);});
    }
    @Test
    public void testAdvertiserUpdateCampaign() {
        Merchant mer = new Merchant("1234");
        Product pro = new Product(mer, "TV", 500);
        Product pro1 = new Product(mer, "TV", 500);
        Campaign com = new Campaign("ahhhhhh",List.of(pro));
        Campaign com1 = new Campaign("ahhhhhh",List.of(pro1));
        Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",
                com);
        adv.addCampaign(com1);
        assertDoesNotThrow(() -> {adv.cancelCampaign(com1);});
    }
}
