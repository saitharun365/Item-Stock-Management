package com.saitharun.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.saitharun.model.Stock;

public class StockDbUtil {

	private DataSource dataSource;

	public StockDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Stock> getStocks() throws Exception {
		
		List<Stock> stocks = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			
			// create sql statement
			String sql = "select * from stock order by item_name";
			
			//String sql="SELECT item_code, item_name, beginning_inventory, quantity_on_hand, price_per_unit, date(date_of_manufacture), date(date_of_expiry), location, item_category, item_sub_category FROM stock";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int itemCode = myRs.getInt("item_code");
				String itemName = myRs.getString("item_name");
				String unit=myRs.getString("unit");
				int beginningInventory =myRs.getInt("beginning_inventory");
				int quantityOnHand= myRs.getInt("quantity_on_hand");
				double pricePerUnit= myRs.getDouble("price_per_unit");
				Date dateOfManufacture=myRs.getDate("date_of_manufacture");
				Date dateOfExpiry=myRs.getDate("date_of_expiry");
				String location =myRs.getString("location");
				String itemCategory=myRs.getString("item_category");
				String itemSubCategory=myRs.getString("item_sub_category");
				
				
				
				// create new student object
				Stock tempStock = new Stock(itemCode, itemName,unit, beginningInventory, quantityOnHand, pricePerUnit, dateOfManufacture, dateOfExpiry, location, itemCategory, itemSubCategory);
				
				// add it to the list of students
				stocks.add(tempStock);				
			}
			
			return stocks;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	
	public void addStock(Stock theStock) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into stock "
					   + "(item_name,unit, beginning_inventory, quantity_on_hand,price_per_unit,date_of_manufacture,date_of_expiry,location,item_category,item_sub_category) "
					   + "values (?, ?, ?, ?,?,?,?,?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, theStock.getItemName());
			myStmt.setString(2,theStock.getUnit());
			myStmt.setInt(3, theStock.getBeginningInventory());
			myStmt.setInt(4, theStock.getQunatityOnHand());
			myStmt.setDouble(5, theStock.getPricePerUnit());
			myStmt.setTimestamp(6, new java.sql.Timestamp(theStock.getDateOfManufacture().getTime()));
			myStmt.setTimestamp(7, new java.sql.Timestamp(theStock.getDateOfExpiry().getTime()));
			myStmt.setString(8, theStock.getLocation());
			myStmt.setString(9, theStock.getItemCategory());
			myStmt.setString(10, theStock.getItemSubCategory());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Stock getItem(String theStudentId) throws Exception {

		Stock theStock = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from stock where item_code=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				
				String itemName = myRs.getString("item_name");
				String unit = myRs.getString("unit");
				int beginningInventory =myRs.getInt("beginning_inventory");
				int quantityOnHand= myRs.getInt("quantity_on_hand");
				int pricePerUnit= myRs.getInt("price_per_unit");
				Date dateOfManufacture=myRs.getDate("date_of_manufacture");
				Date dateOfExpiry=myRs.getDate("date_of_expiry");
				String location =myRs.getString("location");
				String itemCategory=myRs.getString("item_category");
				String itemSubCategory=myRs.getString("item_sub_category");
				
				// use the studentId during construction
				theStock = new Stock(studentId, itemName,unit, beginningInventory, quantityOnHand, pricePerUnit, dateOfManufacture, dateOfExpiry, location, itemCategory, itemSubCategory);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}				
			
			return theStock;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateStock(Stock theStock) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update stock "
						+ "set item_name=?,unit=?, beginning_inventory=?, quantity_on_hand=?, price_per_unit=?, date_of_manufacture=?, date_of_expiry=? "
						+ "where item_code=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theStock.getItemName());
			myStmt.setString(2, theStock.getUnit());
			myStmt.setInt(3, theStock.getBeginningInventory());
			myStmt.setInt(4, theStock.getQunatityOnHand());
			myStmt.setDouble(5, theStock.getPricePerUnit());
			myStmt.setTimestamp(6, new java.sql.Timestamp(theStock.getDateOfManufacture().getTime()));
			myStmt.setTimestamp(7, new java.sql.Timestamp(theStock.getDateOfExpiry().getTime()));
			myStmt.setInt(8, theStock.getItemCode());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteStock(String theItemCode) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int itemCode = Integer.parseInt(theItemCode);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from stock where item_code=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, itemCode);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}

	public List<Stock> searchStocks(String theSearchName)  throws Exception {

		List<Stock> stocks = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			
			// get connection to database
			myConn = dataSource.getConnection();
			
	        //
	        // only search by item name if theSearchName is not empty
	        //
			if (theSearchName != null && theSearchName.trim().length() > 0) {

				// create sql to search for stocks by name
				String sql = "select * from stock where lower(item_name) like ? or lower(item_category) like ?";

				// create prepared statement
				myStmt = myConn.prepareStatement(sql);

				// set params
				String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
				myStmt.setString(1, theSearchNameLike);
				myStmt.setString(2, theSearchNameLike);
				
			} else {
				// create sql to get all stocks
				String sql = "select * from stock order by item_name";

				// create prepared statement
				myStmt = myConn.prepareStatement(sql);
			}
	        
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			while (myRs.next()) {
				
				int itemCode = myRs.getInt("item_code");
				String itemName = myRs.getString("item_name");
				String unit = myRs.getString("unit");
				int beginningInventory =myRs.getInt("beginning_inventory");
				int quantityOnHand= myRs.getInt("quantity_on_hand");
				int pricePerUnit= myRs.getInt("price_per_unit");
				Date dateOfManufacture=myRs.getDate("date_of_manufacture");
				Date dateOfExpiry=myRs.getDate("date_of_expiry");
				String location =myRs.getString("location");
				String itemCategory=myRs.getString("item_category");
				String itemSubCategory=myRs.getString("item_sub_category");
				
				// create new stock object
				Stock tempStock = new Stock(itemCode, itemName,unit, beginningInventory, quantityOnHand, pricePerUnit, dateOfManufacture, dateOfExpiry, location, itemCategory, itemSubCategory);
				
				// add it to the list of stocks
				stocks.add(tempStock);				
				
				
			}
			
			return stocks;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
}















