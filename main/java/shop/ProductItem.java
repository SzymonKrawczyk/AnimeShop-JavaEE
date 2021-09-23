package shop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductItem implements Comparable<ProductItem> {

    private int id;
    private int id_category;
    private String name;
    private String anime;
    private String size;
    private String manufacturer;
    private double price;
    private String description;
    private String mediaPath;

    public ProductItem() {
        this.id = -1;
        this.id_category = -1;
        this.name = "";
        this.anime = "";
        this.size = "";
        this.manufacturer = "";
        this.price = -1;
        this.description = "";
        this.mediaPath = "";
    }

    public ProductItem(int id, int id_category, String name, String anime, String size, String manufacturer, double price, String description, String mediaPath) {
        this.id = id;
        this.id_category = id_category;
        this.name = name;
        this.anime = anime;
        this.size = size;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
        this.mediaPath = mediaPath;
    }

    public ProductItem(ResultSet result) throws SQLException {
        this.id =  result.getInt(1);
        this.id_category = result.getInt(3);
        this.name =  result.getString(2);
        this.anime =  result.getString(4);
        this.size =  result.getString(5);
        this.manufacturer =  result.getString(6);
        this.price =  result.getDouble(7);
        this.description = result.getString(8);
        this.mediaPath =  result.getString(9);
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", id_category=" + id_category +
                ", name='" + name + '\'' +
                ", anime='" + anime + '\'' +
                ", size=" + size +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", mediaPath='" + mediaPath + '\'' +
                '}';
    }



    public static boolean isGoodName (String toCheck) {
        return toCheck.length() >= 2;
    }
    public static boolean isGoodAnime (String toCheck) {
        return toCheck.length() >= 2;
    }
    public static boolean isGoodSize (String toCheck) {
        return toCheck.length() >= 2;
    }
    public static boolean isGoodManufacturer (String toCheck) {
        return toCheck.length() >= 2;
    }
    public static boolean isGoodPrice (String toCheck) {
        return toCheck.matches("(\\d+\\.\\d\\d)");
    }
    public static boolean isGoodPrice (Double toCheck) {
        return isGoodPrice(String.valueOf(toCheck).replace(',', '.'));
    }
    public static boolean isGoodDescription (String toCheck) {
        return toCheck.length() >= 10;
    }
    public static boolean isGoodMediaPath (String toCheck) {
        return toCheck.length() >= 2;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    @Override
    public int compareTo(ProductItem o) {
        return this.getId() - o.getId();
    }

    // Two employees are equal if their IDs are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem prod = (ProductItem) o;
        return id == prod.id;
    }
}
