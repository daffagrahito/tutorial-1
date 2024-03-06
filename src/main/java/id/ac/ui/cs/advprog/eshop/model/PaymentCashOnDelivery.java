package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;

import java.util.Map;

public class PaymentCashOnDelivery extends Payment {
    public PaymentCashOnDelivery(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    private static final String DELIVERY_FEE = "deliveryFee";
    private static final String ADDRESS = "address";

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty() || paymentData.get(ADDRESS).isEmpty() || paymentData.get(ADDRESS) == null
                || paymentData.get(DELIVERY_FEE).isEmpty() || paymentData.get(DELIVERY_FEE) == null) {
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            if (paymentData.get(DELIVERY_FEE).matches("\\d+")) {
                this.paymentData = paymentData;
                this.status = PaymentStatus.SUCCESS.getValue();
            } else {
                this.status = PaymentStatus.REJECTED.getValue();
            }
        }
    }
}