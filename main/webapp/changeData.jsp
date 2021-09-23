<%@ page import="java.util.ArrayList" %>
<%@ page import="shop.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<%
    ArrayList<String> changeDataErrors =  (ArrayList<String>)session.getAttribute("ChangeDataError");
    User currentUser =  (User)session.getAttribute("currentUser");
%>

<div class="contentBg formTableHolder">
    <span>Zmiana danych</span><br>
    <form method="post" action="ChangeData" accept-charset="utf-8">
        <table class="loginregister_table formTable">
            <tr>
                <td class="left_td">Email</td>
                <td><input class="input" type="text" name="Cemail" value="<%=currentUser != null && currentUser.getEmail() != null ? currentUser.getEmail() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(0) != null ? changeDataErrors.get(0) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Hasło</td>
                <td><input class="input" type="password" name="Cpassword"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(1) != null ? changeDataErrors.get(1) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Imię</td>
                <td><input class="input" type="text" name="Cfirst" value="<%=currentUser != null && currentUser.getFirstName() != null ? currentUser.getFirstName() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(2) != null ? changeDataErrors.get(2) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Nazwisko</td>
                <td><input class="input" type="text" name="Clast" value="<%=currentUser != null && currentUser.getLastName() != null ? currentUser.getLastName() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(3) != null ? changeDataErrors.get(3) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Nr Telefonu</td>
                <td><input class="input" type="number" name="Cphone" value="<%=currentUser != null && currentUser.getPhone() != -1 ? currentUser.getPhone() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(4) != null ? changeDataErrors.get(4) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Kod pocztowy</td>
                <td><input class="input" type="text" name="CzipCode" value="<%=currentUser != null && currentUser.getZipCode() != null ? currentUser.getZipCode() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(5) != null ? changeDataErrors.get(5) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Miasto</td>
                <td><input class="input" type="text" name="Ccity" value="<%=currentUser != null && currentUser.getCity() != null ? currentUser.getCity() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(6) != null ? changeDataErrors.get(6) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Ulica</td>
                <td><input class="input" type="text" name="Cstreet" value="<%=currentUser != null && currentUser.getStreet() != null ? currentUser.getStreet() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(7) != null ? changeDataErrors.get(7) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Nr Budynku</td>
                <td><input class="input" type="text" name="Cbuilding" value="<%=currentUser != null && currentUser.getBuilding() != null ? currentUser.getBuilding() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(8) != null ? changeDataErrors.get(8) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Nr Lokalu</td>
                <td><input class="input" type="text" name="Capartment" value="<%=currentUser != null && currentUser.getApartment() != null ? currentUser.getApartment() : ""%>"></td>
                <td><input style="display: none" type="text" name="Cchange" value="true">
                    <span style="color: darkred; font-size: 12px;"><%=changeDataErrors != null && changeDataErrors.get(9) != null ? changeDataErrors.get(9) : ""%></span>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input class="button" type="submit" value="Zmień dane"></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>



<jsp:include page="footer.jsp"/>