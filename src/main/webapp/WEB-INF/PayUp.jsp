<html>
<head>
    <title>Pay Up</title>
</head>
<body>
Welcome in PayUP

<c:if test="${ sessionScope.commandChartreuse != null}" >
    <p>Votre Commande :</p>
    <div>
        <p>Montant : <c:out value="${sessionScope.commandChartreuse.totalPrice}"/></p>
        <p>Mode de livraison : <c:out value="${sessionScope.commandChartreuse.deliveryMethod}"/></p>
        <p>Adresse : <c:out value="${sessionScope.commandChartreuse.address}"/></p>
    </div>
    <p>Souhaitez-vous payer maintenant ?</p>

    <form method="post" action="PayUp">
        <fieldset>
            <input type="hidden" id="idCommand" name="idCommand" required="required" value="${ sessionScope.commandChartreuse.commandId }"/>
            <label for="modePayment">Mode de Paiement</label>
            <!-- TODO : add list options for payment method -->
            <input type="text" id="modePayment" name="modePayment" placeholder="Entrez votre mode de paiement" size="40" maxlength="500" required="required" value="address"/>
        </fieldset>
        <fieldset>
            <input type="submit" name="payButton" value="Payer"  />
        </fieldset>
    </form>
</c:if>
<c:if test="${ commandPayed != null}" >
    <p>Votre paiement est passée !</p>
    <p>Récapitulatif de votre commande :</p>
    <div>
        <p>Montant : <c:out value="${commandPayed.totalPrice}"/></p>
        <p>Mode de livraison : <c:out value="${commandPayed.deliveryMethod}"/></p>
        <p>Adresse : <c:out value="${commandPayed.address}"/></p>
        <p>Mode de paiement : <c:out value="${commandPayed.paymentMethod}"/></p>
    </div>
    <p>Thank you for your patronage !</p>
</c:if>
<c:if test="${ sessionScope.commandChartreuse == null && commandPayed.deliveryMethod == null }" >
    <p>Erreur de la banque en votre faveur! La commande a déjà été payée.</p>
    <p>Ou vous n'avez pas payé car il n'y a peut-être pas de commande ?</p>
</c:if>
</body>
</html>
