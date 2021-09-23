//
//  Servlet edytujący dostawę
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "ChangeShipment", urlPatterns = ("/ChangeShipment"))
public class ChangeShipment extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isAdmin = false;
        if (session.getAttribute("isAdmin") != null) isAdmin = (boolean) session.getAttribute("isAdmin");

        session.setAttribute("ChangeShipmentErrorList", null);

        Shipment ship = null;

        if (isAdmin) {
            try {
                ResultSet getShipment = Server.executeQuery("Select * from shipments where id='" + request.getParameter("id") + "';");
                if (getShipment == null) throw new NullPointerException();
                getShipment.next();
                ship = new Shipment(getShipment);
            } catch (Exception err) {
                err.printStackTrace();
            }
            String name = getParameter(request, "ChangeShipmentName");
            String costS = getParameter(request, "ChangeShipmentCost");
            double cost = -1.0;
            boolean errCost = false;
            try {
                cost = Double.parseDouble(costS);
            } catch (Exception err) {
                errCost = true;
            }


            if (request.getParameter("ShipChange") != null && ship != null && !errCost) {


                Shipment editedShipment = new Shipment();

                if (!name.equals("") && !name.equals("null"))   editedShipment.setName(name);
                editedShipment.setCost(cost);

                ArrayList<String> errorList = new ArrayList<>();
                for (int i = 0; i < 2; ++i) {
                    errorList.add("");
                }

                String query = "Select * from shipments where lower(nazwa) LIKE '%" + name.toLowerCase() + "%'";

                try {
                    System.out.println("Weryfikacja danych");

                    ResultSet result = Server.executeQuery(query);

                    if (!Shipment.isGoodName(editedShipment.getName())) {
                        errorList.set(0, "Niepoprawna nazwa");

                    } else if (result != null && result.next() && !ship.getName().equals(editedShipment.getName())) {

                        System.out.println("Już taka dostawa istnieje...");
                        Server.closeDbConnection();
                        errorList.set(0, "Nazwa jest juz wykorzystana");

                    } else if (!ship.getName().equals(editedShipment.getName())) {

                        String queryN = "UPDATE shipments SET nazwa = '" + editedShipment.getName() + "' WHERE Id = " + ship.getId() + ";";
                        Server.executeUpdate(queryN);

                        errorList.set(0, "Nazwa zmieniona");
                    }


                    if (!Shipment.isGoodCost(editedShipment.getCost())) {
                        errorList.set(1, "Niepoprawny koszt");

                    } else if (ship.getCost() != editedShipment.getCost()) {

                        String queryC = "UPDATE shipments SET cost = '" + editedShipment.getCost() + "' WHERE Id = " + ship.getId() + ";";
                        Server.executeUpdate(queryC);

                        errorList.set(2, "Koszt zmieniony");
                    }

                    editedShipment.setId(ship.getId());
                    ship = editedShipment;

                    session.setAttribute("ChangeShipmentErrorList", errorList);

                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            session.setAttribute("currentShipment", ship);

            session.setAttribute("title", "Anime Shop | Zmiana danych dostawy");
            request.getRequestDispatcher("changeShipment.jsp").forward(request, response);
        } else {
            response.sendRedirect("Konto");
        }


    }
}
