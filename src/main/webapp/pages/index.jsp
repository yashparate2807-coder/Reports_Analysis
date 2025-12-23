<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Reports App</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">

</head>
<body>
	<div class="container-fluid">
		<h1>Reports Application</h1>

		<form:form action="search" modelAttribute="search" method="POST">
			<table>
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${names}" />
						</form:select></td>
					<td>Plan Status:</td>
					<td><form:select path="planSatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}" />
						</form:select></td>
					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Start Date:</td>
					<td><form:input type="date" path="startDate" /></td>
					<td>End Date:</td>
					<td><form:input type="date" path="endDate" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="search"
						class="btn btn-primary" /></td>
				</tr>
			</table>
		</form:form>

		<hr>

		<table class="table table-striped table-hover" >

			<thead>
				<tr>
					<td>S.No</td>
					<td>Citizen Name</td>
					<td>Plan Name</td>
					<td>Gender</td>
					<td>Plan Status</td>
					<td>Plan Start Date</td>
					<td>Plan End Date</td>
					<td>Benefit Amt.</td>
				</tr>
			</thead>
			<tbody>
					<c:forEach items="${plans}" var="plans" varStatus="index">
					<tr>
					<td>${index.count}</td> 
					<td>${plans.citizenName}</td>
					<td>${plans.gender}</td>
					<td>${plans.planName}</td>
					<td>${plans.planStatus}</td>
					<td>${plans.planStartDate}</td>
					<td>${plans.planEndDate}</td>
					<td>${plans.benefitAmount}</td>
					</tr>					
					</c:forEach>
					
					<tr>
					<td colspan="8" style="text-align:center">
					<c:if test="${empty plans}">
					Records Not Found
					</c:if>
					</td> 
					
					</tr>
			</tbody>
		</table>

		<hr>

		Export: <a href="">Excel</a> <a href="">Pdf</a>

	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous"></script>
</body>
</html>