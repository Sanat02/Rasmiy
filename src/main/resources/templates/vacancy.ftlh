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

        .job-title {
            font-weight: bold;
            font-size: 24px;
            color: #333;
            margin-bottom: 10px;
        }

        .salary {
            color: #00a86b;
            font-size: 18px;
            margin-bottom: 5px;
        }

        .experience {
            font-size: 16px;
            margin-bottom: 5px;
        }

        .job-description {
            margin-bottom: 20px;
        }

        .category {
            color: #777;
            font-size: 16px;
            margin-bottom: 10px;
        }

        .apply-btn {
            background-color: #00a86b;
            color: #fff;
            border: none;
            padding: 8px 20px;
            border-radius: 4px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .apply-btn:hover {
            background-color: #008754;
        }

        .contact-info {
            margin-top: 20px;
        }

        .contact-info span {
            display: block;
            margin-bottom: 5px;
        }

        .contact-info a {
            color: #00a86b;
            text-decoration: none;
        }

        .contact-info a:hover {
            text-decoration: underline;
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

        .slider-container {
            display: flex;
            overflow-x: scroll;
            width: 100%;
            max-width: 100%;
            white-space: nowrap;
            margin-bottom: 5px;
            margin-top: 5px;
        }


        .resume-item {
            padding: 10px;
            margin-right: 10px;
            background-color: #f0f0f0;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: inline-block;
            vertical-align: top;
            margin-bottom: 6px;
        }

        h2 {
            margin-top: 15px;
            font-size: 18px;
        }

        .btn1 {
            color: #0056b3;
        }


    </style>
    <title>Specific Vacancy</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <div class="navbar-links">
            <a class="nav-link" aria-current="page" href="/">Home</a>
            <a class="nav-link " href="/profile">Profile</a>
            <a class="nav-link active" href="/vacancies">Vacancies</a>
        </div>
        <div class="right-buttons">
            <a class="btn btn-primary btn-sign-in" href="/logout" id="logout">Log Out</a>
        </div>
    </div>
</nav>

<div class="container">
    <h1 class="job-title">Company:${employer.accountName}</h1>
    <div class="salary">Phone: ${employer.phoneNumber}</div>
    <div class="experience">Email:${employer.email}</div>
    <div class="job-description">


        <h2>Available Job Resumes</h2>
        <#if employer.jobResumes?size == 0>
            <p>No job resumes found!</p>
        <#else>
            <div class="slider-container">
                <#list employer.jobResumes as jresume>
                    <div class="resume-item">
                        <div class="resumeinfo">
                            <div class="resumeName">${jresume.jobTitle}</div>
                            <div>Date of the update:</div>
                            <div>02/02/2022</div>
                        </div>
                        <div class="rbtn">
                            <a class="nav-link active" href="/jobresumes/${jresume.id}">
                                <div class="btn1">See</div>
                            </a>
                        </div>
                    </div>
                </#list>
            </div>
        </#if>
        <#if username?? && type == "JOB_SEEKER">
            <a href="/vacancies/${employer.id}/chat">
                <button class="apply-btn">Chat</button>
            </a>
        </#if>
    </div>
    <script>
        document.getElementById('logout').addEventListener('click', () => {
            localStorage.removeItem('user')
        })
    </script>
</body>
</html>
