//
//  Servlet składający zamówienie
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeMap;

@WebServlet(name = "Kupno", urlPatterns = ("/Kupno"))
public class Kupno extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();

        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");


        String info = (String)session.getAttribute("info");

        Shipment selectedShipment = (Shipment)session.getAttribute("selectedShipment");


        if (isLogged && selectedShipment != null) {

            User currentUser = new User();
            try {
                ResultSet getUser = Server.executeQuery("Select * from users where email='" + session.getAttribute("email") + "';");
                getUser.next();
                currentUser = new User(getUser);
            } catch (Exception err) {
                err.printStackTrace();
            }

            try {

                if (info ==  null || info.equals("") || info.equals("null")) info = "NULL";
                else info = "'" + info + "'";

                String shipment_info = "";


                shipment_info += currentUser.getEmail() + "<br>" +
                                 currentUser.getPhone()+ "<br>" +
                                 currentUser.getFirstName() + " " + currentUser.getLastName() + "<br>" +
                                 currentUser.getZipCode() + " " + currentUser.getCity() + "<br>" +
                                 currentUser.getStreet() + " " + currentUser.getBuilding() + " " + currentUser.getApartment();


                String addOrderQuery = "INSERT INTO orders (Id, Id_user, Id_shipment, dostawa_informacje, timestamp, informacje_dodatkowe, status_Id) " +
                        "VALUES (NULL, '" + currentUser.getId() + "', '" + selectedShipment.getId() + "', '" + shipment_info + "', current_timestamp(), " + info + ", '2')";
                int lastId = Server.executeUpdate(addOrderQuery, Statement.RETURN_GENERATED_KEYS);
                System.out.println("last id: " + lastId);

                TreeMap<ProductItem, Integer> cartList = (TreeMap<ProductItem, Integer>)session.getAttribute("cartList");

                for (ProductItem item : cartList.keySet()) {

                    String addOrderListQuery = "INSERT INTO orders_list (Id, Id_order, Id_product, ilosc, cena_sztuka) " +
                            "VALUES (NULL, '" + lastId + "', '" + item.getId() + "', '" + cartList.get(item) + "', '" + item.getPrice() + "');";
                    Server.executeUpdate(addOrderListQuery);

                    Server.removeFromCart(request, item.getId());
                }

                session.setAttribute("cartlist", null);
                session.setAttribute("selectedShipment", null);
                session.setAttribute("info", null);


            } catch (Exception err) {
                err.printStackTrace();
            }

            response.sendRedirect("Konto");

        } else {

            request.getRequestDispatcher("zamowienie.jsp").forward(request, response);
        }


    }
}
