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
            gap: 20px;
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
        input[type="number"],
        select,
        input[type="date"],
        select,
        input[type="tel"],
        select,
        input[type="email"] {
            width: 70%;
            padding: 8px;
            margin-bottom: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        form {
            margin-bottom: 30px;
        }

        input[type="submit"] {
            background-color: greenyellow;
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .educationbtn {
            background-color: greenyellow;
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-bottom: 10px;
            margin-right: 700px;
            margin-top: 20px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .abbox {
            display: flex;
            flex-direction: column;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            align-items: flex-start;
            justify-content: flex-start;
            align-self: center;
            padding-left: 10px;
            padding-right: 10px;
            padding-bottom: 17px;
            padding-top: 17px;
            gap: 10px;
            width: 800px;
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

        .secondForm {
            display: none;
        }

        .sbm {
            margin-top: 20px;
            border: none;
            background-color: greenyellow;
            width: 730px;
            height: 50px;
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
            <a class="nav-link" href="/profile"><@spring.message 'navbar.navi.profile'/></a>
            <a class="nav-link" href="/vacancies"><@spring.message 'navbar.navi.vacancies'/></a>
            <a class="nav-link" href="/jobresumes"><@spring.message 'navbar.navi.jobs'/></a>
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
            <a class="btn btn-primary btn-sign-in" href="/logout" id="logout"><@spring.message 'logout'/></a>
        </div>
    </div>
</nav>

<div class="container">
    <form action="/resumes/add" method="post" id="resumeForm">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>

        </#if>

        <label for="resume_name"><@spring.message 'resume.name'/></label>
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

        <label for="expected_salary"><@spring.message 'salary'/></label>
        <input type="number" id="expected_salary" name="expected_salary" required min="0"><br><br>

        <button type="button" class="educationbtn" id="edubtn"><@spring.message 'education'/></button>
        <div class="secondForm" id="secondForm" style="display: none;">
            <label for="university"><@spring.message 'university'/></label>
            <input id="edinput" type="text" id="university" name="university">
            <label for="degree"><@spring.message 'degree'/></label>
            <input id="edinput2" type="text" id="degree" name="degree">
            <label for="startDate"><@spring.message 'start.date'/></label>
            <input id="edinput3" type="date" id="startDate" name="startDate">
            <label for="endDate"><@spring.message 'end.date'/></label>
            <input id="edinput4" type="date" id="endDate" name="endDate">
        </div>

        <button type="button" class="educationbtn" id="expbtn"><@spring.message 'experience'/></button>
        <div class="secondForm" id="thirdForm" style="display: none;">
            <label for="position"><@spring.message 'position'/></label>
            <input id="exinput" type="text" id="position" name="position">
            <label for="startDateJob"><@spring.message 'start.date'/></label>
            <input id="exinput2" type="date" id="startDateJob" name="startDateJob">
            <label for="endDateJob"><@spring.message 'end.date'/></label>
            <input id="exinput3" type="date" id="endDateJob" name="endDateJob">
        </div>


        <button type="button" class="educationbtn" id="contactbtn"><@spring.message 'contacts'/></button>
        <div class="secondForm" id="fourthForm" style="display: none;">
            <label for="phone"><@spring.message 'phone.number'/></label>
            <input type="tel" id="phone" name="phone">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email">
            <label for="telegram">Telegram:</label>
            <input type="text" id="telegram" name="telegram">
            <label for="linkedin">LinkedIn:</label>
            <input type="text" id="linkedin" name="linkedin">
            <label for="facebook">Facebook:</label>
            <input type="text" id="facebook" name="facebook">
        </div>

        <button class="sbm" type="submit" onclick="validateAndSubmit()"><@spring.message 'update'/></button>

    </form>
</div>

<script>
    let edubutton = document.getElementById("edubtn");
    let eduform = document.getElementById("secondForm");


    edubutton.addEventListener('click', () => {
        if (eduform.style.display === "none" || eduform.style.display === "") {
            document.getElementById("edinput").setAttribute("required", "");
            document.getElementById("edinput2").setAttribute("required", "");
            document.getElementById("edinput3").setAttribute("required", "");
            document.getElementById("edinput4").setAttribute("required", "");
            eduform.style.display = "block"


        } else {
            document.getElementById("edinput").removeAttribute("required", "");
            document.getElementById("edinput2").removeAttribute("required", "");
            document.getElementById("edinput3").removeAttribute("required", "");
            document.getElementById("edinput4").removeAttribute("required", "");
            document.getElementById("edinput").value = "";
            document.getElementById("edinput2").value = "";
            document.getElementById("edinput3").value = "";
            document.getElementById("edinput4").value = "";
            eduform.style.display = "none";
        }
    });

    let expbutton = document.getElementById("expbtn");
    let expform = document.getElementById("thirdForm");
    expbutton.addEventListener('click', () => {
        if (expform.style.display === "none" || expform.style.display === "") {
            document.getElementById("exinput").setAttribute("required", "");
            document.getElementById("exinput2").setAttribute("required", "");
            document.getElementById("exinput3").setAttribute("required", "");
            expform.style.display = "block";

        } else {
            document.getElementById("exinput").removeAttribute("required", "");
            document.getElementById("exinput2").removeAttribute("required", "");
            document.getElementById("exinput3").removeAttribute("required", "");
            document.getElementById("exinput").value = "";
            document.getElementById("exinput2").value = "";
            document.getElementById("exinput3").value = "";
            expform.style.display = "none";

        }
    });
    let contactform = document.getElementById("fourthForm")
    let contactbtn = document.getElementById("contactbtn");

    contactbtn.addEventListener('click', () => {
        if (contactform.style.display === "none" || contactform.style.display === "") {
            contactform.style.display = "block";
        } else {
            contactform.style.display = "none";
        }

    })

    document.getElementById('logout').addEventListener('click',()=>{
        localStorage.removeItem('user')
    })


</script>
</body>
</html>