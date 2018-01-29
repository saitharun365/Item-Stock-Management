function requiredItem(){
	var itemName=document.item.itemName.value;
	var unit=document.item.unit.value;
	var unit=document.item.beginningInventory.value;
	var unit=document.item.qunatityOnHand.value;
	var unit=document.item.pricePerUnit.value;
	var unit=document.item.dateOfManufacture.value;
	var unit=document.item.dateOfExpiry.value;
	if(itemName==""){
		alert("Please Enter Item Name");
		document.item.itemName.focus();
		return false;
	}
	else if(unit==""){
		alert("Please enter Unit");
		document.item.unit.focus();
		return false;
	}
	else if(unit==""){
		alert("Please enter beginningInventory");
		document.item.beginningInventory.focus();
		return false;
	}
	else if(unit==""){
		alert("Please enter qunatityOnHand");
		document.item.qunatityOnHand.focus();
		return false;
	}
	else if(unit==""){
		alert("Please enter pricePerUnit");
		document.item.pricePerUnit.focus();
		return false;
	}
	else if(unit==""){
		alert("Please enter dateOfManufacture");
		document.item.dateOfManufacture.focus();
		return false;
	}
	else if(unit==""){
		alert("Please enter dateOfExpiry");
		document.item.dateOfExpiry.focus();
		return false;
	}
	else{
		return true;
	}
}