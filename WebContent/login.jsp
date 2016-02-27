<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>InfoPool</title>    
    <%@ include file="views/heads.jsp" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
</head>
<body>

   <jsp:include page="views/header.jsp">
    	<jsp:param name="itemActivo" value="87"/>
    </jsp:include>

    <div class="main_bg">    
        <div class="container">        
        	<div class="col-md-4 centrar margentb2 form-login">
                <% String user = (String) session.getAttribute("usrLogin"); 
                if (user == null){ %>
                <h3 class="text-center"><s:text name="login.ingresarlbl" /></h3>                
                <s:fielderror/>
                <s:form cssClass="form-signin" theme="simple" role="form" action="iniciarSesion">                	    		                                  
                    <div class="form-group">                        
                        <label>Usuario</label>                            
                    	<s:textfield cssClass="form-control" name="usuario"/>                        
                    </div>
                    <div class="form-group">                                                
                        <label>Clave</label>
                    	<s:password cssClass="form-control" name="clave"/>
                    </div>                   
                    <div class="form-group">
                    	<a>¿Olvidó su clave?</a>
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