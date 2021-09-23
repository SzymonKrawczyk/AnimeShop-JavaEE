<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<%
    ArrayList<String> AddCategoryErrorList =  (ArrayList<String>)session.getAttribute("AddCategoryErrorList");
    String AddCategoryName =  (String)session.getAttribute("AddCategoryName");
%>

<div class="contentBg formTableHolder">
    <span>Dodawanie kategorii</span><br>
    <form method="get" action="AddCategory" accept-charset="utf-8">
        <input style="display: none" type="text" name="CatNew" value="true"/>
        <table class="loginregister_table formTable">
            <tr>
                <td class="left_td">Nazwa</td>
                <td><input class="input" type="text" name="AddCategoryName" value="<%=AddCategoryName != null ? AddCategoryName : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddCategoryErrorList != null && AddCategoryErrorList.get(0) != null ? AddCategoryErrorList.get(0) : ""%></span>
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