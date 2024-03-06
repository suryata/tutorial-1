package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class BankTransferPayment extends Payment {
    public BankTransferPayment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }
    
    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.get("bankName").isBlank() ||
            paymentData.get("referenceCode").isBlank()) {
            throw new IllegalArgumentException();
        }
        
        this.paymentData = paymentData;
    }
    public BankTransferPayment(String id, Order order, String method, Map<String, String >paymentData) {
        super(id, method, order, paymentData);
    }

}