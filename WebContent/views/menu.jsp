<div class="container">
	<div class="row h_menu">
		<nav class="navbar navbar-default navbar-left" role="navigation">
			<% if  (session.getAttribute("usrLogin") != null){  %>
				<% if  (session.getAttribute("perfil") == "administrador"){ %>
					<%@ include file="menu_administrador.jsp"%>
				<% }
	               else{%>
					<%@ include file="menu_viajero.jsp"%>
				<% }
               }
               else{  
            %>								
				<jsp:include page="menu_visitante.jsp">
					<jsp:param name="itemActivo" value="${param.itemActivo}"/>				
				</jsp:include>		
			<% } %>
		</nav>
	</div>
</div>