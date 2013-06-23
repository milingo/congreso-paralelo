<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="hero-unit">
	<h1>
		<s:message code="view.index.title" />
	</h1>
	<p>Bienvenido al congreso de los ciudadanos!</p>
</div>

	<c:if test="${not empty votacionesActivas}">
		<c:forEach items="${votacionesActivas}" var="votacionActiva"
			varStatus="loop">
			<c:set var="votacion" value="${votacionActiva.votacion}" scope="request" />
            <c:set var="votosDiputados" value="${votacionActiva.votosDiputados}" scope="request" />
<%--             <c:set var="votosPublico" value="${votacionActiva.votosPublico}" scope="request" /> --%>
            <c:set var="totalPublico" value="${votacionActiva.totalPublico}" scope="request" />
            <c:set var="principalAlreadyVoted" value="${votacionActiva.principalAlreadyVoted}" scope="request" />
			<jsp:include page="../votacion/votacionBrief.jsp"/>
		</c:forEach>
	</c:if>

