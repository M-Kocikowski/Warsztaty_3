<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marcin
  Date: 08.06.19
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Programming school</title>
</head>
<body>
    <jsp:include page="WEB-INF/header.jsp"/>

    <h2>Ostatnie zadania:</h2>
    <jsp:include page="WEB-INF/solutions.jsp"/>

</body>
</html>
