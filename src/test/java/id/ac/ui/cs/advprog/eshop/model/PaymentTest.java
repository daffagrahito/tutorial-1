package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.PaymentMethod;
import enums.PaymentStatus;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(),
                this.paymentData);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(),
                this.paymentData, "SUCCESS");
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(),
                    this.paymentData,
                    "INVALID");
        });
    }

    @Test
    void testSetPaymentDataEmpty() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(),
                this.paymentData);
        this.paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testSetPaymentDataSuccess() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(),
                this.paymentData);
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment.setPaymentData(this.paymentData);
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testSetMethodValid() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(),
                this.paymentData);
        payment.setMethod(PaymentMethod.CASH_ON_DELIVERY.getValue());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), payment.getMethod());
    }

    @Test
    void testSetMethodInvalid() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(),
                this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setMethod("INVALID");
        });
    }

    @Test
    void testCreatePaymentVoucherCodeMethod() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(),
                this.paymentData);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentBankTransferMethod() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.CASH_ON_DELIVERY.getValue(),
                this.paymentData);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }
}