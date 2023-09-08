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
        .apply-btn2 {
            background-color: #fff;

            color: #00a86b;
            border: solid #00a86b;
            padding: 8px 20px;
            border-radius: 4px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .apply-btn2:hover {
            background-color: #fff;
            color: #00a86b;
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


    </style>
    <title>Specific Vacancy</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <div class="navbar-links">
            <a class="nav-link" aria-current="page" href="/">Home</a>
            <#if username??><a class="nav-link" href="/profile">Profile</a></#if>
            <a class="nav-link active" href="/vacancies">Vacancies</a>
            <#if username?? && type == "EMPLOYER"><a class="nav-link " href="/resumes">Resumes</a></#if>
            <#if username?? && type == "JOB_SEEKER">
                <a class="nav-link" href="/jobresumes">Jobs</a>
            </#if>
        </div>
        <div class="right-buttons">
            <a class="btn btn-primary btn-sign-in" href="/logout" id="logout">Log Out</a>
        </div>
    </div>
</nav>

<div class="container">
    <h1 class="job-title">Job:${jobresume.jobTitle}</h1>
    <div class="salary">Salary: ${jobresume.salary}</div>
    <div class="experience">Experience:${jobresume.experience}</div>
    <div class="job-description">
        <h2>Job Description:</h2>
        <p>
            ${jobresume.jobDescription}
        </p>
        <p>
            Key Responsibilities:
        </p>
        <ul>
            <li>Design, code, test, and debug software applications</li>
            <li>Collaborate with product managers and software architects to define technical requirements</li>
            <li>Contribute to the development of new features and improvements</li>
            <li>Perform code reviews and provide constructive feedback to team members</li>
            <li>Participate in team meetings and contribute to the overall development process</li>
        </ul>
    </div>
    <div class="category">Category: ${jobresume.category}</div>

    <#if type=="JOB_SEEKER">
        <#if isInterested>
            <a href="/click/delete/${jobresume.id}">
                <button class="apply-btn2">Unclick</button>
            </a>

        <#else>
            <a href="/click/${jobresume.id}">
                <button class="apply-btn">Click</button>
            </a>
        </#if>

        <a href="/vacancies/${jobresume.user.id}/chat">
            <button class="apply-btn">Chat</button>
        </a>
    </#if>
    <div class="contact-info">
        <span>Contact Email: <a href="mailto:contact@example.com">${jobresume.user.email}</a></span>
        <span>Contact Phone: <a href="tel:+123456789">${jobresume.user.phoneNumber}</a></span>
    </div>
</div>
<script>
    document.getElementById('logout').addEventListener('click', () => {
        localStorage.removeItem('user')
    })
</script>
</body>
</html>
