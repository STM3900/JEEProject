<html>
<head>
    <title>Cart</title>
</head>
<body>

<p>Session params of cart :</p>
<c:forEach var="product" items="${sessionScope.cartChartreuse}">
    <c:out value="${product.name}" />
</c:forEach>
<c:out value="${sessionScope.idUserChartreuse}" />
<c:out value="${sessionScope.roleUserChartreuse}" />
<br>

</body>
</html>
