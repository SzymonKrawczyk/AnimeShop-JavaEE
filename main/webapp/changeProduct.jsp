<%@ page import="java.util.ArrayList" %>
<%@ page import="shop.Category" %>
<%@ page import="shop.ProductItem" %>
<%@ page import="shop.Server" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<%
    ArrayList<String> ChangeProductErrorList =  (ArrayList<String>)session.getAttribute("ChangeProductErrorList");
    ArrayList<Category> catList =  (ArrayList<Category>)session.getAttribute("catList");

    ProductItem currentProduct =  (ProductItem)session.getAttribute("currentProduct");

    String ChangeProductName = null;
    String ChangeProductCategory = null;
    String ChangeProductAnime = null;
    String ChangeProductSize = null;
    String ChangeProductManufacturer = null;
    String ChangeProductPrice = null;
    String ChangeProductDescription = null;
    String ChangeProductMediaPath = null;

    if (currentProduct != null) {


        ChangeProductName = currentProduct.getName();
        ChangeProductCategory = String.valueOf(currentProduct.getId_category());
        ChangeProductAnime =  currentProduct.getAnime();
        ChangeProductSize = currentProduct.getSize();
        ChangeProductManufacturer = currentProduct.getManufacturer();
        ChangeProductPrice = String.valueOf(currentProduct.getPrice()).replace(',', '.');
        ChangeProductDescription = currentProduct.getDescription();
        ChangeProductMediaPath = currentProduct.getMediaPath();


        /*ChangeProductName =  (String)request.getParameter("ChangeProductName");
        ChangeProductCategory =  (String)request.getParameter("ChangeProductCategory");
        ChangeProductAnime =  (String)request.getParameter("ChangeProductAnime");
        ChangeProductSize =  (String)request.getParameter("ChangeProductSize");
        ChangeProductManufacturer =  (String)request.getParameter("ChangeProductManufacturer");
        ChangeProductPrice =  (String)request.getParameter("ChangeProductPrice");
        ChangeProductDescription =  (String)request.getParameter("ChangeProductDescription");
        ChangeProductMediaPath =  (String)request.getParameter("ChangeProductMediaPath");*/
    }
%>

<div class="contentBg formTableHolder">
    <span>Dodawanie produktu</span><br>
    <form method="get" action="ChangeProduct" accept-charset="utf-8">
        <input style="display: none" type="text" name="ProdChange" value="true"/>
        <input style="display: none" type="text" name="id" value="<%=request.getParameter("id")%>"/>
        <table class="loginregister_table formTable">

            <tr>
                <td class="left_td">Nazwa</td>
                <td><input class="input" type="text" name="ChangeProductName" value="<%=ChangeProductName != null ? ChangeProductName : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeProductErrorList != null && ChangeProductErrorList.get(0) != null ? ChangeProductErrorList.get(0) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Kategoria</td>
                <td>
                    <select class="cartInput" name="ChangeProductCategory">
                        <% for (Category cat : catList) {
                            String selected = "";
                            try {
                                if (cat.getId() == Integer.parseInt(ChangeProductCategory)) selected = "selected";
                            } catch (Exception err) {
                                err.printStackTrace();
                            }
                        %>
                        <option value="<%=cat.getId()%>" <%=selected%>><%=cat.getName()%></option>
                        <%}%>
                    </select>
                </td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeProductErrorList != null && ChangeProductErrorList.get(1) != null ? ChangeProductErrorList.get(1) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Anime</td>
                <td><input class="input" type="text" name="ChangeProductAnime" value="<%=ChangeProductAnime != null ? ChangeProductAnime : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeProductErrorList != null && ChangeProductErrorList.get(2) != null ? ChangeProductErrorList.get(2) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Wielkość</td>
                <td><input class="input" type="text" name="ChangeProductSize" value="<%=ChangeProductSize != null ? ChangeProductSize : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeProductErrorList != null && ChangeProductErrorList.get(3) != null ? ChangeProductErrorList.get(3) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Producent</td>
                <td><input class="input" type="text" name="ChangeProductManufacturer" value="<%=ChangeProductManufacturer != null ? ChangeProductManufacturer : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeProductErrorList != null && ChangeProductErrorList.get(4) != null ? ChangeProductErrorList.get(4) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Cena</td>
                <td><input class="input " type="text" name="ChangeProductPrice" value="<%=ChangeProductPrice != null ? ChangeProductPrice : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeProductErrorList != null && ChangeProductErrorList.get(5) != null ? ChangeProductErrorList.get(5) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Opis</td>
                <td> <textarea class="input cartInput" name="ChangeProductDescription" rows="7" cols="25" style="min-height: 100px;"><%=ChangeProductDescription != null ? Server.brToNewLine(ChangeProductDescription) : ""%></textarea></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeProductErrorList != null && ChangeProductErrorList.get(6) != null ? ChangeProductErrorList.get(6) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Ścieżka do folderu ze zdjęciami</td>
                <td><input class="input" type="text" name="ChangeProductMediaPath" value="<%=ChangeProductMediaPath != null ? ChangeProductMediaPath : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeProductErrorList != null && ChangeProductErrorList.get(7) != null ? ChangeProductErrorList.get(7) : ""%></span>
                </td>
            </tr>

            <tr>
                <td></td>
                <td><input class="button" type="submit" value="Zmień"></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>



<jsp:include page="footer.jsp"/>