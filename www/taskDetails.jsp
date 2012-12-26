<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="user" class="com.olam.blsheet.beans.User" scope="session" />
<html>
<head>
<title>Simple jQuery and JSP example</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/jQuery/theme/default/jquery-ui.css" />
<script src="<%=request.getContextPath()%>/resources/jQuery/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/jQuery/jquery-ui.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		console.log("userId : ");
		var userId = <%=user.getId()%>;
		$("p").text(userId);
		console.log(userId);
			//alert(userId);
			$.ajax({
				type: 		"post",
				url: 		"<%=request.getContextPath()%>/DetailServlet",
				data: 		{userId: userId},
				dataType: "json",
				success:	function(data) {
					console.log("data.id");
					console.log(data);
					$('#name').val(data.name);
					
					var userList = data.userList;
					for(var i=0;i<userList.length;i++){
						var user = userList[i];
						console.log(user.name);
						console.log(user.userId);
						var $ctrl = $('<input/>').attr({ type: 'checkbox', name:'chk',value:user.userId}).addClass("chk")
						$("#otherUsers").append($ctrl);
						$("#otherUsers").append(user.name);
						$("#otherUsers").append("&nbsp;&nbsp;");
						
						//$('#checkboxes').append('<input type="checkbox" />)
					}
				}
		}),
		 $(function() {
		        $("#datepicker").datepicker();
		 });
		 $('#submit').click(function() {
			
			
			var name = $('#name').val();
			if(name==null || name==""){
				 alert("Name is Empty.")
				 return false;
			}
			var date = $('#datepicker').val();
			if(date==null || date==""){
				 alert("Date is Empty.")
				 return false;
			}
			var amount = $('#amount').val();
			if(amount==null || amount==""){
				 alert("Amount cannot be Empty.")
				 return false;
			}else{
				console.log("----------"+amount);
				console.log(isNaN(amount));
				if(isNaN(amount)){
					alert("Please provide some integer value(s).");
					 return false;
				}
			}
			var description = $('#description').val();
			if(description==null || description==""){
				 alert("Description is Empty.")
				 return false;
			}
			
			var selectedIds ="";
			if($('input[name=chk]').is(':checked')){
				var chkBox = document.getElementsByName("chk");
				
				for(var i = 0;i<chkBox.length;i++){
					if(chkBox[i].checked == true){
						selectedIds = selectedIds+chkBox[i].value+",";
					}
				}
				
				console.log(selectedIds);
			}else{
				alert("Please select atleast one member.");
				return false;
			}
			console.log(name);
			console.log(date);
			console.log(amount);
			console.log(description);
			console.log(userId);
			//alert(number);
			 $.ajax({
				type: "post",
				url: "DataServlet",
				data: {userId : userId, name : name,amount : amount,description : description,date : date,selectedUsers : selectedIds},
				dataType: "json",
				success:	function(data) {
					if(data!=null){
						alert("Your transaction successfully Completed....");
					}
					console.log("data");
					console.log(data);
					$('#datepicker').val("");
					selectedIds = "";
					$('#description').val("");
					$('#amount').val("");
					$('input:checkbox').removeAttr('checked');
				}
			}); 
	
		}); 
		$('#link').click(function(){
			window.location.replace("<%=request.getContextPath()%>/viewStatus.jsp");
		});
		$('#signOut').click(function(){
			window.location.replace("<%=request.getContextPath()%>");
		});
	});
</script>
<body>
	<dir>
		<table width="95%">
			<tr>
				<td align="left"></td>
				<td align="right"><a href="#" style="text-decoration: none;"
					id="link">Summary&nbsp;&nbsp;</a><a href="#"
					style="text-decoration: none;" id="signOut">Sign Out</td>
			</tr>
		</table>
	</dir>
	<dir>
		<h3 style="font-family: fantasy; color: red;">Enter the Details
			of Purchase</h3>
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" id="name" readonly="true" /></td>
			</tr>
			<tr>
				<td>Purchase Date</td>
				<td><input type="text" id="datepicker" /></td>
			</tr>
			<tr>
				<td>Amount Spend</td>
				<td><input type="text" id="amount" /></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><textarea id="description" rows="4" cols="24"> </textarea></td>
			</tr>
		</table>
		<div id=otherUsers>
			<h4 style="color: blue;">Select Atleast One member</h4>
		</div>
		<div>
			<input id="submit" type="button" value="&nbsp;SUBMIT&nbsp;"
				name="submit" />
		</div>
	</dir>
</body>
</html>