package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.PaymentMethod;
import enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class VoucherCodePaymentTest {
    List<Product> products;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        Product product2 = new Product();
        product2.setProductID("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        products.add(product2);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b", 
            products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
            products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
            products, 1708570000L, "Bambang Sudrajat");
        orders.add(order3);
    }

    @Test
    void testCreatePaymentSuccessfulVoucher() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new VoucherCodePayment("4074c620-013b-4414-b085-08f7b089408c", orders.get(1), PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        assertSame(orders.get(1), payment.getOrder());
        assertEquals("4074c620-013b-4414-b085-08f7b089408c", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(paymentDataVoucher, payment.getPaymentData());
    }

    
    @Test
    void testCreatePaymentVoucherFailed16Length() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC567");
        
        assertThrows(IllegalArgumentException.class, ()-> { 
            new VoucherCodePayment("ec556e96-10a5-4d47-a068-d45c6fca71c0",orders.get(1),
            PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }
    
    @Test
    void testCreatePaymentVoucherFailedESHOPStart() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "SHOPE1234ABC5678");
        
        assertThrows(IllegalArgumentException.class, ()-> { 
            new VoucherCodePayment("ec556e96-10a5-4d47-a068-d45c6fca71c0",orders.get(1),
            PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }
    
    @Test
    void testCreatePaymentVoucherWithStatus() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");

        VoucherCodePayment VoucherCodePayment = new VoucherCodePayment("ec556e96-10a5-4d47-a068-d45c6fca71c0",
            orders.get(0), PaymentMethod.VOUCHER.getValue(), paymentDataVoucher, PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(0), VoucherCodePayment.getOrder());
        assertEquals("ec556e96-10a5-4d47-a068-d45c6fca71c0", VoucherCodePayment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), VoucherCodePayment.getMethod());
        assertEquals(paymentDataVoucher, VoucherCodePayment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), VoucherCodePayment.getStatus());
    }
    
    @Test
    void testCreatePaymentVoucherFailed8Numerical() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABCD678");
        
        assertThrows(IllegalArgumentException.class, ()-> {
            new VoucherCodePayment("ec556e96-10a5-4d47-a068-d45c6fca71c0",orders.get(1),
                PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }
}