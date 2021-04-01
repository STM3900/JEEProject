<html>
<head>
    <title>Validate Cart</title>
</head>
<body>
Welcome in ValidateCart

<c:forEach var="product" items="${ cart }">
    <h5>
        <c:out value="${ product.name }" />
    </h5>
    <p>
            ${ product.image }
            ${ product.limitDate }
            ${ product.quantity }
            ${ product.price }
    </p>
    <form method="post" action="Cart">
        <fieldset>
            <input type="hidden" id="idToDelete" name="idToDelete" value="${ product.idProduct }"/>
            <input type="submit" name="deleteButton"  value="Delete"  />
        </fieldset>
    </form>
</c:forEach>
</body>
</html>
