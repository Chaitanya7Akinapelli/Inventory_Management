public class InventoryItem {
    private final String id;
    private final String name;
    private final String category;
    private int quantity;

    public InventoryItem(String id, String name, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Category: %s, Quantity: %d",
                id, name, category, quantity);
    }
}
