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

<div class="content">
    <header>
        <aside></aside>
        <h1>La grandeur est la gloire en bouteille.</h1>
    </header>
    <article class="product-list">
        <section>
            <div class="article-image"><img src="chartreuse.png" alt="" /></div>
            <div class="article-info">
                <div class="article-info-title">
                    <div>
                        <h3>Chartreuse</h3>
                        <p>1.5L</p>
                    </div>
                    <p>34,50€</p>
                </div>
                <button>Ajouter au panier</button>
            </div>
        </section>
        <section>
            <div class="article-image"><img src="genepi.png" alt="" /></div>
            <div class="article-info">
                <div class="article-info-title">
                    <div>
                        <h3>Genepi</h3>
                        <p>2L</p>
                    </div>
                    <p>15.50€</p>
                </div>
                <button>Ajouter au panier</button>
            </div>
        </section>
        <section>
            <div class="article-image"><img src="vodka.jpg" alt="" /></div>
            <div class="article-info">
                <div class="article-info-title">
                    <div>
                        <h3>Vodka</h3>
                        <p>1.5L</p>
                    </div>
                    <p>10€</p>
                </div>
                <button>Ajouter au panier</button>
            </div>
        </section>
    </article>
</div>

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
