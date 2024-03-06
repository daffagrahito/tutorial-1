package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
import enums.PaymentMethod;

import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    OrderRepository orderRepository;
    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(2);
        products.add(product);

        orders = new ArrayList<>();

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order);

        payments = new ArrayList<>();

        Map<String, String> paymentData1 = Map.of("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(),
                paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = Map.of("address", "Jalan Pondok Sofura", "deliveryFee", "25000");
        Payment payment2 = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                PaymentMethod.CASH_ON_DELIVERY.getValue(),
                paymentData2);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Order order = orders.get(0);
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER_CODE.getValue(),
                Map.of("voucherCode", "ESHOP1234ABC5678"));
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        verify(orderRepository, times(1)).save(order);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        verify(orderRepository, times(1)).save(order);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = payments.get(0);
        Order order = orders.get(0);
        doReturn(order).when(orderRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payment, "MEOW");
        });

        verify(orderRepository, times(0)).save(any(Order.class));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        verify(paymentRepository, times(1)).findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();
        assertEquals(payments, result);
    }

    @Test
    void testGetAllPaymentsEmpty() {
        doReturn(new ArrayList<Payment>()).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();
        assertEquals(new ArrayList<Payment>(), result);
    }
}