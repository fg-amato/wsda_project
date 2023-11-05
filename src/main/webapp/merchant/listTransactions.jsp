<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.7.1.slim.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <title>Lista Transazioni In attesa</title>
</head>

<body class="d-flex flex-column h-100">

<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp"/>


<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
            ${successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>
        <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
        </div>



        <div class='card'>
            <div class='card-header'>
                <h5>Transazioni in attesa</h5>
            </div>
            <div class='card-body'>

                <div class='table-responsive'>
                    <table class='table table-striped ' >
                        <thead>
                        <tr>
                            <th>Numero Carta</th>
                            <th>Prezzo</th>
                            <th>Azioni</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${transactionCreatedList }" var="transactionItem">
                            <tr>
                                <td>${transactionItem.cardDTO.cardNumber}</td>
                                <td>${transactionItem.amount}</td>
                                <td>
                                    <a class="btn btn-outline-success btn-sm ml-2" href="${pageContext.request.contextPath }/merchant/ExecuteChangeStateTransactionServlet?idTransazione=${transactionItem.id}&Operation=CONFIRM">Conferma</a>
                                    <a class="btn btn-outline-danger btn-sm mr-2" href="${pageContext.request.contextPath }/merchant/ExecuteChangeStateTransactionServlet?idTransazione=${transactionItem.id}&Operation=DENY">Nega</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- end card-body -->
            </div>
            <!-- end card -->
        </div>


        <!-- end container -->
    </div>

</main>

</body>
</html>