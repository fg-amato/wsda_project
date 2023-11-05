<!doctype html>
<html lang="it" class="h-100">
<head>
    <script src="${pageContext.request.contextPath}/js/utilFunctions.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.7.1.slim.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

    <title>Ricerca transazioni della tua carta</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<main class="flex-shrink-0">

    <div class="container">
        <div class="alert alert-success alert-dismissible ${successMessage==null?'d-none':'' }" role="alert"
             id="success">
            <strong>${successMessage}</strong>
            <button onclick="hideSuccessMessage()" type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>

        <div class='card'>
            <div class='card-header'>
                <h5>Ricerca Transazioni</h5>
            </div>
            <div class='card-body'>

                <form method="get" action="${pageContext.request.contextPath }/shared/ExecuteReportServlet"
                      class="row g-3" id="myForm">


                    <div class="col-md-6">
                        <label for="importo" class="form-label">Importo</label>
                        <input type="text"
                               class="form-control" id="importo"
                               name="importo"
                               placeholder="Inserisci importo minimo" value="0">
                        <span id="errorValidation" style="color: red;"></span>
                    </div>


                    <div class="col-md-6">
                        <label for="dateTransaction" class="form-label">A partire da</label>
                        <input class="form-control" id="dateTransaction" type="date" placeholder="dd/MM/yyyy"
                               title="formato : gg/mm/aaaa" name="dateTransaction">
                    </div>

                    <div class="col-md-6 mt-4">
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="accredito" name="tipologia" value="+" checked required>
                            <label class="form-check-label" for="accredito">Accredito</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="addebito" name="tipologia" value="-">
                            <label class="form-check-label" for="addebito">Addebito</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="estratto-conto" name="tipologia" value="none">
                            <label class="form-check-label" for="estratto-conto">Accr./Add. (importo sara' ignorato)</label>
                        </div>
                        <input type="hidden" name="userId" value="${userInfo.id}">
                        <input type="hidden" name="userTypeParam" value="CLASSIC_USER">
                    </div>

                    <div class="col-12 mt-2">
                        <button type="submit" name="submit" class="btn btn-outline-primary float-right ml-2">Genera
                        </button>
                        <input class="btn btn-outline-warning float-right mr-2" type="reset" value="Ripulisci">
                    </div>


                </form>


                <!-- end card-body -->
            </div>
            <!-- end card -->
        </div>

    </div>

</main>
<script>
    document.getElementById("myForm").addEventListener("submit", function (event) {
        var numberInput = document.getElementById("importo").value ;

        if(!!numberInput){
            if (!isValidDecimal(numberInput)) {

                document.getElementById("errorValidation").textContent = "Inserire un numero valido.";
                event.preventDefault();
            } else {

                var importo = parseFloat(numberInput);
                if (importo < 0) {
                    document.getElementById("errorValidation").textContent = "Il numero deve essere maggiore di zero.";
                    event.preventDefault();
                } else{
                    document.getElementById("errorValidation").textContent = "";
                }
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
