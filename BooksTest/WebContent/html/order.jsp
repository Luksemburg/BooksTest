<%@page import="book.store.testtask.ReaderPSQL"%>
<%@page import="book.store.testtask.BookDTO"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>

<html>
	<head>
		<title>
			Details and Order Here!
		</title>
	</head>
	
	<body>
		<h1><font color="Orchid" size="7">Book Store "BackSlash"</font></h1>
		<h3><font color="Orchid" size="6">Details:</font></h3>	

		<%
			ReaderPSQL reader = new ReaderPSQL();
			int id = Integer.parseInt(request.getParameter("bookid"));
			
			List<String> genr = reader.getGenresById(id);
			List<String> auth = reader.getAuthorsById(id);
		
			out.println("<font size=\"4\" >BOOK ID: <font color=\"LimeGreen\">");
			out.println(id);
			out.println("</font><br>");
			out.println("<br>");
			
			out.println("Title: <font color=\"LimeGreen\">");
			out.println(reader.getBookItem("title", id));
			out.println("</font><br>");
			out.println("<br>");
			
			out.println("Description: <font color=\"LimeGreen\">");
			out.println("<br>");
			out.println(reader.getBookItem("description", id));
			out.println("</font><br>");
			out.println("<br>");
			
			out.println("Price: <font color=\"LimeGreen\">");
			out.println(reader.getBookItem("price", id));
			out.println("</font><br>");
			out.println("<br>");
			
			
			out.println("List of Authors: ");			
			out.println("<font color=\"LimeGreen\"><ul>");
			for(int i = 0; i < auth.size(); i++){
				out.println("<li>" + auth.get(i));
			}
			out.println("</ul></font>");			
			out.println("<br>");
			
			out.println("Genres: ");			
			out.println("<font color=\"LimeGreen\"><ul>");
			for(int i = 0; i < genr.size(); i++){
				out.println("<li>" + genr.get(i));
			}
			out.println("</ul></font>");			
			out.println("<br></font>");
		%>
		
		<form method="post" action="../add_order?bookid=<%= request.getParameter("bookid")%>">
			First Name: <br>
			<input size="50" type="text" name="first_name" required /><br><br>
			Last Name: <br>
			<input size="50" type="text" name="last_name" required /><br><br>
			Address: <br>
			<input size="50" type="text" name="address" required /><br><br>
			Quantity of books: <br>
			<input type="number" id="quantity" name="quantity" min="1" max="1000" required />		<br><br>

			
			<button>
				<img src="../images/check.png" style="vertical-align:center" width="30" height="30"/>
				<font size="6" color="LimeGreen">Order!</font>
			</button>
			
		</form>
		
	</body>
</html>