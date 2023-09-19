<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"

          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 40px;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
        }

        .navbar-brand {
            margin-left: 10px;
        }

        .navbar-links {
            display: flex;
            align-items: center;
        }

        .navbar-links a {
            margin-left: 10px;
            color: #212529;
            text-decoration: none;
            padding: 14px 16px;
            transition: background-color 0.3s ease;
        }

        .navbar-links a:hover {
            background-color: #ddd;
        }

        .navbar-links a.active {
            background-color: greenyellow;
            color: white;
            border-radius: 4px;
        }

        .navbar-links a.disabled {
            color: #6c757d;
            pointer-events: none;
        }


        .right-buttons {
            display: flex;
            align-items: center;
        }

        .right-buttons .btn {
            margin-left: 10px;
        }

        .right-buttons .btn-sign-in,
        .right-buttons .btn-sign-up {
            background-color: greenyellow;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 5px 10px;
            cursor: pointer;
        }

        .right-buttons .btn-sign-in:hover,
        .right-buttons .btn-sign-up:hover {
            background-color: #0056b3;
        }
        #lk{
            background-color: greenyellow;
            border-color:greenyellow; ;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><@spring.message 'navbar.logo'/></a>
        <div class="navbar-links">
            <a class="nav-link active" aria-current="page" href="#"><@spring.message 'navbar.navi.home'/></a>
            <#if username??>
                <a class="nav-link" href="/profile"><@spring.message 'navbar.navi.profile'/></a>
            </#if>
            <a class="nav-link" href="/vacancies"><@spring.message 'navbar.navi.vacancies'/></a>
            <#if username?? && type == "EMPLOYER">
                <a class="nav-link" href="/resumes"><@spring.message 'navbar.navi.resumes'/></a>
            </#if>
            <#if username?? && type == "JOB_SEEKER">
                <a class="nav-link" href="/jobresumes"><@spring.message 'navbar.navi.jobs'/></a>
            </#if>
        </div>
        <div class="right-buttons">
            <div class="dropdown" >
                <button class="btn btn-secondary dropdown-toggle" id="lk" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                    <i class="flag-united-kingdom flag m-0"></i>
                    <@spring.message 'navbar.navi.lang'/>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="?lang=en">English</a></li>
                    <li><a class="dropdown-item" href="?lang=ru">Русский</a></li>
                </ul>
            </div>
            <#if username??>
                <a class="btn btn-primary btn-sign-in" href="/logout" id="logout"><@spring.message 'navbar.user.logout'/></a>
            <#else>
                <a class="btn btn-primary btn-sign-in" href="/login"><@spring.message 'navbar.user.signin'/></a>
                <a class="btn btn-success btn-sign-up" href="/register"><@spring.message 'navbar.user.signup'/></a>

            </#if>
        </div>
    </div>
</nav>
<div class="container">

    <#if username??>
        <h1><@spring.message 'navbar.user.hi'/>  ${username}</h1>
    <#else>
        <h1><@spring.message 'navbar.user.welcome'/></h1>
    </#if>
    <script>
        document.getElementById('logout').addEventListener('click', () => {
            localStorage.removeItem('user')
        })
    </script>

</div>
</body>
</html>
