package shop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {

    private int id;
    private String name;

    public Category() {
        this.id = -1;
        this.name = null;
    }

    public Category(ResultSet result) throws SQLException {
        this.id = result.getInt(1);
        this.name = result.getString(2);
    }


    public static boolean isGoodName (String toCheck) {
        return toCheck.length() >= 2;
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
}
