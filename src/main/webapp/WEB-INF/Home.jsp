<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
</head>
<body>
<h3>Hello Home</h3>
<c:out value="test jsp tag" />
<c:out value="${ product.name } ${ product.image }" />
<c:out value="${ error }" />
<br>
<form method="post" action="Home">
    <fieldset>
        <label for="idProduct">idProduct</label>
        <input type="text" id="idProduct" name="idProduct" size="40" maxlength="60" required="required" value="${ idProduct }"/>
    </fieldset>
    <fieldset>
        <input type="submit" name="validate"  value="Search"  />
    </fieldset>
</form>

</body>
</html>
