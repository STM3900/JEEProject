<html>
<head>
    <title>Pay Up</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/373a1c097b.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="content">
    <c:if test="${ sessionScope.commandChartreuse != null}" >
        <h1>Votre commande :</h1>
        <article class="commands-list">
            <section>
                <div class="commands-list-group">
                    <div>
                        <h3><i class="fas fa-euro-sign"></i> Montant :</h3>
                        <p><c:out value="${sessionScope.commandChartreuse.totalPrice}"/>€</p>
                    </div>
                    <div>
                        <h3><i class="fas fa-truck"></i> Mode de livraison :</h3>
                        <p><c:out value="${sessionScope.commandChartreuse.deliveryMethod}"/></p>
                    </div>
                    <div>
                        <h3><i class="fas fa-map-marker-alt"></i> Adresse :</h3>
                        <p><c:out value="${sessionScope.commandChartreuse.address}"/></p>
                    </div>
                </div>
                <div class="commands-list-group"></div>
                <div>
                    <h2>Souhaitez-vous payer maintenant ?</h2>
                    <form method="post" action="PayUp">
                        <input
                            type="hidden"
                            id="idCommand"
                            name="idCommand"
                            required
                            value="${ sessionScope.commandChartreuse.commandId }"
                        />
                        <select
                            name="modePayment"
                            id="modePayment"
                            maxlength="60"
                            required
                        >
                            <option value="CB">Carte bleue</option>
                            <option value="Paypal">Paypal</option>
                            <option value="Bitcoin">Bitcoin</option>
                        </select>

                        <input
                            type="submit"
                            name="payButton"
                            class="button"
                            value="Payer"
                        />
                    </form>
                </div>
            </section>
        </article>
    </c:if>
    <c:if test="${ commandPayed != null}" >
        <h1>Merci pour votre commande !</h1>
        <h2>Récapitulatif :</h2>
        <article class="commands-list">
            <section>
                <div class="commands-list-group">
                    <div>
                        <h3><i class="fas fa-euro-sign"></i> Montant :</h3>
                        <p><c:out value="${commandPayed.totalPrice}"/>€</p>
                    </div>
                    <div>
                        <h3><i class="fas fa-euro-sign"></i> Mode de paiement :</h3>
                        <p><c:out value="${commandPayed.paymentMethod}"/></p>
                    </div>
                    <div>
                        <h3><i class="fas fa-truck"></i> Mode de livraison :</h3>
                        <p><c:out value="${commandPayed.deliveryMethod}"/></p>
                    </div>
                </div>
                <div class="commands-list-group">
                    <div>
                        <h3><i class="fas fa-map-marker-alt"></i> Adresse :</h3>
                        <p><c:out value="${commandPayed.address}"/></p>
                    </div>
                </div>
                <div>
                    <h2>Thank you for your patronage !</h2>
                </div>
                <div class="commands-list-menu-button">
                    <a href="/JEE_Liquors_war/Commands" class="button"
                    >Voir Mes Commandes</a
                    >
                </div>
            </section>
        </article>
    </c:if>
    <c:if test="${ sessionScope.commandChartreuse == null && commandPayed.deliveryMethod == null }" >
        <article class="commands-list">
            <section>
                <p>
                    Erreur de la banque en votre faveur! La commande a déjà été payée.
                </p>
                <p>
                    Ou vous n'avez pas payé car il n'y a peut-être pas de commande ?
                </p>
            </section>
        </article>
    </c:if>
</div>
</body>
</html>
