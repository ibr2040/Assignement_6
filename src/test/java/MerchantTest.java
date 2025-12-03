import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantTest {
    @Test
    void testCreateProductCreatesReverseConnection() {
        Merchant m = new Merchant("12345");
        Product p = m.createProduct("Laptop", 1200);

        assertEquals(m, p.getMerchant());
        assertTrue(m.getProducts().contains(p));
    }

    @Test
    void testMultipleProductsCanBeCreated() {
        Merchant m = new Merchant("98765");

        Product p1 = m.createProduct("Phone", 800);
        Product p2 = m.createProduct("Tablet", 500);

        assertEquals(2, m.getProducts().size());
        assertEquals(m, p1.getMerchant());
        assertEquals(m, p2.getMerchant());
    }

    @Test
    void testAddExistingProductCreatesReverseConnection() {
        Merchant m = new Merchant("11111");

        Product p = new Product("Camera", 300);

        m.addExistingProduct(p);

        assertEquals(m, p.getMerchant());
        assertTrue(m.getProducts().contains(p));
    }

    @Test
    void testCannotAddExistingProductThatAlreadyBelongsToMerchant() {
        Merchant m = new Merchant("11111");

        Product p = m.createProduct("Monitor", 200);

        assertThrows(IllegalStateException.class, () -> m.addExistingProduct(p));
    }

    @Test
    void testCannotAddExistingProductBelongingToAnotherMerchant() {
        Merchant m1 = new Merchant("11111");
        Merchant m2 = new Merchant("22222");

        Product p = m1.createProduct("Router", 150);

        assertThrows(IllegalStateException.class, () -> m2.addExistingProduct(p));
    }

    @Test
    void testCannotAddNullProduct() {
        Merchant m = new Merchant("12345");

        assertThrows(IllegalArgumentException.class, () -> m.addExistingProduct(null));
    }
    @Test
    void testRemoveProductRemovesReverseConnection() {
        Merchant m = new Merchant("33333");
        Product p = m.createProduct("Keyboard", 50);

        m.removeProduct(p);

        assertNull(p.getMerchant());
        assertFalse(m.getProducts().contains(p));
    }

    @Test
    void testCannotRemoveNullProduct() {
        Merchant m = new Merchant("33333");

        assertThrows(IllegalArgumentException.class, () -> m.removeProduct(null));
    }

    @Test
    void testCannotRemoveProductNotBelongingToMerchant() {
        Merchant m1 = new Merchant("33333");
        Merchant m2 = new Merchant("44444");

        Product p = m2.createProduct("Headphones", 80);

        assertThrows(IllegalArgumentException.class, () -> m1.removeProduct(p));
    }

    @Test
    void testProductCannotExistWithoutMerchant() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(null, "Shoe", 40));
    }

    @Test
    void testMerchantBankAccountCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Merchant(""));
        assertThrows(IllegalArgumentException.class, () -> new Merchant(null));
    }

    @Test
    void testGetProductsReturnsUnmodifiableList() {
        Merchant m = new Merchant("55555");
        m.createProduct("Mouse",20);

        var list = m.getProducts();

        assertThrows(UnsupportedOperationException.class, list::clear);
    }
}