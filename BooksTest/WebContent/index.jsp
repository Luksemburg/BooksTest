<%@page import="book.store.testtask.ReaderPSQL"%>
<%@page import="book.store.testtask.BookDTO"%>
<%@page import="java.util.List"%>

<script type="text/javascript" >
	function get_data() {
		var a = document.getElementById("authors").value;
		var g = document.getElementById("genres").value;
		window.location.replace("index.jsp?a=" + a + "&g=" + g);
	}
</script>	

<%
	ReaderPSQL reader = new ReaderPSQL();
	List<String> authors = reader.getAuthors();
	List<String> genres = reader.getGenres();
	
	List<BookDTO> books = reader.getBooksWithFilter(request.getParameter("g"), request.getParameter("a"));
%>

<!DOCTYPE html>

<html>
	<head>
		<title>
			Welcome to Book Store "BackSlash"!
		</title>
	</head>
	
	<body>
	
		<table cellspasing="0" cellpadding="5">
			<tr>
				<td width="900" > 
					<h1><font color="Orchid" size="7">Book Store "BackSlash"</font></h1>	
						
					<font size="4">							
					
					Authors: 					
					<select id="authors" onchange="get_data();">
					  <option selected>All</option>	
					  <%
						for(int i = 0; i < authors.size(); i++){
							String temp = "";
							if(request.getParameter("a") != null && request.getParameter("a").equals(authors.get(i))){
								temp = " selected ";
							}
							out.println("<option" + temp + ">" + authors.get(i) + "</option>");
						}
					  %>
					</select>
					
					Genres: 
					<select id="genres" onchange="get_data();">
					  <option selected>All</option>	
					  <%						
					  	for(int i = 0; i < genres.size(); i++){
							String temp = "";
							if(request.getParameter("g") != null && request.getParameter("g").equals(genres.get(i))){
								temp = " selected ";
							}
							out.println("<option" + temp + ">" + genres.get(i) + "</option>");
						}
					  %>
					</select>
					
					<br><br>	
						
					</font>	
					
						<table cellspasing="0" cellpadding="5" border="1">
							<tr bgcolor="Thistle">
								<td> Title: <br> </td>
								<td> Authors: <br> </td>
								<td> Genres: <br> </td>
								<td> Price: <br> </td>
								<td> Info / Order <br> </td>
							</tr>	

							
								<%									
									for(int i = 0; i < books.size(); i++){
										
										out.println("<tr>");
										
											out.println("<td>");
											out.println(books.get(i).getTitle());
											//out.println("<br>");
											
											//out.println("<br>");
											out.println("</td>");
											
											
											out.println("<td>");
											for(int j = 0; j < books.get(i).getAuthors().size(); j++){
												out.println(books.get(i).getAuthors().get(j));
												out.println("<br>");
											}
											
											//out.println("<br>");
											out.println("</td>");
											
											
											out.println("<td>");
											for(int j = 0; j < books.get(i).getGenres().size(); j++){
												out.println(books.get(i).getGenres().get(j));
												out.println("<br>");
											}
											
											//out.println("<br>");
											out.println("</td>");
											
											
											out.println("<td>");
											out.println(books.get(i).getPrice());
											//out.println("<br>");
											
											//out.println("<br>");
											out.println("</td>");
											
											
											out.println("<td>");
												out.println("<form method=\"post\" action=\"html/order.jsp?bookid=" + books.get(i).getId() + "\">");
												out.println("<input  type=\"submit\" value=\"Details / Order\">");
												out.println("</form>");
											out.println("</td>");
										
										out.println("</tr>");
									}
								%>							
												
						</table>
				</td>
				
				<script type="text/javascript" >
					function filter() {
						alert(document.getElementById("genres").value);
					}
				</script>					
				
				<td> 
					<form action="html/restrict/admin.jsp" >
						<div style="text-align:right">
							<button style="vertical-align:center" name="signup" value="signup"> 
								<img src="images/note.png" style="vertical-align:center" width="30" height="30"/>
								<font style="vertical-align:center" color="Blue" size="6">Book Manager</font>													
							</button><br><br>
						</div>
					</form>
				</td>
				
			</tr>
		</table>	
			
	</body>
</html>