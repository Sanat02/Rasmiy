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

        .navbar a {
            margin-top: 10px;
            margin-bottom: 10px;
            float: left;
            display: block;
            color: #212529; /* Change the text color */
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            margin-left: 10px;
        }

        .navbar a:hover {
            background-color: #ddd; /* Add a background color on hover */
            
        }

        /* Add styles for active link */
        .navbar a.active {
            background-color:greenyellow;
            color: white;
            border-radius: 4px;
        }

        /* Add styles for disabled link */
        .navbar a.disabled {
            color: #6c757d;
            pointer-events: none; /* Disable link interaction */
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
        .vtxt{
            margin-bottom: 40px;
        }
        .publ{
            width: 150px;
            display: inline-block;
        }
        .cat{
            width: 150px;
            display: inline-block;
        }
    </style>
    <title>Vacancies</title>
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
                <a class="nav-link " href="#">Profile</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="#">Vacancies</a>
              </li>
            </ul>
          </div>
        </div>
    </nav>

    <div class="container">
        <h1 class="vtxt">Vacancies</h1>
        <div class="search-form">
            <input type="text" name="category" placeholder="Search by category">
            <button type="submit">Search</button>
        </div>
        <ul>
            <#list vacancies as vacancy>
            <li>
                <div class="publ"><span class="publisher">${vacancy.publisher.accountName}</span></div>
                <div class="cat"><span class="category">${vacancy.category.name}</span></div>
                <span class="date">${vacancy.date}</span>
            </li>
           </#list>
        </ul>
    </div>
</body>
</html>
