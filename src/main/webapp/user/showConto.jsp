<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!doctype html>
<html lang="it" class="h-100">
<head>
  <script src="${pageContext.request.contextPath}/assets/js/jquery-3.7.1.slim.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
  <title>Il tuo conto</title>
</head>

<body class="d-flex flex-column h-100">

<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp"/>


<!-- Begin page content -->
<main class="flex-shrink-0">
  <div class="container">

    <div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
      ${successMessage}
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
      ${errorMessage}
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <div class='card mt-4'>
      <div class='card-header'>
        <h5>Il tuo conto:</h5>
      </div>


      <div class='card-body'>

        <dl class="row">
          <dt class="col-sm-3 text-left">Numero di carta:</dt>
          <dd class="col-sm-9">${userCard.cardNumber}</dd>
        </dl>

        <dl class="row">
          <dt class="col-sm-3 text-left">Saldo:</dt>
          <dd class="col-sm-9">${userCard.balance}</dd>
        </dl>
        <!-- end card body -->
      </div>

      <!-- end card -->
    </div>
    <div class='card mt-4'>
      <div class='card-header'>
        <h5>Transazioni sul tuo conto</h5>
      </div>
      <div class='card-body'>

        <div class='table-responsive'>
          <table class='table table-striped '>
            <thead>
            <tr>
              <th>Data</th>
              <th>Importo</th>
              <th>Numero Carta</th>
              <th>Negoziante</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${transactionUserList }" var="transactionItem">
              <tr>
                <td><fmt:formatDate type="date" value="${transactionItem.dateTransaction}"/></td>
                <td style="color: ${transactionItem.amount > 0 ? 'green' : 'red'};"><strong>${transactionItem.amount}</strong></td>
                <td>${transactionItem.cardDTO.cardNumber}</td>
                <td>${transactionItem.merchantDTO.username}</td>
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