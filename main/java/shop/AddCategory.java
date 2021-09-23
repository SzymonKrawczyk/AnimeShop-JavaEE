//
//  Servlet dodający kategorię, jeśli się zgadza
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "AddCategory", urlPatterns = ("/AddCategory"))
public class AddCategory extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isAdmin = false;
        if (session.getAttribute("isAdmin") != null) isAdmin = (boolean) session.getAttribute("isAdmin");

        session.setAttribute("AddCategoryErrorList", null);

        if (isAdmin) {
            //loguj
            String name = getParameter(request, "AddCategoryName");

            String query = "Select * from category where lower(nazwa) LIKE '" + name.toLowerCase() + "'";

            boolean error = false;
            ArrayList<String> AddCategoryErrorList = new ArrayList<>();
            for (int i = 0; i < 1; ++i) {
                AddCategoryErrorList.add("");
            }

            if (request.getParameter("CatNew") != null) {

                try {
                    System.out.println("Weryfikacja danych");

                    if (!Category.isGoodName(name)) {
                        error = true;
                        AddCategoryErrorList.set(0, "Wprowadź poprawną nazwę");
                    }
                    ResultSet result = Server.executeQuery(query);

                    if (!error && result != null && result.next()) {
                        System.out.println("Nazwa kategorii w użyciu");
                        Server.closeDbConnection();
                        error = true;
                        AddCategoryErrorList.set(0, "Nazwa kategorii w użyciu");
                    }



                    if (!error) {
                        System.out.println("Dane ok");

                        String queryAdd = "INSERT INTO category " +
                                "(id, nazwa) " +
                                "VALUES (NULL, '" + name + "');";

                        System.out.println(queryAdd);
                        Server.executeUpdate(queryAdd);

                        AddCategoryErrorList.set(0, "Dodano kategorię");

                    } else {
                        System.out.println("Dane złe");

                    }

                } catch (Exception err) {
                    err.printStackTrace();
                }

            }

            session.setAttribute("AddCategoryName", name);
            session.setAttribute("AddCategoryErrorList", AddCategoryErrorList);
            session.setAttribute("title", "Anime Shop | Dodawanie kategorii");
            request.getRequestDispatcher("addCategory.jsp").forward(request, response);

        } else {
            session.setAttribute("title", "Anime Shop | Konto");
            response.sendRedirect("Konto");
        }


    }
}
