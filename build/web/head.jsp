    <link href="<%= request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath()%>/assets/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/all.css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/style.css">
    <script src="<%= request.getContextPath()%>/assets/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/assets/js/popper.min.js"></script>
    <script src="<%= request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath()%>/assets/js/bootstrap-select.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="#"><i class="fas fa-book-medical mr-2"></i><strong>You</strong> Health</a><button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown-1" aria-controls="navbarNavDropdown-1" aria-expanded="false" aria-label="Toggle navigation" style=""><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown-1">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active"><a class="nav-link" href="index.jsp">Acceuil <span class="sr-only">(current)</span></a></li>
                <li class="nav-item"><a class="nav-link" href="rendezvous.jsp">Prendre Rendez-vous</a></li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Recherches</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="PaginationTest?page=1">M&eacutedicament</a><a class="dropdown-item" href="#">Medecin</a><a class="dropdown-item" href="#">Auto-consultation</a>
                    </div>
                </li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Archives</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">
                        <a class="dropdown-item" href="upload_analyse.jsp">Envoyer analyse</a><a class="dropdown-item" href="download_file.jsp">Archives donn&eacute;es</a>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-twitter"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-facebook"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-linkedin"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-github"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="login.jsp"><i class="fas fa-user"></i></a></li>
            </ul>
        </div>
    </div>
</nav>