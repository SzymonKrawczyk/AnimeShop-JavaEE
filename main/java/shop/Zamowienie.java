//
//  Servlet wytwarzający "podsumowanie" z koszyka
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "Zamowienie", urlPatterns = ("/Zamowienie"))
public class Zamowienie extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");
        int dostawa_id = 0;
        try {
            dostawa_id = Integer.parseInt(getParameter(request, "shipment_id"));
        } catch (Exception err){
            err.printStackTrace();
        }

        System.out.println("Dostawa id: " + dostawa_id);

        if (isLogged && dostawa_id != 0) {
            String info = getParameter(request, "info");

            Shipment selectedShipment = null;

            String queryShipment = "select * from shipments where id = '" + dostawa_id + "';";

            try {
                ResultSet resultShipment = Server.executeQuery(queryShipment);
                if (resultShipment != null && resultShipment.next()){

                    selectedShipment = new Shipment(resultShipment);
                }
            } catch (Exception err) {
                err.printStackTrace();
            }

            session.setAttribute("info", info);
            session.setAttribute("selectedShipment", selectedShipment);

            request.getRequestDispatcher("zamowienie.jsp").forward(request, response);

        } else {

            response.sendRedirect("Konto");
        }


    }
}
