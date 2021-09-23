//
//  Servlet logujący użytkownika.
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;


@WebServlet(name = "Login", urlPatterns = ("/Login"))
public class Login extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");

        session.setAttribute("loginError", false);

        if (!isLogged) {
            //loguj
            String email = Server.securifyString(request.getParameter("Lemail"));
            String pass = Server.securifyString(request.getParameter("Lpassword"));
            ResultSet result = null;
            if (email.length() > 0 && pass.length() > 0) {
                result = Server.executeQuery("Select * from users where email='" + email
                        + "' and password = '" + Server.hashPassword(email, pass) + "';");
            }
            try {
                if (result != null && result.next()) {
                    try {
                        String emailT = (String)result.getString(2);
                        session.setAttribute("email", emailT);

                        boolean isAdmin = (boolean)result.getBoolean(4);
                        session.setAttribute("isAdmin", isAdmin);
                        System.out.println(emailT + " " + isAdmin);

                        Server.closeDbConnection();

                        session.setAttribute("isLogged", true);

                        System.out.println("Udane logowanie");
                        session.setAttribute("title", "Anime Shop | Katalog");
                        //request.getRequestDispatcher("Konto").forward(request, response);
                        response.sendRedirect("Konto");

                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                } else {
                    throw new NullPointerException();
                }
            }
            catch (NullPointerException nullpex) {
                session.setAttribute("title", "Anime Shop | Logowanie / Rejestracja");
                session.setAttribute("loginError", true);
                System.out.println("Bledne dane");
                request.getRequestDispatcher("loginRegister.jsp").forward(request, response);
            }
            catch (Exception err) {
                err.printStackTrace();
            }
        } else {
            System.out.println("Już zalogowany...");
            session.setAttribute("title", "Anime Shop | Katalog");
            //request.getRequestDispatcher("Konto").forward(request, response);
            response.sendRedirect("Konto");
        }


    }
}
