package service;

import entity.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GroceryItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public GroceryItem addGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    public List<GroceryItem> viewGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public void removeGroceryItem(Long itemId) {
        groceryItemRepository.deleteById(itemId);
    }

    public GroceryItem updateGroceryItemDetails(Long itemId, GroceryItem updatedItem) {
        Optional<GroceryItem> optionalItem = groceryItemRepository.findById(itemId);

        if (optionalItem.isPresent()) {
            GroceryItem existingItem = optionalItem.get();
            existingItem.setName(updatedItem.getName());
            existingItem.setPrice(updatedItem.getPrice());
            groceryItemRepository.save(existingItem);
            return existingItem;
        }
        return null;
    }

    public void manageInventory(Long itemId, int quantity) {
        Optional<GroceryItem> optionalItem = groceryItemRepository.findById(itemId);

        if (optionalItem.isPresent()) {
            GroceryItem existingItem = optionalItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            groceryItemRepository.save(existingItem);
        }
    }
}

