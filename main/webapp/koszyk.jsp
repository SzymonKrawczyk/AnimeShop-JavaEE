<%@ page import="java.util.TreeMap" %>
<%@ page import="shop.ProductItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="shop.Shipment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<div class="contentBg">
    <%
        TreeMap<ProductItem, Integer> cartList = (TreeMap<ProductItem, Integer>)session.getAttribute("cartList");
        ArrayList<Shipment> shipmentList = (ArrayList<Shipment>)session.getAttribute("shipmentList");

    if (cartList != null && cartList.size() > 0) {%>

        <form method="post" action="Zamowienie" accept-charset="utf-8">
            <table class="order_info_table">

                <tr>
                    <td class="left_td">Dostawa</td>
                    <td class="right_td">
                        <select class="cartInput" name="shipment_id">
                            <%for (Shipment shipment : shipmentList) { %>
                                <option value="<%=shipment.getId()%>"><%=shipment.getName()%> | <%=shipment.getCost()%>zł</option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="left_td">Informacje do dostawy</td>
                    <td class="right_td"><i>Adres zostanie pobrany z konta</i></td>
                </tr>
                <tr>
                    <td class="left_td">Dodatkowe informacje od kupującego</td>
                    <td class="right_td">
                        <textarea class="cartInput" name="info" rows="7" cols="30" style="min-height: 100px;"></textarea>
                    </td>
                </tr>
                <tr>
                    <td class="left_td"></td>
                    <td class="right_td">
                        <input type="submit" class="MyButton" value="Podsumowanie"/>
                    </td>
                </tr>

            </table>
        </form>

        <table class="order_items_table cart_table">

            <thead>
                <tr>
                    <td>Produkt</td>
                    <td>Ilość</td>
                    <td>Cena za sztukę</td>
                    <td></td>
                </tr>
            </thead>

            <tbody>
                <%  double total = 0.0;
                    for (ProductItem product : cartList.keySet()) {
                    total += product.getPrice() * cartList.get(product);
                %>
                    <tr>
                        <td class="left_td"><a href="Katalog?id=<%=product.getId()%>"><%=product.getName()%></a></td>
                        <td class="middle_td"><%=cartList.get(product)%></td>
                        <td class="right_td"><%=product.getPrice()%>zł</td>
                        <td class="delete_td"><a href="Koszyk?delete=<%=product.getId()%>">Usuń</a></td>
                    </tr>
                <%}%>

                <tr>
                    <td class="left_td">&nbsp;</td>
                    <td class="middle_td">&nbsp;</td>
                    <td class="right_td">&nbsp;</td>
                </tr>
                <tr>
                    <td class="left_td">Łącznie</td>
                    <td class="middle_td"></td>
                    <td class="right_td"><%=Double.parseDouble(String.format("%.2f", total).replace(',', '.'))%>zł</td>
                </tr>
            </tbody>

        </table>

        <div style="clear: both;"></div>
        <div></div>

    <%} else {%>
        Koszyk jest pusty
    <%}%>
</div>

<jsp:include page="footer.jsp"/>