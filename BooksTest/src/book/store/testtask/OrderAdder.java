package book.store.testtask;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_order")
public class OrderAdder extends HttpServlet {
	private static final long serialVersionUID = 145545151576764L;
       
    public OrderAdder() {
        super();        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WriterDB writer = new WriterPSQL();		
		
		try {
			writer.writeOrder(request.getParameter("first_name"), request.getParameter("last_name"), request.getParameter("address"), 
						request.getParameter("quantity"), request.getParameter("bookid"));
			//throw new Exception();
			
			response.setContentType("text/html");
	        PrintWriter pw = response.getWriter();
	        try {
	        	pw.println("<h2><font color=\"Green\">Your order was registred successfully</font></h2>" + 
	        					"<a href=\"index.jsp\">Back to store</a>");
	        } finally {
	        	pw.close();  
	        }
		
		
		}catch(Exception e) {
			 response.setContentType("text/html");
		        PrintWriter pw = response.getWriter();
		        try {
		        	pw.println("<h2><font color=\"Red\">Error occurs while register your order</font></h2>" + 
		        					"<a href=\"index.jsp\">Back to store</a>");
		        } finally {
		        	pw.close();  
		        }
		}
	}

}
