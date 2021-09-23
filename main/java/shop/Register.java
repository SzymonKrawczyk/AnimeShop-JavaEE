//
//  Servlet rejestrujący użytkownika
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "Register", urlPatterns = ("/Register"))
public class Register extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");

        session.setAttribute("RegisterError", null);

        if (!isLogged) {
            //loguj
            String email = Server.securifyString(request.getParameter("email")).trim();
            String password = Server.securifyString(request.getParameter("password")).trim();
            String firstName = Server.securifyString(request.getParameter("first")).trim();
            String lastName = Server.securifyString(request.getParameter("last")).trim();
            int phone = 0;
            if(!request.getParameter("phone").equals("")) phone = Integer.parseInt(request.getParameter("phone"));
            String zipCode = Server.securifyString(request.getParameter("zipCode")).trim();
            String city = Server.securifyString(request.getParameter("city")).trim();
            System.out.println(city);
            String street = Server.securifyString(request.getParameter("street")).trim();
            String building = Server.securifyString(request.getParameter("building")).trim();
            String apartment = Server.securifyString(request.getParameter("apartment")).trim();

            ResultSet result = Server.executeQuery("Select * from users where email='" + email + "';");

            boolean error = false;
            ArrayList<String> errorList = new ArrayList<>();
            for (int i = 0; i < 9; ++i) {
                errorList.add("");
            }

            try {
                System.out.println("Weryfikacja danych");

                if (!User.isGoodEmail(email)) {
                    error = true;
                    errorList.set(0, "Niepoprawny adres email");
                }

                if (result != null && result.next()) {
                    System.out.println("Już taki użytkownik istnieje...");
                    Server.closeDbConnection();
                    error = true;
                    errorList.set(0, "Email jest już wykorzystany");

                }

                if (!User.isGoodPassword(password)) {
                        error = true;
                        errorList.set(1, "Hasło jest za krótkie, minimalna ilość znaków = 8");
                        //errorList.add("Hasło jest za krótkie, minimalna ilość znaków = 8");
                    }
                if (!User.isGoodFirstName(firstName)) {
                        error = true;
                        errorList.set(2, "Niepoprawne imię");
                        //errorList.add("Niepoprawne imię");
                    }
                if (!User.isGoodLastName(lastName)) {
                        error = true;
                        errorList.set(3, "Niepoprawne nazwisko");
                        //errorList.add("Niepoprawne nazwisko");
                    }
                if (!User.isGoodPhone(phone)) {
                        error = true;
                        errorList.set(4, "Niepoprawny numer telefonu");
                        //errorList.add("Niepoprawny numer telefonu");
                    }
                if (!User.isGoodZipCode(zipCode)) {
                        error = true;
                        errorList.set(5, "Niepoprawny kod pocztowy, użyj formatu 12-345");
                        //errorList.add("Niepoprawny kod pocztowy, użyj formatu 12-345");
                    }
                if (!User.isGoodCity(city)) {
                        error = true;
                        errorList.set(6, "Niepoprawna nazwa miasta");
                        //errorList.add("Niepoprawny adres");
                    }
                if (!User.isGoodStreet(street)) {
                        error = true;
                        errorList.set(7, "Niepoprawna nazwa ulicy");
                        //errorList.add("Niepoprawny adres");
                    }
                if (!User.isGoodBuilding(building)) {
                        error = true;
                        errorList.set(8, "Niepoprawny adres");
                        //errorList.add("Niepoprawny adres");
                    }

                if (!error) {
                    System.out.println("Dane ok");

                    password = Server.hashPassword(email, password);

                    if (apartment.equals("")) apartment = "NULL";
                    else apartment = "'" + apartment + "'";

                    String query = "INSERT INTO users " +
                            "(email, password, admin, imie, nazwisko, telefon, kod_pocztowy, miasto, ulica, nr_domu, nr_lokalu) " +
                            "VALUES ('" + email + "', '" + password + "', '0', '" + firstName + "', '" + lastName + "' , '" + phone + "', '" + zipCode + "', '" + city + "', '" + street + "', '" + building + "', " + apartment + ");";

                    System.out.println(query);
                    Server.executeUpdate(query);

                    session.setAttribute("isAdmin", false);
                    session.setAttribute("isLogged", true);
                    session.setAttribute("email", email);
                    response.sendRedirect("Konto");

                } else {
                    System.out.println("Dane złe");

                    session.setAttribute("RegisterError", errorList);
                    session.setAttribute("title", "Anime Shop | Logowanie / Rejestracja");
                    request.getRequestDispatcher("loginRegister.jsp").forward(request, response);

                }

            } catch (Exception err) {
                err.printStackTrace();
            }

        } else {
            System.out.println("Już zalogowany...");
            session.setAttribute("title", "Anime Shop | Katalog");
            response.sendRedirect("Konto");
        }


    }
}
