<nav>
    <article>
        <div>
            <a href="/JEE_Liquors_war/Home">Home</a>
        </div>
        <aside>
            <c:if test="${sessionScope.idUserChartreuse == null }">
                <a href="/JEE_Liquors_war/Login">Connexion</a>
                <a href="/JEE_Liquors_war/SignIn">Inscription</a>
            </c:if>
            <c:if test="${sessionScope.idUserChartreuse != null }">
                <a href="/JEE_Liquors_war/Logout">Déconnexion</a>
            </c:if>
        </aside>
    </article>
</nav>
<div class="navbar-margin"></div>
