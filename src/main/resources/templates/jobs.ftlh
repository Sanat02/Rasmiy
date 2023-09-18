
<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
        }

        .container {
            margin-top: 100px;
            max-width: 800px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            border-bottom: 1px solid #ccc;
            padding: 10px 0;
        }

        .publisher {
            font-weight: bold;
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

        .search-form {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .search-form input[type="text"] {
            width: 250px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px 0 0 4px;
            box-sizing: border-box;
        }

        .search-form button {
            background-color: greenyellow;
            color: #fff;
            border: none;
            border-radius: 0 4px 4px 0;
            padding: 8px 20px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .search-form button:hover {
            background-color: #008754;
        }

        .vtxt {
            margin-bottom: 40px;
        }

        .publ {
            width: 150px;
            display: inline-block;
        }


        .pag {
            margin-left: 15px;
            margin-top: 10px;
            text-decoration: none;
            color: white;
            background-color: #00a86b;
            padding: 5px;
        }

        form {
            max-width: 660px;
            margin-bottom: 20px;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
            border-radius: 5px;
        }

        label {
            font-weight: bold;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #fff;
            font-size: 16px;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 3px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .job {
            margin-right: 5px;
        }
    </style>
    <title>Resumes</title>
</head>
<body>


<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><@spring.message 'navbar.logo'/></a>
        <div class="navbar-links">
            <a class="nav-link" aria-current="page" href="/"><@spring.message 'navbar.navi.home'/></a>
            <a class="nav-link" href="/profile"><@spring.message 'navbar.navi.profile'/></a>
            <a class="nav-link" href="/vacancies"><@spring.message 'navbar.navi.vacancies'/></a>
            <a class="nav-link active" href="#"><@spring.message 'navbar.navi.jobs'/></a>
        </div>
        <div class="right-buttons">
            <a class="btn btn-primary btn-sign-in" href="/logout" id="logout"><@spring.message 'logout'/></a>
        </div>
    </div>
</nav>

<div class="container">
    <form action="/jobresumes" method="GET">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>

        </#if>
        <label for="sort"><@spring.message 'text.sort'/>:</label>
        <select id="sort" name="sort">
            <option name="date" value="date"><@spring.message 'text.date'/></option>
            <option name="clicks" value="clicks"><@spring.message 'text.clicks'/></option>
        </select>
        <button type="submit"><@spring.message 'button.sort'/></button>
    </form>


    <#if jobs.content?size == 0>
        <p>No vacancies found.</p>
    <#else>
        <ul>
            <#list jobs.content as resume>
                <a href="/jobresumes/${resume.id}">
                    <li>
                        <div class="publ"><span class="publisher">${resume.user.accountName}</span></div>
                        <span class="job">${resume.jobTitle}</span>
                        <span class="job">${resume.date}</span>
                    </li>
                </a>
            </#list>
        </ul>
    </#if>


    <#if jobs.hasPrevious()>
        <a class="pag" href="/jobresumes?page=${jobs.number - 1}"><@spring.message 'button.previous'/>/a>
    </#if>
    <#if jobs.hasNext()>
        <a class="pag" href="/jobresumes?page=${jobs.number + 1}"><@spring.message 'button.next'/></a>
    </#if>
    <script>
        document.getElementById('logout').addEventListener('click', () => {
            localStorage.removeItem('user')
        })
    </script>

</body>
</html>
