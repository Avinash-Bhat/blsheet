<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.security.MessageDigest"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String message = request.getParameter("message");
	session.invalidate();
%>
<html>
<head>
<title>Balance Sheet - Login</title>
<script src="resources/jQuery/jquery.js" type="text/javascript"></script>

</head>
<script type="text/javascript">
<%if(message != null){%>
	alert("<%=message%>");
<%}%>
</script>
<body>
	<div>
		<h4 style="color: green;">Home Management</h4>
		<form id="form" action="LoginServlet" method="post" >
			<table border="0">
				<tr>
					<td><span style="color:blue;">Sign in</span></td>
					<td></td>
				</tr>
				<tr>
					<td>Username</td>
					<td><input type="text" name="name" id="username"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<tr>
					<td></td>
					<td><input id="submit" type="submit"
						value="&nbsp;Sign In&nbsp;" name="submit" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>