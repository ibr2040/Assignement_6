import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class CartProductTest {
    @Test
    public void TestAddProductToCart() {
        Cart cart = new Cart();
        Product p = new Product("123",123);
        cart.addProduct(p);
        assertTrue(p.getCartsContainingProduct().contains(cart));
        assertTrue(cart.getProductsInTheCart().contains(p));
    }
    @Test
    public void TestRemoveProductFromCart(){
        Cart cart = new Cart();
        Product p = new Product("123",123);
        cart.addProduct(p);
        cart.removeProduct(p);
        assertFalse(p.getCartsContainingProduct().contains(cart));
        assertFalse(cart.getProductsInTheCart().contains(p));
    }
    @Test
    public void TestAddCartFromProduct(){
        Cart cart = new Cart();
        Product p = new Product("123",123);
        p.addToCart(cart);
        assertTrue(p.getCartsContainingProduct().contains(cart));
        assertTrue(cart.getProductsInTheCart().contains(p));
    }
    @Test
    public void TestRemoveCartFromProduct(){
        Cart cart = new Cart();
        Product p = new Product("123",123);
        p.addToCart(cart);
        p.removeFromCart(cart);
        assertFalse(p.getCartsContainingProduct().contains(cart));
        assertFalse(cart.getProductsInTheCart().contains(p));
    }
    @Test
    public void TestAddingAlreadyExistingProductInCart(){
        Cart cart = new Cart();
        Product p = new Product("123",123);
        Product p2 = p;
        cart.addProduct(p);
        cart.addProduct(p2);
        assertEquals(p.getCartsContainingProduct().size(),1);
        assertEquals(cart.getProductsInTheCart().size(),1);
    }
    @Test
    public void TestAddingAlreadyExistingCartInProduct(){
        Cart cart = new Cart();
        Cart cart2 = cart;
        Product p = new Product("123",123);
        p.addToCart(cart);
        p.addToCart(cart2);
        assertEquals(p.getCartsContainingProduct().size(),1);
        assertEquals(cart.getProductsInTheCart().size(),1);
    }
}
