<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Item Stock Management</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Item Stock Management</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			
			
            <!--  add a search box -->
			<form action="StockControllerServlet" method="GET">
		
				<input type="hidden" name="command" value="SEARCH" />
			
                Search Item: <input type="text" name="theSearchName" placeholder="Item Name or Category"/>
                
                <input type="submit" value="Search" class="add-student-button" />
               
                <!-- put new button: Add Item -->
			
			<input type="button" value="Add Item" 
				   onclick="window.location.href='add-item-form.jsp'; return false;"
				   class="add-student-button"
			/>
            
            </form>

			<table>
			
				<tr>
					<th>ItemName</th>
					<th>Unit</th>
					<th>BeginningInventory</th>
					<th>QuantityOnHand</th>
					<th>PricePerUnit</th>
					<th>DateOfManufacture</th>
					<th>DateOfExpiry </th>
					<th>Location</th>
					<th>Category</th>
					<th>SubCategory</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempStock" items="${STOCK_LIST}">
					
					<!-- set up a link for each item -->
					<c:url var="tempLink" value="StockControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="itemCode" value="${tempStock.itemCode}" />
					</c:url>

					<!--  set up a link to delete a item -->
					<c:url var="deleteLink" value="StockControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="itemCode" value="${tempStock.itemCode}" />
					</c:url>
																		
					<tr>
						<td> ${tempStock.itemName} </td>
						<td> ${tempStock.unit} </td>
						<td> ${tempStock.beginningInventory} </td>
						<td> ${tempStock.qunatityOnHand} </td>
						<td> ${tempStock.pricePerUnit} </td>
						<td> ${tempStock.dateOfManufacture} </td>
						<td> ${tempStock.dateOfExpiry} </td>
						<td> ${tempStock.location} </td>
						<td> ${tempStock.itemCategory} </td>
						<td> ${tempStock.itemSubCategory} </td>
						<td> 
							<a href="${tempLink}">Update</a>|<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








