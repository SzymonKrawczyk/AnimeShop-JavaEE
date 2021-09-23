<%@ page import="java.util.ArrayList" %>
<%@ page import="shop.User" %>
<%@ page import="shop.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<%
    ArrayList<String> AddProductErrorList =  (ArrayList<String>)session.getAttribute("AddProductErrorList");
    ArrayList<Category> catList =  (ArrayList<Category>)session.getAttribute("catList");

    String AddProductName =  (String)request.getParameter("AddProductName");
    String AddProductCategory =  (String)request.getParameter("AddProductCategory");
    String AddProductAnime =  (String)request.getParameter("AddProductAnime");
    String AddProductSize =  (String)request.getParameter("AddProductSize");
    String AddProductManufacturer =  (String)request.getParameter("AddProductManufacturer");
    String AddProductPrice =  (String)request.getParameter("AddProductPrice");
    String AddProductDescription =  (String)request.getParameter("AddProductDescription");
    String AddProductMediaPath =  (String)request.getParameter("AddProductMediaPath");
%>

<div class="contentBg formTableHolder">
    <span>Dodawanie produktu</span><br>
    <form method="get" action="AddProduct" accept-charset="utf-8">
        <input style="display: none" type="text" name="ProdNew" value="true"/>
        <table class="loginregister_table formTable">

            <tr>
                <td class="left_td">Nazwa</td>
                <td><input class="input" type="text" name="AddProductName" value="<%=AddProductName != null ? AddProductName : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddProductErrorList != null && AddProductErrorList.get(0) != null ? AddProductErrorList.get(0) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Kategoria</td>
                <td>
                    <select class="cartInput" name="AddProductCategory">
                        <%for (Category cat : catList) {
                            String selected = "";
                            try {
                                if (cat.getId() == Integer.parseInt(AddProductCategory)) selected = "selected";
                            } catch (Exception err) {
                                err.printStackTrace();
                            }
                        %>
                        <option value="<%=cat.getId()%>" <%=selected%>><%=cat.getName()%></option>
                        <%}%>
                    </select>
                </td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddProductErrorList != null && AddProductErrorList.get(1) != null ? AddProductErrorList.get(1) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Anime</td>
                <td><input class="input" type="text" name="AddProductAnime" value="<%=AddProductAnime != null ? AddProductAnime : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddProductErrorList != null && AddProductErrorList.get(2) != null ? AddProductErrorList.get(2) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Wielkość</td>
                <td><input class="input" type="text" name="AddProductSize" value="<%=AddProductSize != null ? AddProductSize : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddProductErrorList != null && AddProductErrorList.get(3) != null ? AddProductErrorList.get(3) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Producent</td>
                <td><input class="input" type="text" name="AddProductManufacturer" value="<%=AddProductManufacturer != null ? AddProductManufacturer : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddProductErrorList != null && AddProductErrorList.get(4) != null ? AddProductErrorList.get(4) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Cena</td>
                <td><input class="input " type="text" name="AddProductPrice" value="<%=AddProductPrice != null ? AddProductPrice : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddProductErrorList != null && AddProductErrorList.get(5) != null ? AddProductErrorList.get(5) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Opis</td>
                <td> <textarea class="input cartInput" name="AddProductDescription" rows="7" cols="25" style="min-height: 100px;"><%=AddProductDescription != null ? AddProductDescription : ""%></textarea></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddProductErrorList != null && AddProductErrorList.get(6) != null ? AddProductErrorList.get(6) : ""%></span>
                </td>
            </tr>

            <tr>
                <td class="left_td">Ścieżka do folderu ze zdjęciami</td>
                <td><input class="input" type="text" name="AddProductMediaPath" value="<%=AddProductMediaPath != null ? AddProductMediaPath : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddProductErrorList != null && AddProductErrorList.get(7) != null ? AddProductErrorList.get(7) : ""%></span>
                </td>
            </tr>

            <tr>
                <td></td>
                <td><input class="button" type="submit" value="Dodaj"></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>



<jsp:include page="footer.jsp"/>