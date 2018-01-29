<!DOCTYPE html>
<html>

<head>
	<title>Update Item</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
	<script type="text/javascript" src="js/validation.js"></script>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Item Stock Management</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Item</h3>
		
		<form action="StockControllerServlet" method="GET" name="item">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="itemCode" value="${THE_STUDENT.itemCode}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Item Name:</label></td>
						<td><input type="text" name="itemName" value="${THE_STUDENT.itemName }" /></td>
					</tr>
					
					<tr>
						<td><label>Unit:</label></td>
						<td><input type="text" name="unit" value="${THE_STUDENT.unit }" /></td>
					</tr>

					<tr>
						<td><label>Beginning Inventory:</label></td>
						<td><input type="text" name="beginningInventory" value="${THE_STUDENT.beginningInventory }" /></td>
					</tr>

					<tr>
						<td><label>Quantity on Hand:</label></td>
						<td><input type="text" name="qunatityOnHand" value="${THE_STUDENT.qunatityOnHand }" /></td>
					</tr>
					
					<tr>
						<td><label>Price per Unit:</label></td>
						<td><input type="text" name="pricePerUnit" value="${THE_STUDENT.pricePerUnit }" /></td>
					</tr>
					
					<tr>
						<td><label>Date Of Manufacture:</label></td>
						<td><input type="text" name="dateOfManufacture" value="${THE_STUDENT.dateOfManufacture }"  /></td>
					</tr>
					
					<tr>
						<td><label>Date of Expiry:</label></td>
						<td><input type="text" name="dateOfExpiry" value="${THE_STUDENT.dateOfExpiry }" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" onclick="return requiredItem();" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="StockControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











