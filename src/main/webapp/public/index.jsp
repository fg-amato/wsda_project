<!doctype html>
<html lang="it" class="h-100">
<head>
    <!-- Includi il foglio di stile di Bootstrap -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

    <style>
        .transparent-border {
            border: none;
        }
    </style>

    <script src="${pageContext.request.contextPath}/js/utilFunctions.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>


    <title>Welcome Page</title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp" />
<h1 Align = "center">Benvenuto alla home page del progetto di wsda</h1>
<h2 Align ="center" class="${userInfo != null? 'd-none' : ''}">Vuoi accedere alla tua area riservata? <a href="${pageContext.request.contextPath }/public/PrepareLoginServlet">Login</a></h2>

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">


        <div
                class="alert alert-danger alert-dismissible ${errorMessage==null?'d-none':'' }"
                role="alert" id="error">
            <strong>${errorMessage}</strong>
            <button onclick="hideErrorMessage()" type="button" class="close"
                    data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div
                class="alert alert-info alert-dismissible ${saldo==null?'d-none':'' }"
                role="alert" id="info">
            <strong>Saldo carta ${cardNumber}: ${saldo} euro</strong>
            <button onclick="hideInfoMessage()" type="button" class="close"
                    data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>

    </div>


    <div class="container d-flex justify-content-center align-items-center" style="height: 60vh;">
        <div class="col-10">
            <div class="card transparent-border">
                <img src="${pageContext.request.contextPath}/assets/images/logo-unipa.png" class="card-img-top" alt="Logo unipa">
            </div>
            <h3>Verifica il saldo di una carta</h3>
            <form class="input-group" method="get" action="${pageContext.request.contextPath }/public/CheckBalanceCardServlet">
                <input type="text" name = "cardNumber" id = "cardNumber" class="form-control" placeholder="Search Card...">
                <button class="btn btn-primary" type="submit">Search</button>
            </form>
        </div>
    </div>

</main>

<script>
    function hideInfoMessage() {
        const infoElement = document.getElementById('info');
        if (infoElement) {
            infoElement.classList.add('d-none');
        }
    }
</script>
</body>
</html>

