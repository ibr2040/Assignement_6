import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Test
    void testTransactionConstructorInitializesFields() {
        Transaction t = new Transaction("BankA", "PersonB", 150.0, "USD");

        assertEquals("BankA", t.getReceiverBankInformation());
        assertEquals("PersonB", t.getPayerInformation());
        assertEquals(150.0, t.getValue());
        assertEquals("USD", t.getCurrency());
        assertNotNull(t.getDateOfTransaction());
    }

    @Test
    void testChildrenListInitiallyEmpty() {
        Transaction t = new Transaction("A", "B", 10, "USD");
        assertTrue(t.getChildren().isEmpty());
    }

    @Test
    void testParentInitiallyNull() {
        Transaction t = new Transaction("A", "B", 10, "USD");
        assertNull(t.getParent());
    }
}