<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meme Generator</title>
</head>
<body>
<form:form method="POST" modelAttribute="newImage"
		enctype="multipart/form-data">
		<section>
<div>Upload an Image to create a meme</div>
				<div>
					<form:input type='file' path="image" required="true" />

				</div>











				<input type="submit" value="Upload Image" />

		
		</section>
	</form:form>
</body>
</html>