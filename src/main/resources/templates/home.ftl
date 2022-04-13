<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form method="post" name="frmMain">
        <table border=1>
        <thead>
            <th>Темы опросов</th>
        </thead>
        <#list theme as el>
            <tr>
                <td>${el.text}</td>
                <td><a href="/poll/${el.id}">перейти</a></td>
            </tr>
        </#list>
        </table>
        Введите пароль для админки: <input type="text" name="password" value=""> <br>
        <a onclick='window.open("/admin?password="+frmMain.password.value);'>Админка</a>
    </form>
</body>
</html>