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
                <li class="nav-item active"><a class="nav-link" href="index_admin.jsp">Acceuil <span class="sr-only">(current)</span></a></li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Insertion données</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <button class="dropdown-item" type="button" data-toggle="modal" data-target="#exampleModal1">M&eacute;decin</button><button class="dropdown-item" type="button" data-toggle="modal" data-target="#exampleModal2">Patient</button><button class="dropdown-item" type="button" data-toggle="modal" data-target="#exampleModal3">Rendez-vous</button>
                    </div>
                </li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Lister Tables</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">M&eacute;decin</a><a class="dropdown-item" href="#">Patient</a><a class="dropdown-item" href="#">Rendez-vous</a>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-twitter"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-facebook"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-linkedin"></i></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-github"></i></a></li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user"></i></a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink3">
                        <a class="dropdown-item" href="#">Mon compte</a><a class="dropdown-item" href="<%= request.getContextPath()%>/LogoutServlet">Se d&eacute;connecter</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
                    <div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel1">M&eacute;decin</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form method="POST" action="AdminServlet?table=med&action=add">
                                        <p></p>
                                        <div class="form-group">
                                            <label for="name">Username</label>
                                            <input type="text" name="username" id="name" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Mot de passe</label>
                                            <input type="password" name="password" id="password" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="specialisation">Sp&eacute;cialisation</label>
                                            <input type="text" name="specialisation" id="specialisation" class="form-control">
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                        <button type="submit" class="btn btn-primary" >Ins&eacute;rer</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel2">Patient</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form method="POST" action="AdminServlet?table=pat&action=add">
                                        <p></p>
                                        <div class="form-group">
                                            <label for="name">Username</label>
                                            <input type="text" name="username" id="name" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Mot de passe</label>
                                            <input type="password" name="password" id="password" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="dtn">Date de naissance</label>
                                            <input type="date" name="dtn" id="dtn" class="form-control">
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                        <button type="submit" class="btn btn-primary" >Ins&eacute;rer</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel3">Rendez-vous</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form method="POST" action="AdminServlet?table=rdv&action=add">
                                        <p></p>
                                        <div class="form-group">
                                            <label for="idpat">Id Patient</label>
                                            <input type="text" name="idpat" id="idpat" class="form-control">
                                        </div>
                                         <div class="form-group">
                                            <label for="idmed">Id Medecin</label>
                                            <input type="text" name="idmed" id="idmed" class="form-control">
                                        </div>
                                         <div class="form-group">
                                            <label for="date">Date du rendez-vous</label>
                                            <input type="datetime-local" name="dateRdv" id="date" class="form-control">
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                        <button type="submit" class="btn btn-primary" >Valider</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>