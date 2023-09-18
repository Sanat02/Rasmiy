<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<div><h1><@spring.message 'no.user'/></h1></div>
<a href="/login">
    <div><h1><@spring.message 'login.again'/></h1></div>
</a>
<a href="/register">
    <div><h1><@spring.message 'register'/></h1></div>
</a>
</body>
</html>