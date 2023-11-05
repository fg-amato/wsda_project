<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>

  <!-- Includi il foglio di stile di Bootstrap -->
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
  <title>Visualizza Carta</title>

</head>
<body class="d-flex flex-column h-100">

<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp"/>


<!-- Begin page content -->
<main class="flex-shrink-0">
  <div class="container">

    <div class='card'>
      <div class='card-header'>
        <h5>Visualizza dettaglio</h5>
      </div>


      <div class='card-body'>
        <dl class="row">
          <dt class="col-sm-3 text-left">Id:</dt>
          <dd class="col-sm-9">${cardToShow.id}</dd>
        </dl>

        <dl class="row">
          <dt class="col-sm-3 text-left">Numero di carta:</dt>
          <dd class="col-sm-9">${cardToShow.cardNumber}</dd>
        </dl>

        <dl class="row">
          <dt class="col-sm-3 text-left">Saldo:</dt>
          <dd class="col-sm-9">${cardToShow.balance}</dd>
        </dl>

        <!-- info Utente -->
        <dl class="row">
          <dt class="col-sm-3 text-left">Titolare:</dt>
          <dd class="col-sm-9">${cardToShow.cardHolderDTO.username}</dd>
        </dl>


        <!-- end card body -->
      </div>

      <div class='card-footer'>
        <c:if test="${cardToShow.enabled}">
          <a class="btn btn-outline-danger btn-sm float-right ml-2" href="${pageContext.request.contextPath}/admin/ExecuteChangeStateCardServlet?idCarta=${cardToShow.id }">Disabilita</a>
        </c:if>
        <c:if test="${!cardToShow.enabled}">
          <a class="btn btn-outline-success btn-sm float-right mr-2" href="${pageContext.request.contextPath}/admin/ExecuteChangeStateCardServlet?idCarta=${cardToShow.id }">Abilita</a>
        </c:if>
      </div>
      <!-- end card -->
    </div>


    <!-- end container -->
  </div>

</main>

</body>
</html>