<html>
<head>
    <title>Commands</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/373a1c097b.js" crossorigin="anonymous"></script>
</head>
<body>

<!-- TODO : show message deleted ok somewhere -->
<div class="content">
    <h1>Mes commandes :</h1>
    <article class="commands-list">
        <c:forEach var="command"  items="${ commands }" >
            <section>
                <div>
                    <h2>Commande n°${command.commandId}</h2>
                </div>
                <div class="commands-list-group">
                    <div>
                        <h3><i class="fas fa-euro-sign"></i> Montant :</h3>
                        <p>${command.totalPrice}€</p>
                    </div>
                    <div>
                        <h3><i class="fas fa-euro-sign"></i> Mode de paiement :</h3>
                        <p>${command.paymentMethod}</p>
                    </div>
                    <div>
                        <h3><i class="fas fa-truck"></i> Mode de livraison :</h3>
                        <p>${command.deliveryMethod}</p>
                    </div>
                </div>
                <div class="commands-list-group">
                    <div>
                        <h3><i class="fas fa-map-marker-alt"></i> Adresse :</h3>
                        <p>${command.address}</p>
                    </div>
                </div>
                <div class="button-group">
                    <form method="post" action="Commands">
                        <input type="hidden" id="idCommand" name="idCommand" value="${ command.commandId }"/>
                        <input type="submit" name="deleteButton" class="button" value="Supprimer la commande"/>
                    </form>
                    <c:if test="${command.paymentMethod == null }">
                        <form method="post" action="Commands">
                            <input type="hidden" id="commandToPay" name="commandToPay" value="${ command }"/>
                            <input type="submit" name="validateButton" class="button" value="Payer"  />
                        </form>
                    </c:if>
                </div>
            </section>
        </c:forEach>
    </article>
</div>

</body>
</html>
