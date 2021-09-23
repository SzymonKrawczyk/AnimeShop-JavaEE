<%@ page import="java.util.ArrayList" %>
<%@ page import="shop.Order" %>
<%@ page import="shop.ProductItem" %>
<%@ page import="shop.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="CSS/styleSub.css"/>



<%
    User currentUser =  (User)session.getAttribute("currentUser");
%>

<div class="contentBg account_card">
    <div class="account_info">
        Informacje o koncie<br>
        <table class="order_info_table">
            <tr>
                <td class="left_td">Email</td>
                <td class="right_td"><%=currentUser != null ? currentUser.getEmail() : ""%></td>
            </tr>
            <tr>
                <td class="left_td">Imię i Nazwisko</td>
                <td class="right_td"><%=currentUser != null ? currentUser.getFirstName() : ""%> <%=currentUser != null ? currentUser.getLastName() : ""%></td>
            </tr>
            <tr>
                <td class="left_td">Nr telefonu</td>
                <td class="right_td"><%=currentUser != null ? currentUser.getPhone() : ""%></td>
            </tr>
            <tr>
                <td class="left_td">Adres:</td>
                <td class="right_td"><%=currentUser != null ? currentUser.getStreet() : ""%> <%=currentUser != null ? currentUser.getBuilding() : ""%> <%=currentUser != null ? currentUser.getApartment() : ""%></td>
            </tr>
            <tr>
                <td class="left_td"></td>
                <td class="right_td"><%=currentUser != null ? currentUser.getZipCode() : ""%> <%=currentUser != null ? currentUser.getCity() : ""%></td>
            </tr>
        </table>
        <div style="width: 100%; float: left; padding-top: 20px;">
            <form method="post" action="ChangeData" accept-charset="utf-8" style="margin:0">
                <input class="MyButton" type="submit" value="Zmień dane"/>
            </form>
            <a href="Konto?logout=true"><div class="MyButton">Wyloguj</div></a>
        </div>
        <div style="clear: both;"></div>
        <div></div>
    </div>


    <div style="clear: both;"></div>
    <div></div>

</div>
<%
    ArrayList<Order> orders = (ArrayList<Order>)session.getAttribute("orders");
    if (orders != null && orders.size() > 0) {
%>
<div class="contentBg account_card">
    <div style="margin-bottom: 15px;">Historia zamówień</div>
    <%
        for (Order currentOrder : orders) {
    %>
    <div class="order">

        <table class="order_info_table">
            <tr>
                <td class="left_td">Status</td>
                <td class="right_td"><%=currentOrder.getStatusName()%></td>
            </tr>
            <tr>
                <td class="left_td">ID zamówienia</td>
                <td class="right_td"><%=currentOrder.getId()%></td>
            </tr>
            <tr>
                <td class="left_td">Data zamówienia</td>
                <td class="right_td"><%=currentOrder.getTimestampAsString()%></td>
            </tr>
            <tr>
                <td class="left_td">Rodzaj dostawy</td>
                <td class="right_td"><%=currentOrder.getShipmentName()%></td>
            </tr>
            <tr>
                <td class="left_td">Informacje dotyczące dostawy</td>
                <td class="right_td"><%=currentOrder.getShipmentInfo()%></td>
            </tr>
            <tr>
                <td class="left_td">Dodatkowe informacje od kupującego</td>
                <td class="right_td"><%=currentOrder.getAdditionalInfo()%></td>
            </tr>
        </table>

        <table class="order_items_table">
            <thead>
                <tr>
                    <td>Produkt</td>
                    <td>Ilość</td>
                    <td>Cena za sztukę</td>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ProductItem item : currentOrder.getProductList().keySet()) {
                %>
                    <tr>
                        <td class="left_td"><a href="Katalog?id=<%=item.getId()%>"><%=item.getName()%></a></td>
                        <td class="middle_td"><%=currentOrder.getProductList().get(item)%></td>
                        <td class="right_td"><%=item.getPrice()%>zł</td>
                    </tr>
                <%}%>

                <tr>
                    <td class="left_td">&nbsp;</td>
                    <td class="middle_td">&nbsp;</td>
                    <td class="right_td">&nbsp;</td>
                </tr>
                <tr>
                    <td class="left_td">Dostawa</td>
                    <td class="middle_td"></td>
                    <td class="right_td"><%=currentOrder.getShipmentCost()%>zł</td>
                </tr>
                <tr>
                    <td class="left_td">&nbsp;</td>
                    <td class="middle_td">&nbsp;</td>
                    <td class="right_td">&nbsp;</td>
                </tr>
                <tr>
                    <td class="left_td">Łącznie</td>
                    <td class="middle_td"></td>
                    <td class="right_td"><%=currentOrder.calculateTotal()%>zł</td>
                </tr>
            </tbody>
        </table>

        <div style="clear: both;"></div>
        <div></div>

    </div>
    <%}%>
</div>
<%}%>

<jsp:include page="footer.jsp"/>

<%--
<div class="order">

    <table class="order_info_table">
        <tr>
            <td class="left_td">ID zamówienia</td>
            <td class="right_td">999</td>
        </tr>
        <tr>
            <td class="left_td">Data zamówienia</td>
            <td class="right_td">21/10/2020 14:55:23</td>
        </tr>
        <tr>
            <td class="left_td">Rodzaj dostawy</td>
            <td class="right_td">Paczkomaty InPost</td>
        </tr>
        <tr>
            <td class="left_td">Informacje dotyczące dostawy</td>
            <td class="right_td">TMS02A</td>
        </tr>
        <tr>
            <td class="left_td">Dodatkowe informacje od kupującego</td>
            <td class="right_td">br<br>asdASDA<br>ak</td>
        </tr>
    </table>

    <table class="order_items_table">
        <thead>
        <tr>
            <td>Produkt</td>
            <td>Ilość</td>
            <td>Cena za sztukę</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="left_td"><a href="#">Megumin w munurku szkolnym</a></td>
            <td class="middle_td">2</td>
            <td class="right_td">1944.55zł</td>
        </tr>
        <tr>
            <td class="left_td"><a href="#">Megumin Nendoroid</a></td>
            <td class="middle_td">295</td>
            <td class="right_td">54.59zł</td>
        </tr>



        <tr>
            <td class="middle_td">&nbsp;</td>
            <td class="left_td">&nbsp;</td>
            <td class="right_td">&nbsp;</td>
        </tr>
        <tr>
            <td class="middle_td"></td>
            <td class="left_td">Łącznie</td>
            <td class="right_td">9999999.51zł</td>
        </tr>
        </tbody>
    </table>

    <div style="clear: both;"></div>
    <div></div>

</div>--%>
