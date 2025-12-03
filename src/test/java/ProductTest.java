import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void testProductConstructorValid() {
        Product p = new Product("Phone", 800);
        assertEquals("Phone", p.getTitle());
        assertEquals(800, p.getPrice());
    }

    @Test
    void testProductTitleCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Product("", 100));
        assertThrows(IllegalArgumentException.class, () -> new Product(null, 100));
    }

    @Test
    void testSetCampaignSetsCorrectly() {
        Product p = new Product("Phone", 800);
        Campaign c = new Campaign("Sale", List.of(p));

        assertEquals(c, p.getCampaign());
    }

    @Test
    void testRemoveCampaign() {
        Product p = new Product("Phone", 800);
        Campaign c = new Campaign("SpringSale", List.of(p));

        p.removeCampaign();

        assertNull(p.getCampaign());
    }
}