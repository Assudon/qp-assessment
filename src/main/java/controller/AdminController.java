package controller;

import entity.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/add-item")
    public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem groceryItem) {
        return ResponseEntity.ok(adminService.addGroceryItem(groceryItem));
    }

    @GetMapping("/view-items")
    public ResponseEntity<List<GroceryItem>> viewGroceryItems() {
        return ResponseEntity.ok(adminService.viewGroceryItems());
    }

    @DeleteMapping("/remove-item/{itemId}")
    public ResponseEntity<Void> removeGroceryItem(@PathVariable Long itemId) {
        adminService.removeGroceryItem(itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-item/{itemId}")
    public ResponseEntity<GroceryItem> updateGroceryItemDetails(@PathVariable Long itemId,
                                                                @RequestBody GroceryItem updatedItem) {
        GroceryItem updated = adminService.updateGroceryItemDetails(itemId, updatedItem);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/manage-inventory/{itemId}")
    public ResponseEntity<Void> manageInventory(@PathVariable Long itemId,
                                                @RequestParam int quantity) {
        adminService.manageInventory(itemId, quantity);
        return ResponseEntity.ok().build();
    }
}

