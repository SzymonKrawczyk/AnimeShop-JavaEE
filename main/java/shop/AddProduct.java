//
//  Servlet dodający produkt jeśli poprawny
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "AddProduct", urlPatterns = ("/AddProduct"))
public class AddProduct extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isAdmin = false;
        if (session.getAttribute("isAdmin") != null) isAdmin = (boolean) session.getAttribute("isAdmin");

        session.setAttribute("AddProductErrorList", null);

        if (isAdmin) {

            ArrayList<Category> catList = new ArrayList<>();

            String queryCat = "select * from category";

            try {

                ResultSet resultCat = Server.executeQuery(queryCat);
                while (resultCat != null && resultCat.next()){

                    catList.add(new Category(resultCat));
                }
            } catch (Exception err) {
                err.printStackTrace();
            }

            session.setAttribute("catList", catList);







            String name = getParameter(request, "AddProductName");
            String category = getParameter(request, "AddProductCategory");
            String anime = getParameter(request, "AddProductAnime");
            String size = getParameter(request, "AddProductSize");
            String manufacturer = getParameter(request, "AddProductManufacturer");
            String price = getParameter(request, "AddProductPrice");
            String description = getParameter(request, "AddProductDescription");
            String mediaPath = getParameter(request, "AddProductMediaPath");


            String query = "Select * from products where lower(nazwa) LIKE '" + name.toLowerCase() + "'";

            boolean error = false;
            ArrayList<String> AddProductErrorList = new ArrayList<>();
            for (int i = 0; i < 8; ++i) {
                AddProductErrorList.add("");
            }

            if (request.getParameter("ProdNew") != null) {


                try {
                    System.out.println("Weryfikacja danych");

                    if (!ProductItem.isGoodName(name)) {
                        error = true;
                        AddProductErrorList.set(0, "Wprowadź poprawną nazwę");
                    }

                    ResultSet result = Server.executeQuery(query);

                    if (!error && result != null && result.next()) {
                        System.out.println("Nazwa produktu w użyciu");
                        Server.closeDbConnection();
                        error = true;
                        AddProductErrorList.set(0, "Nazwa produktu w użyciu");
                    }

                    try {
                        if (!(Integer.parseInt(category) > 0)) {
                            error = true;
                            AddProductErrorList.set(1, "Wprowadź poprawną kategorię");
                        } else {
                            ResultSet resultCat = Server.executeQuery("select * from category where id = " + category + ';');
                            if (!(resultCat != null && resultCat.next())) throw new Exception();
                        }
                    } catch (Error err) {
                        error = true;
                        AddProductErrorList.set(1, "Wprowadź poprawną kategorię");
                    }

                    if (!ProductItem.isGoodAnime(anime)) {
                        error = true;
                        AddProductErrorList.set(2, "Wprowadź poprawną nazwę anime");
                    }

                    if (!ProductItem.isGoodSize(size)) {
                        error = true;
                        AddProductErrorList.set(3, "Wprowadź poprawny rozmiar");
                    }

                    if (!ProductItem.isGoodManufacturer(manufacturer)) {
                        error = true;
                        AddProductErrorList.set(4, "Wprowadź poprawną nazwę producenta");
                    }

                    if (!ProductItem.isGoodPrice(price)) {
                        error = true;
                        AddProductErrorList.set(5, "Wprowadź poprawną cenę");
                    }

                    if (!ProductItem.isGoodDescription(description)) {
                        error = true;
                        AddProductErrorList.set(6, "Wprowadź poprawny opis");
                    }

                    if (!ProductItem.isGoodMediaPath(mediaPath)) {
                        error = true;
                        AddProductErrorList.set(7, "Wprowadź poprawny folder zawierajacy zdjecia");
                    }




                    if (!error) {
                        System.out.println("Dane ok");

                        description = Server.newLineToBr(description);

                        String queryAdd = "INSERT INTO products (Id, nazwa, id_category, anime, wielkosc, producent, cena, opis, media) VALUES " +
                                "(NULL, '" + name + "', '" + category + "', '" + anime + "', '" + size + "', '" + manufacturer + "', '" + price + "', '" + description + "', '" + mediaPath + "')";


                        System.out.println(queryAdd);
                        Server.executeUpdate(queryAdd);

                        AddProductErrorList.set(0, "Dodano Produkt");

                    } else {
                        System.out.println("Dane złe");

                    }

                } catch (Exception err) {
                    err.printStackTrace();
                }

            }

            session.setAttribute("AddProductErrorList", AddProductErrorList);
            session.setAttribute("title", "Anime Shop | Dodawanie produktu");
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);

        } else {
            session.setAttribute("title", "Anime Shop | Konto");
            response.sendRedirect("Konto");
        }


    }
}
