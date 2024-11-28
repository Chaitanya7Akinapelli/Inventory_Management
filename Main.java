import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager(10);
        // ConsoleBased Interaction.
        while (true) {
            System.out.println("\n===== Inventory Management System =====");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item Quantity");
            System.out.println("3. Remove Item");
            System.out.println("4. Display Items by Category");
            System.out.println("5. Display Top K Items by Quantity");
            System.out.println("6. Merge Another Inventory");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                // To add items into in inventory
                case 1:
                    System.out.print("Enter Item ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Item Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Item Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Item Quantity: ");
                    int quantity = scanner.nextInt();
                    manager.addItem(new InventoryItem(id, name, category, quantity));
                    break;
                // To update the  no of quantity of an already existing item
                case 2:
                    System.out.print("Enter Item ID to Update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter New Quantity: ");
                    int newQuantity = scanner.nextInt();
                    manager.updateItemQuantity(updateId, newQuantity);
                    break;
                // To remove an item by id
                case 3:
                    System.out.print("Enter Item ID to Remove: ");
                    String removeId = scanner.nextLine();
                    manager.removeItem(removeId);
                    break;
                // To Find the items by category
                case 4:
                    System.out.print("Enter Category: ");
                    String searchCategory = scanner.nextLine();
                    List<InventoryItem> itemsByCategory = manager.getItemsByCategory(searchCategory);
                    if (itemsByCategory.isEmpty()) {
                        System.out.println("No items found in this category.");
                    } else {
                        itemsByCategory.forEach(System.out::println);
                    }
                    break;
                // To display the top k items
                case 5:
                    System.out.print("Enter the value of K: ");
                    int k = scanner.nextInt();
                    List<InventoryItem> topKItems = manager.getTopKItems(k);
                    if (topKItems.isEmpty()) {
                        System.out.println("No items in inventory.");
                    } else {
                        topKItems.forEach(System.out::println);
                    }
                    break;
                // Merging the inventory to the existing inventory
                case 6:
                    System.out.println("Creating a new inventory to merge...");
                    InventoryManager otherManager = new InventoryManager(10);
                    System.out.print("Enter the number of items in the new inventory: ");
                    int n = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter Item ID: ");
                        String mergeId = scanner.nextLine();
                        System.out.print("Enter Item Name: ");
                        String mergeName = scanner.nextLine();
                        System.out.print("Enter Item Category: ");
                        String mergeCategory = scanner.nextLine();
                        System.out.print("Enter Item Quantity: ");
                        int mergeQuantity = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        otherManager.addItem(new InventoryItem(mergeId, mergeName, mergeCategory, mergeQuantity));
                    }
                    manager.mergeInventory(otherManager);
                    System.out.println("Inventory merged successfully.");
                    break;

                case 7:
                    System.out.println("Completed");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
