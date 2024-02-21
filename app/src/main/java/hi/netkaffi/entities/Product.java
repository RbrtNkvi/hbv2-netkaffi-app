package hi.netkaffi.entities;

public class Product {

    private String name;
    private String type;
    private int price;
    private boolean deleted;

    public Product() {
    }

    public Product(String name, String type, int price, boolean deleted) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
