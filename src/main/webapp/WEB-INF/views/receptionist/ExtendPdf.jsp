<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="aiims.vishram_sadan.entities.User"%>
<% String contextPath = request.getContextPath(); %>
<% User loggedInUser = (User)session.getAttribute("user"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" />
<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="format-detection" content="telephone=no" />
<meta name="description" content="">
<meta name="author" content="">
<link rel="apple-touch-icon"
	href="assets/images/favicon/apple-touch-icon.png">
<link rel="icon"
	href="<%= contextPath %>/assets/images/favicon/aiims.png">
<title>Vishram Sadan | AIIMS Delhi</title>
<!-- Custom styles for this template -->
<link href="<%= contextPath %>/assets/css/base.css" rel="stylesheet"
	media="all">
<link href="<%= contextPath %>/assets/css/base-responsive.css"
	rel="stylesheet" media="all">
<link href="<%= contextPath %>/assets/css/grid.css" rel="stylesheet"
	media="all">
<link href="<%= contextPath %>/assets/css/font.css" rel="stylesheet"
	media="all">
<link href="<%= contextPath %>/assets/fontawesome-free/css/all.min.css"
	rel="stylesheet" media="all">
<link
	href="<%= contextPath %>/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%= contextPath %>/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="<%= contextPath %>/assets/css/flexslider.css"
	rel="stylesheet" media="all">
<link href="<%= contextPath %>/assets/css/megamenu.css" rel="stylesheet"
	media="all" />
<link href="<%= contextPath %>/assets/css/print.css" rel="stylesheet"
	media="print" />
<link href="<%= contextPath %>/assets/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<!-- Theme styles for this template -->
<link href="<%= contextPath %>/assets/css/megamenu.css" rel="stylesheet"
	media="all" />
<link href="<%= contextPath %>/theme/css/site.css" rel="stylesheet"
	media="all">
<link href="<%= contextPath %>/theme/css/site-responsive.css"
	rel="stylesheet" media="all">
<link href="<%= contextPath %>/theme/css/ma5gallery.css"
	rel="stylesheet" type="text/css">
<link href="<%= contextPath %>/theme/css/print.css" rel="stylesheet"
	type="text/css" media="print">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.css" />
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+Devanagari&display=swap" rel="stylesheet">
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
<style>
body {
	
	margin: 20px;
	
}

#myTable_filter {
	display: none;
}

.column-filter {
	width: 100%;
	box-sizing: border-box;
	margin-bottom: 5px;
}

/* custom css */
.fs-24 {
	font-size: 16px;
}

.custom-bg-success {
	background-color: #69ea85;
}

.custom-bg-danger {
	background-color: #f7858f;
}

.custom-bg-secondary {
	background-color: lightgray;
}

.min-height-100 {
	min-height: 50px;
}

.mw-15 {
	width: 100px !important;
}

.max-width-20 {
	width: 20%;
}

.list-style-type li ::marker {
	display: none;
}

.not-selected-room {
	border: 2px solid white !important;
}

.selected-room {
	border: 2px solid green !important;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}

.btn:hover, #v-pills-tab button:hover, #v-pills-tab button.active {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19) !important;
}

#v-pills-tab button {
	border: 1px solid lightgray !important;
}

#v-pills-tab button.active {
	background-color: #FFC107;
	color: black;
}

.mw-100 {
	max-width: 100px !important;
}

.patient-details th, .attendants-details th, .payment-receipt th {
	color: black !important;
}

.info-button {
    background-color: #004757;
    color: white;
    border-radius: 50%;
    width: 25px;
    height: 25px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    margin-top: 5px;
    margin-left:3px;
    margin-right:3px;
}
/* end custom css */

#paymentReceipt {
    width: 1280px;  /* Forces the width for a desktop-like view */
    max-width: 100%; /* Ensure content does not overflow */
    margin: 0 auto;  /* Center the content for a desktop view */
}

.table {
    width: 100%;
    border-collapse: collapse;
    font-size: 16px;
}

.table th, .table td {
    padding: 8px 12px;
    text-align: left;
    border: 1px solid #ddd;
}

/* Media queries for responsiveness */
@media (max-width: 768px) {
    .table, .table thead, .table tbody, .table th, .table td, .table tr {
        display: block;
    }
    
    /* Hide the table headers on smaller screens */
    .table thead {
        display: none;
    }

    /* Display each row as a block with labels for each cell */
    .table tr {
        margin-bottom: 10px;
        border-bottom: 1px solid #ddd;
    }

    .table td {
        display: block;
        text-align: right;
        font-size: 14px;
        padding-left: 50%;
        position: relative;
    }
    
    /* Add pseudo-elements to simulate table headers in mobile view */
    .table td::before {
        content: attr(data-label);
        position: absolute;
        left: 10px;
        width: 45%;
        white-space: nowrap;
        text-align: left;
        font-weight: bold;
        font-size: 14px;
  
</style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>
    <style>
        #content {
            border: 1px solid #000;
            padding: 20px;
            font-family: Arial, sans-serif;
            max-width: 600px; /* Portrait width */
            margin: 0 auto;
        }
        h1 {
            text-align: center;
        }
        .receipt-section {
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #000;
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body class="d-flex flex-column h-100 bg-light " ng-app="App"
	ng-controller="checkoutController">
	<header>


		<section class="bg-white">
			<div class="container ">
				<h1 class="logo">
					<a href="home" title="Home" rel="home" class="header_logo"
						id="logo"> <img class="national_emblem"
						src="<%= contextPath %>/assets/images/aiims4.png"
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
								src="<%= contextPath %>/assets/images/swach-bharat.png"
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
						<a class="navbar-brand text-orange" href="home"><i
							class="fa fa-home"></i></a>
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse" data-bs-target="#navbarNav"
							aria-controls="navbarNav" aria-expanded="false"
							aria-label="Toggle navigation">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</button>
						<div class="collapse navbar-collapse" id="navbarNav">
							<ul class="navbar-nav ">
							<li class="nav-item"><a class="nav-link text-white "
									href="booking">Manage Booking</a></li>
								<li class="nav-item"><a class="nav-link text-white"
									href="checkout">Check Out</a></li>
									<li class="nav-item"><a class="nav-link text-white "
									href="room_check">Check Room Availability</a></li>
											<li class="nav-item"><a class="nav-link text-white"
									href="booking_list">Booking request List</a></li>
									<li class="nav-item"><a class="nav-link text-white "
									href="report">Payment Report</a></li>
									<li class="nav-item"><a class="nav-link text-white "
									href="closereport">Close Request Report</a></li>
								<li class="nav-item"><a class="nav-link text-white "
									href="contacts">Contact Us</a></li>
							</ul>
						
							<ul class="navbar-nav ms-auto">
								<li><span class="text-white welcome"><%= loggedInUser.getName() %></span>
								</li>
								<li class="nav-item "><a class="nav-link text-white"
									href="<%=contextPath %>/logout"><i
										class="fas fa-power-off fs-22"></i></a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle text-white" href="#"
									id="navbarDropdown" role="button" data-bs-toggle="dropdown"
									aria-expanded="false"><i class="fas fa-gear fs-22"></i></a>
									<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
										<li><a class="dropdown-item text-orange"
											href="<%=contextPath %>/reception/changepassword">Change
												Password</a></li>
									</ul></li>
							</ul>


						</div>
					</div>
				</nav>
			</div>
		</section>

	</header>
<section>
<div  >
					<div  class="row justify-content-center ">
						<div  class="col-lg-8"id="paymentReceipt">
							
								<div class="row py-5" id="pdfHeader">
									
									<div class="col-2" >
										<img src="../assets/images/verified.png" class="w-75">
									</div>
									<div class="col-10">
										<center><h3 class="font-weight-bold mb-0">विश्राम सदन, अंसारी नगर, नई दिल्ली-110029</h3>
										<h4 class="mb-0">VISHRAM SADAN, ANSARI NAGAR, NEW DELHI-110029</h4>
										<h5 class="mb-0">रसीद /RECEIPT</h5></center>
									</div>
								</div>
							<div >
								<div class="row" id="below">
									<div class="col">
										<h5 class="text">
										रसीद सं./Receipt No: <b><i>{{extendData.bookings[0].paymentHistory[0].txnId}}</i></b>
											
										</h5>
									</div>
									<div class="col text-end"><h5 class="text-end">
											बुकिंग संदर्भ संख्या/Booking reference no: <b><i>{{extendData.requestId}}</i></b>
										</h5></div>
								</div>
									<div class="row" >
									<div class="col">
										<h5 class="text">
											रसीद का प्रकार/Receipt Type: {{extendData.bookings[0].paymentHistory[0].receiptType}}</i></b>
										</h5>
									</div>
									<div class="col text-end">दिनांक/Date: {{today}}</div>
								</div>
								<table class="table payment-receipt">
									<tbody>
										<tr>
											<th colspan="8">रोगी विवरण/Patient Details</th>
										</tr>
										<tr>
											<th>नाम/Name</th>
											<td>{{extendData.patient.fullname}}</td>
											<th>UHID</th>
											<td class="text-end">{{extendData.patient.uhid}}</td>
											<th>Contact No.</th>
											<td>{{extendData.patient.contactNo}}</td>
											<th>पता/Address</th>
											<td class="text-end">{{extendData.patient.address}}</td>
										</tr>
									</tbody>
								</table>
								
								 <table class="table payment-receipt mt-3">
                    <tbody>
                       <!--  <tr><th colspan="9"><span {{extendData.requestId}}></span> &nbsp; Extend Booking Receipt <span class="float-end" ng-bind="'Extend On: '+getFormattedDate(extendBookingOn)"></span></th></tr> -->
                        <tr><th>Sadan - Room & Category</th><th>Guests</th><th>Check-In</th><th>Scheduled Check-Out</th><th>New CheckOut</th><th>charges/day</th><th>days</th><th>Amount</th></tr>    
                        <tr ng-repeat="booking in extendData">
                            <td>{{booking.sadan.name+' - '+floorIndex.get(booking.room.floor)+booking.room.name+' ('+booking.room.category.name+')'}}</td>
                            <td>{{booking.attendants.length}}</td>
                            <td>{{booking.fromDate}}</td>
                            <td>{{booking.scheduleCheckOutDate}}</td>
                            <td>{{booking.toDate}}</td>
                            <td>{{booking.room.category.maxPrice}}</td>
                            <td>{{extendsBookingDays}}</td>
                            <td>{{booking.balanceReceipt.amount}}</td>
                        </tr>
                        <tr>
                          <th colspan="7">Total</th>
                          <td><b class="text-success" ng-bind="getTotalBalanceAmount(extendData)+' Rs.'"></b></td>
                        </tr>
                    </tbody>
                 </table>
								
									
								
								<div class="row py-3">
									
									
										<div class="col text-end"><h5 class="text-end">
											<b>हस्ताक्षर/Signature______________________</b>
										</h5></div>
								</div>
								
								<div class="row py-3">
								<div class="col text"><h5 class="text">
											<b>नियम एवं शर्तें/Terms &Conditions</b>
										</h5></div>
										</div>
								<div class="row">
								    <div class="col">
								        <ul style="list-style-type: disc; padding-left: 20px;">
								            <li>Payments are non-refundable.</li>
								            <li>भुगतान वापसी योग्य नहीं है।</li>
								            <li>Check-in time is 11:00 AM, and check-out time is 10:00 AM.</li>
								            <li>चेक-इन का समय सुबह 11:00 बजे है, और चेक-आउट का समय सुबह 10:00 बजे है।</li>
								            <li>Please carry a valid ID during check-in.</li>
								            <li>कृपया चेक-इन के दौरान एक वैध आईडी साथ रखें।</li>
								            <li>If you check in and wish to check out within 1 hour, you are still required to pay for a full day.</li>
								            <li>यदि आप चेक इन करते हैं और 1 घंटे के भीतर चेक आउट करना चाहते हैं, तो भी आपको पूरे दिन का भुगतान करना होगा।</li>
								            <li>For rooms with a specified capacity, an additional charge of ₹20 per person per day will apply for each extra person beyond the room's standard capacity.</li>
								            <li>निर्दिष्ट क्षमता वाले कमरों के लिए, कमरे की मानक क्षमता से परे प्रत्येक अतिरिक्त व्यक्ति के लिए प्रति दिन ₹20 का अतिरिक्त शुल्क लागू होगा।</li>
								        </ul>
								    </div>
								</div>
								<div class="row">
									
									<div class="col text"><h5 class="text">
											<b>Printed Date & Time: {{today}} </b>
										</h5></div>
								</div>
							</div>
							<div >
								<div class="row justify-content-center">
									
									<div class="col-4">
										<button class="btn btn-outline-success w-100" id="generatepdf"
											ng-click="generatePDF(pdfData)">Generate PDF
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<hr />
				</div></section>


	<footer class="wrapper footer-wrapper mt-auto">
	
		<div class="footer-bottom-wrapper">
			<div
				class="container common-container four_content footer-bottom-container">
				<div class="footer-content clearfix">
					<div class="copyright-content">
						Website Content Managed,Designed, Developed and Hosted by <a
							target="_blank"
							title="NIC, External Link that opens in a new window"
							href="https://www.aiims.edu/index.php?lang=en"><strong>All
								India Institute Of medical Sciences</strong></a><strong> ( AIIMS)</strong>
					</div>

				</div>
			</div>
		</div>
	</footer>


<script>
let params = new URLSearchParams(window.location.search);
let pdfData = Object.fromEntries(params.entries());
</script>
	<!--/.footer-wrapper-->
	<script
		src="<%= contextPath %>/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
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

	<script
		src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.js"></script>
	<script src="<%= contextPath %>/assets/js/sweetalert2.min.js"></script>
	<script src="<%= contextPath %>/assets/js/reception.js"></script>

   
</body>
</html>
