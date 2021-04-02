<html>
<head>
    <title>Panier</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/373a1c097b.js" crossorigin="anonymous"></script>
</head>
<body>

<div class="content">
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
                            <p>${ product.price }0€</p>
                        </div>
                        <form method="post" action="Cart">
                            <input type="hidden" id="idToDelete" name="idToDelete" value="${ product.idProduct }"/>
                            <input type="submit" class="button" name="deleteButton"  value="Supprimer l'article"  />
                        </form>
                    </div>
                </section>
            </c:forEach>
        </article>
        <article class="cart-buy">
            <h2>Total : <c:out value="${ total }" />€</h2>
            <a href="/JEE_Liquors_war/ValidateCart" class="button">Commander</a>
        </article>
    </div>
</div>

</body>
</html>
