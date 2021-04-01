<html>
<head>
    <title>AddProduct</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/373a1c097b.js" crossorigin="anonymous"></script>
</head>
<body>

<div class="content">
    <h1>Add product</h1>
    <div class="auth-main">
        <article class="auth-register">
            <form method="POST" action="AddProduct">
                <div>
                    <aside>
                        <i class="fas fa-tag"></i>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            placeholder="Nom du produit"
                            maxlength="40"
                            required
                        />
                    </aside>
                    <aside>
                        <i class="fas fa-calendar-alt"></i>
                        <input
                            type="text"
                            onfocus="(this.type='date')"
                            id="limitDate"
                            name="limitDate"
                            placeholder="Date limite"
                        />
                    </aside>
                </div>
                <aside>
                    <i class="fas fa-link"></i>
                    <input
                        type="url"
                        id="image"
                        name="image"
                        placeholder="Image du produit"
                        required
                    />
                </aside>
                <div>
                    <aside>
                        <i class="fas fa-wine-bottle"></i>
                        <input
                            type="number"
                            id="quantity"
                            step="0.01"
                            name="quantity"
                            min="1"
                            required
                            placeholder="Litrage du produit"
                        />
                    </aside>
                    <aside>
                        <i class="fas fa-euro-sign"></i>
                        <input
                            type="number"
                            id="price"
                            step="0.01"
                            name="price"
                            min="1"
                            required
                            placeholder="Prix du produit"
                        />
                    </aside>
                </div>
                <input
                    type="submit"
                    class="button"
                    name="addButton"
                    value="Ajouter le produit"
                />
            </form>
        </article>
    </div>
    <c:if test="${ product != null }">
        <h1 class="success">Le produit à bien été ajouté</h1>
    </c:if>
</div>
</body>
</html>
