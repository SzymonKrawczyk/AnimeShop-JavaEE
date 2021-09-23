//
//  Servlet wczytujacy katalog lub pojedyńczy produkt
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "Katalog", urlPatterns = ("/Katalog"))
public class Katalog extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();

        session.setAttribute("title", "Anime Shop | Katalog");

        ArrayList<ProductItem> productList = new ArrayList<>();
        ArrayList<String> catList = new ArrayList<>();


        String selectedId = request.getParameter("id");
        String addToCart = request.getParameter("addToCart");
        String selectedCategory = request.getParameter("category");
        if (selectedCategory != null && selectedCategory.equals("")) selectedCategory = null;
        String searchText = request.getParameter("searchText");
        System.out.println("Id:" + selectedId);
        System.out.println("AddToCart:" + addToCart);
        System.out.println("Category:" + selectedCategory);
        System.out.println("searchText:" + searchText);

        if (selectedId != null && !selectedId.equals("null") && !selectedId.equals("")) {

            ProductItem selectedProduct = null;

            try {

                String query = "select * from products where id="+selectedId;
                ResultSet result = Server.executeQuery(query);
                if (result != null && result.next()) {

                    selectedProduct = new ProductItem(result);

                    File file = new File("_UwU_shop_media/" + selectedProduct.getMediaPath());
                    String[] fileListBuf = file.list();
                    ArrayList<String> fileList = new ArrayList<>();
                    if (fileListBuf != null) {
                        fileList.addAll(Arrays.asList(fileListBuf));
                    }
                    System.out.println("Ilosc zdjec: " + fileList.size());
                    session.setAttribute("selectedProductMediaSize", fileList.size());
                }

                if (addToCart != null && !addToCart.equals("null") && !addToCart.equals("") && Integer.parseInt(addToCart) > 0) {
                    Server.addToCart(request, Integer.parseInt(selectedId),Integer.parseInt(addToCart));
                }


            } catch (Exception err) {
                err.printStackTrace();
            }

            session.setAttribute("selectedProduct", selectedProduct);
            request.getRequestDispatcher("selectedProduct.jsp").forward(request, response);

        } else {


            try {

                String query = "select * from products p";

                if (selectedCategory != null) {
                    selectedCategory = Server.securifyString(selectedCategory);
                    query += ", category c where p.id_category = c.id and c.nazwa = '" + selectedCategory + "'";
                }
                if (selectedCategory == null && searchText != null) {
                    query += " where";
                }
                if (selectedCategory != null && searchText != null) {
                    query += " and";
                }
                if (searchText != null) {
                    searchText = Server.securifyString(searchText);
                    query += " (lower(p.nazwa) LIKE '%" + searchText.toLowerCase() + "%' or lower(p.anime) LIKE '%" + searchText.toLowerCase() + "%' or lower(p.producent) LIKE '%" + searchText.toLowerCase() + "%')";
                }
                query += " order by p.nazwa asc";

                ResultSet result = Server.executeQuery(query);
                while (result != null && result.next()) {

                    productList.add(new ProductItem(result));
                }

                String query2 = "select * from category";
                ResultSet result2 = Server.executeQuery(query2);
                while (result2 != null && result2.next()) {

                    catList.add(result2.getString(2));
                }


            } catch (Exception err) {
                err.printStackTrace();
            }

            session.setAttribute("catList", catList);
            session.setAttribute("productList", productList);
            request.getRequestDispatcher("katalog.jsp").forward(request, response);

        }

    }
}

