<!--font-Awesome-->
<link rel="stylesheet" href="fonts/css/font-awesome.min.css">
<!--font-Awesome-->

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="css/bootstrap-datetimepicker.min.css" rel='stylesheet' type='text/css' />
<link href="css/dataTables.bootstrap.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> 
    addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
    function hideURLbar(){ window.scrollTo(0,1); } 
</script>
 <!--[if lt IE 9]>
     <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
     <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

<!-- start plugins -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/moment.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/modernizr.custom.28468.js"></script>
<script type="text/javascript" src="js/jqBootstrapValidation.js"></script>

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
        } );
        $('#datetimepicker2').datetimepicker({            
        });
    });    
</script>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>

<script type="text/javascript" src="js/configuraciongmap.js"></script>
<script type="text/javascript" src="js/funcionesgenerales.js"></script>