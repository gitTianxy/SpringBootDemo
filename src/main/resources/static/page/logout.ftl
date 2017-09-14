<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>logout</title>
</head>
<body>
<nav role="navigation">
    <ul>
    <#if currentUser??>
        <li>
            id: ${currentUser.id!}
        </li>
        <li>
            name: ${currentUser.name!}
        </li>
        <li>
            roles: <#list currentUser.roles as role>${role}</#list>
        </li>
        <li>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Log out</button>
            </form>
        </li>
    </#if>
    </ul>
</nav>
</body>
</html>