<html>
<head>
    <title>Home</title>
</head>
<body>
<h3>Hello Home</h3>
<c:out value="test jsp tag" />
<c:out value="${ user.lastName } ${ user.firstName }" />
<form method="post" action="Home">
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
