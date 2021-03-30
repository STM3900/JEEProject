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
<form method="post" action="Login">
    <fieldset>
        <label for="login">login</label>
        <input type="text" id="login" name="login" placeholder="Entrez votre login" size="40" maxlength="60" required="required" value="${ login }"/>
        <br/>
        <label for="password">password</label>
        <input type="password" id="password" name="password" placeholder="Entrez votre password" size="40" maxlength="60" required="required" value="${ password }"/>
    </fieldset>
    <fieldset>
        <input type="submit" name="validate"  value="Valider"  />
    </fieldset>
</form>

</body>
</html>
