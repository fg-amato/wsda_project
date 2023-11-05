<!doctype html>
<html lang="it" class="h-100">
<head>
    <!-- Includi il foglio di stile di Bootstrap -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/utilFunctions.js"></script>
    <title>Inserisci Transazione</title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../navbar.jsp"/>

<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">
        <div
                class="alert alert-danger alert-dismissible ${errorMessage==null?'d-none':'' }"
                role="alert" id="error">
            ${errorMessage}
            <button onclick="hideErrorMessage()" type="button" class="close"
                    data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="card" style="width: 300px; margin-top: 180px;">
            <div class="card-body">
                <h5 class="card-title">Esegui transazione</h5>
                <form id="myForm" method="post"
                      action="${pageContext.request.contextPath }/merchant/CheckInsertTransactionServlet">
                    <div class="form-group">
                        <label for="cardNumber">Numero di carta:</label> <input type="text"
                                                                                class="form-control" id="cardNumber"
                                                                                name="cardNumber"
                                                                                placeholder="Inserisci numero di carta"
                                                                                required minlength="16" maxlength="16">
                    </div>
                    <div class="form-group">
                        <label for="importo">Importo:</label> <input type="text"
                                                                     class="form-control" id="importo"
                                                                     name="importo"
                                                                     placeholder="Inserisci importo" required>
                        <span id="errorValidation" style="color: red;"></span>

                    </div>

                    <div class="form-group">
                        <label for="transactionType">Tipo transazione:</label>
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="transactionType" name="tipologia" value="+"
                                   required>
                            <label class="form-check-label" for="transactionType">Accredito</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="addebito" name="tipologia" value="-">
                            <label class="form-check-label" for="addebito">Addebito</label>
                        </div>
                    </div>
                    <input type ="hidden" name= "merchantId" value = "${userInfo.id}">
                    <button type="submit" class="btn btn-outline-primary">Esegui operazione</button>
                    <input class="btn btn-outline-warning" type="reset"
                           value="Ripristina">
                </form>
            </div>
        </div>
    </div>
</main>
<script>
    document.getElementById("myForm").addEventListener("submit", function (event) {
        var numberInput = document.getElementById("importo").value;

        if (!isValidDecimal(numberInput)) {

            document.getElementById("errorValidation").textContent = "Inserire un numero valido.";
            event.preventDefault();
        } else {

            var importo = parseFloat(numberInput);
            if (importo <= 0) {
                document.getElementById("errorValidation").textContent = "Il numero deve essere maggiore di zero.";
                event.preventDefault();
            } else{
                document.getElementById("errorValidation").textContent = "";
            }
        }
    });

    function isValidDecimal(value) {
        // Utilizza una regular expression per verificare se il valore è un numero decimale valido
        var decimalPattern = /^[+-]?\d+(\.\d+)?$/;
        return decimalPattern.test(value);
    }
</script>
</body>
</html>