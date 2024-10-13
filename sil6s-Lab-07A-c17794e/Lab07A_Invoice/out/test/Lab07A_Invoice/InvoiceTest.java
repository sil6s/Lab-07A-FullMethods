import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    private Customer customer;
    private Product product1;
    private Product product2;
    private LineItem lineItem1;
    private LineItem lineItem2;
    private Invoice invoice;

    @BeforeEach
    public void setUp() {
        // Initialize objects before each test
        customer = new Customer("John Doe", "123 Elm Street");
        product1 = new Product("Laptop", 1500.00);
        product2 = new Product("Mouse", 25.00);
        lineItem1 = new LineItem(product1, 1); // 1 Laptop
        lineItem2 = new LineItem(product2, 2); // 2 Mice
        invoice = new Invoice(customer);
    }

    @Test
    public void testAddLineItem() {
        // Test adding a line item
        invoice.addLineItem(lineItem1);
        invoice.addLineItem(lineItem2);

        assertEquals(2, invoice.getLineItems().size());  // Invoice should have 2 line items
        assertEquals(product1, invoice.getLineItems().get(0).getProduct());  // First item should be Laptop
        assertEquals(2, invoice.getLineItems().get(1).getQuantity());  // Second item quantity should be 2
    }

    @Test
    public void testGetTotalAmountDue() {
        // Test calculating total amount due
        invoice.addLineItem(lineItem1);
        invoice.addLineItem(lineItem2);

        double expectedTotal = (1500.00 * 1) + (25.00 * 2);  // Laptop + 2 Mice
        assertEquals(expectedTotal, invoice.getTotalAmountDue(), 0.01);  // Assert total amount is correct
    }

    @Test
    public void testGetCustomerDetails() {
        // Test getting customer information
        assertEquals("John Doe", invoice.getCustomer().getName());
        assertEquals("123 Elm Street", invoice.getCustomer().getAddress());
    }

    @Test
    public void testSetCustomer() {
        // Test changing the customer
        Customer newCustomer = new Customer("Jane Smith", "456 Oak Street");
        invoice.setCustomer(newCustomer);

        assertEquals("Jane Smith", invoice.getCustomer().getName());
        assertEquals("456 Oak Street", invoice.getCustomer().getAddress());
    }
}
