//
//  Servlet wczytujacy konto, jak ktoś nie jest zalogowany to przesyła na logowanie / rejestrację
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "Konto", urlPatterns = ("/Konto"))
public class Konto extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");
        boolean isAdmin  = false;
        if (session.getAttribute("isAdmin") != null) isAdmin = (boolean) session.getAttribute("isAdmin");


        boolean logout = false;
        if (request.getParameter("logout") != null && request.getParameter("logout").equals("true")) logout = true;
        System.out.println("Logout:" + logout);



        if (logout) {

            session.setAttribute("currentUser", null);
            session.setAttribute("email", false);
            session.setAttribute("isLogged", false);
            session.setAttribute("isAdmin", false);
            System.out.println("wyloguj");
            response.sendRedirect("Konto");

        } else if (isLogged) {

            session.setAttribute("title", "Anime Shop | Konto");

            User currentUser = new User();
            try {
                ResultSet getUser = Server.executeQuery("Select * from users where email='" + session.getAttribute("email") + "';");
                getUser.next();
                currentUser = new User(getUser);
            } catch (Exception err) {
                err.printStackTrace();
            }
            session.setAttribute("email", currentUser.getEmail());
            session.setAttribute("currentUser", currentUser);



            if (isAdmin) {
                System.out.println("admin");

                ArrayList<Shipment> shipments = new ArrayList<>();
                ArrayList<ProductItem> products = new ArrayList<>();
                ArrayList<Category> categories = new ArrayList<>();
                ArrayList<Order> orders = new ArrayList<>();

                try {

                    String queryShipments = "select * from shipments";
                    ResultSet resultShipments = Server.executeQuery(queryShipments);
                    while (resultShipments != null && resultShipments.next()) {
                        shipments.add(new Shipment(resultShipments));
                    }

                    String queryProducts = "select * from products";
                    ResultSet resultProducts = Server.executeQuery(queryProducts);
                    while (resultProducts != null && resultProducts.next()) {
                        products.add(new ProductItem(resultProducts));
                    }

                    String queryCategories = "select * from category";
                    ResultSet resultCategories = Server.executeQuery(queryCategories);
                    while (resultCategories != null && resultCategories.next()) {
                        categories.add(new Category(resultCategories));
                    }





                    String queryOrders = "select * from orders order by status_Id asc, id asc;";
                    ResultSet resultOrders = Server.executeQuery(queryOrders);

                    while (resultOrders != null && resultOrders.next()) {
                        // kazde kolejne zamowienie

                        Order newOrder = new Order(resultOrders);

                        String qShipment = "SELECT * FROM shipments WHERE Id = " + newOrder.getShipmentId() + ";";
                        ResultSet rShipment = Server.executeQuery(qShipment);
                        if (rShipment != null && rShipment.next()) {
                            newOrder.setShipmentName(rShipment.getString(2));
                            newOrder.setShipmentCost(rShipment.getDouble(3));
                        }

                        String qStatus = "SELECT * FROM orders_status WHERE Id = " + newOrder.getStatusId() + ";";
                        ResultSet rStatus = Server.executeQuery(qStatus);
                        if (rStatus != null && rStatus.next()) {
                            newOrder.setStatusName(rStatus.getString(2));
                        }



                        String queryOrderItems = "SELECT * FROM orders_list o, products p WHERE p.Id = o.Id_product and o.Id_order = " + newOrder.getId() + ";";
                        ResultSet resultOrderItems = Server.executeQuery(queryOrderItems);

                        while (resultOrderItems != null && resultOrderItems.next()) {

                            ProductItem newProduct = new ProductItem();
                            newProduct.setId(resultOrderItems.getInt(5+1));
                            newProduct.setId_category(resultOrderItems.getInt(5+3));
                            newProduct.setName(resultOrderItems.getString(5+2));
                            newProduct.setAnime(resultOrderItems.getString(5+4));
                            newProduct.setSize(resultOrderItems.getString(5+5));
                            newProduct.setManufacturer(resultOrderItems.getString(5+6));
                            newProduct.setPrice(resultOrderItems.getDouble(5));
                            newProduct.setDescription(resultOrderItems.getString(5+8));
                            newProduct.setMediaPath(resultOrderItems.getString(5+9));

                            newOrder.getProductList().put(newProduct, resultOrderItems.getInt(4));
                        }

                        if (newOrder.getProductList().size() > 0) orders.add(newOrder);
                    }

                } catch (Exception err) {
                    err.printStackTrace();
                }



                session.setAttribute("shipments", shipments);
                session.setAttribute("products", products);
                session.setAttribute("categories", categories);
                session.setAttribute("orders", orders);
                request.getRequestDispatcher("admin.jsp").forward(request, response);

            } else {

                System.out.println("zwykły użytkownik");


                ArrayList<Order> orders = new ArrayList<>();

                try {
                    String email = (String) session.getAttribute("email");
                    if (email == null) throw new NullPointerException();

                    String query = "Select * from orders o, users u where o.Id_user = u.Id and u.email = '" + email + "' order by o.id desc;";
                    ResultSet result = Server.executeQuery(query);



                    while (result != null && result.next()) {
                        // kazde kolejne zamowienie

                        Order newOrder = new Order(result);

                        String queryShipment = "SELECT * FROM shipments WHERE Id = " + newOrder.getShipmentId() + ";";
                        ResultSet resultShipment = Server.executeQuery(queryShipment);
                        if (resultShipment != null && resultShipment.next()) {
                            newOrder.setShipmentName(resultShipment.getString(2));
                            newOrder.setShipmentCost(resultShipment.getDouble(3));
                        }

                        String queryStatus = "SELECT * FROM orders_status WHERE Id = " + newOrder.getStatusId() + ";";
                        ResultSet resultStatus = Server.executeQuery(queryStatus);
                        if (resultStatus != null && resultStatus.next()) {
                            newOrder.setStatusName(resultStatus.getString(2));
                        }

                        String query2 = "SELECT * FROM orders_list o, products p WHERE p.Id = o.Id_product and o.Id_order = " + newOrder.getId() + ";";
                        ResultSet result2 = Server.executeQuery(query2);

                        while (result2 != null && result2.next()) {

                            ProductItem newProduct = new ProductItem();
                            newProduct.setId(result2.getInt(5+1));
                            newProduct.setId_category(result2.getInt(5+3));
                            newProduct.setName(result2.getString(5+2));
                            newProduct.setAnime(result2.getString(5+4));
                            newProduct.setSize(result2.getString(5+5));
                            newProduct.setManufacturer(result2.getString(5+6));
                            newProduct.setPrice(result2.getDouble(5));
                            newProduct.setDescription(result2.getString(5+8));
                            newProduct.setMediaPath(result2.getString(5+9));

                            newOrder.getProductList().put(newProduct, result2.getInt(4));
                        }


                        if (newOrder.getProductList().size() > 0) orders.add(newOrder);
                    }

                } catch (Exception err) {
                    err.printStackTrace();
                }




                session.setAttribute("orders", orders);
                request.getRequestDispatcher("account.jsp").forward(request, response);
            }
        } else {
            session.setAttribute("title", "Anime Shop | Logowanie / Rejestracja");
            session.setAttribute("RegisterError", null);
            session.setAttribute("loginError", false);
            System.out.println("loguj / rejestruj");
            request.getRequestDispatcher("loginRegister.jsp").forward(request, response);
        }

        //request.getRequestDispatcher("Katalog").forward(request, response);

    }
}
