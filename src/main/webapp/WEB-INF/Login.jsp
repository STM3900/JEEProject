<html>
<head>
    <title>Login Or Sign Up or Log Out</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/373a1c097b.js" crossorigin="anonymous"></script>
</head>
<body>

<div class="content">
    <div class="auth">
        <article class="auth-register">
            <h1>Connexion</h1>
            <form method="POST" action="Login">
                <aside>
                    <i class="fas fa-user"></i>
                    <input
                        type="text"
                        id="login"
                        name="login"
                        placeholder="Login (ex : Avalokiteshvara)"
                        maxlength="60"
                        required
                    />
                </aside>
                <aside>
                    <i class="fas fa-lock"></i>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Mot de passe"
                        maxlength="60"
                        required
                    />
                </aside>
                <input
                    type="submit"
                    class="button"
                    name="loginButton"
                    value="Connexion"
                />
            </form>
        </article>
        <article class="auth-register">
            <h1>Inscription</h1>
            <form method="POST" action="SignIn">
                <aside>
                    <i class="fas fa-user"></i>
                    <input
                        type="text"
                        id="firstName"
                        name="firstName"
                        placeholder="Prénom (ex : Dalaï)"
                        maxlength="60"
                        required
                    />
                </aside>
                <aside>
                    <i class="fas fa-user"></i>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        placeholder="Nom (ex : Lama)"
                        maxlength="60"
                        required
                    />
                </aside>
                <aside>
                    <i class="fas fa-user"></i>
                    <input
                        type="text"
                        id="login"
                        name="login"
                        placeholder="Login (ex : Avalokiteshvara)"
                        maxlength="60"
                        required
                    />
                </aside>
                <aside>
                    <i class="fas fa-user"></i>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        placeholder="Nom (ex : Lama)"
                        maxlength="60"
                        required
                    />
                </aside>
                <aside>
                    <i class="fas fa-lock"></i>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Mot de passe"
                        maxlength="60"
                        required
                    />
                </aside>
                <input
                    type="submit"
                    class="button"
                    name="signinButton"
                    value="Inscription"
                />
            </form>
        </article>
    </div>
</div>

</body>
</html>
