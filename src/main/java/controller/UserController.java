package controller;

import entity.GroceryItem;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/view-available-items")
    public ResponseEntity<List<GroceryItem>> viewAvailableItems() {
        return ResponseEntity.ok(userService.viewAvailableItems());
    }

    @PostMapping("/place-order")
    public ResponseEntity<Order> placeOrder(@RequestBody List<GroceryItem> items) {
        return ResponseEntity.ok(userService.placeOrder(items));
    }
}

