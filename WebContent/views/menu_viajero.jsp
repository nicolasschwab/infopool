<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="navbar-header">
  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
  </button>
</div>
<!-- Collect the nav links, forms, and other content for toggling -->
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
  <ul class="nav navbar-nav">
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 1) {%> class="active" <%}%>><a href="index.jsp"><span class="fa fa-home" aria-hidden="true"> </span> <s:text name="menu.inicio" /></a></li>                
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%> class="active" <%}%>><a href="miPerfil"><span class="fa fa-user" aria-hidden="true"> </span> <s:text name="menuviajero.perfil" /></a></li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 3) {%> class="active" <%}%>><a href="viajes"><span class="fa fa-road" aria-hidden="true"> </span> <s:text name="menuviajero.viajes" /></a></li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 4) {%> class="active" <%}%>><a href="listarMensajes"><span class="fa fa-comment" aria-hidden="true"> </span> <s:text name="menuviajero.mensajes" /></a></li>
    <li><a href="#" onclick="javascript: mostrarNotificaciones()"><i class="fa fa-bell-o"></i><span id="circulo" class="circulo"></span></a></li>
  </ul>
  <div id="notificaciones">
  		<div class="flechaArriba"></div>
  		<div id="header">Notificaciones	</div>
  		<div id="bodyNotificaciones">
  				
  		</div>
  		<div class="footer">Ver Todas</div>
  </div>
</div><!-- /.navbar-collapse -->            