package book.store.testtask;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/delete_book")
public class BookDeleter extends HttpServlet {
	private static final long serialVersionUID = 1004272240002722L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WriterDB writer = new WriterPSQL();	
		
		try {
			
			writer.deleteBook(request.getParameter("bookid"));
			
			response.setContentType("text/html");
	        PrintWriter pw = response.getWriter();
	        try {
	        	pw.println("<h2><font color=\"Green\">Book was deleted successfully</font></h2>" + 
	        					"<a href=\"html/restrict/admin.jsp\">Back to manager</a>");
	        } finally {
	        	pw.close();  
	        }
			
			
		}catch(Exception e) {
			e.printStackTrace();
			 response.setContentType("text/html");
		        PrintWriter pw = response.getWriter();
		        try {
		        	pw.println("<h2><font color=\"Red\">Error occurs while deleting book</font></h2>" + 
		        					"<a href=\"html/restrict/admin.jsp\">Back to manager</a>");
		        } finally {
		        	pw.close();  
		        }
		}
	}

}
