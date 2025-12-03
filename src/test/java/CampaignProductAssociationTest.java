import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CampaignProductAssociationTest {
    @Test
    void testReverseConnectionCreatedOnAdd() {
        Product p1 = new Product("Laptop", 1200);
        Product p2 = new Product("Mouse", 20);

        Campaign c = new Campaign("TechSale", List.of(p1));
        c.addProduct(p2);

        assertEquals(c, p2.getCampaign());
        assertTrue(c.getProducts().contains(p2));
    }

    @Test
    void testReverseConnectionRemovedOnRemove() {
        Product p1 = new Product("Laptop", 1200);
        Product p2 = new Product("Mouse", 20);

        Campaign c = new Campaign("TechSale", List.of(p1, p2));
        c.removeProduct(p1);

        assertNull(p1.getCampaign());
        assertFalse(c.getProducts().contains(p1));
    }

    @Test
    void testProductCannotBelongToTwoCampaigns() {
        Product p = new Product("Speaker", 200);
        Campaign c1 = new Campaign("MusicSale", List.of(p));
        Campaign c2 = new Campaign("BlackFriday", List.of(new Product("TV", 500)));

        assertThrows(IllegalStateException.class, () -> c2.addProduct(p));
    }
}
