<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="hero-unit">
	<h3>
		${votacionDTO.votacion.title}
	</h3>

	<c:if test="${not empty votacionDTO}">
			<c:set var="votacion" value="${votacionDTO.votacion}" scope="request" />
            <c:set var="votosDiputados" value="${votacionDTO.votosDiputados}" scope="request" />
            <c:set var="totalPublico" value="${votacionDTO.totalPublico}" scope="request" />
            <c:set var="principalAlreadyVoted" value="${votacionDTO.principalAlreadyVoted}" scope="request" />
			<jsp:include page="../votacion/votacion.jsp"/>
	</c:if>


</div>