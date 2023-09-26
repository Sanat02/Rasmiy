<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>

    <style>body {
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
            width: 85px;
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

        form {
            width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        select,
        input[type="number"] {
            width: 70%;
            padding: 8px;
            margin-bottom: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: greenyellow;
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .btn {
            width: 200px;
            background-color: greenyellow;
            margin-left: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
        }


        .social h2 {
            margin-bottom: 10px;
        }


        .social-links a {
            display: inline-block;
            width: 40px;
            height: 40px;
            background-color: #007bff;
            color: #fff;
            border-radius: 50%;
            text-align: center;
            line-height: 40px;
            text-decoration: none;
            font-size: 18px;
            transition: background-color 0.3s ease;
        }

        .social-links a:hover {
            background-color: #0056b3;
        }

        .publishbtn {
            margin-bottom: 100px;
            margin-top: 50px;
            width: 730px;
            height: 60px;
            background-color: greenyellow;
            display: flex;
            justify-items: center;
            justify-content: center;
            align-items: center;
        }

        #vacancy-description {
            height: 220px;
        }

        #lk {
            background-color: greenyellow;
            border-color: greenyellow;;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><@spring.message 'navbar.logo'/></a>
        <div class="navbar-links">
            <a class="nav-link " aria-current="page" href="/"><@spring.message 'navbar.navi.home'/></a>
            <a class="nav-link active" href="/profile"><@spring.message 'navbar.navi.profile'/></a>
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
            <a class="btn btn-primary btn-sign-in" href="/logout" id="logout"><@spring.message 'logout'/></a>
        </div>
    </div>
</nav>
<div class="container">
    <#if error??>
        <div>${error}</div></#if>
    <form action="/jobresumes/add" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>

        </#if>

        <label for="resume_name"><@spring.message 'name.vacancy'/></label>
        <input type="text" id="resume_name" name="resume_name" required><br><br>

        <label for="category"><@spring.message 'category'/></label>
        <select id="category" name="category" required>
            <option value=""><@spring.message 'select.category'/></option>
            <option value="1">IT</option>
            <option value="2"><@spring.message 'marketing'/></option>
            <option value="3"><@spring.message 'finance'/></option>
            <option value="4"><@spring.message 'education'/></option>
            <option value="5"><@spring.message 'human.resources'/></option>
        </select><br><br>

        <label for="expected_salary"><@spring.message 'salary'/>:</label>
        <input type="number" id="expected_salary" name="expected_salary" required min="0"><br><br>

        <label for="vacancy-description"><@spring.message 'vacancy.description'/></label>
        <input type="text" id="vacancy-description" name="vacancy-description" required><br><br>


        <h1><@spring.message 'required.experience'/></h1>
        <label for="from_year"><@spring.message 'from'/></label>
        <input type="number" id="from_year" name="from_year" placeholder="Enter the start" required min="0">

        <label for="till_year"><@spring.message 'till'/></label>
        <input type="number" id="till_year" name="till_year" placeholder="Enter the end" required min="0">


        <a class="nav-link active" href="#"><input type="submit" value="<@spring.message 'submit'/>"></a>
    </form>


</div>
<script>
    document.getElementById('logout').addEventListener('click', () => {
        localStorage.removeItem('user')
    })
</script>


</body>
</html>
