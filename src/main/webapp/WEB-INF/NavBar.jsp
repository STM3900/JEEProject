<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
    <article>
        <div>
            <a href="">Home</a>
        </div>
        <aside>
            <a href="">Connexion</a>
            <a href="">Inscription</a>
            <c:if test="${ isConnected == true }">
                <a href="">Déconnexion</a>
            </c:if>
        </aside>
    </article>
</nav>
