<html>
<head>
    <title>Login Or Sign Up or Log Out</title>
</head>
<body>

<p>Session params :</p>
<c:out value="${sessionScope.idUserChartreuse}" />
<c:out value="${sessionScope.roleUserChartreuse}" />
<p>if errors : </p>
<c:out value="${error}" />
<br>

<h2>Login form</h2>
<form method="post" action="Login">
    <fieldset>
        <label for="login">login</label>
        <input type="text" id="login" name="login" placeholder="Entrez votre login" size="40" maxlength="60" required="required" value="Avalokiteshvara"/>
        <br/>
        <label for="password">password</label>
        <input type="password" id="password" name="password" placeholder="Entrez votre password" size="40" maxlength="60" required="required" value="bouddha"/>
    </fieldset>
    <fieldset>
        <input type="submit" name="loginButton" value="Login"  />
    </fieldset>
</form>

<h2>Sign in form</h2>
<form method="post" action="SignIn">
    <fieldset>
        <label for="firstName">firstName</label>
        <input type="text" id="firstName" name="firstName" placeholder="Entrez votre prénom" size="40" maxlength="60" required="required" value="Dalai"/>
        <br/>
        <label for="lastName">lastName</label>
        <input type="text" id="lastName" name="lastName" placeholder="Entrez votre nom" size="40" maxlength="60" required="required" value="Lama"/>
        <br/>
        <label for="login">login</label>
        <input type="text" id="login" name="login" placeholder="Entrez votre login" size="40" maxlength="60" required="required" value="Avalokiteshvara"/>
        <br/>
        <label for="password">password</label>
        <input type="password" id="password" name="password" placeholder="Entrez votre password" size="40" maxlength="60" required="required" value="bouddha"/>
    </fieldset>
    <fieldset>
        <input type="submit" name="signinButton"  value="Sign In"  />
    </fieldset>
</form>

</body>
</html>
