<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Brand and toggle get grouped for better mobile display -->
<div class="navbar-header">
  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
  </button>
</div>

<!-- Collect the nav links, forms, and other content for toggling -->
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
  <ul class="nav navbar-nav">  	
  	<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 1) {%> class="active" <%}%> >    
    	<a href="index.jsp">Inicio</a>
    </li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%> class="active" <%}%> >
    	<a href="que_es.jsp">&iquest;Qu&eacute; es?</a>
    </li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 3) {%> class="active" <%}%> >
    	<a href="como_funciona.jsp">&iquest;C&oacute;mo funciona?</a>
    </li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 4) {%> class="active" <%}%> >
    	<a href="preguntas_frecuentes.jsp">Preguntas Frecuentes</a>
    </li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 5) {%> class="active" <%}%> >
    	<a href="contacto.jsp">Contacto</a>
    </li>
  </ul>
</div><!-- /.navbar-collapse -->            