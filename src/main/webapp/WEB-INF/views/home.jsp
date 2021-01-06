<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>HEALTHY GOAL</h1>
<div>
    <h3>User Info</h3>
    <div>Height: ${user.height}m</div>
    <div>Current weight: ${user.currentWeight}Kg</div>
    <div>Current BMI: ${user.currentBMI}</div>
    <br>
    <div>Healthy maximum weight: ${user.healthyMaxWeight}Kg</div>
    <div>Healthy minimum weight: ${user.healthyMinWeight}Kg</div>
    <div>Weight to lose: ${user.weightToLose}Kg</div>
    <br>
</div>

<h3>History</h3>
<c:forEach var="oldWeight" items="${user.weights}">
    Date: ${oldWeight.key}
    Weight: ${oldWeight.value} -
</c:forEach>


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