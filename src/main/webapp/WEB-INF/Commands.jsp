<html>
<head>
    <title>Commands</title>
</head>
<body>

<!-- TODO : show message deleted ok somewhere -->

<div class="content">
    <header>
        <aside></aside>
        <h1>Mes Commandes : </h1>
    </header>
    <article class="product-list">
        <c:forEach var="command"  items="${ commands }" >
            <section>
                <div>Numero de commande : ${command.commandId}</div>
                <div class="article-info">
                    <div class="article-info-title">
                        <div>
                            <h3> Mode de payment : ${command.paymentMethod}</h3>
                            <p> Montant : ${command.totalPrice}</p>
                        </div>
                        <div>
                            <h3> Mode de payment : ${command.deliveryMethod}</h3>
                            <p> Montant : ${command.address}</p>
                        </div>
                    </div>
                        <form method="post" action="Commands">
                            <input type="hidden" id="idCommand" name="idCommand" value="${ command.commandId }"/>
                            <input type="submit" name="deleteButton" class="deleteButton" value="Supprimer la commande"  />
                        </form>
                    <c:if test="${command.paymentMethod == null }">
                        <form method="post" action="Commands">
                            <input type="hidden" id="commandToPay" name="commandToPay" value="${ command }"/>
                            <input type="submit" name="validateButton" class="validateButton" value="Payer"  />
                        </form>
                    </c:if>
                </div>
            </section>
        </c:forEach>
    </article>
</div>

</body>
</html>
