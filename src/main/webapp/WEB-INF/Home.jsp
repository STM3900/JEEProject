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

<div class="content">
    <header>
        <aside></aside>
        <h1>La grandeur est la gloire en bouteille.</h1>
    </header>
    <article class="product-list">
        <c:forEach var="product"  items="${ products }" >
            <section>
                <div class="article-image"><img src="${product.image}" alt="" /></div>
                <div class="article-info">
                    <div class="article-info-title">
                        <div>
                            <h3>${product.name}</h3>
                            <p>${product.quantity}</p>
                        </div>
                        <p>${product.price}0€</p>
                    </div>
                    <c:if test="${sessionScope.idUserChartreuse != null }">
                        <form method="post" action="Home">
                                <input type="hidden" id="idToAdd" name="idToAdd" value="${ product.idProduct }"/>
                                <input type="submit" name="addButton"  value="Ajouter au panier"  />
                        </form>
                    </c:if>
                </div>
            </section>
        </c:forEach>
    </article>
</div>

</body>
</html>
