<%@ page import="java.util.ArrayList" %>
<%@ page import="shop.Shipment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<link rel="stylesheet" href="CSS/styleSub.css"/>

<%
    ArrayList<String> ChangeShipmentErrorList =  (ArrayList<String>)session.getAttribute("ChangeShipmentErrorList");
    Shipment ship = (Shipment) session.getAttribute("currentShipment");
%>

<div class="contentBg formTableHolder">
    <span>Edycja dostawy</span><br>
    <form method="get" action="ChangeShipment" accept-charset="utf-8">
        <input style="display: none" type="text" name="ShipChange" value="true"/>
        <input style="display: none" type="text" name="id" value="<%=request.getParameter("id")%>"/>
        <table class="loginregister_table formTable">
            <tr>
                <td class="left_td">Nazwa</td>
                <td><input class="input" type="text" name="ChangeShipmentName" value="<%=ship != null ? ship.getName() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeShipmentErrorList != null && ChangeShipmentErrorList.get(0) != null ? ChangeShipmentErrorList.get(0) : ""%></span>
                </td>
            </tr>
            <tr>
                <td class="left_td">Koszt</td>
                <td><input class="input" type="text" name="ChangeShipmentCost" value="<%=ship != null ? ship.getCost() : ""%>"></td>
                <td>
                    <span style="color: darkred; font-size: 12px;"><%=ChangeShipmentErrorList != null && ChangeShipmentErrorList.get(1) != null ? ChangeShipmentErrorList.get(1) : ""%></span>
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