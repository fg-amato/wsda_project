<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-3">
    <h6 class="navbar-brand">WSDA PROJECT A.A. 22/23</h6>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}">Welcome Page</a>
            </li>
            <c:if test="${userInfo!=null}">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${userInfo.isAdmin()}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Area Riservata</a>
                        </c:when>
                        <c:when test="${userInfo.isMerchant()}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/merchant/homeMerchant.jsp">Area Riservata</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/homeUser.jsp">Area Riservata</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:if>
            <c:if test="${userInfo != null && userInfo.isAdmin() }">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/PrepareInsertUserServlet">Inserisci
                        utente</a>
                </li>
            </c:if>

            <c:if test="${userInfo != null && userInfo.isAdmin() }">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/PrepareSearchTransactionReportServlet">Genera report</a>
                </li>
            </c:if>

            <c:if test="${userInfo != null && userInfo.isMerchant()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/merchant/ExecuteListTransactionCreatedServlet?idMerchant=${userInfo.id}">Gestisci transazioni</a>
                </li>
            </c:if>

            <c:if test="${userInfo != null && userInfo.isClassicUser()}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/PrepareSearchTransactionServlet">Genera report</a>
                </li>
            </c:if>

            <c:if test="${userInfo != null}">
                <li class=" nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/public/ExecuteLogoutServlet">Logout</a>
                </li>
            </c:if>
        </ul>
    </div>

</nav>
