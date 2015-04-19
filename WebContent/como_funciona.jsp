<!DOCTYPE HTML>
<html>
<head>
	<title>InfoPool</title>
	<%@ include file="views/heads.jsp"%>
</head>
<body>

	<%@ include file="views/header.jsp"%>
	<jsp:include page="views/menu.jsp">
    	<jsp:param name="itemActivo" value="3"/>
    </jsp:include>

	<div class="main_bg">
		<!-- start main -->
		<div class="container">
			<div class="col-md-10 text-center centrar margentb2">
				<h3>&iquest;C&oacute;mo funciona <strong>Info Pool</strong>?</h3>
				<img alt="imagen explicativa de como funciona infopool" src="images/bgCF.png">
			</div>
		</div>
	</div>
	<!-- end main -->

	<%@ include file="views/footer.jsp"%>

</body>
</html>