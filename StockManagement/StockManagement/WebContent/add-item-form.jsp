<!DOCTYPE html>
<html>

<head>
	<title>Add Item</title>
<style>
      input:invalid {
        border: 2px dashed red;
      }
      input:valid {
        border: 2px solid black;
      }
    </style>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
	
	<script type="text/javascript" src="js/validation.js"></script>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Stock Management</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Item</h3>
		
		<form action="StockControllerServlet" method="POST" name="item">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Item Name:</label></td>
						
						<td><input type="text" name="itemName" required="required" placeholder="Item Name" pattern="[a-zA-Z0-9\s\.]+"/></td>
					</tr>
					<tr>
						<td><label>Unit:</label></td>
						<td><input type="text" name="unit" required="required" placeholder="Eg 250 ml, 50 mg, 100 pcs"/></td>
					</tr>

					<tr>
						<td><label>Beginning_Inventory:</label></td>
						<td><input type="number" name="beginingInventory" required="required" placeholder="Beginning Inventory"/></td>
					</tr>

					<tr>
						<td><label>Quantity_on_Hand:</label></td>
						<td><input type="number" name="qunatityOnHand" required="required" placeholder="Quantity On Hand" /></td>
					</tr>
					
					<tr>
						<td><label>Price_per_Unit:</label></td>
						<td><input type="number" name="pricePerUnit" required="required" placeholder="Price Per Unit" step="0.01"/></td>
					</tr>
					
					<tr>
						<td><label>Date_Of_Manufacture:</label></td>
						<td><input type="date" name="dateOfManufacture" required="required" placeholder="Date Of" /></td>
					</tr>
					
					<tr>
						<td><label>Date_of_Expiry:</label></td>
						<td><input type="date" name="dateOfExpiry" required="required" /></td>
					</tr>
					
					<tr>
						<td><label>Location:</label></td>
						<td>
						<select name="location">
						<option>Bulk Zone</option>
						<option>Pick Zone</option>
						<option>Distribution Center</option>
						</select>
						</td>
					</tr>
					
					<tr>
						<td><label>Category:</label></td>
						<td>
						<select name="itemCategory">
						<option>Pharma Drug</option>
						<option>Health and Personal Care</option>
						<option>Baby Care </option>
						<option>Medical Supplies and Equipments</option>
						</select>
						</td>
					</tr>
					
					<tr>
						<td><label>Sub_Category:</label></td>
						<td>
						<select name="itemSubCategory">
						<option>Tablet</option>
						<option>Capsule</option>
						<option>Syrup</option>
						<option>Cream and Lotion</option>
						<option>Powders</option>
						<option>Hand and Foot Care</option>
						<option>Sun Care</option>
						<option>Face Pack and Cleanser</option>
						<option>Diaper and Wipe</option>
						<option>Soap</option>
						<option>Baby Powder</option>
						<option>Cereal and Formula Food Powder</option>
						<option>Medical Monitor</option>
						<option>Diagnostic</option>
						<option>Surgical Instrument</option>
						</select>
						</td>
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