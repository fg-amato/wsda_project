<!doctype html>
<html lang="it" class="h-100" >
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <title>Riepilogo</title>

</head>
<body class="d-flex flex-column h-100">

<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp"/>


<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <div class='card'>
            <div class='card-header'>
                <h5>Operazione da confermare</h5>
            </div>


            <div class='card-body'>
                <dl class="row">
                    <dt class="col-sm-3 text-left">Negoziante:</dt>
                    <dd class="col-sm-9">${transactionToCheck.merchantDTO.username}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-left">Importo:</dt>
                    <dd class="col-sm-9">${transactionToCheck.amount}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-left">Numero Carta:</dt>
                    <dd class="col-sm-9">${transactionToCheck.cardDTO.cardNumber}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-left">Intestatario carta:</dt>
                    <dd class="col-sm-9">${transactionToCheck.cardDTO.cardHolderDTO.username}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-left">Id transazione:</dt>
                    <dd class="col-sm-9">${transactionToCheck.id}</dd>
                </dl>
                <!-- end card body -->
            </div>

            <div class='card-footer'>

                    <a class="btn btn-outline-danger btn-sm float-right ml-2" href="${pageContext.request.contextPath }/merchant/ExecuteChangeStateTransactionServlet?idTransazione=${transactionToCheck.id}&Operation=DENY">Nega</a>

                    <a class="btn btn-outline-success btn-sm float-right mr-2" href="${pageContext.request.contextPath }/merchant/ExecuteChangeStateTransactionServlet?idTransazione=${transactionToCheck.id}&Operation=CONFIRM">Conferma</a>

            </div>
            <!-- end card -->
        </div>


        <!-- end container -->
    </div>

</main>

</body>
</html>
