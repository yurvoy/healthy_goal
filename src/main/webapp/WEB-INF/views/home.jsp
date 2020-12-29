<html>
    <body>
        <h1>Home Page</h1>

        <div>
            <h3>User Info</h3>
            <div>ID: ${user.id}</div>
            <div>Name: ${user.name}</div>
            <div>Email: ${user.email}</div>
            <div>Social Provider: ${user.provider}</div>
        </div>
        
        <form id="logoutForm" method="POST" action="/logout">
            <button type="submit" style="margin-top: 2rem;">Log Out</button>
        </form>
    </body>
</html>