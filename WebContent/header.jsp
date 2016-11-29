

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- title of the Page--%>
        <title>Researchers Exchange Participations</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/main.css">
        <script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        
        <!-- BootStrap -->
        
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
        integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"> -->
          <link rel="stylesheet" href="styles/bootstrap.min.css">
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <%-- Code to specify Header section of the page--%>
        <div id="header">
            <nav id="header_menu">
                <ul  class="left" >
                
                <li> <a href="user?action=home" style= "display: block;padding:0.5em 1em; line-height: 1em;text-decoration: none;!important;color:#009933;">Researchers Exchange Participations</a></li>
                </ul>
                <ul class="right">
                    <c:if test="${theUser == null && theAdmin == null}">
                        <li><a href="user?action=about">About Us</a></li>
                        <li><a href="user?action=how">How it Works</a></li>
                        <li><a href="login.jsp">Login</a></li>
                    </c:if>
                        <c:if test="${theUser != null}">
                        <li><a href="user?action=about">About Us</a></li>
                        <li><a href="user?action=how">How it Works</a></li>
                        <li>Hello, ${theUser.name   }</li>
                        <li><a href="user?action=logout">Logout</a></li>
                        </c:if>
                        <c:if test="${theAdmin != null}">
                        <li><a href="user?action=about">About Us</a></li>
                        <li><a href="user?action=how">How it Works</a></li>
                        <li>Hello, ${theAdmin.name  } </li>
                        <li><a href="user?action=logout">Logout</a></li>
                        </c:if>
                </ul>

            </nav>



        </div>

