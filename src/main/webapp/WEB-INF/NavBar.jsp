<nav>
    <article>
        <div>
            <a href="/JEE_Liquors_war/Home">Home</a>
        </div>
        <aside>
            <c:if test="${sessionScope.cartChartreuse != null }">
                <a href="/JEE_Liquors_war/Cart">Panier (<c:out value="${sessionScope.cartChartreuse.size()}" />)</a>
            </c:if>
            <c:if test="${sessionScope.idUserChartreuse == null }">
                <a href="/JEE_Liquors_war/Login">Connexion</a>
                <a href="/JEE_Liquors_war/SignIn">Inscription</a>
            </c:if>

            <c:if test="${sessionScope.idUserChartreuse != null }">
                <c:if test="${sessionScope.roleUserChartreuse != 'client'}">
                    <a href="/JEE_Liquors_war/AddProduct">AddProduct</a>
                </c:if>
                <a href="/JEE_Liquors_war/Commands">Commandes</a>
                <a href="/JEE_Liquors_war/Logout">Déconnexion</a>
            </c:if>
        </aside>
    </article>
</nav>
<div class="navbar-margin"></div>
