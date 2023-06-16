<!DOCTYPE html>
<html>
<head>
    <title>Обмен Товарами</title>
</head>
<body>
<h1>Обмен Товарами</h1><hr>
<h4>Подробности о товаре</h4>



<b>Название товара : </b>${product.title}<br>
<b>Описание товара : </b>${product.description}<br>
<b>Город : </b>${product.city}<br>
<b>Продавец : </b>${product.author}<br>
<b>Цена : </b>${product.price}<br>
<hr>
<h3>Удалить товар</h3>
<form action="/product/delete/${product.id}" method="post">

    <input type="submit" value="Удалить товар"/>
</form>
</body>
</html>