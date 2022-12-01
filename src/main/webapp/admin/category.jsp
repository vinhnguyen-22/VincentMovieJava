<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category</title>
</head>
<body>
<%@include file="partials/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- CSS -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	<!-- Bootstrap theme -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
</head>
<body>
	
	<div class="wrapper">
      	<%@include file="partials/sidebar.jsp" %>

      	<c:if test="${message != null }" >
			<input id="notification" type="hidden" value="${message }">
		</c:if>
		

		<div class="container p-5">
			<h1 class="text-center mb-4">Category Management</h1>
			<hr class="mx-auto" style="width:50%;">
			
			<h2 class="text-center">Quick action</h2>
			<div class="d-flex justify-content-center">
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createModal" data-bs-whatever="@mdo">
					<i class="fa fa-plus"></i>
					New
				</button>
			</div>
			<hr class="mx-auto" style="width:50%;">
			
			<div class="table-responsive py-3">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Index</th>
							<th>ID</th>
							<th>Category Name</th>
							<th>Actions</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${categoryList}" var="category" varStatus="count">
							<c:url var="updateLink" value="manage_category">
								<c:param name="command" value="LOAD"/>
								<c:param name="categoryId" value="${category.categoryId}"/>
							</c:url>
							
							<tr>
								<td>${count.index + 1}</td>
								<td>${category.categoryId }</td>
								<td>${category.name }</td>
								<td>
									<button data-id="${category.categoryId }" type="button"
									data-bs-toggle="modal" data-bs-target="#updateModal" data-bs-whatever="@mdo"
									name="" id="" class="btn btn-warning edit btn-block">
										<i class="fa fa-pen"></i>
										Edit
									</button>
									<span class="mx-3"> | </span>
									<button data-id="${category.categoryId }" type="button" name="" id="" class="btn btn-danger delete btn-block">
										<i class="fa fa-bin"></i>
										<a class="text-light " style="text-decoration: none" href="manage_category?command=DELETE&categoryId=${category.categoryId}">Delete</a>
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
    </div>

<jsp:include page="partials/categoryModal.jsp">
	<jsp:param name="title" value="Create category" />
	<jsp:param name="command" value="INSERT" />
	<jsp:param name="modal" value="createModal" />
</jsp:include>

<jsp:include page="partials/categoryModal.jsp">
	<jsp:param name="title" value="Edit category" />
	<jsp:param name="command" value="UPDATE" />
	<jsp:param name="modal" value="updateModal" />
</jsp:include>

<%@include file="partials/footer.jsp" %>
<!-- JavaScript -->
<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>
	
<script type="text/javascript">
	var notification = document.getElementById("notification");
	
	if (notification != null && notification.value.length > 0) {
		alertify.error(notification.value);
	}
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#categoryForm").validate({
			rules: {
				name: {required: true },
			},
			errorPlacement: function(error, element) {},
			highlight: function (element, errorClass, validClass) {
				$(element).addClass("is-invalid").removeClass("is-valid");
			},
			unhighlight: function (element, errorClass, validClass) {
				$(element).addClass("is-valid").removeClass("is-invalid");
			},
		});
		$(".edit").on('click', function() {
			var id = $(this).data("id");
			$('#updateModal #id').val(id)
			$.ajax({
				type: "GET",
				url:'manage_category?command=LOAD',
				data:{
					categoryId: id
				},
				dataType: "JSON",
				contentType: "application/json",
				success: function(rs) {
					$("#updateModal #name").val(rs.name);
				}
			})
		})

	});
</script>

</body>
</html>
