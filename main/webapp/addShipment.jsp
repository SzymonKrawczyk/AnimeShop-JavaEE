<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<%
    ArrayList<String> AddShipmentErrorList =  (ArrayList<String>)session.getAttribute("AddShipmentErrorList");
    String AddShipmentName =  (String)session.getAttribute("AddShipmentName");
    String AddShipmentCost =  (String)session.getAttribute("AddShipmentCost");
%>

<div class="contentBg formTableHolder">
    <span>Dodawanie dostawy</span><br>
    <form method="get" action="AddShipment" accept-charset="utf-8">
        <input style="display: none" type="text" name="ShipNew" value="true"/>
        <table class="loginregister_table formTable">
            <tr>
                <td class="left_td">Nazwa</td>
                <td><input class="input" type="text" name="AddShipmentName" value="<%=AddShipmentName != null ? AddShipmentName : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddShipmentErrorList != null && AddShipmentErrorList.get(0) != null ? AddShipmentErrorList.get(0) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Koszt</td>
                <td><input class="input" type="text" name="AddShipmentCost" value="<%=AddShipmentCost != null ? AddShipmentCost : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=AddShipmentErrorList != null && AddShipmentErrorList.get(1) != null ? AddShipmentErrorList.get(1) : ""%></span>
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