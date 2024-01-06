package service;

import entity.GroceryItem;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GroceryItemRepository;
import repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public List<GroceryItem> viewAvailableItems() {
        return groceryItemRepository.findAll();
    }

    @Transactional
    public Order placeOrder(List<GroceryItem> items) {
        Order order = new Order();
        order.setItems(items);
        orderRepository.save(order);

        // Update inventory quantities after the order is placed
        for (GroceryItem item : items) {
            GroceryItem existingItem = groceryItemRepository.findById(item.getId()).orElse(null);
            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() - item.getQuantity());
                groceryItemRepository.save(existingItem);
            }
        }

        return order;
    }
}
