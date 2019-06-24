
<!DOCTYPE html>

<html>
	<head>
		<title>
			Add new book here!
		</title>
	</head>
	
	<body>
		<h1><font color="Blue" size="7">Add Book</font></h1>
		<!-- <h3><font color="Orchid" size="6">Details:</font></h3>	-->
		
		<form method="post" action="../../add_book">
			Title: <br>
			<input size="50" type="text" name="title" required /><br><br>					
			Price: <br>
			<input type="number" name="price" min="10" max="10000" required />		<br><br>
			
			Description: <br>			
			<textarea name="description" rows="5" cols="50" required ></textarea> <br><br>
			
			Genres (use ', ' as separator): <br>
			<textarea name="genres" rows="5" cols="50" required ></textarea> <br><br>
			
			Authors (use ', ' as separator): <br>
			<textarea name="authors" rows="5" cols="50" required ></textarea> <br><br>
			
			<button>
				<img src="../../images/plus_green.png" style="vertical-align:center" width="30" height="30"/>
				<font size="6" color="LimeGreen">ADD!</font>
			</button>
			
		</form>

		
	</body>
</html>