package id.ac.ui.cs.advprog.eshop.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;

@WebMvcTest(PaymentController.class)
public class PaymentControlTest {
    Map<String, String> paymentData;
    Order order;
    List<Product> products;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    private Payment samplePayment;

    @BeforeEach
    public void setup() {
                this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        this.products.add(product1);
        order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6", 
        products, 1708560000L, "Bambang Suryanto");
        samplePayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData, PaymentStatus.SUCCESS.getValue());
    }
    

    @Test
    public void getPaymentDetailForm_ShouldReturnForm() throws Exception {
        mockMvc.perform(get("/payment/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("paymentDetailForm"));
    }


    @Test
    public void getPaymentsAdminList_ShouldShowAllPayments() throws Exception {
        List<Payment> payments = Arrays.asList(samplePayment);
        given(paymentService.getAllPayment()).willReturn(payments);

        mockMvc.perform(get("/payment/admin/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payments"))
                .andExpect(view().name("paymentListAdmin"));
    }
}