<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>

    <!-- Includi il foglio di stile di Bootstrap -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <title>Lista Negozianti</title>
</head>

<body class="d-flex flex-column h-100">

<!-- Fixed navbar -->
<jsp:include page="../navbar.jsp"/>


<!-- Begin page content -->
<main class="flex-shrink-0">
    <div class="container">

        <div class='card'>
            <div class='card-header'>
                <h5>Lista dei negozianti</h5>
            </div>
            <div class='card-body'>
                <div class='table-responsive'>
                    <table class='table table-striped ' >
                        <thead>
                        <tr>
                            <th>Username</th>
                            <th>Stato</th>
                            <th>Azioni</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${merchant_list_attribute }" var="merchantItem">
                            <tr>
                                <td>${merchantItem.username }</td>
                                <td>${merchantItem.enabled? 'ATTIVO' : 'DISATTIVO'}</td>
                                <td>
                                    <c:if test="${merchantItem.enabled}">
                                        <a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath }/admin/ExecuteChangeStateMerchantServlet?idMerchant=${merchantItem.id }">Disabilita</a>
                                    </c:if>
                                    <c:if test="${!merchantItem.enabled}">
                                        <a class="btn btn-outline-success btn-sm" href="${pageContext.request.contextPath }/admin/ExecuteChangeStateMerchantServlet?idMerchant=${merchantItem.id }">Abilita</a>
                                    </c:if>

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