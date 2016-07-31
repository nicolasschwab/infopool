<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="voy.con.vos">
<title>Infopool</title>
<!--font-Awesome-->
<link rel="stylesheet" href="resources/fonts/css/font-awesome.min.css">

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="resources/css/bootstrap-datetimepicker.min.css" rel='stylesheet' type='text/css' />
<link href="resources/css/dataTables.bootstrap.css" rel='stylesheet' type='text/css' />

<!-- Estilos de voy.con.vos -->
<link href="resources/css/style.css" rel='stylesheet' type='text/css' />
<link href="resources/css/estilosSolicitudes.css" rel='stylesheet' type='text/css' />
<link href="resources/css/estilosEditarPerfil.css" rel='stylesheet' type='text/css' />
<link href="resources/css/spiner.css" rel='stylesheet' type='text/css' />
<link href="resources/css/foro.css" rel='stylesheet' type='text/css' />
<link href="resources/css/estilosEditarPerfil.css" rel='stylesheet' type='text/css' />
<link href="resources/css/estilosNotificaciones.css" rel='stylesheet' type='text/css' />
<link href="resources/css/estilosConversacion.css" rel='stylesheet' type='text/css' />

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href='resources/css/ie10-viewport-bug-workaround.css' rel='stylesheet' type='text/css'>

<script type="application/x-javascript"> 
    addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
    function hideURLbar(){ window.scrollTo(0,1); } 
</script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<link href="resources/css/style.css" rel="stylesheet" type="text/css" media="all" />

<!-- start plugins -->
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/foro.js"></script>
<script type="text/javascript" src="resources/js/editarUsuario.js"></script>
<script type="text/javascript" src="resources/js/funcionesAltaUsuario.js"></script>
<script type="text/javascript" src="resources/js/viajes.js"></script>
<script type="text/javascript" src="resources/js/conversaciones.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/moment.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/notificaciones.js"></script>

<script type="text/javascript" src="resources/js/modernizr.custom.28468.js"></script>
<script type="text/javascript" src="resources/js/jqBootstrapValidation.js"></script>
<script type="text/javascript" src="resources/js/funcionesAltaUsuario.js"></script>




<script type="text/javascript">
    $(function() {        
        
    	$('.dropdown-toggle').dropdown();
        
        $('.datepicker').datetimepicker({            
            pickTime: false
        });
        
        $('.horaviaje').datetimepicker({
            pickDate: false
        });
        
        $(document).ready(function() {
            $('#datatable').dataTable();
        });
        
        $('#datetimepicker2').datetimepicker({});
        
        $(document).ready(function() {
            $('#myTab').tab();
        });
        
        //$('#myModal').modal();
        
    });    
</script>
<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4ONLIO2speSB3enNoqAvrzgWePwLGee0&signed_in=true&callback=initMap" async defer></script> -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4ONLIO2speSB3enNoqAvrzgWePwLGee0&v=3.exp&libraries=places"></script>

<script type="text/javascript" src="resources/js/configuraciongmap.js"></script>
<script type="text/javascript" src="resources/js/funcionesgenerales.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="resources/js/ie10-viewport-bug-workaround.js"></script>