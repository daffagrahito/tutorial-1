package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentCashOnDeliveryTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testSetPaymentData() {
        this.paymentData.put("address", "Jalan Pondok Sofura");
        this.paymentData.put("deliveryFee", "25000");
        PaymentCashOnDelivery payment = new PaymentCashOnDelivery("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyAddress() {
        this.paymentData.put("address", "");
        this.paymentData.put("deliveryFee", "25000");
        PaymentCashOnDelivery payment = new PaymentCashOnDelivery("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyDeliveryFee() {
        this.paymentData.put("address", "Jalan Pondok Sofura");
        this.paymentData.put("deliveryFee", "");
        PaymentCashOnDelivery payment = new PaymentCashOnDelivery("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyPaymentData() {
        PaymentCashOnDelivery payment = new PaymentCashOnDelivery("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}