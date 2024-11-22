<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="aiims.vishram_sadan.entities.User" %>
    <% String contextPath = request.getContextPath(); %>
<% User loggedInUser = (User)session.getAttribute("user"); %>
<!DOCTYPE html>
<html lang="eng" class="h-100">

<head>
       
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="format-detection" content="telephone=no" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="apple-touch-icon" href="assets/images/favicon/apple-touch-icon.png">
    <link rel="icon" href="<%= contextPath %>/assets/images/favicon/aiims.png">
    <title>Vishram Sadan | AIIMS Delhi</title>
    <!-- Custom styles for this template -->
    <link href="<%= contextPath %>/assets/css/base.css" rel="stylesheet" media="all">
    <link href="<%= contextPath %>/assets/css/base-responsive.css" rel="stylesheet" media="all">
    <link href="<%= contextPath %>/assets/css/grid.css" rel="stylesheet" media="all">
    <link href="<%= contextPath %>/assets/css/font.css" rel="stylesheet" media="all">
     <link href="<%= contextPath %>/assets/fontawesome-free/css/all.min.css" rel="stylesheet" media="all">
 <link href="<%= contextPath %>/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"  >
  <link href="<%= contextPath %>/assets/vendor/bootstrap-icons/bootstrap-icons.css"  rel="stylesheet"  >
    <link href="<%= contextPath %>/assets/css/flexslider.css" rel="stylesheet" media="all">
    <link href="<%= contextPath %>/assets/css/megamenu.css" rel="stylesheet" media="all" />
    <link href="<%= contextPath %>/assets/css/print.css" rel="stylesheet" media="print" />
    <link href="<%= contextPath %>/assets/boxicons/css/boxicons.min.css" rel="stylesheet"  > 
    <!-- Theme styles for this template -->
    <link href="<%= contextPath %>/assets/css/megamenu.css" rel="stylesheet" media="all" />
    <link href="<%= contextPath %>/theme/css/site.css" rel="stylesheet" media="all">
    <link href="<%= contextPath %>/theme/css/site-responsive.css" rel="stylesheet" media="all">
    <link href="<%= contextPath %>/theme/css/ma5gallery.css" rel="stylesheet" type="text/css">
    <link href="<%= contextPath %>/theme/css/print.css" rel="stylesheet" type="text/css" media="print">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.css" />
    
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.21/css/jquery.dataTables.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.21/js/jquery.dataTables.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    
    <!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
    <!-- Custom JS for this template -->
    <noscript>
        <link href="theme/css/no-js.css" type="text/css" rel="stylesheet">
    </noscript>
   <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        #myTable_filter { display: none; }
        .column-filter { width: 100%; box-sizing: border-box; margin-bottom: 5px; }
        
        /* custom css */
        .fs-24{
    font-size: 16px;
}
.custom-bg-success{
    background-color: #69ea85;
}
.custom-bg-danger{
    background-color: #f7858f;
}
.custom-bg-secondary{
    background-color: lightgray;
}
.min-height-100{
    min-height: 50px;
}
.mw-15{
    width: 100px !important;
}
.max-width-20{
    width: 20%;
}
.list-style-type li ::marker{
    display: none;
}
.not-selected-room{
    border: 2px solid white !important;
}
.selected-room{
    border: 2px solid green !important;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.btn:hover,#v-pills-tab button:hover,#v-pills-tab button.active{
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19) !important;
}
#v-pills-tab button{
    border:1px solid lightgray !important;
}
#v-pills-tab button.active{
    background-color: #FFC107;
    color: black;
}
.mw-100{
    max-width: 100px !important;
}
.patient-details th,.attendants-details th, .payment-receipt th{
  color: black !important;
}

.required-field::after {
    content: "*";
    color: red;
     margin-left: 5px;
        }
  
        /* end custom css */
    </style>
    
</head>

<body class="d-flex flex-column h-100 bg-light "  ng-app="App" >
    <header>
      <section class="bg-white">
            <div class="container ">
                <h1 class="logo"><a href="home" title="Home" rel="home" class="header_logo" id="logo">
                        <img class="national_emblem" src="<%= contextPath %>/assets/images/aiims4.png" alt="national emblem">
                         <p>अ.भा.आ.सं - विश्राम सदन</p>
                        <span class="vishram"> AIIMS - Vishram Sadan</span>
                    </a></h1>
                    <div class="header-right clearfix">
                        <div class="right-content clearfix">
                            <div class="float-element">
							   
                                <a class="sw-logo" target="_blank" href="https://swachhbharat.mygov.in/"
                                    title="Swachh Bharat, External link that open in a new windows"><img
                                        src="<%= contextPath %>/assets/images/swach-bharat.png" alt="Swachh Bharat"></a>
                            </div>
                        </div>
                    </div>
            </div>
            
        </section>
      <section class="dark-blue">
            <div class="container ">
                <nav class="navbar navbar-expand-lg navbar-dark">
                        <div class="container-fluid ">
                        <a class="navbar-brand text-orange" href="home"><i class="fa fa-home" style="font-size:15px"></i></a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                            aria-label="Toggle navigation">
                            <i class="fa fa-bars" aria-hidden="true"></i>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ">
                                <li class="nav-item"><a class="nav-link text-white "
									href="booking">Manage Booking</a></li>
								<li class="nav-item"><a class="nav-link text-white "
									href="checkout">Check Out</a></li>
									<li class="nav-item"><a class="nav-link text-white "
									href="room_check">Check Room Availability</a></li>
											<li class="nav-item"><a class="nav-link text-white"
									href="booking_list">Booking request List</a></li>
									<li class="nav-item"><a class="nav-link text-white "
									href="report">Payment Report</a></li>
									<li class="nav-item"><a class="nav-link text-white "
									href="closereport">Close Request Report</a></li>
                                <li class="nav-item">
                                    <a class="nav-link text-white active"  href="contacts">Contact Us</a>
                                </li>
                                </ul>
                               
                                <ul class="navbar-nav ms-auto">
                                 <li >
                                   <span class="text-white welcome"><%= loggedInUser.getName() %></span>  </li>
                                    <li class="nav-item ">
                                   <a class="nav-link text-white" href="<%=contextPath %>/logout"><i class="fas fa-power-off fs-22"></i></a></li>
                                   <li class="nav-item dropdown">
                                   <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button"
                                        data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-gear fs-22"></i></a>
                                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <li><a class="dropdown-item text-orange" href="<%=contextPath %>/reception/changepassword" >Change Password</a></li>
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
      <div class="container" >
        <div class="d-flex justify-content-between align-items-center">
          <h2><i class="fa fa-phone"></i>Contact Us</h2>
          <ol>
            <li><a href="home">Home</a></li>
            <li>Contact Us</li>
          </ol>
        </div>
      </div>
    </section>
    </br>
     
    <section class="container my-5" ng-controller="BookingController">
      <div class="container-fluid">
	    <div class="card card-shadow p-3">
	        <div class="card-header">
	            <h4 class="mb-0">Contact Us</h4>
	        </div>
	    
    <div class="card-body">
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group" >
                        <label class="required-field">Concerned by</label>
                        <select class="form-control requiredFields mt-2" ng-model="contact.type">
                            <option value="">--select--</option>
                            <option value="checkIn">Check In</option>
                            <option value="checkOut">Check Out</option>
                            <option value="payment">Payment Related Issue</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-controll mt-2">
                        <label>Enter Booking Reference Id</label>
                        <input type="number" ng-model="contact.value"  class="form-control  mt-2" placeholder="Booking Reference Id" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-controll mt-2">
                        <label class="required-field">Description</label>
                        <textarea rows="3"  ng-model="contact.description" class="form-control requiredFields mt-2" placeholder="Brief Your Concern" /></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2">
                    <div class="form-controll">
                        <br />
                        <button class="btn login-outline-primary w-100"  ng-click="submitContact()">Contact Us</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

	      </div>
	    </div>
    </section>
    <footer class="wrapper footer-wrapper mt-auto">
        
        <div class="footer-bottom-wrapper">
           <div class="container common-container four_content footer-bottom-container">
              <div class="footer-content clearfix">
                 <div class="copyright-content"> Website Content Managed,Designed, Developed and Hosted by <a target="_blank" title="NIC, External Link that opens in a new window" href="https://www.aiims.edu/index.php?lang=en"><strong>All India Institute Of medical Sciences</strong></a><strong> ( AIIMS)</strong></div>
                 
              </div>
           </div>
        </div>
     </footer>
	
	 
   
         <!--/.footer-wrapper-->
      <script src="<%= contextPath %>/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
         <!--/.footer-wrapper-->
      <!-- jQuery v1.11.1 -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js" integrity="sha512-nhY06wKras39lb9lRO76J4397CH1XpRSLfLJSftTeo3+q2vP7PaebILH9TqH+GRpnOhfAGjuYMVmVTOZJ+682w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <!-- jQuery Migration v1.4.1 -->
      <script src="https://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
      <!-- jQuery v3.6.0 -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <!-- jQuery Migration v3.4.0 -->
      <script src="https://code.jquery.com/jquery-migrate-3.4.0.min.js"></script>

      <script src="<%= contextPath %>/assets/js/jquery-accessibleMegaMenu.js"></script>
      <script src="<%= contextPath %>/assets/js/framework.js"></script>
      <script src="<%= contextPath %>/assets/js/jquery.flexslider.js"></script>
      <script src="<%= contextPath %>/assets/js/font-size.js"></script>
      <script src="<%= contextPath %>/assets/js/swithcer.js"></script>
      <script src="<%= contextPath %>/theme/js/ma5gallery.js"></script>
      <script src="<%= contextPath %>/assets/js/angular.min.js"></script>
     
      <script src="<%= contextPath %>/assets/js/megamenu.js"></script>
      <script src="<%= contextPath %>/theme/js/easyResponsiveTabs.js"></script>
      
          <script src="<%= contextPath %>/theme/js/custom.js"></script>
       <script src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.js"></script>
        <script src="<%= contextPath %>/assets/js/sweetalert2.min.js"></script>     
   <script src="<%= contextPath %>/assets/js/reception.js"></script>
  

</body>

</html>