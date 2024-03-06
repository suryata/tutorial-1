package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, PaymentStatus.PENDING.getValue());
    }

    private void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order can't null");
        } else {
            this.order = order;
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment");
        }
    }

    protected void setPaymentData(Map<String, String>paymentData) {
        if (PaymentMethod.contains(this.method)) {
            throw new IllegalArgumentException("Payment method is not specified");
        } else {
            this.paymentData = null;
        }
    }
}