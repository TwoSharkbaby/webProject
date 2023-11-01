<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<title>에러 페이지</title>
</head>
<body>
<h1>
	에러 페이지 <c:out value="${exception.getMessage()}"/>
</h1>

</body>
</html>