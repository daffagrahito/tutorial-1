package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;

import java.util.Map;

public class PaymentVoucher extends Payment {

    public PaymentVoucher(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentVoucher(String id, String method, Map<String, String> paymentData, String status) {
        super(id, method, paymentData, status);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        final String VOUCHER_CODE_KEY = "voucherCode";

        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            int numericalChar = 0;
            for (char c : paymentData.get(VOUCHER_CODE_KEY).toCharArray()) {
                if (Character.isDigit(c)) {
                    numericalChar++;
                }
            }
            this.paymentData = paymentData;
            if (paymentData.get(VOUCHER_CODE_KEY).length() == 16
                    && paymentData.get(VOUCHER_CODE_KEY).startsWith("ESHOP")
                    && numericalChar == 8) {
                this.status = PaymentStatus.SUCCESS.getValue();
            } else {
                this.status = PaymentStatus.REJECTED.getValue();
            }
        }
    }
}