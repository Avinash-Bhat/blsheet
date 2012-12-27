<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.security.MessageDigest"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String message = (String)session.getAttribute("message");
	if(message == null) {
		message = request.getParameter("message");
	}
	if(message == null) {
		message = "";
	}
	if(!session.isNew()) {
		session.invalidate();
	} else {
		
	}
%>
<html>
<head>
<title>Balance Sheet - Login</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/jQuery/theme/default/jquery-ui.css" />
<script src="<%=request.getContextPath()%>/resources/jQuery/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/jQuery/jquery-ui.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
	$(function() {
		var message, form;
		message = $(".message");
		if(message.text()) {
			message.position({
				my: "center",
				at: "center",
				of: document.body
			}).show().delay(2000).fadeOut(2000);
		}
		form = $("form:first");
		$(":button,:submit,:reset").button();
		$(".login").click(function(e) {
			var link = this;
			e.preventDefault();
			form.position({
				my: "right top",
				at: "right bottom",
				of: this
			}).fadeIn();
			$(document).on("click.login", function(e) {
				if($(e.target).closest("form").is(form) || e.target === link) {
					return;
				}
				form.fadeOut();
				$(document).off("click.login");
			});
			form.find(":input:first").focus();
		});
	});
</script>
<body class="ui-widget">
	<div class="message ui-helper-hidden ui-corner-all ui-state-error" style="position:fixed;padding: 0.5em"><%=message%></div>
<h3 align="center" style="color: green;">Home Management</h3><a style="float: right;" href="#" class="login">Sign in</a>
	<form class="ui-helper-hidden ui-widget-content ui-corner-all" style="position:fixed; width: 30%; float: right;" id="form" action="<%=request.getContextPath()%>/LoginServlet" method="post">
		<table width="100%">
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
				<td colspan="2">
					<input id="submit" type="submit" value="&nbsp;Sign In&nbsp;" name="submit" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>