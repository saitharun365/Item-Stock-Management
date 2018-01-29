package com.saitharun.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.saitharun.data.StockDbUtil;
import com.saitharun.model.Stock;

/**servlet implementation class StockControllerServlet
 */
@WebServlet("/StockControllerServlet")
public class StockControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StockDbUtil stockDbUtil;
	
	@Resource(name="jdbc/stock_management")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our stock db util ... and pass in the conn pool / datasource
		try {
			stockDbUtil = new StockDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listStocks(request, response);
				break;
				
				
			case "LOAD":
				loadStock(request, response);
				break;
				
			case "UPDATE":
				updateStock(request, response);
				break;
			
			case "DELETE":
				deleteStock(request, response);
				break;

			case "SEARCH":
				searchStocks(request, response);
				break;
				
			default:
				listStocks(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void searchStocks(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read search name from form data
		String theSearchName = request.getParameter("theSearchName");
		
		// search students from db util
		List<Stock> stocks = stockDbUtil.searchStocks(theSearchName);
		
		// add students to the request
		request.setAttribute("STOCK_LIST", stocks);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-items.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
					
			// route to the appropriate method
			switch (theCommand) {
							
			case "ADD":
				addStock(request, response);
				break;
								
			default:
				listStocks(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	
	private void deleteStock(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String theItemCode = request.getParameter("itemCode");
		
		// delete student from database
		stockDbUtil.deleteStock(theItemCode);
		
		// send them back to "list students" page
		listStocks(request, response);
	}

	private void updateStock(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student info from form data
		int itemCode=Integer.parseInt(request.getParameter("itemCode"));
		String itemName = request.getParameter("itemName");
		String unit=request.getParameter("unit");
		int beginningInventory = Integer.parseInt(request.getParameter("beginningInventory"));
		int qunatityOnHand = Integer.parseInt(request.getParameter("qunatityOnHand"));
		double pricePerUnit = Double.parseDouble(request.getParameter("pricePerUnit"));	
		String dateOfManufacture = request.getParameter("dateOfManufacture");	
		String dateOfExpiry = request.getParameter("dateOfExpiry");	
		String location=request.getParameter("location");
		String itemCategory = request.getParameter("itemCategory");	
		String itemSubCategory = request.getParameter("itemSubCategory");
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Date dom=new Date();
		dom=sf.parse(dateOfManufacture);
		
		Date doe=new Date();
		doe=sf.parse(dateOfExpiry);
		
		// create a new student object
		Stock theStock = new Stock(itemCode,itemName,unit, beginningInventory, qunatityOnHand, pricePerUnit, dom, doe, location, itemCategory, itemSubCategory);
		
		// perform update on database
		stockDbUtil.updateStock(theStock);
		
		// send them back to the "list students" page
		listStocks(request, response);
		
	}

	private void loadStock(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read student id from form data
		String theItemCode = request.getParameter("itemCode");
		
		// get student from database (db util)
		Stock theStock = stockDbUtil.getItem(theItemCode);
		
		// place student in the request attribute
		request.setAttribute("THE_STUDENT", theStock);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-item-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addStock(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String itemName = request.getParameter("itemName");
		String unit=request.getParameter("unit");
		int beginingInventory = Integer.parseInt(request.getParameter("beginingInventory"));
		int qunatityOnHand = Integer.parseInt(request.getParameter("qunatityOnHand"));
		double pricePerUnit = Double.parseDouble(request.getParameter("pricePerUnit"));	
		String dateOfManufacture = request.getParameter("dateOfManufacture");	
		String dateOfExpiry = request.getParameter("dateOfExpiry");	
		String location=request.getParameter("location");
		String itemCategory = request.getParameter("itemCategory");	
		String itemSubCategory = request.getParameter("itemSubCategory");
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Date dom=new Date();
		dom=sf.parse(dateOfManufacture);
		
		Date doe=new Date();
		doe=sf.parse(dateOfExpiry);
		
		
		// create a new student object
		Stock theStock = new Stock(itemName,unit, beginingInventory, qunatityOnHand, pricePerUnit, dom, doe, location, itemCategory, itemSubCategory);
			
		// add the student to the database
		stockDbUtil.addStock(theStock);
				
		// send back to main page (the student list)
		// SEND AS REDIRECT to avoid multiple-browser reload issue
		response.sendRedirect(request.getContextPath() + "/StockControllerServlet?command=LIST");
	}

	private void listStocks(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Stock> stocks = stockDbUtil.getStocks();
		
		// add students to the request
		request.setAttribute("STOCK_LIST", stocks);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-items.jsp");
		dispatcher.forward(request, response);
	}

}