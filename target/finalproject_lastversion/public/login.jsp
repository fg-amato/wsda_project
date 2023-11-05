<!doctype html>
<html lang="it" class="h-100">
<head>
    <!-- Includi il foglio di stile di Bootstrap -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/utilFunctions.js"></script>
    <title>Login</title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp" />

<!-- Begin page content -->
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
    </div>
    <div class="row justify-content-center">
        <div class="card" style="width: 300px; margin-top: 180px;">
            <div class="card-body">
                <h5 class="card-title">Accedi</h5>
                <form method="post"
                      action="${pageContext.request.contextPath }/public/ExecuteLoginServlet">
                    <div class="form-group">
                        <label for="username">Username:</label> <input type="text"
                                                                       class="form-control" id="username"
                                                                       name="username"
                                                                       placeholder="Inserisci username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label> <input type="password"
                                                                       class="form-control" id="password"
                                                                       name="password"
                                                                       placeholder="Inserisci password" required>
                    </div>
                    <button type="submit" class="btn btn-outline-primary">Accedi</button>
                    <input class="btn btn-outline-warning" type="reset"
                           value="Ripristina">
                </form>
            </div>
        </div>
    </div>
</main>

</body>
</html>