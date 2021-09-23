package shop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int phone;
    private String zipCode;
    private String city;
    private String street;
    private String building;
    private String apartment;

    public User() {
        this.id = -1;
        this.email = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.phone = -1;
        this.zipCode = "";
        this.city = "";
        this.street = "";
        this.building = "";
        this.apartment = "";
    }

    public User(ResultSet result) throws SQLException {
        this.id = result.getInt(1);
        this.email = result.getString(2);
        this.firstName = result.getString(5);
        this.lastName = result.getString(6);
        this.phone = result.getInt(7);
        this.zipCode = result.getString(8);
        this.city = result.getString(9);
        this.street = result.getString(10);
        this.building = result.getString(11);
        this.apartment = result.getString(12);
    }

    public static boolean isGoodEmail(String toCheck) {
        return toCheck.length() >= 4 && toCheck.matches("(.*@.*\\..*)");
    }
    public static boolean isGoodPassword(String toCheck) {
        return toCheck.length() >= 8;
    }
    public static boolean isGoodFirstName(String toCheck) {
        return toCheck.length() >= 2 && !toCheck.matches(".*[0-9].*");
    }
    public static boolean isGoodLastName(String toCheck) {
        return toCheck.length() >= 2 && !toCheck.matches(".*[0-9].*");
    }
    public static boolean isGoodPhone(int toCheck) {
        return toCheck > 99999999;
    }
    public static boolean isGoodZipCode(String toCheck) {
        return toCheck.matches("([0-9][0-9]-[0-9][0-9][0-9])");
    }
    public static boolean isGoodCity(String toCheck) {
        return toCheck.length() >= 2 && !toCheck.matches(".*[0-9].*");
    }
    public static boolean isGoodStreet(String toCheck) {
        return toCheck.length() >= 2;
    }
    public static boolean isGoodBuilding(String toCheck) {
        return toCheck.length() >= 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        if (apartment == null || apartment.equals("null")) return "";
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
