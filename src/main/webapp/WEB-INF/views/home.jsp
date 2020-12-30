<html>
<body>
<h1>Home Page</h1>
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

<form action="/setter.jsp" method="post">
    Your height: <input name="height" type="text" />
    <input type="submit" value="save" />
</form>
<form action="setter.jsp" method="post">
    Your current weight: <input name="height" type="text" />
    <input type="submit" value="save" />
</form>


<form id="logoutForm" method="POST" action="/add">
    <button type="submit" style="margin-top: 2rem;">Log Out</button>
</form>
</body>
</html>