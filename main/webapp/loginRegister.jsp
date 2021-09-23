<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<% response.setContentType( "text/html; charset=utf-8" ); %>
<link rel="stylesheet" href="CSS/styleSub.css"/>


<div class="contentBg loginHolder">
    <span>Logowanie</span><br>
    <form method="post" action="Login" accept-charset="utf-8">
        <table class="loginregister_table">
            <tr>
                <td class="left_td">Email</td>
                <td><input class="input" type="text" name="Lemail" value="<%=request.getParameter("Lemail") != null ? request.getParameter("Lemail") : ""%>"></td>
            </tr>
            <tr>
                <td class="left_td">Hasło</td>
                <td><input class="input" type="password" name="Lpassword" value="<%=request.getParameter("Lpassword") != null ? request.getParameter("Lpassword") : ""%>"></td>
            </tr>
            <tr>
                <td></td>
                <td><input class="button" type="submit" value="zaloguj"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <%if (session.getAttribute("loginError") != null && (boolean)session.getAttribute("loginError")){ %>
                        <span style="color: darkred; font-size: 12px;">Niepoprawne dane logowania</span>
                    <%}%>
                </td>
            </tr>
        </table>
    </form>
</div>




<div class="contentBg registerHolder">
    <span>Rejestracja</span><br>
    <form method="post" action="Register" accept-charset="utf-8">
        <table class="loginregister_table">
            <tr>
                <td class="left_td">Email</td>
                <td class="middle_td"><input class="input" type="text" name="email" value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(0) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(0) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Hasło</td>
                <td class="middle_td"><input class="input" type="password" name="password" value="<%=request.getParameter("password") != null ? request.getParameter("password") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(1) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(1) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Imię</td>
                <td class="middle_td"><input class="input" type="text" name="first" value="<%=request.getParameter("first") != null ? request.getParameter("first") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(2) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(2) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Nazwisko</td>
                <td class="middle_td"><input class="input" type="text" name="last" value="<%=request.getParameter("last") != null ? request.getParameter("last") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(3) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(3) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Nr Telefonu</td>
                <td class="middle_td"><input class="input" type="number" name="phone" value="<%=request.getParameter("phone") != null ? request.getParameter("phone") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(4) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(4) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Kod pocztowy</td>
                <td class="middle_td"><input class="input" type="text" name="zipCode" value="<%=request.getParameter("zipCode") != null ? request.getParameter("zipCode") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(5) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(5) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Miasto</td>
                <td class="middle_td"><input class="input" type="text" name="city" value="<%=request.getParameter("city") != null ? request.getParameter("city") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(6) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(6) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Ulica</td>
                <td class="middle_td"><input class="input" type="text" name="street" value="<%=request.getParameter("street") != null ? request.getParameter("street") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(7) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(7) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Nr Budynku</td>
                <td class="middle_td"><input class="input" type="text" name="building" value="<%=request.getParameter("building") != null ? request.getParameter("building") : ""%>"></td>
                <td class="right_td">
                    <%if (session.getAttribute("RegisterError") != null){ %>
                        <span style="color: darkred; font-size: 12px;"><%=((ArrayList<String>)session.getAttribute("RegisterError")).get(8) != null ? ((ArrayList<String>)session.getAttribute("RegisterError")).get(8) : ""%></span>
                    <%}%>
                </td>
            </tr>
            <tr>
                <td class="left_td">Nr Lokalu</td>
                <td class="middle_td"><input class="input" type="text" name="apartment" value="<%=request.getParameter("apartment") != null ? request.getParameter("apartment") : ""%>"></td>
                <td class="right_td">

                </td>
            </tr>
            <tr>
                <td class="left_td"></td>
                <td class="middle_td"><input class="button" type="submit" value="Zarejestruj"></td>
                <td class="right_td"></td>
            </tr>
            <%--<%if (session.getAttribute("RegisterError") != null){
                ArrayList<String> errorList = (ArrayList<String>)session.getAttribute("RegisterError");
                for (String error: errorList ) { %>
                    <tr>
                        <td></td>
                        <td>
                            <span style="color: darkred; font-size: 12px;"><%=error%></span>
                        </td>
                    </tr>

            <%}}%>--%>
        </table>
    </form>
</div>



<jsp:include page="footer.jsp"/>