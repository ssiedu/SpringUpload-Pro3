<%@page isELIgnored="false" %>
<html>
<body>
	<h3>Customer-Details</h3>
	<hr>
	<pre>
		Code	${customer.code}
		Name	${customer.name}
		Adrs	${customer.address}
		Pict	<img src="LoadImage?code=${customer.code}"/>
	</pre>		
	<hr>
	<a href="search">Search-More</a><br>
	<a href="index.jsp">Home</a>
</body>
</html>