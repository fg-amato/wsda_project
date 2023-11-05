<!doctype html>
<html lang="it" class="h-100">
<head>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.7.1.slim.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/utilFunctions.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <style>
        .box {
            cursor: pointer;
        }
    </style>

    <title>Home User</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<main class="flex-shrink-0">

    <div class="container">
        <div class="alert alert-danger alert-dismissible ${errorMessage==null?'d-none':'' }"
             role="alert" id="error">
            <strong>${errorMessage}</strong>
            <button onclick="hideErrorMessage()" type="button" class="close"
                    data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="alert alert-success alert-dismissible ${successMessage==null?'d-none':'' }" role="alert"
             id="success">
            <strong>${successMessage}</strong>
            <button onclick="hideSuccessMessage()" type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>
    <div class="container mt-5">
        <h1 class="text-center">Bentornato ${userInfo.username}!</h1>
        <div class="row justify-content-center">
            <div class="col-md-6 box"
                 onclick="location.href='${pageContext.request.contextPath }/user/ExecuteShowContoServlet?idUser=${userInfo.id}'">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Visualizza Conto</h5>
                        <p class="card-text">Clicca qui per accedere alla Funzionalita'</p>
                    </div>
                </div>
            </div>

            <div class="col-md-6 box" onclick="location.href='${pageContext.request.contextPath }/user/PrepareSearchTransactionServlet'">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Genera report</h5>
                        <p class="card-text">Clicca qui per accedere alla Funzionalita'</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
</body>
</html>