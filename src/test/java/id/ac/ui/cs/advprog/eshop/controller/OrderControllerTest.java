package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private List<Order> orders;

    @BeforeEach
    public void setup() {
        Order order = new Order("1", Arrays.asList(new Product("P1", "Product 1", 100.0)), System.currentTimeMillis(), "Author");
        orders = Arrays.asList(order);
    }

    @Test
    public void getCreateOrderForm_ShouldReturnForm() throws Exception {
        mockMvc.perform(get("/order/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createOrderForm"));
    }

    @Test
    public void getHistoryForm_ShouldReturnForm() throws Exception {
        mockMvc.perform(get("/order/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("historyForm"));
    }

    @Test
    public void postHistoryForm_ShouldReturnOrders() throws Exception {
        String author = "Author";
        given(orderService.findAllByAuthor(author)).willReturn(orders);

        mockMvc.perform(post("/order/history")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", author))
                .andExpect(status().isOk())
                .andExpect(model().attribute("orders", orders));
    }

    @Test
    public void getPayOrder_ShouldReturnPaymentPage() throws Exception {
        String orderId = "1";
        given(orderService.findById(orderId)).willReturn(orders.get(0));

        mockMvc.perform(get("/order/pay/{orderId}", orderId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name("paymentOrderPage"));
    }

    @Test
    public void postPayOrder_ShouldProcessPayment() throws Exception {
        String orderId = "1";
        given(orderService.updateStatus(orderId, "PAID")).willReturn(orders.get(0));

        mockMvc.perform(post("/order/pay/{orderId}", orderId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/order/paymentSuccess?paymentId=" + orders.get(0).getId()));
    }
}

