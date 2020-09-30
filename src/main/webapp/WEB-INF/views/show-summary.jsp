<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Summary</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body>
<h1 class="jumbotron" align="center">ORDER SUMMARY</h1>

<div class="alert alert-success">
<h1>Your order Submitted successfully.</h1>
</div>
<div class="alert alert-info">
<h3>USER DETAILS</h3>
</div>
 <section class="container-fluid p-4">
<div>USER NAME: ${username}</div>
<div>EMAIL ID: ${email}</div>
<div>CONTACT NUMBER: ${contactnumber}</div>
</section>

<div class="alert alert-info">
<h3>User Address</h3>
</div>
 <section class="container-fluid p-4">
<div>Address : ${Address}</div>
<div>City: ${City}</div>
<div>State: ${State}</div>
</section>
<div class="alert alert-info">
<h3>Product Summary</h3>
</div>
            <table class="table table-striped table-hover">  
                <tr>  
                    <th>Corona Kit ID#</th>  
                    <th>Product ID</th>  
                    <th>Product Name</th>  
                    <th>Quantity</th>  
                    <th>Total Cost</th>   
                    
                                        
                </tr>  
                <c:forEach items="${kitdetails}" var="kitdetail">  
                  <c:if test="${quantitymap.get(kitdetail.productId)!=0}">
                    <tr>  
                        <td>${kitdetail.coronaKitId }</td>  
                        <td>${kitdetail.productId}</td>  
                        <td>${kitdetail.productFullName}</td>  
                        <td>${kitdetail.quantity }</td>  
                        <td>${kitdetail.amount}</td>               
                
                    </tr>                             
                 </c:if>
                </c:forEach>  
            </table> 

<div class="alert alert-success">
<h4>Total Amount to pay: ${Totalamount} INR</h4>
</div>
<br/>

<br/>
</body>
</html>