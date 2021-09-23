<%@ page import="java.util.ArrayList" %>
<%@ page import="shop.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<%
    ArrayList<String> ChangeCategoryErrorList =  (ArrayList<String>)session.getAttribute("ChangeCategoryErrorList");
    Category cat = (Category) session.getAttribute("currentCategory");
%>

<div class="contentBg formTableHolder">
    <span>Edycja kategorii</span><br>
    <form method="get" action="ChangeCategory" accept-charset="utf-8">
        <input style="display: none" type="text" name="CatChange" value="true"/>
        <input style="display: none" type="text" name="id" value="<%=request.getParameter("id")%>"/>
        <table class="loginregister_table formTable">
            <tr>
                <td class="left_td">Nazwa</td>
                <td><input class="input" type="text" name="ChangeCategoryName" value="<%=cat != null ? cat.getName() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeCategoryErrorList != null && ChangeCategoryErrorList.get(0) != null ? ChangeCategoryErrorList.get(0) : ""%></span>
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