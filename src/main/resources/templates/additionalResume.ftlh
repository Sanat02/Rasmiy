<!DOCTYPE html>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet' />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
        }
        .container {
            max-width: 800px;
            margin: 60px auto;
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

        .category {
            color: #00a86b;
        }

        .date {
            color: #777;
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

        /* Style for the "Sign In" and "Sign Up" buttons */
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
            border-radius: 4px;
            padding: 8px 20px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
            margin-left: -4px;
        }

        .search-form button:hover {
            background-color: #008754;
        }

        .vtxt{
            margin-bottom: 40px;
        }


        .form-section h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .form-section form {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-section form label {
            font-weight: bold;
        }

        .form-section form input[type="text"],
        .form-section form input[type="date"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-section form button {
            background-color: greenyellow;
            color: #fff;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .form-section form button:hover {
            background-color: #008754;
        }
        #addEducationBtn{
            margin-bottom: 10px;
            display: inline-block;
            margin-right: 10px;
            background-color: #00a86b;
            border:none;
        }
    </style>
    <title>Resume</title>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <div class="navbar-links">
            <!-- Navigation links -->
            <a class="nav-link " aria-current="page" href="#">Home</a>
            <a class="nav-link active" href="/profile">Profile</a>
            <a class="nav-link" href="/vacancies">Vacancies</a>

        </div>
        <div class="right-buttons">
            <a class="btn btn-primary btn-sign-in" href="/logout">Log Out</a>
        </div>
    </div>
</nav>

    <div class="container">
        <h1 class="vtxt">Resume</h1>


        <ul>
            <li><span class="publisher">Category:</span> ${resume.category}</li>
            <li><span class="publisher">Resume Name:</span> ${resume.job}</li>
            <li><span class="publisher">Expected Salary:</span> ${resume.expectedSalary}</li>

        </ul>

        <#if resume.education??>
        <h1 class="vtxt">Education</h1>
        <ul>
            <li><span class="publisher">University:</span> ${resume.education.institutionName}</li>
            <li><span class="publisher">Degree: </span>${resume.education.degree} </li>
            <li><span class="publisher">Start date: </span> ${resume.education.startDate}</li>
            <li><span class="publisher">End date: </span>${resume.education.endDate}</li>
        </ul>
        <#else>
            <a href="/resumes/${resume.id}/education">
            <div>
                <button class="btn btn-primary" id="addEducationBtn">Add Education</button>
            </div>
            </a>
        </#if>


        <#if resume.jobExperience??>
            <h1 class="vtxt">Experience</h1>
                    <ul>
                        <li><span class="publisher">Position:</span> ${resume.jobExperience.position}</li>
                        <li><span class="publisher">Start date:</span>${resume.jobExperience.startDate}</li>
                        <li><span class="publisher">End date:</span>${resume.jobExperience.endDate}</li>
                    </ul>
        <#else>
            <div>
                <a href="/resumes/${resume.id}/experience">
                <button class="btn btn-primary" id="addEducationBtn">Add Experience</button>
                    </a>
            </div>
        </#if>
        <#if resume.contacts?size == 0>
            <a href="/resumes/${resume.id}/contacts">
                <button class="btn btn-primary" id="addEducationBtn">Add Contact</button>
            </a>
         <#else>
             <h1 class="vtxt">Contacts</h1>
             <#list resume.contacts as contact>
                 <li><span class="publisher">${contact.type}</span> ${contact.value}</li>
             </#list>
        </#if>

    </div>
</body>
</html>
