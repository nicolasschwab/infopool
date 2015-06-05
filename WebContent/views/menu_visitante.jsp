<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    	<a href="index.jsp"><s:text name="menu.inicio" /></a>
    </li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%> class="active" <%}%> >
    	<a href="que_es.jsp"><s:text name="menu.quees" /></a>
    </li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 3) {%> class="active" <%}%> >
    	<a href="como_funciona.jsp"><s:text name="menu.comofunciona" /></a>
    </li>
    <li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 4) {%> class="active" <%}%> >
    	<a href="preguntas_frecuentes.jsp"><s:text name="menu.faq" /></a>
    </li>
  </ul>
</div><!-- /.navbar-collapse -->            