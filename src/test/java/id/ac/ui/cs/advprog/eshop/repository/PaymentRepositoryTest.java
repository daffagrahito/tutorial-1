package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(),
                paymentData);
        payments.add(payment1);

        Payment payment2 = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                PaymentMethod.CASH_ON_DELIVERY.getValue(),
                paymentData);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Payment newPayment = new Payment(payment.getId(), PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> findResult = paymentRepository.findAll();
        assertEquals(payments.size(), findResult.size());
        for (int i = 0; i < payments.size(); i++) {
            assertEquals(payments.get(i).getId(), findResult.get(i).getId());
            assertEquals(payments.get(i).getMethod(), findResult.get(i).getMethod());
            assertEquals(payments.get(i).getStatus(), findResult.get(i).getStatus());
            assertSame(payments.get(i).getPaymentData(), findResult.get(i).getPaymentData());
        }
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testSaveNewPayment() {
        Payment payment = new Payment("abc58e9f-2c39-460e-8860-71af6af63bd7", PaymentMethod.VOUCHER_CODE.getValue(),
                paymentData);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById("abc58e9f-2c39-460e-8860-71af6af63bd7");
        assertEquals("abc58e9f-2c39-460e-8860-71af6af63bd7", result.getId());
        assertEquals("abc58e9f-2c39-460e-8860-71af6af63bd7", findResult.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), findResult.getMethod());
    }

    @Test
    void testSaveExistingPayment() {
        Payment payment = payments.get(0);
        payment.setMethod(PaymentMethod.CASH_ON_DELIVERY.getValue());
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payments.get(0).getId(), result.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), findResult.getMethod());
    }

    @Test
    void testFindByIdNotFound() {
        Payment findResult = paymentRepository.findById("abc58e9g-1d64-460e-8860-71bf6zx63bd7");
        assertNull(findResult);
    }

    @Test
    void testFindAllEmpty() {
        paymentRepository = new PaymentRepository();
        List<Payment> findResult = paymentRepository.findAll();
        assertTrue(findResult.isEmpty());
    }
}