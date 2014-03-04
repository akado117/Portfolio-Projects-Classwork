<%-- 
    Document   : contactList
    Created on : Feb 21, 2014, 3:05:44 PM
    Author     : apprentice
--%>
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


        <title>Addresses</title>
    </head>
    <body>

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Orders for ${date}</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">

                        <li><a href="../index.jsp">Home</a></li>


                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>



        
        <div class="col-sm-10 col-sm-offset-1" style="padding-top: 50px;" >
            <div class="col-sm-12"
                 <h1 style="font-size: 32px;">Orders for ${date}</h1>
            </div>
            <div class="col-sm-12" style="padding-bottom: 25px;">
                <button class="btn btn-danger " data-toggle="modal" data-target="#addOrder">
                    <span class="glyphicon-plus"> Add a new order </span> 
                </button>
                <button class="btn btn-danger " data-toggle="modal" data-target="#searchModal">
                    <span class="glyphicon-asterisk"> Search for orders from date </span> 
                </button>
            </div>

            <table class="table table-striped table-bordered">
                <tr>
                    <th>Customer Name</th>
                    <th>Date</th>
                    <th>State</th>
                    <th>Product Type</th>
                    <th>Area</th>                    
                    <th>Cost/SqFt</th>
                    <th>Labor/SqFt </th>
                    <th>Material Cost</th>
                    <th>Labor Cost</th>
                    <th>Tax Rate</th>
                    <th>Tax</th>
                    <th>Total</th>
                    <th></th>


                </tr>
                <c:forEach var="order" items="${orders}">

                    <tr>
                        <td>${order.customerName}</td>
                        <td>${order.date}</td> 
                        <td>${order.state}</td> 
                        <td>${order.productType}</td> 
                        <td>${order.area}</td> 
                        <td>${order.cPSQFT}</td> 
                        <td>${order.lCPSQFT}</td> 
                        <td>${order.materialCost}</td> 
                        <td>${order.laborCost}</td> 
                        <td>${order.taxRate}</td> 
                        <td>${order.tax}</td> 
                        <td>${order.total}</td> 
                        <td><a href="deleteOrder?orderId=${order.orderNumber}">Delete</a>
                            | <a href="updateOrder?orderId=${order.orderNumber}">Update</a></td>
                    </tr>
                </c:forEach>
            </table>


            <!-- Button trigger modal -->

        </div>
        <!--
        <!-- Modal for add contact-->

        <div class="modal fade" id="addOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="POST" action="addOrder">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Add an Order</h4>
                        </div>
                        <div class="modal-body" >

                            <div class="form-group">
                                <label for="aname">Name:</label>
                                <input name="addName" type="text" class="form-control" id="aname" placeholder="Enter name">
                            </div>

                            <div class="form-group">
                                <label for="aarea">Area:</label>
                                <input name="addAArea" type="text" class="form-control" id="aarea" placeholder="Enter Area (in SqFt)">
                            </div>
                            <div class="form-group">
                                <label for="adate">Date:</label>
                                <input name="addDate" type="text" class="form-control" id="adate" placeholder="Enter date (MMDDYYYY)">
                            </div>
                            <div class="form-group">
                                <label for="astate">State:</label>
                                <select name="addState"   id="astate" >
                                    <c:forEach var="state" items="${states}">
                                        <option value="${state}">${state}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <div class="form-group">
                                <label for="atype">Product Type:</label>
                                <select name="addProductType"   id="atype" >
                                    <c:forEach var="product" items="${productTypes}">
                                        <option value="${product}">${product}</option>
                                    </c:forEach>

                                </select>
                            </div>




                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <input type="submit" value="Add Order" class="btn btn-danger"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <!-- Modal for search contact-->


        <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="POST" action="searchOrder">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Search Dates in Database</h4>
                        </div>
                        <div class="modal-body" >

                            <div class="form-group">
                                <label for="atype">Date:</label>
                                <select name="addDateSearch"   id="atype" >
                                    <c:forEach var="date" items="${dates}">
                                        <option value="${date}">${date}</option>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <input type="submit" value="Search Addresses" class="btn btn-danger"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>





    </div>   
    <!­­ jQuery (necessary for Bootstrap's JavaScript plugins) ­­>
    <script src="../js/jquery.js" ></script>
    <!­­ Include all compiled plugins, or include individual files as needed ­­>
    <script src="../js/bootstrap.js" ></script>

</body>
</html>
