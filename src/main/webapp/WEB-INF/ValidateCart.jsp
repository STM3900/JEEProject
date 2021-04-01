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

<form method="post" action="ValidateCart">
    <fieldset>
        <label for="userAdress">Adress</label>
        <input type="text" id="userAdress" name="userAdress" placeholder="Entrez votre adresse" size="40" maxlength="60" required="required" value="address"/>
        <br/>
        <label for="deliveryMethod">Delivery Method</label>
        <input type="text" id="deliveryMethod" name="deliveryMethod" placeholder="Entrez votre moyen de livraison" size="40" maxlength="60" required="required" value="Relais"/>
    </fieldset>
    <fieldset>
        <input type="submit" name="NewCommandButton" value="Command"  />
    </fieldset>
</form>
</body>
</html>
