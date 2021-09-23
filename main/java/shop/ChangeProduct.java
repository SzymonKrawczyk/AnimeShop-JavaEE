//
//  Servlet edytujący produkt
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "ChangeProduct", urlPatterns = ("/ChangeProduct"))
public class ChangeProduct extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isAdmin = false;
        if (session.getAttribute("isAdmin") != null) isAdmin = (boolean) session.getAttribute("isAdmin");

        session.setAttribute("ChangeProductErrorList", null);

        ProductItem currentProduct = null;

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








            try {
                ResultSet getProduct = Server.executeQuery("Select * from products where id='" + request.getParameter("id") + "';");
                if (getProduct == null) throw new NullPointerException();
                getProduct.next();
                currentProduct = new ProductItem(getProduct);
            } catch (Exception err) {
                err.printStackTrace();
            }

            String name = getParameter(request, "ChangeProductName");
            String category = getParameter(request, "ChangeProductCategory");
            String anime = getParameter(request, "ChangeProductAnime");
            String size = getParameter(request, "ChangeProductSize");
            String manufacturer = getParameter(request, "ChangeProductManufacturer");
            String price = getParameter(request, "ChangeProductPrice");
            String description = getParameter(request, "ChangeProductDescription");
            String mediaPath = getParameter(request, "ChangeProductMediaPath");

            double priceD = -1.0;
            int catI = -1;
            boolean errD = false;
            try {
                priceD = Double.parseDouble(price);
                catI = Integer.parseInt(category);
            } catch (Exception err) {
                errD = true;
            }


            if (request.getParameter("ProdChange") != null && currentProduct != null && !errD) {


                ProductItem editedProduct = new ProductItem();

                if (!name.equals("") && !name.equals("null"))   editedProduct.setName(name);
                editedProduct.setId_category(catI);
                if (!anime.equals("") && !anime.equals("null"))   editedProduct.setAnime(anime);
                if (!size.equals("") && !size.equals("null"))   editedProduct.setSize(size);
                if (!manufacturer.equals("") && !manufacturer.equals("null"))   editedProduct.setManufacturer(manufacturer);
                editedProduct.setPrice(priceD);
                description = Server.newLineToBr(description);
                if (!description.equals("") && !description.equals("null"))   editedProduct.setDescription(description);
                if (!mediaPath.equals("") && !mediaPath.equals("null"))   editedProduct.setMediaPath(mediaPath);




                ArrayList<String> errorList = new ArrayList<>();
                for (int i = 0; i < 8; ++i) {
                    errorList.add("");
                }










                String query = "Select * from products where lower(nazwa) LIKE '" + name.toLowerCase() + "'";

                try {
                    System.out.println("Weryfikacja danych");

                    ResultSet result = Server.executeQuery(query);

                    if (!ProductItem.isGoodName(editedProduct.getName())) {
                        errorList.set(0, "Niepoprawna nazwa");

                    } else if (result != null && result.next() && !currentProduct.getName().equals(editedProduct.getName())) {

                        System.out.println("Już taki produkt istnieje...");
                        Server.closeDbConnection();
                        errorList.set(0, "Nazwa jest juz wykorzystana");

                    } else if (!currentProduct.getName().equals(editedProduct.getName())) {

                        String queryN = "UPDATE products SET nazwa = '" + editedProduct.getName() + "' WHERE Id = " + currentProduct.getId() + ";";
                        Server.executeUpdate(queryN);

                        errorList.set(0, "Nazwa zmieniona");
                    }


                    try {
                        if (!(editedProduct.getId_category() > 0)) {
                            errorList.set(1, "Wprowadź poprawną kategorię");
                        } else {
                            ResultSet resultCat = Server.executeQuery("select * from category where id = " + editedProduct.getId_category()  + ';');
                            if (!(resultCat != null && resultCat.next())) throw new Exception();

                            if (currentProduct.getId_category() != editedProduct.getId_category()) {

                                String queryC = "UPDATE products SET id_category = '" + editedProduct.getId_category() + "' WHERE Id = " + currentProduct.getId() + ";";
                                Server.executeUpdate(queryC);

                                errorList.set(1, "Kategoria zmieniona");
                            }
                        }
                    } catch (Error err) {
                        errorList.set(1, "Wprowadź poprawną kategorię");
                    }



                    if (!ProductItem.isGoodAnime(editedProduct.getAnime())) {
                        errorList.set(2, "Niepoprawna nazwa anime");

                    } else if (!currentProduct.getAnime().equals(editedProduct.getAnime())) {

                        String queryA = "UPDATE products SET anime = '" + editedProduct.getAnime() + "' WHERE Id = " + currentProduct.getId() + ";";
                        Server.executeUpdate(queryA);

                        errorList.set(2, "Nazwa anime zmieniona");
                    }



                    if (!ProductItem.isGoodSize(editedProduct.getSize())) {
                        errorList.set(3, "Niepoprawna wielkość");

                    } else if (!currentProduct.getSize().equals(editedProduct.getSize())) {

                        String queryS = "UPDATE products SET wielkosc = '" + editedProduct.getSize() + "' WHERE Id = " + currentProduct.getId() + ";";
                        Server.executeUpdate(queryS);

                        errorList.set(3, "Wielkość zmieniona");
                    }



                    if (!ProductItem.isGoodManufacturer(editedProduct.getManufacturer())) {
                        errorList.set(4, "Niepoprawna nazwa producenta");

                    } else if (!currentProduct.getManufacturer().equals(editedProduct.getManufacturer())) {

                        String queryM = "UPDATE products SET producent = '" + editedProduct.getManufacturer() + "' WHERE Id = " + currentProduct.getId() + ";";
                        Server.executeUpdate(queryM);

                        errorList.set(4, "Nazwa producenta zmieniona");
                    }



                    if (!ProductItem.isGoodPrice(editedProduct.getPrice())) {
                        errorList.set(5, "Niepoprawna cena");

                    } else if (currentProduct.getPrice() != editedProduct.getPrice()) {

                        String queryC = "UPDATE products SET cena = '" + editedProduct.getPrice() + "' WHERE Id = " + currentProduct.getId() + ";";
                        Server.executeUpdate(queryC);

                        errorList.set(5, "Cena zmieniona");
                    }




                    if (!ProductItem.isGoodDescription(editedProduct.getDescription())) {
                        errorList.set(6, "Niepoprawny opis");

                    } else if (!currentProduct.getDescription().equals(editedProduct.getDescription())) {

                        String queryM = "UPDATE products SET opis = '" + editedProduct.getDescription() + "' WHERE Id = " + currentProduct.getId() + ";";
                        Server.executeUpdate(queryM);

                        errorList.set(6, "Opis zmieniony");
                    }


                    if (!ProductItem.isGoodMediaPath(editedProduct.getMediaPath())) {
                        errorList.set(7, "Niepoprawna ścieżka do folderu zawierajacego zdjęcia");

                    } else if (!currentProduct.getMediaPath().equals(editedProduct.getMediaPath())) {

                        String queryM = "UPDATE products SET media = '" + editedProduct.getMediaPath() + "' WHERE Id = " + currentProduct.getId() + ";";
                        Server.executeUpdate(queryM);

                        errorList.set(7, "Ścieżka do folderu zawierajacego zdjęcia zmieniona");
                    }






                    editedProduct.setId(currentProduct.getId());
                    currentProduct = editedProduct;

                    session.setAttribute("ChangeProductErrorList", errorList);

                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            session.setAttribute("currentProduct", currentProduct);

            session.setAttribute("title", "Anime Shop | Zmiana danych produktu");
            request.getRequestDispatcher("changeProduct.jsp").forward(request, response);
        } else {
            response.sendRedirect("Konto");
        }


    }
}
