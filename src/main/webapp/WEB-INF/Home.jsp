<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
</head>
<body>
<%@ include file="NavBar.jsp" %>
<h3>Hello Home</h3>
<c:out value="test jsp tag" />
<c:forEach var="product"  items="${ products }" >
    ${product.name} ${product.price}
</c:forEach>
<c:out value="${ error }" />
<br>
<p>Session params :</p>
<c:out value="${sessionScope.idUserChartreuse}" />
<c:out value="${sessionScope.roleUserChartreuse}" />
<br>



<form method="post" action="Home" style="display: none">
    <fieldset>
        <label for="idProduct">idProduct</label>
        <input type="text" id="idProduct" name="idProduct" size="40" maxlength="60" required="required" value="${ idProduct }"/>
    </fieldset>
    <fieldset>
        <input type="submit" name="validate"  value="Search"  />
    </fieldset>
</form>

</body>
</html>
