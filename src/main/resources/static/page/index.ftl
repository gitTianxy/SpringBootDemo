<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="com.example.base.vo.CurrentUser" -->
<!DOCTYPE html>
<html>
<head>
    <title>index</title>
</head>
<body>
    <h1>欢迎来到Spring Boot Demo</h1>
    <p>点击 <a href="/hello">这里</a> 打个招呼吧</p>

    <#if currentUser??>
    name: ${currentUser.name!}<br>
    roles: <#list currentUser.roles as role>${role}</#list>
    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Log out</button>
    </form>
    <#else>
    <a href="/login">login</a>
    </#if>
</body>
</html>