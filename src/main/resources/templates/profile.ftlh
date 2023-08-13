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

            display: flex;
            text-align: center;
            margin-top: 40px;
            flex-direction: column;
            gap:40px;
           
        }
        .header {
            font-size: 30px;
            margin-bottom: 20px;
        }
        .content {
           background-color:  #f5fbff;
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
           
        }
        .column{
            display: flex;
            padding-top: 30px;
            padding-bottom: 30px;
            padding-left: 50px;
            padding-right: 50px;
            align-items: center;
            justify-content: center;
            gap: 50px
        }
        .photo {
            max-width: 100%;
            max-height: 200px;
            border-radius: 50%;
            margin-bottom: 10px;
            margin-right: 50px;
        }
        
        
        .name{
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: flex-start;
            width: 240px;
            gap: 10px;
            font-size: 20px;
    
        }
        .name::after{
            margin-top: 8px;
           
        
        }
        .edit{
            width: 150px;
            background-color: greenyellow;
            padding-top: 3px;
            padding-bottom: 3px;
            padding-left: 5px;
            padding-right: 5px;
            font-size: 20px;
            border-radius: 6px;
        }

        .resumebtn{
            height: 122px;
            background-color:#b0b9c0;
            border-radius: 10px;
            display: flex;
            align-items: center;
            padding-left: 20px;
            padding-right: 20px;
            margin-left: 70px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            gap: 5px;
        }
        .clicks{
            height: 122px;
            background-color:#b0b9c0;
            border-radius: 10px;
            display: flex;
            align-items: center;
            padding-left: 40px;
            padding-right: 40px;

        }
        .resumecontent{
            background-color:  #f5fbff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            display: flex;
            padding-top: 30px;
            padding-bottom: 30px;
            padding-left: 50px;
            padding-right: 50px;
            align-items: center;
            justify-content: center;
            gap: 50px
        }
        .resumeinfo {
            padding-top: 25px;
            padding-bottom: 25px;
            padding-left: 18px;
            padding-right: 18px;
            background-color:#b0b9c0;
            margin-top: 10px;
            margin-bottom: 10px;
            display: flex;
            flex-direction: column;
            font-size: 20px;
            border-radius: 10px;
            border: none; /* Add a border (you can specify border style, width, and color) */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Add box shadow */
        }
        .resumetxt{
            font-size: 22px;
        }

        .resumeName{
            margin-bottom: 10px;
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
        

        .profileimg{
            width: 200px;
            height: 200px;
        }
        .rbtn{
            display: flex;
            justify-content: center;
            gap:15px;
            margin-top: 5px;
        }
        .btn1{
            font-size: 20x;
            width: 110px;
            padding-top: 6px;
            padding-bottom: 6px;
            background-color: greenyellow;
            border-radius: 5px;

        }
        .btn2{
            font-size: 20x;
            width:110px;
            padding-right: 10px;
            padding-top: 6px;
            padding-bottom: 6px;
            background-color: greenyellow;
            border-radius: 5px;

        }
        .svg{
            color: greenyellow;
        }

    </style>
    <title>Profile</title>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <div class="navbar-links">
            <!-- Navigation links -->
            <a class="nav-link" aria-current="page" href="/">Home</a>
            <a class="nav-link active" href="/profile">Profile</a>
            <a class="nav-link" href="/vacancies">Vacancies</a>
            <#if account.accountType=="EMPLOYER">
            <a class="nav-link " href="/resumes">Resumes</a>
            </#if>
        </div>
        <div class="right-buttons">
            <a class="btn btn-primary btn-sign-in" href="/logout">Log Out</a>
        </div>
    </div>
</nav>
    <div class="container">
        <div class="header">${account.accountType.value} profile</div>
        <div class="content">
            <div class="column">

                <#if account.profileImage??>
                    <div class="profileimg"><img class="photo" src=${account.profileImage} /></div>
                    <#else>
                      <div class="profileimg"><img class="photo" src="data:image/png;base64,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" alt="Profile Photo" /></div>

                </#if>
                <div class="name">
                    <div>Username:${account.accountName}</div>
                    <div>Email:${account.email}</div>
                    <a class="nav-link active" href="/image/${account.id}">
                    <div class="edit">
                        <div>Set Image</div>
                    </div>
                    </a>
                </div>

                <#if account.accountType == "JOB_SEEKER">
                <a class="nav-link active" href="/resumes/add/${account.id}">
                <div class="resumebtn">
                    <div class="svg"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                      </svg></div>
                    <div>Create <br>resume</div>
                </div>
                </a>
                <a class="nav-link active" href="#">
                <div class="clicks">Clicks</div>
               </a>
                    <#else>
                        <a class="nav-link active" href="/jobresumes/add">
                            <div class="resumebtn">
                                <div class="svg"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                    </svg></div>
                                <div>Create <br>job resume</div>
                            </div>
                        </a>
                        <a class="nav-link active" href="#">
                            <div class="clicks">Clicks</div>
                        </a>
                </#if>
            </div>
        </div>
        <div class="resumetxt">Resume</div>
        <div class="resumecontent">

            <#list account.resumes as resume>
            <div >
                 <div class="resumeinfo">
                    <div class="resumeName">${resume.job}</div>
                    <div>Date of the update:</div>
                    <div>02/02/2022</div>
                 </div>
              <div class="rbtn">
                <a class="nav-link active" href="#">
                <div class="btn1">See</div>
                </a>
                <a class="nav-link active" href="#">
                <div class="btn2">Edit</div>
                </a>
              </div>
            </div>
            </#list>

            <#list account.jobResumes as jresume>
                <div >
                    <div class="resumeinfo">
                        <div class="resumeName">${jresume.jobTitle}</div>
                        <div>Date of the update:</div>
                        <div>02/02/2022</div>
                    </div>
                    <div class="rbtn">
                        <a class="nav-link active" href="/jobresumes/${jresume.id}">
                            <div class="btn1">See</div>
                        </a>
                        <a class="nav-link active" href="/jobresumes/edit/${jresume.id}">
                            <div class="btn2">Edit</div>
                        </a>
                    </div>
                </div>
            </#list>
        </div>
       </div>
    </div>
</body>
</html>
