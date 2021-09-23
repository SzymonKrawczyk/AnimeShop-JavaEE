//
//  Servlet przenoszący do strony o nas
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Onas", urlPatterns = ("/Onas"))
public class Onas extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();

        session.setAttribute("title", "Anime Shop | O nas");
        request.getRequestDispatcher("onas.jsp").forward(request, response);

    }
}

