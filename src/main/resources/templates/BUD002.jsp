
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>(BUD002)Student Register</title>
<link th:href="@{/resources/css/container.css}" rel="stylesheet" type="text/css" />
<link th:href="@{/resources/css/base.css}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/resources/js/accordion-menu.js"></script>
<script type="text/javascript" src="/resources/js/general.js"></script>
<script type="text/javascript">
	window.onload = function() {
		menu();
	}
</script>
</head>
<body class="main_body">

	<div th:replace=header></div>

	<div id="container">
		<div id="left_menu">
			<!-- menu html code exist the menu function of general.js -->
		</div>
		<div id="main_contents">
			<div id="contents">
				<div class="search_form">
					<h3>Student Register</h3>
					<label id="errormsg" th:text="${err}">  </label>
				    <label style="color: blue;" th:text="${msg}"></label>
					<br />
					<br />
					<br />
					<form action="studentRegister" method="post" th:object="${studentBean}">
						<table class="tableForm">
							<tr>
								<td class="tblSingleLabel">Student No *</td>
								<td class="tblSingleInput"><input type="text" th:field="*{id}" class="txtlong" /></td>
								<label th:if="${#fields.hasErrors('id')}" th:errors="*{id}" style="color:red;">Error</label>
							</tr>
							<tr>
								<td class="tblSingleLabel">Student Name *</td>
								<td class="tblSingleInput"><input type="text" th:field="*{name}" class="txtlong" /></td>
								<label th:if="${#fields.hasErrors('name')}" th:errors="*{name}" style="color:red;">Error</label>
							</tr>
							<tr>
								<td class="tblSingleLabel">Class Name *</td>
								<td class="tblSingleInput"><select id="expenseType" class="normal_sel" th:field="*{className}" >
								
									<option th:value="${null}"></option>
										<option th:each="list:${application.classlist}" th:value="${list.name}" th:text="${list.name}"></option>
								</select></td>
							</tr>
							<tr>
								<td class="tblSingleLabel">Registered Date *</td>
								<td class="tblSingleInput"><select id="expenseType" class="short_sel" th:field="*{year}" >
								
									<option th:value="${null}">Year</option>
										<option th:each="i: ${#numbers.sequence(2020, 2030)}" th:value="${i}" th:text="${i}">Year</option>
										

								</select> <select id="expenseType" class="short_sel" th:field="*{month}" >
								
								<option th:value="${null}">Month</option>
										<option th:each="i: ${#numbers.sequence(1, 12)}" th:value="${i}" th:text="${i}">Month</option>
										
								</select> <select id="expenseType" class="short_sel" th:field="*{day}" >
								
								<option th:value="${null}">Day</option>
										<option th:each="i: ${#numbers.sequence(1, 31)}" th:value="${i}" th:text="${i}">Day</option>
										
								</select></td>
							</tr>
							<tr>
								<td class="tblSingleLabel">Status *</td>
								<td class="tblSingleInput"><select id="expenseType" class="normal_sel" th:field="*{status}">
								
										<option th:value="${null}"></option>
										<option th:value="Attending">Attending</option>
										<option th:value="Passed">Passed</option>
										<option th:value="Failed">Failed</option>
								</select></td>
							</tr>


						</table>
						<br />
						<div id="btnGroup">
							<input type="submit" value="Register" class="button" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="footer">
		<span>Copyright &#169; ACE Inspiration 2016</span>
	</div>
</body>
</html>