package shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TreeMap;

public class Order {
    private int id;
    private int userId;
    private int shipmentId;
    private String shipmentName;
    private double shipmentCost;
    private String shipmentInfo;
    private String additionalInfo;
    private TreeMap<ProductItem, Integer> productList;
    private Timestamp timestamp;
    private int statusId;
    private String statusName;

    public Order() {
        this.id = -1;
        this.userId = -1;
        this.shipmentId = -1;
        this.shipmentName = null;
        this.shipmentCost = -1.0;
        this.shipmentInfo = null;
        this.additionalInfo = null;
        this.productList = null;
        this.timestamp = null;
        this.statusId = -1;
        this.statusName = null;
    }

    public Order(ResultSet result) throws SQLException {
        this.id = result.getInt(1);
        this.userId = result.getInt(2);
        this.shipmentId = result.getInt(3);
        this.shipmentName = "";
        this.shipmentCost = -1;
        this.shipmentInfo = result.getString(4);
        this.additionalInfo = result.getString(6);
        this.productList = new TreeMap<>();
        this.timestamp = result.getTimestamp(5);
        this.statusId = result.getInt(7);
    }


    public void addToList(ProductItem product, int amount) {
        productList.put(product, amount);
    }

    public String getTimestampAsString() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(String shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }

    public String getAdditionalInfo() {
        if (additionalInfo == null || additionalInfo.equals("null")) {
            return "<i>brak</i>";
        } else {
            return additionalInfo;
        }
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public TreeMap<ProductItem, Integer> getProductList() {
        return productList;
    }

    public void setProductList(TreeMap<ProductItem, Integer> productList) {
        this.productList = productList;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public double getShipmentCost() {
        return shipmentCost;
    }

    public void setShipmentCost(double shipmentCost) {
        this.shipmentCost = shipmentCost;
    }

    public String calculateTotal() {
        double total = 0.0;
        for(ProductItem item: productList.keySet()) {
            total += (item.getPrice() * productList.get(item));
        }
        total += shipmentCost;
        return String.format("%.2f", total).replace(',', '.');
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", shipmentId=" + shipmentId +
                ", shipmentName='" + shipmentName + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", shipmentCost='" + shipmentCost + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
