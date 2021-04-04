<html>
<head>
    <title>Validate Cart</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/373a1c097b.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="content">
    <c:if test="${sessionScope.commandChartreuse == null}" >
        <h1>Votre panier contient <c:out value="${ sessionScope.cartChartreuse.size() }" /> articles :</h1>
        <div class="cart">
            <article class="cart-products">
                <c:forEach var="product" items="${ sessionScope.cartChartreuse }">
                    <section>
                        <div class="cart-products-image">
                            <img src="${ product.image }" alt="" />
                        </div>
                        <div class="cart-products-info">
                            <div class="cart-products-info-title">
                                <div>
                                    <h3><c:out value="${ product.name }" /></h3>
                                    <p>${ product.quantity }L</p>
                                </div>
                                <p>${ product.price }€</p>
                            </div>
                        </div>
                    </section>
                </c:forEach>
            </article>
            <article class="cart-buy cart-confirm-buy">
                <h2>Total : ${ total }€</h2>
                <form method="POST" action="ValidateCart">
                    <aside>
                        <i class="fas fa-map-marker-alt"></i>
                        <input type="hidden" id="totalPrice" name="totalPrice" required="required" value="${ total }"/>
                        <input
                            type="text"
                            id="userAddress"
                            name="userAddress"
                            placeholder="Entrez votre adresse"
                            maxlength="500"
                            required
                        />
                    </aside>
                    <aside>
                        <i class="fas fa-truck"></i>
                        <select
                            name="deliveryMethod"
                            id="deliveryMethod"
                            maxlength="60"
                            required
                        >
                            <option value="relais">En point relais</option>
                            <option value="domicile">À votre domicile</option>
                            <option value="retrait">En retrait magasin</option>
                        </select>
                    </aside>
                    <input
                        type="submit"
                        name="commandButton"
                        class="button"
                        value="Valider la commande"
                    />
                </form>
            </article>
        </div>
    </c:if>
    <c:if test="${ sessionScope.commandChartreuse != null}" >
        <div class="confirmed-command">
            <h1>Votre commande a été enregistrée !</h1>
            <p>Montant : <b><c:out value="${sessionScope.commandChartreuse.totalPrice}"/></b></p>
            <p>Mode de livraison : <b><c:out value="${sessionScope.commandChartreuse.deliveryMethod}"/></b></p>
            <p>Adresse : <b><c:out value="${sessionScope.commandChartreuse.address}"/></b></p>
            <a href="/JEE_Liquors_war/PayUp" class="button">Passer au paiement</a>
        </div>
    </c:if>
</div>
</body>
</html>
