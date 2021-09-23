package shop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Shipment {

    private int id;
    private String name;
    private double cost;

    public Shipment() {
        this.id = -1;
        this.name = "";
        this.cost = -1.0;
    }
    public Shipment(ResultSet result) throws SQLException {
        this.id = result.getInt(1);
        this.name = result.getString(2);
        this.cost = result.getDouble(3);
    }



    public static boolean isGoodName (String toCheck) {
        return toCheck.length() >= 2;
    }
    public static boolean isGoodCost (String toCheck) {
        return toCheck.matches("(\\d+\\.\\d\\d)");
    }
    public static boolean isGoodCost (Double toCheck) {
        return isGoodCost(String.valueOf(toCheck).replace(',', '.'));
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
