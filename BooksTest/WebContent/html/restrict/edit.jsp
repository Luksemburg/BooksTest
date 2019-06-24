<%@page import="book.store.testtask.ReaderPSQL"%>
<%@page import="book.store.testtask.BookDTO"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>

<html>
	<head>
		<title>
			Edit book here!
		</title>
	</head>	
		
	<body>
		<h1><font color="Blue" size="7">Edit Book</font></h1>
		<!-- <h3><font color="Orchid" size="6">Details:</font></h3>	-->
		
		<form method="post" action="../../delete_book?bookid=<%= request.getParameter("bookid")%>">
			<button>
				<img src="../../images/exclamation.png" style="vertical-align:center" width="25" height="25"/>
				<font size="5" color="Red">Delete this book!</font>
			</button>			
		</form><br><br>
		
		<form method="post" action="../../edit_book?bookid=<%= request.getParameter("bookid")%>">

		<%
			ReaderPSQL reader = new ReaderPSQL();
			int id = Integer.parseInt(request.getParameter("bookid"));
			
			List<String> genr = reader.getGenresById(id);
			List<String> auth = reader.getAuthorsById(id);
		
			out.println("<font size=\"4\" >BOOK ID: <font color=\"Blue\">");
			out.println(id);
			out.println("</font><br>");
			out.println("<br>");
			
			out.println("Title:<br> <input name=\"title\" type=\"text\" size=\"50\" required value=\"");
			out.println(reader.getBookItem("title", id));
			out.println("\"/><br>");
			out.println("<br>");
			
			out.println("Price:<br> <input name=\"price\" type=\"text\" size=\"50\" required value=\"");
			out.println(reader.getBookItem("price", id));
			out.println("\"/><br>");
			out.println("<br>");
			
						
			out.println("Description:<br> <textarea name=\"description\" rows=\"5\" cols=\"50\" required>");			
			out.println(reader.getBookItem("description", id));
			out.println("</textarea><br>");
			out.println("<br>");
			
			
			out.println("List of Authors: <br>");			
			out.println("<textarea name=\"authors\" rows=\"5\" cols=\"50\" required>");
			for(int i = 0; i < auth.size(); i++){
				out.print(auth.get(i));								
				out.print(", ");
			}
			out.println("</textarea>");			
			out.println("<br><br>");
			
			out.println("Genres: <br>");			
			out.println("<textarea name=\"genres\" rows=\"5\" cols=\"50\" required>");
			for(int i = 0; i < genr.size(); i++){
				out.print(genr.get(i));
				out.print(", ");				
			}
			out.println("</textarea>");			
			out.println("<br></font><br>");
		%>	
		
		
			<button>
				<img src="../../images/check.png" style="vertical-align:center" width="30" height="30"/>
				<font size="6" color="LimeGreen">Apply!</font>
			</button>			
		</form>	
				
	</body>
</html>