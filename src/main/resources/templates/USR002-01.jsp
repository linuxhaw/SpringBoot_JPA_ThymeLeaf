<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>(USR002)User Update</title>
<link th:href="@{/resources/css/container.css}" rel="stylesheet" type="text/css" />
<link th:href="@{/resources/css/base.css}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/resources/js/accordion-menu.js"></script>
<script type="text/javascript" src="/resources/js/general.js"></script>

</head>
<body class="main_body">

	<div th:replace=header></div>

	<div id="container">
		<div id="left_menu">
			<!-- menu html code exist the menu function of general.js -->
		</div>
		<div id="main_contents">
			<div id="contents">
				<h3>User Update</h3>
				<label id="errormsg" th:text=" ${err}"> </label> 
				<label style="color: blue;" th:text="${msg}"></label>
				<br />
				<br />
				<br />

				<form name="registerForm" action="userupdate" method="post" th:object="${userBean}">
					
					<table class="tableForm">
						<tr>
							<td class="tblSingleLabel">User ID *</td>
							<td class="tblSingleInput"><input type="text" readonly="readonly" id="txtUserId" class="txt_popup"  th:field="*{id}"  /></td>

						</tr>
						<tr>
							<td class="tblSingleLabel">User Name</td>
							<td class="tblSingleInput" ><input type="text" class="txtlong_popup" id="txtUserName" th:field="*{name}" /></td>
							<label th:if="${#fields.hasErrors('name')}" th:errors="*{name}" style="color:red;">Error</label>
						</tr>
						<tr>
							<td class="tblSingleLabel">Password *</td>
							<td class="tblSingleInput"><input type="password" class="txtlong_popup" id="txtUserName" th:field="*{password}" /></td>
							<label th:if="${#fields.hasErrors('password')}" th:errors="*{password}" style="color:red;">Error</label>
						</tr>
						<tr>
							<td class="tblSingleLabel">Confirm Password *</td>
							<td class="tblSingleInput"><input type="password" class="txtlong_popup" id="txtUserName" th:field="*{confirm}" /></td>
							<label th:if="${#fields.hasErrors('confirm')}" th:errors="*{confirm}" style="color:red;">Error</label>
						</tr>
					</table>
					<br /> <input type="submit" value="Update" class="button" /> 
				</form>

				<br />
				<br />
				<br />
			</div>
		</div>

	</div>

	<div class="footer">
		<span>Copyright &#169; ACE Inspiration 2016</span>
	</div>
</body>
</html>