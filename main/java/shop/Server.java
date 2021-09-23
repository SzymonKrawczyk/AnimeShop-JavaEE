package shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeMap;

public class Server {


    private static Connection connect = null;
    private static String url = "jdbc:mysql://localhost:3306/aplikacja?useLegacyDatetimeCode=false&serverTimezone=CET&useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root", pswd = "";

    public static ResultSet executeQuery(String query) {
        try {

            connect = DriverManager.getConnection(url, user, pswd);
            Statement statement = connect.createStatement();

            return statement.executeQuery(query);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public static void closeDbConnection () {
        try {
            connect.close();
        } catch (Exception err){
            err.printStackTrace();
        }
    }

    public static void executeUpdate(String query) {
        try {
            connect = DriverManager.getConnection(url, user, pswd);
            Statement statement = connect.createStatement();

            statement.executeUpdate(query);

            closeDbConnection();

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static int executeUpdate(String query, int mode) {
        try {
            connect = DriverManager.getConnection(url, user, pswd);
            Statement statement = connect.createStatement();

            statement.executeUpdate(query, mode);

            ResultSet rs = statement.getGeneratedKeys();
            int lastid = 0;
            if(rs.next()) { lastid = rs.getInt(1); }
            return lastid;

        } catch (Exception err) {
            err.printStackTrace();
            return 0;
        }
    }

    public  static String securifyString(String input) {
        char[] tempTab = input.toCharArray();

        for (int i = 0; i < tempTab.length; i++) {
            switch (tempTab[i]) {
                case ';': tempTab[i] = '_';
                    break;
                case '\'': tempTab[i] = 'á';
                    break;

            }
        }

        return new String(tempTab);
    }

    public static String hashPassword(String email, String password) {
        String in = password.charAt(0) + email + password.substring(1);

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] haslo_w_bitach = digest.digest(in.getBytes(StandardCharsets.UTF_8));

            // z bitow na hexy
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : haslo_w_bitach) {
                stringBuilder.append(String.format("%02x", b));
            }
            //System.out.println(stringBuilder.toString());
            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            return "null";
        }
    }





    public static void addToCart(HttpServletRequest request, int product_id, int amount) {

        TreeMap<Integer, Integer> cart = null;
        HttpSession session = request.getSession();

        cart = session.getAttribute("cart") != null ? (TreeMap <Integer, Integer>) session.getAttribute("cart") : new TreeMap <>();

        cart.put(product_id, cart.get(product_id) != null ? amount+cart.get(product_id) : amount);

        session.setAttribute("cart", cart);
    }

    public static void setinCart(HttpServletRequest request, int product_id, int amount) {

        TreeMap<Integer, Integer> cart = null;
        HttpSession session = request.getSession();

        cart = session.getAttribute("cart") != null ? (TreeMap <Integer, Integer>) session.getAttribute("cart") : new TreeMap <>();

        cart.remove(product_id);
        cart.put(product_id, amount);

        session.setAttribute("cart", cart);
    }

    public static void removeFromCart(HttpServletRequest request, int product_id) {

        TreeMap<Integer, Integer> cart = null;
        HttpSession session = request.getSession();

        if (session.getAttribute("cart") != null) {
            cart = (TreeMap <Integer, Integer>)session.getAttribute("cart");
            cart.remove(product_id);
        }

        session.setAttribute("cart", cart);
    }

    public static TreeMap<Integer, Integer> getCart(HttpServletRequest request) {

        HttpSession session = request.getSession();
        return session.getAttribute("cart") != null ? (TreeMap <Integer, Integer>) session.getAttribute("cart") : null;
    }

    public static int getCartSize(HttpServletRequest request) {
        int cartSize = 0;
        TreeMap<Integer, Integer> cart = Server.getCart(request);
        if (cart != null) {
            for (Integer i: cart.values()) {
                cartSize += i;
            }
        }
        return cartSize;
    }

    public static String newLineToBr (String input) {
        String[] strings = input.split("\n");
        String output = "";
        for (String temp: strings) {
            output = output + (temp + "<br>");
        }
        return output;
    }
    public static String brToNewLine (String input) {
        String[] strings = input.split("(<br>)");
        String output = "";
        for (String temp: strings) {
            output = output + (temp + "\n");
        }
        return output;
    }

}
