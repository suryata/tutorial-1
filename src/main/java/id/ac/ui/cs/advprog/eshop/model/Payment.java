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

    private void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        this.order = order;
    }
    
    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, PaymentStatus.PENDING.getValue());
    }

    
    protected void setPaymentData(Map<String, String> paymentData) {
        if (PaymentMethod.contains(this.method)) {
            throw new IllegalArgumentException(
                "Unable to assign payment data specific to a method when the payment method is not specified"
            );
        }
        this.paymentData = null;
    }

    public void setStatus(String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        this.status = status;
    }

}