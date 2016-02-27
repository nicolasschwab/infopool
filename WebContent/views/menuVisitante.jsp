<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 1) {%>
	class="active" <%}%>><a href="Inicio"><s:text
			name="menu.inicio" /></a></li>
<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 2) {%>
	class="active" <%}%>><a href="que_es.jsp"><s:text
			name="menu.quees" /></a></li>
<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 3) {%>
	class="active" <%}%>><a href="como_funciona.jsp"><s:text
			name="menu.comofunciona" /></a></li>
<li <% if (Integer.valueOf(request.getParameter("itemActivo")) == 4) {%>
	class="active" <%}%>><a href="preguntas_frecuentes.jsp"><s:text
			name="menu.faq" /></a></li>
