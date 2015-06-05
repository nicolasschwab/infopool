<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>    
    <%@ include file="views/heads.jsp" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
</head>
<body>

    <%@ include file="views/header.jsp"%>
   <jsp:include page="views/menu.jsp">
    	<jsp:param name="itemActivo" value="99"/>
    </jsp:include>

    <div class="main_bg">    
        <div class="container">        
        	<div class="col-md-4 text-center centrar margentb2">
                <% String user = (String) session.getAttribute("perfil"); 
                if (user == null){ %>
                <h3><s:text name="login.ingresarlbl" /></h3>                
                <s:fielderror />
                <s:form cssClass="form-signin" theme="simple" role="form" action="validarLogin">                	    		                                  
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon"><i class="fa fa-user"></i></div>
                            <s:textfield cssClass="form-control" name="usuario"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">                        
                            <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                            <s:password cssClass="form-control" name="clave"/>                            
                        </div>                        
                    </div>                    
                    <div class="form-group">
    		          <s:submit cssClass="btn btn-primary" value="%{getText('login.ingresar')}"/>
                    </div>
    		    </s:form>
    		    <% }%>
          </div>        
        </div>
    </div>
    
    <%@ include file= "views/footer.jsp" %>    
    
</body>
</html>