package book.store.testtask;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_book")
public class BookAdder extends HttpServlet {
	private static final long serialVersionUID = 17777484818187817L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WriterDB writer = new WriterPSQL();		
		
		try {
			writer.writeBook(request.getParameter("title"), request.getParameter("price"), request.getParameter("description"), 
						request.getParameter("genres"), request.getParameter("authors"));
			//throw new Exception();
			
			response.setContentType("text/html");
	        PrintWriter pw = response.getWriter();
	        try {
	        	pw.println("<h2><font color=\"Green\">New book was added successfully</font></h2>" + 
	        					"<a href=\"html/restrict/admin.jsp\">Back to manager</a>");
	        } finally {
	        	pw.close();  
	        }
		
		
		}catch(Exception e) {
			e.printStackTrace();
			 response.setContentType("text/html");
		        PrintWriter pw = response.getWriter();
		        try {
		        	pw.println("<h2><font color=\"Red\">Error occurs while adding new book</font></h2>" + 
		        					"<a href=\"html/restrict/admin.jsp\">Back to manager</a>");
		        } finally {
		        	pw.close();  
		        }
		}
	}

}
