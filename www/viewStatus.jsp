<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="user" class="com.olam.blsheet.beans.User" scope="session"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User Status</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/jQuery/theme/default/jquery-ui.css" />
		<script src="<%=request.getContextPath()%>/resources/jQuery/jquery.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/resources/jQuery/jquery-ui.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
	$(document).ready(function() {
		var userId = <%=user.getId()%>;
		$('.datepick').datepicker();
		$('#submit').click(function(){
			//alert("cliked");
			var dateFrom = $('#date_1').val();
			var dateTo = $('#date_2').val();
			if(dateFrom==null || dateFrom == ""){
				alert("From date is not selected.");
				return false;
			}
			if(dateTo==null || dateTo==""){
				alert("End date is not selected.");
				return false;
			}
			if( (new Date(dateFrom).getTime() > new Date(dateTo).getTime()))
			{
			    alert("To date cannot be less than From date");
			    return false;
			}
			$.ajax({
				type: 		"post",
				url: 		"SummaryServlet",
				data: 		{userId: userId,dateFrom:dateFrom,dateTo:dateTo},
				dataType: "json",
				success:	function(data) {
					console.log("data : ");
					console.log(data);
					$('#spend').val(data.amountSpend);
					$('#credit').val(data.creditOrDebit);
				}
			});
		});
		$('#link').click(function(){
			window.location.replace("<%=request.getContextPath()%>/taskDetails.jsp");
		});
		$('#signOut').click(function(){
			window.location.replace("<%=request.getContextPath()%>");
		});
	});
	</script>
	<body>
		<dir>
			<table width="95%"><tr>
				<td align="left"></td>
				<td align="right"><a href="#" style="text-decoration:none;" id="link">Go Back&nbsp;&nbsp;&nbsp;<a href="#" style="text-decoration:none;" id="signOut">Sign Out</td></tr>
			</table>
		</dir>
		<dir>
			<h4 style="font-family:fantasy;color:red;">SELECT THE DATE</h4>
			<table>
				<tr>
					<td>From date</td><td><input type="text" class="datepick" id="date_1" /></td>
				</tr>			
				<tr>
					<td>End date</td> <td><input type="text" class="datepick" id="date_2" /></td>
										
				</tr>
				<tr>
					<td></td><td><input id="submit" type="button" value="&nbsp;Submit&nbsp;" name="submit"/></td>
				</tr>
			</table>
			
		</dir>
		<dir>
			<h4 style="color:blue;">Account Summary</h4>
			<table>
				<tr><td>Amount you Spend</td><td><input type="text" id="spend" readonly="true"/></td></tr>
				<tr><td>Final Amount</td><td><input type="text" id="credit" readonly="true"/></td></tr>
			</table>
			<p style="color:red;">If final amount is positive you will get the amount.If it is negative you need to transfer that amount Common account.. </p>
		</dir>
	</body>
</html>