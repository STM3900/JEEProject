<html>
<head>
    <title>AddProduct</title>
</head>
<body>

<h2>Add Product form</h2>
<form method="post" action="AddProduct">
    <fieldset>
        <label for="name">Product Name</label>
        <input type="text" id="name" name="name" size="40" maxlength="40" required="required" value="Chartreuse Verte"/>
        <label for="image">Image</label>
        <input type="url" id="image" name="image" size="40" required="required" value="url"/>
        <label for="limitDate">Date Limite</label>
        <input type="date" id="limitDate" name="limitDate" size="40" required="required" value=""/>
        <label for="quantity">Quantity</label>
        <input type="number" id="quantity" step="0.01" name="quantity" size="40" min="1" required="required" value=""/>
        <label for="price">Prix</label>
        <input type="number" id="price" step="0.01" name="price" size="40" min="1" required="required" value=""/>
    </fieldset>
    <fieldset>
        <input type="submit" name="addButton"  value="Add"  />
    </fieldset>
</form>

<c:if test="${ product != null }">
    <p>Product Add :</p>
    <c:out value="${ product.name }" />
    <c:out value="${ product.image }" />
    <c:out value="${ product.limitDate }" />
    <c:out value="${ product.quantity }" />
    <c:out value="${ product.price }" />
</c:if>

</body>
</html>
