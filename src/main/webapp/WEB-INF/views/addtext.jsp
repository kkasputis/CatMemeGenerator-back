<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="images/${newImage.id}.jpg" />
		<form:form method="POST" modelAttribute="newImage">

			<section>
	
				<fieldset>
					<legend>Rašyti komentarą:</legend>

					<div>
						<label>komentaras</label>
						<form:input path="text" type="text" />
						<form:errors path="text" />
					</div>
					<input type="submit" value="Add text to a picture" />



				</fieldset>
			</section>
		</form:form>
</body>
</html>