<%@ page import="shop.ProductItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<link rel="stylesheet" href="CSS/styleMain.css"/>
<link rel="stylesheet" href="CSS/styleSub.css"/>



<div class="contentBg catalog_left">
    <div class="category_name" style="font-size: 25px">Kategorie:</div>
    <div class="category_name" <%="".equals(request.getParameter("category")) || request.getParameter("category") == null ? "style=\"text-decoration: underline; color: wheat\"" : ""%>>&nbsp<a class="category_name_link" href="Katalog?category=<%=request.getParameter("searchText") != null && !request.getParameter("searchText").equals("") ? "&searchText=" + request.getParameter("searchText") : ""%>">Wszystkio</a></div>
    <%ArrayList<String> catList = (ArrayList<String>)session.getAttribute("catList");
    for (String item: catList) {%>
        <div class="category_name" <%=item.equals(request.getParameter("category")) ? "style=\"text-decoration: underline; color: wheat\"" : ""%>>&nbsp&nbsp<a class="category_name_link" href="Katalog?category=<%=item%><%=request.getParameter("searchText") != null && !request.getParameter("searchText").equals("") ? "&searchText=" + request.getParameter("searchText") : ""%>"><%=item%></a></div>
       <%}%>
</div>

<div class="catalog_right">
    <div class="contentBg catalog_settings">
        <form method="get" action="Katalog" accept-charset="utf-8" style="margin: 0">
            <%if (request.getParameter("category") != null && !request.getParameter("category").equals("null")){%>
                <input style="display: none" type="text" name="category" value="<%=request.getParameter("category")%>"/>
            <%}%>
            <input class="searchBar" type="text" name="searchText" value="<%=request.getParameter("searchText") != null && !request.getParameter("searchText").equals("") ? request.getParameter("searchText") : ""%>"/>
            <input class="searchButton" type="submit" value="Wyszukaj"/>

        </form>
    </div>


    <%
        ArrayList<ProductItem> productList = (ArrayList<ProductItem>)session.getAttribute("productList");
        if (productList.size() > 0){
        for (ProductItem item: productList) {%>

    <div class="contentBg catalog_item">
        <div class="product_img_holder">
            <a href="Katalog?id=<%=item.getId()%>"><img class="product_img" src="media/<%=item.getMediaPath()%>/1.jpg"/></a>
        </div>
        <div class="product_name"><a href="Katalog?id=<%=item.getId()%>"><%=item.getName()%></a></div>
        <div class="product_price">Cena: <%=item.getPrice()%>zł<span style="color: whitesmoke; font-size: 15px"><br> + dostawa</span></div>
    </div>

    <%}} else {%>
    <div class="contentBg" style="margin-top: 20px">
        Brak przedmiotów
    </div>
    <%}%>

    <div style="clear: both;"></div>
    <div></div>
</div>

<jsp:include page="footer.jsp"/>