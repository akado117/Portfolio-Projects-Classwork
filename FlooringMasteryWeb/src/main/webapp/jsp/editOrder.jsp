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

        <title>Edit Order Form</title>
    </head>
    <body>
        <div class="container col-sm-4 col-sm-offset-4">
            <h1>Edit Order Form</h1>

            <sf:form method="POST" action="updateOrder" commandName="order">
                <sf:label path="customerName">Name:</sf:label> <sf:input path="customerName" size="15"/><br/>
                <sf:label path="date">Date:</sf:label> <sf:input  path="date" size="15"/><br/>
                <sf:label path="area">Area:</sf:label> <sf:input  path="area" size="15"/><br/>
                <sf:label path="productType">Product Type: </sf:label><sf:select path="productType"  items="${productTypes}" /><br/>
                <sf:label path="State">State: </sf:label> <sf:select path="State" items="${states}" /> <br/>             
                <sf:hidden path="orderNumber"/>
                <input type="submit" class="btn btn-danger" value="Update Order"/><br/>

            </sf:form>
        </div>

        <!­­ jQuery (necessary for Bootstrap's JavaScript plugins) ­­>
        <script src="../js/jquery.js" ></script>
        <!­­ Include all compiled plugins, or include individual files as needed ­­>
        <script src="../js/bootstrap.js" ></script>

    </body>
</html>
