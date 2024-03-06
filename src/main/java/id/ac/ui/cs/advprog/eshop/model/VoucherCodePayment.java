package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class VoucherCodePayment extends Payment {
    public VoucherCodePayment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }

    public VoucherCodePayment(String id, Order order, String method, Map<String, String >paymentData) {
        super(id, method, order, paymentData);
    }
    
    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        int numericsCount = 0;
        for (int i = 0; i < paymentData.get("voucherCode").length(); i++) {
            if (Character.isDigit(paymentData.get("voucherCode").charAt(i))) {
                numericsCount += 1;
            }
        }

        if (paymentData.get("voucherCode").length() != 16 ||
            !paymentData.get("voucherCode").startsWith("ESHOP") ||
            numericsCount != 8) {
            throw new IllegalArgumentException();
        }
        
        this.paymentData = paymentData;
    }
}