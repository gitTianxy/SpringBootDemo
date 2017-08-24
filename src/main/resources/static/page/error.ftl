<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8" />
    <title>error</title>
</head>
<body>
<#if url??>
    url: ${url!} <br>
    exception: ${exception!}<br>
<#else>
    <h3>404</h3>
    page not exists
</#if>
</body>
</html>