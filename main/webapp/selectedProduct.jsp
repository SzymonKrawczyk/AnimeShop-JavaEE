<%@ page import="shop.ProductItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<%
    ProductItem item = (ProductItem)session.getAttribute("selectedProduct");
    if (item!=null) {
%>

<div class="contentBg">
    <div class="details_product_name"><%=item.getName()%></div>

    <div class="details_product_info">
        <table class="product_table" style="float: left">
            <tr>
                <td class="left_td">Anime:</td>
                <td class="right_td"><%=item.getAnime()%></td>
            </tr>
            <tr>
                <td class="left_td">Producent:</td>
                <td class="right_td"><%=item.getManufacturer()%></td>
            </tr>
            <tr>
                <td class="left_td">Wielkość:</td>
                <td class="right_td"><%=item.getSize()%></td>
            </tr>
            <tr>
                <td class="left_td"></td>
                <td class="right_td"></td>
            </tr>
            <tr>
                <td class="left_td">Opis:</td>
                <td class="right_td"></td>
            </tr>
            <tr>
                <td class="left_td"></td>
                <td class="right_td" style="text-align: justify"><%=item.getDescription()%></td>
            </tr>
        </table>
        <form method="get" action="Katalog" accept-charset="utf-8" style="margin: 0; padding: 0; float: right; height: 50px">
            <input type="text" name="id" value="<%=request.getParameter("id")%>" style="display: none"/>
            <input type="number" class="addToCartAmount" name="addToCart" value="1"/>
            <input type="submit" class="addToCartButton" value="Dodaj do koszyka"/>
            <div class="details_product_price">Cena: <%=item.getPrice()%>zł<span style="color: whitesmoke; font-size: 15px"><br> + dostawa</span></div>
        </form>
        <div style="clear: both;"></div>
        <div></div>
    </div>
    <div class="details_imgs_container">
        <% if (session.getAttribute("selectedProductMediaSize") != null) {
            for (int i = 1; i <= (Integer)session.getAttribute("selectedProductMediaSize"); ++i) {%>
            <div class="details_product_img_holder">
                <img class="details_product_img" src="media/<%=item.getMediaPath()%>/<%=i%>.jpg"/>
            </div>
        <%}}%>
        <div style="clear: both;"></div>
        <div></div>
    </div>
</div>

<%} else {%>
    Podany produkt nie istnieje
<%}%>

<jsp:include page="footer.jsp"/>