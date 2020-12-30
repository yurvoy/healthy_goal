
<jsp:useBean class="be.intecbrussel.healthy_goal.model.User" id="userinfo"></jsp:useBean>

<jsp:setProperty name="userinfo" property="height"></jsp:setProperty>
<jsp:setProperty name="userinfo" property="currentWeight"></jsp:setProperty>

Height: <jsp:getProperty name="userinfo" property="height"></jsp:getProperty>
Current Weight: <jsp:getProperty name="userinfo" property="currentWeight"></jsp:getProperty>