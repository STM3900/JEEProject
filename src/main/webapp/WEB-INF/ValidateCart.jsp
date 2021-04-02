<html>
<head>
    <title>Validate Cart</title>
</head>
<body>
<!-- Post command -->
<c:if test="${command.deliveryMethod == null}" >
    <p>Votre panier contient ${ sessionScope.cartChartreuse.size() } articles :</p>

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
    </c:forEach>

    <h3>Total : ${ total }"</h3>

    <form method="post" action="ValidateCart">
        <fieldset>
            <input type="hidden" id="totalPrice" name="totalPrice" required="required" value="${ total }"/>
            <label for="userAddress">Adresse</label>
            <input type="text" id="userAddress" name="userAddress" placeholder="Entrez votre adresse" size="40" maxlength="500" required="required" value="address"/>
            <br/>
            <label for="deliveryMethod">Mode de Livraison</label>
            <!-- TODO : add list options for delivery method -->
            <input type="text" id="deliveryMethod" name="deliveryMethod" placeholder="Entrez votre moyen de livraison" size="40" maxlength="60" required="required" value="Relais"/>
        </fieldset>
        <fieldset>
            <input type="submit" name="commandButton" value="Validez la commande"  />
        </fieldset>
    </form>
</c:if>

<!-- Propose payment -->
<c:if test="${ command.deliveryMethod != null}" >

    <p>Votre commande a été enregistrée !</p>
    <div>
        <p>Montant : <c:out value="${command.totalPrice}"/></p>
        <p>Mode de livraison : <c:out value="${command.deliveryMethod}"/></p>
        <p>Adresse : <c:out value="${command.address}"/></p>
    </div>
    <a href="/JEE_Liquors_war/PayUp">Passer au paiement</a>
</c:if>
</body>
</html>
