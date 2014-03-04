<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!­­ Bootstrap ­­>
        <link  href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
        <!­­ HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries ­­>
        <!­­[if lt IE 9]>
        <script src="../../assets/js/html5shiv.js"></script>
        <script src="../../assets/js/respond.min.js"></script>
        <![endif]­­>

        <title>Address Form</title>
    </head>
    <body>
        <div class="container col-lg-4 col-lg-offset-4">
            <h1>Update Address Form</h1>

            <sf:form method="POST" action="updateAddress" commandName="address">
                <sf:label path="first">First Name:</sf:label> <sf:input path="first" size="15"/><br/>
                <sf:label path="last">Last Name:</sf:label> <sf:input  path="last" size="15"/><br/>
                <sf:label path="street">Street:</sf:label> <sf:input  path="street" size="15"/><br/>
                <sf:label path="city">City:</sf:label> <sf:input  path="city" size="15"/><br/>
                <sf:label path="state">State:</sf:label> <sf:input  path="state" size="15"/><br/>
                <sf:label path="zip">Zip:</sf:label> <sf:input  path="zip" size="15"/><br/>
                <sf:hidden path="addressId"/>
                <input type="submit" class="btn btn-danger" value="Update Address"/><br/>

            </sf:form>
        </div>

        <!­­ jQuery (necessary for Bootstrap's JavaScript plugins) ­­>
        <script src="../js/jquery.js" ></script>
        <!­­ Include all compiled plugins, or include individual files as needed ­­>
        <script src="../js/bootstrap.js" ></script>

    </body>
</html>
