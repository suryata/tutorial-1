package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductQuantity(2);
        product.setProductName("Sampo Cap Bambang");
        products.add(product);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", 
            products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
            products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
            products, 1708570000L, "Bambang Sudrajat");
        orders.add(order3);

        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        Payment voucher = new Payment("4074c620-013b-4414-b085-08f7b089408c", "VOUCHER", orders.get(0), paymentDataVoucher, PaymentStatus.PENDING.getValue());
        payments.add(voucher);

        Map<String, String> paymentDataBankTransfer = new HashMap<>();
        paymentDataBankTransfer.put("bankName", "BBCA");
        paymentDataBankTransfer.put("referenceCode", "0123456789");
        Payment bankTransfer = new Payment("ec556e96-10a5-4d47-a068-d45c6fca71c0", "BANK", orders.get(0), paymentDataBankTransfer, PaymentStatus.PENDING.getValue());
        payments.add(bankTransfer);
    }
    
    @Test
    void testAddPaymentFail() {
        Payment payment1 = payments.get(1);
        paymentRepository.save(payment1);

        Payment payment2 = new Payment(payment1.getId(), payment1.getMethod(), payment1.getOrder(), 
            payment1.getPaymentData(), PaymentStatus.PENDING.getValue());
        assertThrows(IllegalStateException.class, () ->
            paymentRepository.save(payment2));
    }

    @Test
    void testAddPaymentSuccess() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }


    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(payments.size(), allPayments.size());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertNotNull(findResult);
        assertSame(payments.get(1).getOrder(), findResult.getOrder());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

}