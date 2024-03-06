package id.ac.ui.cs.advprog.eshop.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;

@WebMvcTest(PaymentController.class)
public class PaymentControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    private Payment samplePayment;

    @BeforeEach
    public void setup() {
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Order order = new Order("1", Arrays.asList(product1), System.currentTimeMillis(), "Author");
        samplePayment = new Payment(UUID.randomUUID().toString(), PaymentMethod.BANK.getValue(), order, new HashMap<>(), PaymentStatus.PENDING.getValue());
    }

    @Test
    public void getPaymentDetailForm_ShouldReturnForm() throws Exception {
        mockMvc.perform(get("/payment/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("paymentDetailForm"));
    }

    @Test
    public void getPaymentDetailById_ShouldShowPaymentDetails() throws Exception {
        given(paymentService.getPayment(samplePayment.getId())).willReturn(samplePayment);

        mockMvc.perform(get("/payment/detail/{paymentId}", samplePayment.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payment"))
                .andExpect(view().name("paymentDetail"));
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

    @Test
    public void getPaymentAdminDetailById_ShouldShowPaymentDetailsAdmin() throws Exception {
        given(paymentService.getPayment(samplePayment.getId())).willReturn(samplePayment);

        mockMvc.perform(get("/payment/admin/detail/{paymentId}", samplePayment.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payment"))
                .andExpect(view().name("paymentDetailAdmin"));
    }

    @SuppressWarnings("null")
    @Test
    public void postPaymentAdminSetStatus_ShouldSetPaymentStatus() throws Exception {
        mockMvc.perform(post("/payment/admin/set-status/{paymentId}", samplePayment.getId())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("status", PaymentStatus.SUCCESS.name())) // Use enum name for status
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/payment/admin/detail/*"));
    }


}

