<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WeatherReport</title>
</head>
<body>

<h3>Weather Report by City</h3>
<form action="getWeather" method="get">
<pre>
Please Select the City:
CITY: LONDON   <input type="radio" checked name="country" value="London"/>
      HONGKONG <input type="radio" name="country" value="Hong Kong"/>
<input type="submit" value="SEARCH"/>
</pre>
</form>

${message}

</body>
</html>