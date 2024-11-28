import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {
    // Data structure to maintain inventory -> HashMap
    private final Map<String, InventoryItem> inventoryMap = new HashMap<>();
    // Data structure to maintain the categories of items from inventory
    private final Map<String, TreeSet<InventoryItem>> categoryMap = new HashMap<>();
    private final int restockThreshold;

    public InventoryManager(int restockThreshold) {
        this.restockThreshold = restockThreshold;
    }

    // Add a new item
    public void addItem(InventoryItem item) {
        if (inventoryMap.containsKey(item.getId())) {
            System.out.println("Item with ID " + item.getId() + " already exists.");
            return;
        }
        inventoryMap.put(item.getId(), item);
        categoryMap.computeIfAbsent(item.getCategory(), k -> new TreeSet<>(Comparator.comparingInt(InventoryItem::getQuantity).reversed()))
                .add(item);
        checkRestockNotification(item);
    }

    // Update item quantity
    public void updateItemQuantity(String id, int newQuantity) {
        InventoryItem item = inventoryMap.get(id);
        if (item == null) {
            System.out.println("Item with ID " + id + " not found.");
            return;
        }
        categoryMap.get(item.getCategory()).remove(item);
        item.setQuantity(newQuantity);
        categoryMap.get(item.getCategory()).add(item);
        checkRestockNotification(item);
    }

    // Remove item
    public void removeItem(String id) {
        InventoryItem item = inventoryMap.remove(id);
        if (item != null) {
            categoryMap.get(item.getCategory()).remove(item);
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    // Retrieve items by category
    public List<InventoryItem> getItemsByCategory(String category) {
        return categoryMap.getOrDefault(category, new TreeSet<>())
                .stream()
                .collect(Collectors.toList());
    }

    // Top K items by quantity
    public List<InventoryItem> getTopKItems(int k) {
        return inventoryMap.values().stream()
                .sorted(Comparator.comparingInt(InventoryItem::getQuantity).reversed())
                .limit(k)
                .collect(Collectors.toList());
    }

    // Merge another inventory
    public void mergeInventory(InventoryManager otherInventory) {
        for (InventoryItem item : otherInventory.inventoryMap.values()) {
            if (inventoryMap.containsKey(item.getId())) {
                InventoryItem currentItem = inventoryMap.get(item.getId());
                if (item.getQuantity() > currentItem.getQuantity()) {
                    updateItemQuantity(item.getId(), item.getQuantity());
                }
            } else {
                addItem(item);
            }
        }
    }

    // Check for restock
    private void checkRestockNotification(InventoryItem item) {
        if (item.getQuantity() < restockThreshold) {
            System.out.println("Restock needed for item: " + item);
        }
    }
}
