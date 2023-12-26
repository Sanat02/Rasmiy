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

        #lk {
            background-color: greenyellow;
            border-color: greenyellow;;
        }

        .vacancies-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); /* Adjust width as needed */
            gap: 30px; /* Adjust the gap between vacancies */
            max-width: 1200px; /* Adjust the maximum width */
            margin: 50px auto; /* Center the vacancies */
        }

        .vacancy {
            padding: 20px;
            border-radius: 8px;
            background-color: #f9f9f9; /* Change background color */
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); /* Optional: Add a box shadow for depth */
        }

        .vacancy h3 {
            margin-bottom: 10px;
            font-size: 1.2em;
        }

        .vacancy p {
            margin-bottom: 5px;
            font-size: 0.9em;
        }

        .apply-button {
            display: inline-block;
            padding: 8px 15px;
            border-radius: 5px;
            background-color: greenyellow;
            color: white;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .apply-button:hover {
            background-color: #7bc41a;
        }


        @media screen and (max-width: 768px) {
            .vacancies-container {
            }
        }

        .no-vacancies-message {
            font-size: 1.2em;
            text-align: center;
            margin: 50px auto;
            color: #555;
        }


    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><@spring.message 'navbar.logo'/></a>
        <div class="navbar-links">
            <a class="nav-link " aria-current="page" href="/"><@spring.message 'navbar.navi.home'/></a>
            <#if username??>
                <a class="nav-link active" href="/profile"><@spring.message 'navbar.navi.profile'/></a>
            </#if>
            <a class="nav-link" href="/vacancies"><@spring.message 'navbar.navi.vacancies'/></a>
            <a class="nav-link" href="/resumes"><@spring.message 'navbar.navi.resumes'/></a>

        </div>
        <div class="right-buttons">
            <div class="dropdown">
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

            <a class="btn btn-primary btn-sign-in" href="/logout"
               id="logout"><@spring.message 'navbar.user.logout'/></a>
        </div>
    </div>
</nav>
<div class="container">

    <div class="vacancies-container">

        <#list jobs as job>
            <div class="vacancy">
                <h3>${job.jobTitle}</h3>
                <p><@spring.message 'company.name'/>: ${job.user.accountName}</p>
                <p><@spring.message 'text.date'/>: ${job.date}</p>
                <a href="/click/hr/${job.id}" class="apply-button">Clicked users</a>
            </div>
        </#list>

    </div>
    <#if jobs?size == 0>
        <p class="no-vacancies-message"><@spring.message 'not.available'/></p>
    </#if>


    <script>
        document.getElementById('logout').addEventListener('click', () => {
            localStorage.removeItem('user')
        })
    </script>

</div>
</body>
</html>
