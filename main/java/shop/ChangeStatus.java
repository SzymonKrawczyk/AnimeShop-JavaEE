//
//  Servlet zmieniający status zamówienia
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChangeStatus", urlPatterns = ("/ChangeStatus"))
public class ChangeStatus extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");
        boolean isAdmin = false;
        if (session.getAttribute("isAdmin") != null) isAdmin = (boolean) session.getAttribute("isAdmin");


        if (isLogged && isAdmin) {
            try {

                String id = getParameter(request, "id");
                String query = "UPDATE orders SET status_Id = 3 WHERE Id = " + id + ";";
                Server.executeUpdate(query);

            } catch (Exception err) {
                err.printStackTrace();
            }

        }

        response.sendRedirect("Konto");
    }
}
