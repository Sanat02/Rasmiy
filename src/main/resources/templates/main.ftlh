<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <meta name="copyright" content="MACode ID, https://macodeid.com/">


    <link rel="stylesheet" href="../../static/css/maicons.css">

    <link rel="stylesheet" href="../../static/css/bootstrap.css">

    <link rel="stylesheet" href="../../static/vendor/animate/animate.css">

    <link rel="stylesheet" href="../../static/css/theme.css">

</head>
<body>

<div class="back-to-top"></div>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white sticky" data-offset="500">
        <div class="container">
            <a href="#" class="navbar-brand">Rasmiy</a>

            <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarContent"
                    aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapse" id="navbarContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/"><@spring.message 'mainPage'/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/dashboard"><@spring.message 'documents'/></a>
                    </li>
                    </li>
                    <div class="dropdown">
                        <button class="btn btn-primary ml-lg-2 dropdown-toggle" type="button" id="languageDropdown"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <@spring.message 'lang'/>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="languageDropdown">
                            <a class="dropdown-item" href="?lang=ru">Русский</a>
                            <a class="dropdown-item" href="?lang=en">Кыргызча</a>
                        </div>
                    </div>


                </ul>
            </div>

        </div>
    </nav>

    <div class="container">
        <div class="page-banner home-banner">
            <div class="row align-items-center flex-wrap-reverse h-100">
                <div class="col-md-6 py-5 wow fadeInLeft">
                    <h1 class="mb-4"><@spring.message 'documents.header'/></h1>
                    <p class="text-lg text-grey mb-5"><@spring.message 'mainPageGoalText'/></p>
                    <a href="#" class="btn btn-primary btn-split">Rasmiy
                        <div class="fab"><span class="mai-play"></span></div>
                    </a>
                </div>
                <div class="col-md-6 py-5 wow zoomIn">
                    <div class="img-fluid text-center">
                        <img src="https://scontent.ffru1-4.fna.fbcdn.net/v/t1.6435-9/82611117_2607660616017550_4333850562643623936_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=5f2048&_nc_ohc=ubTujEQrAVwAb7GSil8&_nc_ht=scontent.ffru1-4.fna&oh=00_AfAdAUDr-uCYZ-gfIJhdcz6vnpWXqPfLa9RhSLA_1h472g&oe=66528FCE  " alt=""
                             style="width: 350px;">
                    </div>
                </div>
            </div>
            <a href="#about" class="btn-scroll" data-role="smoothscroll"><span class="mai-arrow-down"></span></a>
        </div>
    </div>
</header>

<div class="page-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <div class="card-service wow fadeInUp">
                    <div class="header">
                        <img src="https://infra-cloudfront-talkdeskcom.svc.talkdeskapp.com/talkdesk_com/6-advantages-of-using-acd.png"
                             alt="" style="width: 300px;">
                    </div>
                    <div class="body" style="height: 200px; overflow: auto;">
                        <h5 class="text-secondary"><@spring.message 'mainPageAdvantages1'/></h5>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="card-service wow fadeInUp">
                    <div class="header">
                        <img src="https://infra-cloudfront-talkdeskcom.svc.talkdeskapp.com/talkdesk_com/6-advantages-of-using-acd.png"
                             alt="" style="width: 300px;">
                    </div>
                    <div class="body" style="height: 200px; overflow: auto;">
                        <h5 class="text-secondary"><@spring.message 'mainPageAdvantages2'/></h5>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="card-service wow fadeInUp">
                    <div class="header">
                        <img src="https://infra-cloudfront-talkdeskcom.svc.talkdeskapp.com/talkdesk_com/6-advantages-of-using-acd.png"
                             alt="" style="width: 300px;">
                    </div>
                    <div class="body" style="height: 200px; overflow: auto;">
                        <h5 class="text-secondary"><@spring.message 'mainPageAdvantages3'/></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="page-section" id="about">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 py-3 wow fadeInUp">
                <span class="subhead"><@spring.message 'aboutUsText'/></span>
                <h2 class="title-section"><@spring.message 'mainZakonTitle'/></h2>
                <div class="divider"></div>

                <p><@spring.message 'mainZakonText'/></p>
                <p><@spring.message 'mainZakonText2'/></p>
                <a href="https://ud.gov.kg/?page_id=3100" class="btn btn-primary mt-3"><@spring.message 'readMore'/></a>
            </div>
            <div class="col-lg-6 py-3 wow fadeInRight">
                <div class="img-fluid py-3 text-center">
                    <img src="https://24.kg/files/media/237/237875.jpg" alt=""  style="width: 570px;">
                </div>
            </div>
        </div>
    </div>
</div>

<div class="page-section bg-light">
    <div class="container">
        <div class="text-center wow fadeInUp">
            <div class="subhead"><@spring.message 'servicesText'/></div>
            <h2 class="title-section"><@spring.message 'servicesText2'/></h2>
            <div class="divider mx-auto"></div>
        </div>

        <div class="row">
            <div class="col-sm-6 col-lg-4 col-xl-3 py-3 wow zoomIn">
                <div class="features">
                    <div class="header mb-3">
                        <span class="mai-business"></span>
                    </div>
                    <h5><@spring.message 'documentStatement'/></h5>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4 col-xl-3 py-3 wow zoomIn">
                <div class="features">
                    <div class="header mb-3">
                        <span class="mai-business"></span>
                    </div>
                    <h5><@spring.message 'documentOb'/></h5>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4 col-xl-3 py-3 wow zoomIn">
                <div class="features">
                    <div class="header mb-3">
                        <span class="mai-business"></span>
                    </div>
                    <h5><@spring.message 'documentResume'/></h5>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4 col-xl-3 py-3 wow zoomIn">
                <div class="features">
                    <div class="header mb-3">
                        <span class="mai-business"></span>
                    </div>
                    <h5><@spring.message 'documentTilKat'/></h5>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4 col-xl-3 py-3 wow zoomIn">
                <div class="features">
                    <div class="header mb-3">
                        <span class="mai-business"></span>
                    </div>
                    <h5><@spring.message 'documentPrg'/></h5>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4 col-xl-3 py-3 wow zoomIn">
                <div class="features">
                    <div class="header mb-3">
                        <span class="mai-business"></span>
                    </div>
                    <h5><@spring.message 'documentIsh'/></h5>
                </div>
            </div>
        </div>
    </div>

    <div class="page-section">
        <div class="container">
            <div class="text-center wow fadeInUp">
                <div class="subhead">Rasmiy</div>
                <h2 class="title-section"><@spring.message 'mainCategories'/></h2>
                <div class="divider mx-auto"></div>
            </div>

            <div class="row mt-5">
                <div class="col-lg-4 py-3 wow fadeInUp">
                    <div class="card-blog">
                        <div class="body">
                            <h5 class="post-title"><a href="#"><@spring.message 'mainPrivate'/></a></h5>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 py-3 wow fadeInUp">
                    <div class="card-blog">
                        <div class="body">
                            <h5 class="post-title"><a href="#"><@spring.message 'mainEdu'/></a></h5>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 py-3 wow fadeInUp">
                    <div class="card-blog">
                        <div class="body">
                            <h5 class="post-title"><a href="#"><@spring.message 'mainFamily'/></a></h5>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 py-3 wow fadeInUp">
                    <div class="card-blog">
                        <div class="body">
                            <h5 class="post-title"><a href="#"><@spring.message 'mainMedicine'/></a></h5>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 py-3 wow fadeInUp">
                    <div class="card-blog">
                        <div class="body">
                            <h5 class="post-title"><a href="#"><@spring.message 'mainWork'/></a></h5>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 py-3 wow fadeInUp">
                    <div class="card-blog">
                        <div class="body">
                            <h5 class="post-title"><a href="#"><@spring.message 'mainTransport'/></a></h5>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <footer class="page-footer bg-image" style="background-image: url(../assets/img/world_pattern.svg);">
        <div class="container">
            <div class="row mb-5">
                <div class="col-lg-3 py-3">
                    <h3>Rasmiy</h3>

                    <div class="social-media-button">
                        <a href="#"><span class="mai-logo-facebook-f"></span></a>
                        <a href="#"><span class="mai-logo-twitter"></span></a>
                        <a href="#"><span class="mai-logo-google-plus-g"></span></a>
                        <a href="#"><span class="mai-logo-instagram"></span></a>
                        <a href="#"><span class="mai-logo-youtube"></span></a>
                    </div>
                </div>

            </div>

            <p class="text-center" id="copyright">Made by <a
                        href="#" target="_blank">Bakytbekova Sanat</a></p>
        </div>
    </footer>

    <script src="../../static/js/jquery-3.5.1.min.js"></script>

    <script src="../../static/js/bootstrap.bundle.min.js"></script>

    <script src="../../static/js/google-maps.js"></script>

    <script src="../../static/vendor/wow/wow.min.js"></script>

    <script src="../../static/js/theme.js"></script>

</body>
</html>