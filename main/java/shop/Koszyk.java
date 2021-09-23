//
//  Servlet zarzadzajacy koszykiem i skladaniem zamowien
//

package shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

@WebServlet(name = "Koszyk", urlPatterns = ("/Koszyk"))
public class Koszyk extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("title", "Anime Shop | Koszyk");

        String toDelete = request.getParameter("delete");
        System.out.println("delete:" + toDelete);

        try {
            if (toDelete != null && !toDelete.equals("") && !toDelete.equals("null")) {
                Server.removeFromCart(request, Integer.parseInt(toDelete));
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

        TreeMap<ProductItem, Integer> cartList = null;
        ArrayList<Shipment> shipmentList = null;

        TreeMap<Integer, Integer> cart = Server.getCart(request);

        if (cart != null && cart.size() > 0) {

            try{

                cartList = new TreeMap<>();

                for(Integer id: cart.keySet()) {
                    if (cart.get(id) > 0) {


                        String query = "select * from products where id=" + id;

                        ResultSet result = Server.executeQuery(query);
                        if (result != null && result.next()){

                            ProductItem product = new ProductItem(result);
                            cartList.put(product, cart.get(id));
                            System.out.println("dodajmy do listy produkt " + product.getName() + " | " + cart.get(id));
                        } else {
                            throw new NullPointerException();
                        }

                    }
                }

                shipmentList = new ArrayList<>();

                String queryShipment = "select * from shipments";

                ResultSet resultShipment = Server.executeQuery(queryShipment);
                while (resultShipment != null && resultShipment.next()){

                    shipmentList.add(new Shipment(resultShipment));
                }




            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        session.setAttribute("shipmentList", shipmentList);
        session.setAttribute("cartList", cartList);
        request.getRequestDispatcher("koszyk.jsp").forward(request, response);

    }
}

