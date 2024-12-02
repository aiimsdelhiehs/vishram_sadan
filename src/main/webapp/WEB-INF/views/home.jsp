<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% boolean loginFail = (session.getAttribute("login-error")!=null)?true:false; %>
<!DOCTYPE html>
<html lang="eng">

<head>
       
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="format-detection" content="telephone=no" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="apple-touch-icon" href="assets/images/favicon/apple-touch-icon.png">
    <link rel="icon" href="assets/images/favicon/aiims.png">
    <title>Vishram Sadan | AIIMS Delhi</title>
    <!-- Custom styles for this template -->
    <link href="assets/css/base.css" rel="stylesheet" media="all">
    <link href="assets/css/base-responsive.css" rel="stylesheet" media="all">
    <link href="assets/css/grid.css" rel="stylesheet" media="all">
    <link href="assets/css/font.css" rel="stylesheet" media="all">
     <link href="assets/fontawesome-free/css/all.min.css" rel="stylesheet" media="all">
 <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"  >
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"  rel="stylesheet"  >
    <link href="assets/css/flexslider.css" rel="stylesheet" media="all">
    <link href="assets/css/megamenu.css" rel="stylesheet" media="all" />
    <link href="assets/css/print.css" rel="stylesheet" media="print" />
    <link href="assets/boxicons/css/boxicons.min.css" rel="stylesheet"  > 
    <!-- Theme styles for this template -->
    <link href="assets/css/megamenu.css" rel="stylesheet" media="all" />
    <link href="theme/css/site.css" rel="stylesheet" media="all">
    <link href="theme/css/site-responsive.css" rel="stylesheet" media="all">
    <link href="theme/css/ma5gallery.css" rel="stylesheet" type="text/css">
    <link href="theme/css/print.css" rel="stylesheet" type="text/css" media="print">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.css" />
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

<body class="bg-light" ng-app="myApp" ng-controller="IndexController">
    <header>
  

        <section class="bg-white">
            <div class="container ">
                <h1 class="logo"><a href="index" title="Home" rel="home" class="header_logo" id="logo">
                        <img class="national_emblem" src="assets/images/aiims4.png" alt="national emblem">
                         <p>अ.भा.आ.सं - विश्राम सदन</p>
                        <span class="vishram"> AIIMS - Vishram Sadan</span>
                        
                    </a></h1>
                    <div class="header-right clearfix">
                        <div class="right-content clearfix">
                            <div class="float-element">
							   
                                <a class="sw-logo" target="_blank" href="https://swachhbharat.mygov.in/"
                                    title="Swachh Bharat, External link that open in a new windows"><img
                                        src="assets/images/swach-bharat.png" alt="Swachh Bharat"></a>
                            </div>
                        </div>
                    </div>
            </div>
            
        </section>
        <section class="dark-blue">
            <div class="container  ">
                <nav class="navbar navbar-expand-lg navbar-dark ">
                      <div class="container-fluid">
                        <a class="navbar-brand text-orange" href="index"><i class="fa fa-home"></i></a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                            aria-label="Toggle navigation">
                            <i class="fa fa-bars" aria-hidden="true"></i>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ">
                                <li class="nav-item">
                                    <a class="nav-link text-white " href="aboutus">About Us</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="contact">Contact Us</a>
                                </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="rate">Rate List</a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button"
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
    <section class="container my-5">
        <div class="row">
            <div class="col-md-6">
                <h1 class="text-blue">Experience comfort and care at AIIMS Vishram Sadan</h1>
                <p class="text-orange fw-bolder">Where patients and their families find a home away from home. Our state-of-the-art facilities ensure a restful stay.</p>
                <button class="btn orange" role="button"  data-bs-toggle="modal" data-bs-target="#loginModal">Staff Login</button>
                <p class="text-orange fw-bolder mt-3">See Live Vishram Sadan Bed Availability</p>
            </div>
            <div class="col-md-6  align-items-center">
                <img src="assets/images/s.png" class="img-fluid" alt="AIIMS Vishram Sadan">
            </div>
        </div>
    </section>
    



    <section id="featured-services" class="dark-blue">
        <div class="container  ">
            <h5 class="abouts mt-3">Features Of Vishram Sadan</h5>
            <div class="breifs">AIIMS Vishram Sadan is a facility provided by AIIMS (All India Institute of Medical Sciences) to offer accommodation for patients and their attendants who travel from distant places for medical treatment. These accommodations are usually more affordable compared to other lodging options and are located near the AIIMS hospital premises to ensure easy access to medical care. The Vishram Sadan typically includes basic amenities to make the stay comfortable for patients and their families.</div>
			
            <div class="row ">
                  <div class="col-md-3 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                           <div class="iconss"><i class="fas fa-map-marker-alt"></i></div>
                            <h5 class="card-title title ">Proximity to AIIMS</h5>
                            <p class="card-text description">The facility is strategically located near the AIIMS hospital premises, making it convenient for patients to commute for their medical appointments and treatments.</p>
                        </div>
                    </div>
                </div>
                    <div class="col-md-3 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="iconss"><i class="fas fa-clock"></i></div>
                            <h5 class="card-title title">24*7 Available</h5>
                            <p class="card-text description"> Vishram Sadan operates around the clock, meaning it is open and available for check-in, check-out, and accommodation at any time of day or night. </p>
                        </div>
                    </div>
                </div>
                   <div class="col-md-3 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="iconss"><i class="fas fa-hand-holding-droplet"></i></div>
                            <h5 class="card-title title">Maintain Hygiene</h5>
                            <p class="card-text description">Maintaining a clean and hygienic environment is a priority in Vishram Sadan. Regular cleaning and sanitation practices are followed to ensure the health and safety of the occupants.
</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="iconss"><i class="fas fa-bed"></i></div>
                            <h5 class="card-title title">Affordable Accommodation</h5>
                            <p class="card-text description">Offers budget-friendly lodging options for patients and their attendants. This ensures that people from economically weaker sections can have a comfortable stay without financial strain.</p>
                        </div>
                    </div>
                </div>
            </div>
			</div>
       
    </section>
    
    <section class="wrapper carousel-wrapper">
        <div class="container common-container four_content carousel-container">
           <div id="flexCarousel" class="flexslider carousel">
              <ul class="slides">
                 <li>
                    <a target="_blank" href="http://digitalindia.gov.in/" title="Digital India, External Link that opens in a new window"><img src="assets/images/carousel/digital-india.png" alt="Digital India"></a>
                 </li>
                 <li>
                    <a target="_blank" href="http://www.makeinindia.com/" title="Make In India, External Link that opens in a new window"> <img src="assets/images/carousel/makeinindia.png" alt="Make In India"></a>
                 </li>
                 <li>
                    <a target="_blank" href="http://india.gov.in/" title="National Portal of India, External Link that opens in a new window"><img src="assets/images/carousel/india-gov.png" alt="National Portal of India"></a>
                 </li>
                 <li>
                    <a target="_blank" href="http://goidirectory.nic.in/" title="GOI Web Directory, External Link that opens in a new window"><img src="assets/images/carousel/goidirectory.png" alt="GOI Web Directory"></a>
                 </li>
                 <li>
                    <a target="_blank" href="https://data.gov.in/" title="Data portal, External Link that opens in a new window"><img src="assets/images/carousel/data-gov.png" alt="Data portal"></a>
                 </li>
                 <li>
                    <a target="_blank" href="https://mygov.in/" title="MyGov, External Link that opens in a new window"><img src="assets/images/carousel/mygov.png" alt="MyGov Portal"></a>
                 </li>
              </ul>
           </div>
        </div>
     </section>

     <footer class="wrapper footer-wrapper">
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
                 <div class="copyright-content"> Website Content Managed,Designed, Developed and Hosted by <a target="_blank" title="NIC, External Link that opens in a new window" href="https://www.aiims.edu/index.php?lang=en"><strong>All India Institute Of medical Sciences</strong></a><strong> ( AIIMS)</strong></div>
                 
              </div>
           </div>
        </div>
     </footer>
	 <!-- Login Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
			    <div class="modal-header">
				  <div class="row">
						<div class="col-lg-3 d-none d-lg-block">
							<img class="w-100" src="assets/images/aiims4.png"                                                                           >
						</div>
						<div class="col-lg-8 py-2">
							<h3><img class="aiims-logo d-lg-none max-width-60"  src="assets/images/AIIMS2.jpg" > Aiims-Vishram Sadan</h3>
							<h5>Your well-being is our priority</h5>
							<h6>All India Institute Of Medical Sciences, Ansari Nagar New Delhi</h6>
						</div>
						<div class="col-lg-1">
							<button type="button" class="btn-close" data-bs-dismiss="modal" ></button>
						</div>
					</div>
				</div>
				<div class="modal-body p-5">
				<h3 ng-if="!forgotPassword"><i class="fa fa-lock"></i> Login Now</h3>
								<h3 ng-if="forgotPassword"><i class="fa fa-key"></i> Reset Password</h3><br />
								<form action="loginProcess" method="post" id="loginForm">
								<div class="row mb-2">
									<div class="col">
										<div class="form-group">
											<label>Contact Number <i class="text-danger">*</i></label>
											<input type="text" class="form-control form-control-lg required-login-fileds" maxlength="10"
												placeholder="Contact No" name="j_username" ng-disabled="forgotPasswordOTPSent" ng-keypress="validateInput($event)" ng-model="login.username" id="login-username"/>
										</div>
									</div>
								</div>
								<div class="row mb-2" ng-if="forgotPassword && forgotPasswordOTPSent">
									<div class="col">
										<div class="form-group">
											<label>Enter <span ng-bind="resetOTPStatus"></span> <i class="text-danger">*</i></label>
											<input type="text" class="form-control form-control-lg required-login-fileds"
												placeholder="Enter OTP" name="j_otp" ng-keypress="validateDigits($event)" maxlength="10" ng-model="login.otp"/>
										</div>
									</div>
								</div>
								<div class="row mb-2" ng-if="!forgotPassword || forgotPasswordOTPSent">
									<div class="col">
										<div class="form-group">
											<label><span ng-if="forgotPasswordOTPSent">New </span>Password <i class="text-danger">*</i></label>
											<input type="password" class="form-control form-control-lg required-login-fileds"
												placeholder="password" name="j_password" ng-keypress="validateInput($event)" ng-model="login.password"/>
										</div>
									</div>
									<div class="col" ng-if="forgotPasswordOTPSent">
										<div class="form-group">
											<label>Confirm Password <i class="text-danger">*</i></label>
											<input type="password" class="form-control form-control-lg required-login-fileds" id="confirm_password"
												placeholder="password" name="confirm_password" ng-keypress="validateInput($event)" ng-model="login.confirm_password"/>
										</div>
									</div>
								</div>
								<div class="row mb-2">
									<div class="col">
										<div class="form-group">
											<label>Validate Security Code <i class="text-danger">*</i></label>
											<div class="input-group mb-3">
												<span class="input-group-text captcha-holder" ng-click="generateCaptcha('loginCaptchaCode')" id="loginCaptchaCode"></span>
												<input type="text" class="form-control form-control-lg required-login-fileds" id="loginSecurityCode"  ng-keypress="validateInput($event)"
													aria-label="Sizing example input" placeholder="Security code"
													aria-describedby="inputGroup-sizing-default" >
											</div>
										</div>
									</div>
								</div>
								<div class="row mb-2" ng-if="!forgotPassword">
									<div class="col">
										<div class="form-group">
											<button type="button" class="btn btn-lg login-outline-primary w-100" ng-disabled="apiCalling" ng-click="tryLogin()"> Login Now </button>
										</div>
									</div>
								</div>
								<div class="row mb-2" ng-if="forgotPassword && !forgotPasswordOTPSent">
									<div class="col">
										<div class="form-group">
											<button type="button" class="btn btn-lg login-outline-primary w-100" ng-disabled="apiCalling" ng-click="getPasswordResetOTP()">Get OTP</button>
										</div>
									</div>
								</div>
								<div class="row mb-2" ng-if="forgotPassword && forgotPasswordOTPSent">
									<div class="col">
										<div class="form-group">
											<button type="button" class="btn btn-lg btn-outline-primary w-100" ng-disabled="apiCalling" ng-click="resetPasswordByOTP()">Reset Password</button>
										</div>
									</div>
								</div>
								<p><span class="text-danger" id="login-err"><%= (loginFail)?(session.getAttribute("login-error")):"" %></span></p>
								</form>
								<p class="text-center " ng-if="!forgotPassword"><a href="javascript:;" ng-click="setPasswordResetFlag(true)" style="color: #004757">Forgot Password?</a></p>
								<p class="text-center" ng-if="forgotPassword"><a href="javascript:;" ng-click="setPasswordResetFlag(false)" style="color: #004757">Login</a></p>
								
						</div>
						<p id="msg" class="text-danger">&nbsp;</p>
					</form>
					<div class="modal-footer justify-content-center">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close Form</button>
				</div>
				</div>
				
			</div>
		</div>
	
  <!-- End login modal -->
	 
   
         <!--/.footer-wrapper-->
      <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
         <!--/.footer-wrapper-->
      <!-- jQuery v1.11.1 -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js" integrity="sha512-nhY06wKras39lb9lRO76J4397CH1XpRSLfLJSftTeo3+q2vP7PaebILH9TqH+GRpnOhfAGjuYMVmVTOZJ+682w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <!-- jQuery Migration v1.4.1 -->
      <script src="https://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
      <!-- jQuery v3.6.0 -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
      <!-- jQuery Migration v3.4.0 -->
      <script src="https://code.jquery.com/jquery-migrate-3.4.0.min.js"></script>

      <script src="assets/js/jquery-accessibleMegaMenu.js"></script>
      <script src="assets/js/framework.js"></script>
      <script src="assets/js/jquery.flexslider.js"></script>
      <script src="assets/js/font-size.js"></script>
      <script src="assets/js/swithcer.js"></script>
      <script src="theme/js/ma5gallery.js"></script>
      <script src="assets/js/angular.min.js"></script>
      <script src="assets/js/sweetalert2.min.js"></script>
      <script src="assets/js/megamenu.js"></script>
      <script src="theme/js/easyResponsiveTabs.js"></script>
      <script src="theme/js/custom.js"></script>
      <script src="assets/js/indexApp.js"></script>
       <script src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.js"></script>
     

     <% if(session.getAttribute("login-error")!=null){session.removeAttribute("login-error");} %>

</body>

</html>