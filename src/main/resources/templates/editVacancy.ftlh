<!DOCTYPE html>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet' />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <style>body {
        font-family: 'Poppins', sans-serif;
    }
    
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 40px;
    }
    
    .navbar a {
        margin-top: 10px;
        margin-bottom: 10px;
        float: left;
        display: block;
        color: #212529;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        margin-left: 10px;
    }
    
    .navbar a:hover {
        background-color: #ddd;
    }
    
    .navbar a.active {
        background-color: greenyellow;
        color: white;
        border-radius: 4px;
    }
    
    .navbar a.disabled {
        color: #6c757d;
        pointer-events: none;
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
    
    .aboutbox {
        display: flex;
    }
    
    .abtext {
        margin-bottom: 0px;
    }
    
    .btn {
        width: 200px;
        background-color: greenyellow;
        margin-left: 40px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    
    .text {
        width: 300px;
    }
    
    .social {
        margin-top: 40px;
        width: 800px;
    }
    
    .social h2 {
        margin-bottom: 10px;
    }
    
    .social-links {
        display: flex;
        justify-content: space-between;
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
    .publishbtn{
        margin-bottom: 100px;
        margin-top: 50px;
        width: 730px;
        height: 60px;
        background-color: greenyellow;
        display: flex;
        justify-items: center;
        justify-content:center ;
        align-items: center;
    }
    #vacancy-description{
        height: 220px;
    }
    </style>
    <title>Edit Vacancy</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary" >
        <div class="container-fluid">
          <a class="navbar-brand" href="#">Navbar</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link " aria-current="page" href="#">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="#">Profile</a>
              </li>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancies">Vacancies</a>
                </li>
              <li class="nav-item">
                <a class="nav-link" href="/resumes">Resumes</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    <div class="container">
        <h2>Vacancy Submission Form</h2>
<form action="/jobresumes/add/${userId}" method="post">
    <label for="resume_name">Name of the vacancy:</label>
    <input type="text" id="resume_name" name="resume_name" required><br><br>

    <label for="category">Category:</label>
    <select id="category" name="category" required>
        <option value="">Select category</option>
        <option value="1">IT</option>
        <option value="2">Marketing</option>
        <option value="3">Finance</option>
        <option value="4">Education</option>
        <option value="5">Human Resources</option>
    </select><br><br>

    <label for="expected_salary">Salary (USD):</label>
    <input type="number" id="expected_salary" name="expected_salary" required><br><br>

    <label for="vacancy-description">Vacancy description:</label>
    <input type="text" id="vacancy-description" name="vacancy-description" required><br><br>


    <h1>Required experience</h1>
    <label for="from_year">From:</label>
            <input type="number" id="from_year" name="from_year" placeholder="Enter the start" required>

            <label for="till_year">Till:</label>
            <input type="number" id="till_year" name="till_year" placeholder="Enter the end" required>


    <a class="nav-link active" href="#"><input type="submit" value="Submit"></a>
</form>
    

    </div>

     
</body>
</html>
