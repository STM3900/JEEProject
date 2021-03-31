<html>
<head>
    <title>Panier</title>
</head>
<body>

<p>Votre panier contient <c:out value="${ sessionScope.cartChartreuse.size() }" /> articles :</p>

        <c:forEach var="product" items="${ sessionScope.cartChartreuse }">
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

<h3>Total :
    <c:out value="${ total }" />
</h3>

<form method="post" action="Cart">
    <fieldset>
        <input type="submit" name="validateButton"  value="Commander"  />
    </fieldset>
</form>

<p>User : </p>
<c:out value="${sessionScope.idUserChartreuse}" />
<c:out value="${sessionScope.roleUserChartreuse}" />
<br>

</body>
</html>
