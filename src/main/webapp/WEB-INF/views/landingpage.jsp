<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Wait...</title>
</head>
<body>
<sec:authorize access="!isAuthenticated()">
  <script type="text/javascript">
       window.location.href = "login";
   </script>
</sec:authorize>
 
 <sec:authorize access="hasRole('ROLE_REFERRER')">
    <script type="text/javascript">
     window.location.href = "referrer/home";
    </script>
 </sec:authorize>
 <sec:authorize access="hasRole('ROLE_RECEPTIONIST')">
    <script type="text/javascript">
     window.location.href = "reception/home";
    </script>
 </sec:authorize>

 
</body>
</html>