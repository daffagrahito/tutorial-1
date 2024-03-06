package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentVoucherTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testSetPaymentDataWithEmptyPaymentData() {
        PaymentVoucher payment = new PaymentVoucher("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @ParameterizedTest
    @CsvSource({
            "'SHOP1234ABC5678', 'REJECTED'",
            "'ESHOP1234ABC567', 'REJECTED'",
            "'ESHOPABCDEFGH', 'REJECTED'",
            "'ESHOP1234ABC5678', 'SUCCESS'",
            "'ESHOP1234ABC56789', 'REJECTED'",
            "'ESHOP1234ABC5', 'REJECTED'",
            "'ESHOP12345678901234', 'REJECTED'",
    })
    void testSetPaymentDataWithVariousVoucherCodes(String voucherCode, String expectedStatus) {
        this.paymentData.put("voucherCode", voucherCode);
        PaymentVoucher payment = new PaymentVoucher("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(expectedStatus, payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithoutEshop() {
        this.paymentData.put("voucherCode", "1234ABC5678");
        PaymentVoucher payment = new PaymentVoucher("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithoutEightNumericalCharacters() {
        this.paymentData.put("voucherCode", "ESHOPABCDEFGH");
        PaymentVoucher payment = new PaymentVoucher("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithNullVoucherCode() {
        this.paymentData.put("voucherCode", null);
        PaymentVoucher payment = new PaymentVoucher("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        assertThrows(NullPointerException.class, () -> payment.setPaymentData(this.paymentData));
    }
}