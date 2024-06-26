<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 400px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .container h2 {
            margin-bottom: 20px;
            color: #333;
        }

        form {
            width: 100%;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="tel"],
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #6C55F9;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #6C55F9;
        }

        .login-option {
            margin-top: 20px;
            font-size: 14px;
            color: #6C55F9;
        }

        .login-option a {
            color: #6C55F9;
            text-decoration: none;
        }

        .login-divider {
            width: 100%;
            text-align: center;
            margin: 20px 0;
            position: relative;
        }

        .login-divider hr {
            position: absolute;
            top: 50%;
            left: 0;
            width: 100%;
            margin: 0;
        }

        .login-divider .or-text {
            background-color: #f9f9f9;
            padding: 5px 10px;
            font-weight: bold;
            color: #777;
        }

        .social-login {
            display: flex;
            flex-direction: column;
            gap: 10px;
            align-items: center;
            margin-top: 20px;
        }

        .social-login-btn {
            display: flex;
            align-items: center;
            gap: 10px;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 10px 15px;
            font-weight: bold;
            color: #333;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .social-login-btn:hover {
            background-color: #eee;
        }
    </style>
</head>
<body>
<div class="container">
    <h2><@spring.message 'navbar.user.signup'/></h2>
    <#if error??>
        <div>${error}</div>
    </#if>
    <form action="/register" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>

        </#if>
        <label for="account_name"><@spring.message 'account.name'/></label>
        <input type="text" id="account_name" name="account_name" placeholder="<@spring.message 'enter.account.name'/>"
               required>

        <label for="email"><@spring.message 'email'/>:</label>
        <input type="email" id="email" name="email" placeholder="<@spring.message 'text.email'/>" required>

        <label for="password"><@spring.message 'password'/>:</label>
        <input type="password" id="password" name="password" placeholder="<@spring.message 'text.password'/>" required>


        <label for="phone_number"><@spring.message 'phone.number'/></label>
        <input type="tel" id="phone_number" name="phone_number" placeholder="<@spring.message 'enter.phone.number'/>"
               required>

        <input type="submit" value="<@spring.message 'register'/>">
    </form>

    <div class="login-option">
        <@spring.message 'have.account'/> <a href="/login"><@spring.message 'login'/></a>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
<script>
    document.getElementById('account_type').addEventListener('change', function () {
        const accountType = this.value;
        const accountNameLabel = document.querySelector('label[for="account_name"]');

        if (accountType === 'employer') {
            accountNameLabel.textContent = 'Company name';
        } else {
            accountNameLabel.textContent = '<@spring.message "account.name"/>';
        }
    });
</script>
</body>
</html>
