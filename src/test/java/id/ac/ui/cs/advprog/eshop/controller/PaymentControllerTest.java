package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    private Payment samplePayment;

    @BeforeEach
    public void setup() {
        Order sampleOrder = new Order("orderId", Arrays.asList(), System.currentTimeMillis(), "Author");
        samplePayment = new Payment(UUID.randomUUID().toString(), "Credit Card", sampleOrder, new HashMap<>());
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
                .param("status", "SUCCESS"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/payment/admin/detail/*")); 
    }

}

