<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aiims.vishram_sadan.entities.User"%>
<% String contextPath = request.getContextPath(); %>
<% User loggedInUser = (User)session.getAttribute("user"); %>
<!DOCTYPE html>
<html lang="eng" class="h-100">

<head>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="format-detection" content="telephone=no" />
<meta name="description" content="">
<meta name="author" content="">
<link rel="apple-touch-icon"
	href="assets/images/favicon/apple-touch-icon.png">
<link rel="icon"
	href="assets/images/favicon/aiims.png">
<title>Vishram Sadan | AIIMS Delhi</title>
<!-- Custom styles for this template -->
<link href="assets/css/base.css" rel="stylesheet"
	media="all">
<link href="assets/css/base-responsive.css"
	rel="stylesheet" media="all">
<link href="assets/css/grid.css" rel="stylesheet"
	media="all">
<link href="assets/css/font.css" rel="stylesheet"
	media="all">
<link href="assets/fontawesome-free/css/all.min.css"
	rel="stylesheet" media="all">
<link
	href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/css/flexslider.css"
	rel="stylesheet" media="all">
<link href="assets/css/megamenu.css" rel="stylesheet"
	media="all" />
<link href="assets/css/print.css" rel="stylesheet"
	media="print" />
<link href="assets/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<!-- Theme styles for this template -->
<link href="assets/css/megamenu.css" rel="stylesheet"
	media="all" />
<link href="theme/css/site.css" rel="stylesheet"
	media="all">
<link href="theme/css/site-responsive.css"
	rel="stylesheet" media="all">
<link href="theme/css/ma5gallery.css"
	rel="stylesheet" type="text/css">
<link href="theme/css/print.css" rel="stylesheet"
	type="text/css" media="print">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.21/css/jquery.dataTables.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.21/js/jquery.dataTables.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>

<!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
<!-- Custom JS for this template -->
<noscript>
	<link href="theme/css/no-js.css" type="text/css" rel="stylesheet">
</noscript>

</head>

<body class="d-flex flex-column h-100 bg-light ">
	<header>


		<section class="bg-white">
			<div class="container ">
				<h1 class="logo">
					<a href="home" title="Home" rel="home" class="header_logo"
						id="logo"> <img class="national_emblem"
						src="assets/images/aiims4.png"
						alt="national emblem">
						<p>अ.भा.आ.सं -विश्राम सदन</p> <span class="vishram"> AIIMS - Vishram Sadan</span>
						
					</a>
				</h1>
				<div class="header-right clearfix">
					<div class="right-content clearfix">
						<div class="float-element">

							<a class="sw-logo" target="_blank"
								href="https://swachhbharat.mygov.in/"
								title="Swachh Bharat, External link that open in a new windows"><img
								src="assets/images/swach-bharat.png"
								alt="Swachh Bharat"></a>
						</div>
					</div>
				</div>
			</div>

		</section>
		        <section class="dark-blue">
            <div class="container ">
                <nav class="navbar navbar-expand-lg navbar-dark">
                        <div class="container-fluid ">
                        <a class="navbar-brand text-orange" href="index"><i class="fa fa-home" style="font-size:15px"></i></a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                            aria-label="Toggle navigation">
                            <i class="fa fa-bars" aria-hidden="true"></i>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ">
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="aboutus">About Us</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white " href="contact">Contact Us</a>
                                </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white active" href="rate">Rate List</a>
                                </li>
                                </ul>
                                <ul class="navbar-nav ">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle text-white " href="#" id="navbarDropdown" role="button"
                                        data-bs-toggle="dropdown" aria-expanded="false">Useful Links</a>
                                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <li><a class="dropdown-item text-orange" href="https://cfapplication.aiims.edu/santusht/otp_auth" target="_blank">Santust Portal</a></li>
                                        <li><a class="dropdown-item text-orange" href="https://ors.gov.in" target="_blank">Online Appointment</a></li>
                                        <li><a class="dropdown-item text-orange" href="https://www.aiims.edu/index.php?lang=en" target="_blank">AIIMS Official Website</a></li>
                                    </ul>
                                </li>
                                </ul>
                              
                                
                            </div>
                        </div>
                    </nav>
                </div>
            </section>
    

	</header>
	
   <section class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2><i class="fa fa-rupee"></i> Rate List</h2>
          <ol>
            <li><a href="index">Home</a></li>
            <li>Rate List</li>
          </ol>
        </div>
      </div>
      
    </section>



   <section class="container my-5">
  <div class="container-fluid">
    <div class="card card-shadow p-3">
        <div class="card-header">
            <div class="row">
                <div class="col-lg-9">
                    <h4 class="font-weight-bold">Rate List</h4>
                </div>
               
            </div>
        </div>
        <div class="card-body">
           <div class="row">
             
             <div class="col-lg-12 table-responsive">
                  <table class="display" >
        <thead>
            <tr>
                <th>S No.</th>
                    <th>Room Category</th>
                    <th>Price</th>
                    <th>Max. Person Allowed</th>
                    
            </tr>
               
        </thead>
        <tbody>
             <tr >
                    <td>1.</td>
                    <td>Dormitory</td>
                    <td>20</td>
                    <td>1</td>               
                </tr>
                    <tr >
                    <td>2.</td>
                    <td>Semi Dormitory</td>
                    <td>40</td>
                    <td>1</td>               
                </tr>
                    <tr >
                    <td>3.</td>
                    <td>Single Bedded Room</td>
                    <td>100</td>
                    <td>3</td>               
                </tr>
                    <tr >
                    <td>4.</td>
                    <td>2 Beded Room</td>
                    <td>150</td>
                    <td>2</td>               
                </tr>
                    <tr >
                    <td>5.</td>
                    <td>3 Beded Room</td>
                    <td>225</td>
                    <td>3</td>               
                </tr>
        </tbody>
    
    </table>             </div>
           </div>
        </div>
    </div>

      </div>
      <div ng-app="VishramSadanApp" ng-controller="VishramSadanController">
      <div class="row mb-3">
					  <div class="col-lg-9">
					     <div class="card shadow b-1 mx-3 p-2">
							<div class="card-body">
							  <h4>Month wise ticket closing report</h4><br/>
    <canvas id="chart-container" class="w-100" height="200">"></canvas>
    </div>
						</div>
					  </div>
</div>
</div>
    </section>


	    <footer class="wrapper footer-wrapper mt-auto">
        <div class="footer-top-wrapper">
           <div class="container common-container four_content footer-top-container">
              <ul>
                 <li><a href="#">Website Policies</a></li>
                 <li><a href="#">Help</a></li>
                 <li><a href="contact">Contact Us</a></li>
                 <li><a href="term">Terms and Conditions </a></li>
                 <li><a href="#">Feedback</a></li>
                 <li><a href="#">Web Information Manager</a></li>
                 <li><a href="#">Visitor Analytics</a></li>
                 <li><a href="#">FAQ</a></li>
                 <li><a href="#">Disclaimer</a></li>
              </ul>
           </div>
        </div>
        <div class="footer-bottom-wrapper">
           <div class="container common-container four_content footer-bottom-container">
              <div class="footer-content clearfix">
                 <div class="copyright-content"> Website Content Managed,Designed, Developed and Hosted by <a target="_blank" title="NIC, External Link that opens in a new window" href="https://www.aiims.edu/index.php?lang=en"><strong>All India Institute Of medical Sciences</strong></a><strong> ( AIIMS)</strong> </div>
                 
              </div>
           </div>
        </div>
     </footer>


         

	<!--/.footer-wrapper-->
	<script
		src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!--/.footer-wrapper-->
	<!-- jQuery v1.11.1 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"
		integrity="sha512-nhY06wKras39lb9lRO76J4397CH1XpRSLfLJSftTeo3+q2vP7PaebILH9TqH+GRpnOhfAGjuYMVmVTOZJ+682w=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<!-- jQuery Migration v1.4.1 -->
	<script src="https://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
	<!-- jQuery v3.6.0 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
		integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<!-- jQuery Migration v3.4.0 -->
	<script src="https://code.jquery.com/jquery-migrate-3.4.0.min.js"></script>

	<script src="assets/js/jquery-accessibleMegaMenu.js"></script>
	<script src="assets/js/framework.js"></script>
	<script src="assets/js/jquery.flexslider.js"></script>
	<script src="assets/js/font-size.js"></script>
	 <script src="assets/js/index.js"></script>
	<script src="assets/js/swithcer.js"></script>
	<script src="theme/js/ma5gallery.js"></script>
	<script src="assets/js/chart.js"></script>
	<script src="assets/js/angular.min.js"></script>
    
	<script src="assets/js/megamenu.js"></script>
	<script src="theme/js/easyResponsiveTabs.js"></script>
	<script src="theme/js/custom.js"></script>

	<script
		src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.js"></script>
	<script src="assets/js/sweetalert2.min.js"></script>

</body>

</html>