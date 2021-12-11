<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form method = "POST" action = "/client/1/allprocedures">
    <table>
        <tr>
            <td><form:hidden path = "ClientID" value = "1" /></td>
        </tr>
        <tr>
            <td><form:hidden path = "ProcedureID" value="128"/></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Buy"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>