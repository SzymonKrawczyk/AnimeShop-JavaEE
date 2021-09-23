<%@ page import="shop.Server" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=session.getAttribute("title")%></title>
    <link rel="stylesheet" href="CSS/styleMain.css"/>
    <link rel="stylesheet" href="CSS/styleSub.css"/>
    <meta charset="UTF-8"/>
    <%--<link rel="stylesheet" href="CSS/styleSub.css"/>--%>
</head>
<body>
<div class="headerBg">
    <div class="header">
        <a href="Katalog"> <div class="menuLogo"><img src="media/Logo/logo.jpg"/></div> </a>

        <a href="Koszyk"><div class="menuSpacer menuButton">Koszyk (<%=Server.getCartSize(request)%>)</div></a>
        <a href="Konto"> <div class="menuSpacer menuButton">Konto</div> </a>

        <div class="menuSpacer"></div>

        <a href="Onas"> <div class="menuSpacer menuButton">O nas</div></a>
        <a href="Katalog"> <div class="menuSpacer menuButton">Katalog</div> </a>
    </div>
</div>


<div onclick="goToTop()" id="goToTopButton">^</div>
<script>
    // https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_scroll_to_top


    //Get the button
    var mybutton = document.getElementById("goToTopButton");

    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function() {scrollFunction()};

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            mybutton.style.display = "block";
        } else {
            mybutton.style.display = "none";
        }
    }

    // When the user clicks on the button, scroll to the top of the document
    function goToTop() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }
</script>
<div class="contentHolder">