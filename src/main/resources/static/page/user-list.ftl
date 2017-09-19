<!DOCTYPE html>

<html lang="en">

<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>
<#list users as u>

id: ${u.id}, name: ${u.name}, age: ${u.age}<br>

</#list>
</body>

</html>