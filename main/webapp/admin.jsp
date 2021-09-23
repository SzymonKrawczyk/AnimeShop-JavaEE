<%@ page import="java.util.ArrayList" %>
<%@ page import="shop.*" %>
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
                <td class="left_td">Konto administratorskie</td>
                <td class="right_td"></td>
            </tr>
            <tr>
                <td class="left_td">&nbsp;</td>
                <td class="right_td">&nbsp;</td>
            </tr>
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
    ArrayList<Shipment> shipments = (ArrayList<Shipment>)session.getAttribute("shipments");
    ArrayList<Category> categories = (ArrayList<Category>)session.getAttribute("categories");
    ArrayList<ProductItem> products = (ArrayList<ProductItem>)session.getAttribute("products");
    ArrayList<Order> orders = (ArrayList<Order>)session.getAttribute("orders");
%>


<% if (shipments != null && shipments.size() > 0) { %>
    <div class="contentBg account_card">
        <div style="margin-bottom: 15px;">Dostawy</div>
        <table class="admin_table" style="width: 40%;">
            <thead>
            <tr>
                <td>Nazwa</td>
                <td>Koszt</td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <%
                for (Shipment shipment : shipments) {
            %>
            <tr>
                <td class="name_td"><%=shipment.getName()%></td>
                <td class="cost_td"><%=shipment.getCost()%>zł</td>
                <td class="link_td td_link"><a href="ChangeShipment?id=<%=shipment.getId()%>">Edytuj</a></td>
            </tr>
            <%}%>

            <tr>
                <td class="left_td">&nbsp;</td>
                <td class="middle_td">&nbsp;</td>
                <td class="right_td">&nbsp;</td>
            </tr>
            <tr>
                <td class="link_td td_link"><a href="AddShipment">Dodaj</a></td>
            </tr>

            </tbody>
        </table>
    </div>
<%}%>


<% if (categories != null && categories.size() > 0) { %>
<div class="contentBg account_card">
    <div style="margin-bottom: 15px;">Kategorie</div>
    <table class="admin_table" style="width: 40%;">
        <thead>
        <tr>
            <td>Nazwa</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <%
            for (Category category : categories) {
        %>
        <tr>
            <td class="name_td"><%=category.getName()%></td>
            <td class="link_td td_link"><a href="ChangeCategory?id=<%=category.getId()%>">Edytuj</a></td>
        </tr>
        <%}%>

        <tr>
            <td class="left_td">&nbsp;</td>
            <td class="right_td">&nbsp;</td>
        </tr>
        <tr>
            <td class="link_td td_link"><a href="AddCategory">Dodaj</a></td>
        </tr>

        </tbody>
    </table>
</div>
<%}%>


<% if (products != null && products.size() > 0) { %>
<div class="contentBg account_card">
    <div style="margin-bottom: 15px;">Produkty</div>
    <table class="admin_table">
        <thead>
        <tr>
            <td>Nazwa</td>
            <td>Kategoria</td>
            <td>Anime</td>
            <td>Wielkość</td>
            <td>Producent</td>
            <td>Cena</td>
            <td>Ścieżka do zdjęć</td>
        </tr>
        </thead>
        <tbody>
        <%
            for (ProductItem product : products) {
                String catname = "";
                if (categories != null) {
                    for (Category cat: categories) {
                        if (cat.getId() == product.getId_category()) {
                            catname = cat.getName();
                            break;
                        }
                    }
                }
        %>
        <tr>

            <td class="p_td pname_td td_link"><a href="Katalog?id=<%=product.getId()%>"><%=product.getName()%></a></td>
            <td class="p_td pcat_td"><%=catname%></td>
            <td class="p_td panime_td"><%=product.getAnime()%></td>
            <td class="p_td psize_td"><%=product.getSize()%></td>
            <td class="p_td pman_td"><%=product.getManufacturer()%></td>
            <td class="p_td pprice_td"><%=product.getPrice()%>zł</td>
            <td class="p_td pmedia_td"><%=product.getMediaPath()%></td>
            <td class="p_td link_td td_link"><a href="ChangeProduct?id=<%=product.getId()%>">Edytuj</a></td>
        </tr>
        <%}%>

        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td class="link_td td_link"><a href="AddProduct">Dodaj</a></td>
        </tr>

        </tbody>
    </table>
</div>
<%}%>


<% if (orders != null && orders.size() > 0) { %>
<div class="contentBg account_card">
    <div style="margin-bottom: 15px;">Wszystkie zamówienia</div>
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
                <td class="left_td td_link">
                    <%if (!currentOrder.getStatusName().equals("Wysłano")){%>
                        <a href="ChangeStatus?id=<%=currentOrder.getId()%>">Zmień status na "Wysłano"</a>
                    <%}%>
                </td>
                <td class="right_td"></td>
            </tr>
            <tr>
                <td class="left_td">&nbsp;</td>
                <td class="right_td">&nbsp;</td>
            </tr>
            <tr>
                <td class="left_td">&nbsp;</td>
                <td class="right_td">&nbsp;</td>
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
