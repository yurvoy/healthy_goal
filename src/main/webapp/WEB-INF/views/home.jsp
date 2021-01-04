<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
<h1>HEALTHY GOAL</h1>
<div>
    <h3>User Info</h3>
    <div>ID: ${user.id}</div>
    <div>Name: ${user.name}</div>
    <div>Email: ${user.email}</div>
    <div>Social Provider: ${user.provider}</div>
    <br>
    <div>Height: ${user.height}m</div>
    <div>Current weight: ${user.currentWeight}Kg</div>
    <div>Current BMI: ${user.currentBMI}</div>
    <br>
    <div>Healthy maximum weight: ${user.healthyMaxWeight}Kg</div>
    <div>Healthy manimum weight: ${user.healthyMinWeight}Kg</div>
    <div>Weight to lose: ${user.weightToLose}Kg</div>
    <br>
    <div>History: MAP HERE</div>
</div>

<h3>Setters</h3>

<form:form method="POST"
           action="/setter" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="height">height</form:label></td>
            <td><form:input path="height"/></td>
        </tr>
        <tr>
            <td><form:label path="currentWeight">Current weight</form:label></td>
            <td><form:input path="currentWeight"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>


<form id="logoutForm" method="POST" action="/add">
    <button type="submit" style="margin-top: 2rem;">Log Out</button>
</form>
</body>
</html>