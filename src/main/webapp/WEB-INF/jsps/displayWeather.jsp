<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Weather Report</title>
</head>
<body>

	<h1>date</h1>
	<h1>${weather.date}</h1>


	<h1>country</h1>
	<h1>${weather.country}</h1>


	<h1>description</h1>

	${weather.description}


	<h1>temperature in Kelvin</h1>
	<h1>${weather.temp}</h1>


	<h1>Sunrise Time</h1>



	<p>
		Sunrise Time
		<fmt:formatDate pattern="HH:mm a" type="time"
			value="${weather.sunrise}" timeZone="GMT+1" />
	</p>


	<h1>Sunset Time</h1>

	<p>
		Sunset Time
		<fmt:formatDate pattern="HH:mm a" type="time"
			value="${weather.sunset}" timeZone="GMT+1" />
	</p>



</body>
</html>