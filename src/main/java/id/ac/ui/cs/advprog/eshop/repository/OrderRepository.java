package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private List<Order> orderData = new ArrayList<>();

    public Order save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        int i = 0;
        for (Order savedOrder : orderData) {
            if (savedOrder.getId().equals(order.getId())) {
                orderData.remove(i);
                orderData.add(i, order);
                return order;
            }
            i++;
        }

        orderData.add(order);
        return order;
    }

    public Order findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        for (Order savedOrder : orderData) {
            if (savedOrder.getId().equals(id)) {
                return savedOrder;
            }
        }
        return null;
    }

    public List<Order> findAllByAuthor(String author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        List<Order> result = new ArrayList<>();
        for (Order savedOrder : orderData) {
            if (savedOrder.getAuthor().equals(author)) {
                result.add(savedOrder);
            }
        }
        return result;
    }
}
