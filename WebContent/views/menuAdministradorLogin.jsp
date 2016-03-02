<%@ page language="java" import="model.Usuario"%>
<%@ taglib prefix="s" uri="/struts-tags"%>							
<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		<img src="resources/images/user.png" alt="" class="img-perfil"> <%= ((Usuario)session.getAttribute("usrLogin")).getUsuario() %> <span class="caret"></span>
	</a>
	<ul class="dropdown-menu">
		<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%> class="active" <%}%>>
			<s:url var="verPerfilAdministrador" action="verPerfilAdministrador">
				<s:param name="id" value="%{user.id}"></s:param>
			</s:url>
			<s:a href="%{verPerfilAdministrador}"><span class="fa fa-user" aria-hidden="true"> </span> <s:text name="menuviajero.perfil" /></s:a>
		</li>		
		<li><a href='cerrarSesion' class="salir"><s:text name="login.salir" /></a></li>
	</ul>
</li>