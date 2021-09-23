//
//  Servlet edytujący użytkownika
//

package shop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "ChangeData", urlPatterns = ("/ChangeData"))
public class ChangeData extends javax.servlet.http.HttpServlet {

    private String getParameter (HttpServletRequest request, String from) {
        return request.getParameter(from) != null ? Server.securifyString(request.getParameter(from)).trim() : "";
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");

        session.setAttribute("ChangeDataError", null);

        User currentUser = new User();

        if (isLogged) {
            try {
                ResultSet getUser = Server.executeQuery("Select * from users where email='" + session.getAttribute("email") + "';");
                if (getUser == null) throw new NullPointerException();
                getUser.next();
                currentUser = new User(getUser);
            } catch (Exception err) {
                err.printStackTrace();
            }
            session.setAttribute("email", currentUser.getEmail());
            session.setAttribute("currentUser", currentUser);


            if (request.getParameter("Cchange") != null) {

                String email = getParameter(request, "Cemail");
                String password = getParameter(request, "Cpassword");
                String firstName = getParameter(request, "Cfirst");
                String lastName = getParameter(request, "Clast");
                int phone = 0;
                if (request.getParameter("Cphone") != null && !request.getParameter("Cphone").equals(""))
                    phone = Integer.parseInt(request.getParameter("Cphone"));
                String zipCode = getParameter(request, "CzipCode");
                String city = getParameter(request, "Ccity");
                String street = getParameter(request, "Cstreet");
                String building = getParameter(request, "Cbuilding");
                String apartment = getParameter(request, "Capartment");

                User editedUser = new User();
                if (!email.equals("") && !email.equals("null"))         editedUser.setEmail(email);
                if (!password.equals("") && !password.equals("null"))   editedUser.setPassword(password);
                if (!firstName.equals("") && !firstName.equals("null")) editedUser.setFirstName(firstName);
                if (!lastName.equals("") && !lastName.equals("null"))   editedUser.setLastName(lastName);
                if (phone != 0)                                         editedUser.setPhone(phone);
                if (!zipCode.equals("") && !zipCode.equals("null"))     editedUser.setZipCode(zipCode);
                if (!city.equals("") && !city.equals("null"))           editedUser.setCity(city);
                if (!street.equals("") && !street.equals("null"))       editedUser.setStreet(street);
                if (!building.equals("") && !building.equals("null"))   editedUser.setBuilding(building);
                if (!apartment.equals("") && !apartment.equals("null")) editedUser.setApartment(apartment);

                ArrayList<String> errorList = new ArrayList<>();
                for (int i = 0; i <= 9; ++i) {
                    errorList.add("");
                }

                ResultSet result = Server.executeQuery("Select * from users where email='" + email + "';");

                try {
                    System.out.println("Weryfikacja danych");

                    if (!User.isGoodEmail(editedUser.getEmail())) {
                        errorList.set(0, "Niepoprawny adres email");

                    } else if (result != null && result.next() && !currentUser.getEmail().equals(editedUser.getEmail())) {

                        System.out.println("Już taki użytkownik istnieje...");
                        Server.closeDbConnection();
                        errorList.set(0, "Email jest już wykorzystany");

                    } else if (!currentUser.getEmail().equals(editedUser.getEmail())) {

                        String query = "UPDATE users SET email = '" + editedUser.getEmail() + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        currentUser.setEmail(editedUser.getEmail());
                        errorList.set(0, "Email zmieniony");
                    }




                    if (!User.isGoodPassword(editedUser.getPassword())) {
                        errorList.set(1, "Hasło jest za krótkie, minimalna ilość znaków = 8");
                        //errorList.add("Hasło jest za krótkie, minimalna ilość znaków = 8");
                    } else {

                        String query = "UPDATE users SET password = '" + Server.hashPassword(currentUser.getEmail(), editedUser.getPassword()) + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        errorList.set(1, "Hasło zmienione");
                    }





                    if (!User.isGoodFirstName(editedUser.getFirstName())) {
                        errorList.set(2, "Niepoprawne imię");

                    } else if (!currentUser.getFirstName().equals(editedUser.getFirstName())) {

                        String query = "UPDATE users SET imie = '" + editedUser.getFirstName() + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        currentUser.setFirstName(editedUser.getFirstName());
                        errorList.set(2, "Imię zmienione");
                    }



                    if (!User.isGoodLastName(editedUser.getLastName())) {
                        errorList.set(3, "Niepoprawne nazwisko");
                        //errorList.add("Niepoprawne nazwisko");
                    } else if (!currentUser.getLastName().equals(editedUser.getLastName())) {

                        String query = "UPDATE users SET nazwisko = '" + editedUser.getLastName() + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        currentUser.setLastName(editedUser.getLastName());
                        errorList.set(3, "Nazwisko zmienione");
                    }





                    if (!User.isGoodPhone(editedUser.getPhone())) {
                        errorList.set(4, "Niepoprawny numer telefonu");
                        //errorList.add("Niepoprawny numer telefonu");
                    } else if (currentUser.getPhone() != editedUser.getPhone()) {

                        String query = "UPDATE users SET telefon = '" + editedUser.getPhone() + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        currentUser.setPhone(editedUser.getPhone());
                        errorList.set(4, "Numer telefonu zmieniony");
                    }





                    if (!User.isGoodZipCode(editedUser.getZipCode())) {
                        errorList.set(5, "Niepoprawny kod pocztowy, użyj formatu 12-345");
                        //errorList.add("Niepoprawny kod pocztowy, użyj formatu 12-345");
                    } else if (!currentUser.getZipCode().equals(editedUser.getZipCode())) {

                        String query = "UPDATE users SET kod_pocztowy = '" + editedUser.getZipCode() + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        currentUser.setZipCode(editedUser.getZipCode());
                        errorList.set(5, "Kod pocztowy zmieniony");
                    }





                    if (!User.isGoodCity(editedUser.getCity())) {
                        errorList.set(6, "Niepoprawna nazwa miasta");
                        //errorList.add("Niepoprawny adres");
                    } else if (!currentUser.getCity().equals(editedUser.getCity())) {

                        String query = "UPDATE users SET miasto = '" + editedUser.getCity() + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        currentUser.setCity(editedUser.getCity());
                        errorList.set(6, "Nazwa miasta zmieniona");
                    }





                    if (!User.isGoodStreet(editedUser.getStreet())) {
                        errorList.set(7, "Niepoprawna nazwa ulicy");
                        //errorList.add("Niepoprawny adres");
                    } else if (!currentUser.getStreet().equals(editedUser.getStreet())) {

                        String query = "UPDATE users SET ulica = '" + editedUser.getStreet() + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        currentUser.setStreet(editedUser.getStreet());
                        errorList.set(7, "Nazwa ulicy zmienione");
                    }




                    if (!User.isGoodBuilding(editedUser.getBuilding())) {
                        errorList.set(8, "Niepoprawny adres");
                        //errorList.add("Niepoprawny adres");
                    } else if (!currentUser.getBuilding().equals(editedUser.getBuilding())) {

                        String query = "UPDATE users SET nr_domu = '" + editedUser.getBuilding() + "' WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        currentUser.setBuilding(editedUser.getBuilding());
                        errorList.set(8, "Numer budynku zmieniony");
                    }


                    if (!currentUser.getApartment().equals(editedUser.getApartment())) {

                        currentUser.setApartment(editedUser.getApartment());
                        if (editedUser.getApartment().equals("")) editedUser.setApartment("NULL");
                        else editedUser.setApartment("'" + editedUser.getApartment() + "'");

                        String query = "UPDATE users SET nr_lokalu = " + editedUser.getApartment() + " WHERE users.Id = " + currentUser.getId() + ";";
                        Server.executeUpdate(query);

                        errorList.set(9, "Numer lokalu zmieniony");
                    }


                    session.setAttribute("email", currentUser.getEmail());
                    session.setAttribute("currentUser", currentUser);

                    session.setAttribute("ChangeDataError", errorList);

                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            session.setAttribute("title", "Anime Shop | Zmiana danych");
            request.getRequestDispatcher("changeData.jsp").forward(request, response);
        } else {
            System.out.println("nie zalogwany");
            response.sendRedirect("Konto");
        }


    }
}
