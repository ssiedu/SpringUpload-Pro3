<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="jstl" %>

<html>
<body>
	<h3>Customer-List</h3>
	<hr>
	<table border="2">
		<tr>
			<th>Code</th><th>Name</th><th>Address</th><th>Picture</th>
		</tr>
		<jstl:forEach items="${customers}" var="customer">
		<tr> 
		 	<td>${customer.code}</td>
		 	<td>${customer.name}</td>
		 	<td>${customer.address}</td>
		 	<td><img width="50" height="50" src="LoadImage?code=${customer.code}"/></td>
		 	<td><a href="LoadIdentity?code=${customer.code}">IdPrf</a></td>
		</tr>
		</jstl:forEach>			
	</table>
	<hr>
	<a href="index.jsp">Home</a>
</body>
</html>