//
//  Servlet dodający dostawę jeśli poprawna
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "AddShipment", urlPatterns = ("/AddShipment"))
public class AddShipment extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isAdmin = false;
        if (session.getAttribute("isAdmin") != null) isAdmin = (boolean) session.getAttribute("isAdmin");

        session.setAttribute("AddShipmentErrorList", null);

        if (isAdmin) {
            //loguj
            String name = getParameter(request, "AddShipmentName");
            String cost = getParameter(request, "AddShipmentCost");

            String query = "Select * from shipments where lower(nazwa) LIKE '" + name.toLowerCase() + "'";

            boolean error = false;
            ArrayList<String> AddShipmentErrorList = new ArrayList<>();
            for (int i = 0; i < 2; ++i) {
                AddShipmentErrorList.add("");
            }

            if (request.getParameter("ShipNew") != null) {

                try {
                    System.out.println("Weryfikacja danych");

                    if (!Shipment.isGoodName(name)) {
                        error = true;
                        AddShipmentErrorList.set(0, "Wprowadź poprawną nazwę");
                    }
                    if (!Shipment.isGoodCost(cost)) {
                        error = true;
                        AddShipmentErrorList.set(1, "Wprowadź poprawną cenę (np. 19.99)");
                    }

                    ResultSet result = Server.executeQuery(query);

                    if (!error && result != null && result.next()) {
                        System.out.println("Nazwa kategorii w użyciu");
                        Server.closeDbConnection();
                        error = true;
                        AddShipmentErrorList.set(0, "Nazwa dostawy w użyciu");
                    }



                    if (!error) {
                        System.out.println("Dane ok");

                        String queryAdd = "INSERT INTO shipments " +
                                "(id, nazwa, koszt) " +
                                "VALUES (NULL, '" + name + "', " + cost + ");";

                        System.out.println(queryAdd);
                        Server.executeUpdate(queryAdd);

                        AddShipmentErrorList.set(0, "Dodano dostawę");

                    } else {
                        System.out.println("Dane złe");

                    }

                } catch (Exception err) {
                    err.printStackTrace();
                }

            }

            session.setAttribute("AddShipmentName", name);
            session.setAttribute("AddShipmentCost", cost);
            session.setAttribute("AddShipmentErrorList", AddShipmentErrorList);
            session.setAttribute("title", "Anime Shop | Dodawanie dostawy");
            request.getRequestDispatcher("addShipment.jsp").forward(request, response);

        } else {
            session.setAttribute("title", "Anime Shop | Konto");
            response.sendRedirect("Konto");
        }


    }
}
