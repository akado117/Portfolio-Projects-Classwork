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
                    <a class="navbar-brand" href="#">Address in Book</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">

                        <li><a href="../index.jsp">Home</a></li>


                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>



        <p><br></p>
        <p><br></p>
        <div class="col-lg-4 col-lg-offset-4" >
            
                <h1>We found ${numContacts} addresses</h1>
                <c:forEach var="address" items="${addresses}">
                    ${address.first} ${address.last} | <a href="deleteAddress?addressId=${address.addressId}">Delete</a>
                    | <a href="editAddress?addressId=${address.addressId}">Update</a><br/>
                    ${address.street}<br>
                    ${address.city}, ${address.state} ${address.zip}<br><br>

                </c:forEach>
            

            <!-- Button trigger modal -->
            <button class="btn btn-danger " data-toggle="modal" data-target="#myModal">
                <span class="glyphicon-plus"> Add a new address </span> 
            </button>
            <button class="btn btn-danger " data-toggle="modal" data-target="#searchModal">
                <span class="glyphicon-asterisk"> Search for addresses </span> 
            </button>

            <!-- Modal for add contact-->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form method="POST" action="addAddress">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Add an Address</h4>
                            </div>
                            <div class="modal-body" style="text-align: left;">

                                First Name: <input type="text" name="addFirst"/><br/>
                                Last Name: <input type="text" name="addLast"/><br/>

                                Street: <input type="text" name="addStreet"/><br/>
                                City: <input type="text" name="addCity"/><br/>
                                State (SS): <input type="text" name="addState"/><br/>
                                Zip: <input type="text" name="addZip"/><br/>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <input type="submit" value="Add Address" class="btn btn-primary"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Modal for search contact-->
            <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <sf:form method="POST" action="searchAddress" commandName="search">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Add an Address</h4>
                            </div>
                            <div class="modal-body" style="text-align: left;">


                                <sf:label path="first">First Name:</sf:label> <sf:input path="first" size="15"/><br/>
                                <sf:label path="last">Last Name:</sf:label> <sf:input  path="last" size="15"/><br/>
                                <sf:label path="street">Street:</sf:label> <sf:input  path="street" size="15"/><br/>
                                <sf:label path="city">City:</sf:label> <sf:input  path="city" size="15"/><br/>
                                <sf:label path="state">State:</sf:label> <sf:input  path="state" size="15"/><br/>
                                <sf:label path="zip">Zip:</sf:label> <sf:input  path="zip" size="15"/><br/>



                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <input type="submit" value="Search Addresses" class="btn btn-primary"/>
                                </div>
                        </sf:form>
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
