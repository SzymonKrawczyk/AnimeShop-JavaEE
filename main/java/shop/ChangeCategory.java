//
//  Servlet edytujący kategorię
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "ChangeCategory", urlPatterns = ("/ChangeCategory"))
public class ChangeCategory extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isAdmin = false;
        if (session.getAttribute("isAdmin") != null) isAdmin = (boolean) session.getAttribute("isAdmin");

        session.setAttribute("ChangeCategoryErrorList", null);

        Category cat = null;

        if (isAdmin) {
            try {
                ResultSet getCategory = Server.executeQuery("Select * from category where id='" + request.getParameter("id") + "';");
                if (getCategory == null) throw new NullPointerException();
                getCategory.next();
                cat = new Category(getCategory);
            } catch (Exception err) {
                err.printStackTrace();
            }
            String name = getParameter(request, "ChangeCategoryName");


            if (request.getParameter("CatChange") != null && cat != null) {


                Category editedCategory = new Category();

                if (!name.equals("") && !name.equals("null"))   editedCategory.setName(name);

                ArrayList<String> errorList = new ArrayList<>();
                for (int i = 0; i < 1; ++i) {
                    errorList.add("");
                }

                String query = "Select * from category where lower(nazwa) LIKE '" + name.toLowerCase() + "'";

                try {
                    System.out.println("Weryfikacja danych");

                    ResultSet result = Server.executeQuery(query);

                    if (!Category.isGoodName(editedCategory.getName())) {
                        errorList.set(0, "Niepoprawna nazwa");

                    } else if (result != null && result.next() && !cat.getName().equals(editedCategory.getName())) {

                        System.out.println("Już taka kategoria istnieje...");
                        Server.closeDbConnection();
                        errorList.set(0, "Nazwa jest juz wykorzystana");

                    } else if (!cat.getName().equals(editedCategory.getName())) {

                        String queryN = "UPDATE category SET nazwa = '" + editedCategory.getName() + "' WHERE Id = " + cat.getId() + ";";
                        Server.executeUpdate(queryN);

                        errorList.set(0, "Nazwa zmieniona");
                    }




                    editedCategory.setId(cat.getId());
                    cat = editedCategory;

                    session.setAttribute("ChangeCategoryErrorList", errorList);

                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            session.setAttribute("currentCategory", cat);

            session.setAttribute("title", "Anime Shop | Zmiana danych kategorii");
            request.getRequestDispatcher("changeCategory.jsp").forward(request, response);
        } else {
            response.sendRedirect("Konto");
        }


    }
}
