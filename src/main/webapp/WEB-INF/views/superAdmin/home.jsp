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
<link rel="<%= contextPath %>/assets/css/jquery.dataTables.css" rel="stylesheet"> 
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
<style>
body {
	font-family: Arial, sans-serif;
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
tfoot input {
        width: 100%;
        padding: 3px;
        box-sizing: border-box;
    }
</style>

</head>

<body class="d-flex flex-column h-100 bg-light " ng-app="myApp"
	ng-controller="ScheduleBookingController">
	<header>


		<section class="bg-white">
			<div class="container ">
				<h1 class="logo">
					<a href="home" title="Home" rel="home" class="header_logo"
						id="logo"> <img class="national_emblem"
						src="<%= contextPath %>/assets/images/aiims4.png"
						alt="national emblem">
						<p>अ.भा.आ.सं - विश्राम सदन</p> <span class="vishram"> AIIMS
							- Vishram Sadan</span>
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
							<!-- <ul class="navbar-nav ">
								<li class="nav-item"><a class="nav-link text-white"
									href="aboutus">About Us</a></li>
								<li class="nav-item"><a class="nav-link text-white "
									href="contact">Contact Us</a></li>
							</ul> -->
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle text-white " href="#"
								id="navbarDropdown" role="button" data-bs-toggle="dropdown"
								aria-expanded="false">Useful Links</a>
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item text-orange"
										href="https://cfapplication.aiims.edu/santusht/otp_auth"
										target="_blank">Santust Portal</a></li>
									<li><a class="dropdown-item text-orange"
										href="https://ors.gov.in" target="_blank">Online
											Appointment</a></li>
									<li><a class="dropdown-item text-orange"
										href="https://www.aiims.edu/index.php?lang=en" target="_blank">AIIMS
											Official Website</a></li>
								</ul></li>
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
											href="<%=contextPath %>/referrer/changepassword">Change
												Password</a></li>
									</ul></li>
							</ul>


						</div>
					</div>
				</nav>
			</div>
		</section>

	</header>
	<section class="container my-5">
		<div class="container-fluid">
			<div class="card card-shadow p-3">
				<div class="card-header">
					<div class="row">
						<div class="col-lg-9">
							<h4 class="font-weight-bold">Manage Booking Request</h4>
						</div>
						<div class="col-lg-3 text-center">
							<button class="btn login-outline-primary w-100 mw-100"
								data-bs-toggle="modal" data-bs-target="#imageModal">New
								Booking Request</button>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row">

						<div class="col-lg-12 table-responsive">
							<h4>All Booking Request</h4>
							<hr />
							<table id="example" class="w-100  display">
							  <thead>
							        <tr>
										<th>Request</th>
										<th>UHID</th>
										<th>Name</th>
										<th>Contact</th>
										<th>Address</th>
										<th>Date</th>
										<th>Status</th>
										<th>Action</th>
									</tr>
							  </thead>
							  <tbody>
									<tr ng-repeat="row in allBookingRequest.pendingBooking">
										<td>{{ row.requestId }}</td>
										<td>{{ row.patient.uhid }}</td>
										<td>{{ row.patient.fullname }}</td>
										<td>{{ row.patient.contactNo }}</td>
										<td>{{ row.patient.address }}</td>
										<td>{{ getFormattedDate(row.requestedOn) }}</td>
										<td>{{ row.status }}</td>
										<td><button class="btn btn-sm login-outline-primary"
												ng-click="addPriority(row.requestId)">Set Priority
											</button></td>
									</tr>
								</tbody>
								<tfoot>
						            <tr>
										<th>Request</th>
										<th>UHID</th>
										<th>Name</th>
										<th>Contact</th>
										<th>Address</th>
										<th>Date</th>
										<th>Status</th>
										<th></th>
									</tr>
						        </tfoot>
							</table>
							<!--<table id="myTable" class="display">
								<thead>
									<tr>
										<th>Request</th>
										<th>UHID</th>
										<th>Name</th>
										<th>Contact</th>
										<th>Address</th>
										<th>Date</th>
										<th>Status</th>
										<th>Action</th>
									</tr>
									<tr>
										<th><input type="text" class="column-filter form-control"
											placeholder="Filter Request" ng-model="filters.requestId"
											ng-change="applyFilter()"></th>
										<th><input type="text" class="column-filter form-control"
											placeholder="Filter UHID" ng-model="filters.uhid"
											ng-change="applyFilter()"></th>
										<th><input type="text" class="column-filter form-control"
											placeholder="Filter Name" ng-model="filters.name"
											ng-change="applyFilter()"></th>
										<th><input type="text" class="column-filter form-control"
											placeholder="Filter Contact" ng-model="filters.contact"
											ng-change="applyFilter()"></th>
										<th><input type="text" class="column-filter form-control"
											placeholder="Filter Address" ng-model="filters.address"
											ng-change="applyFilter()"></th>
										<th><input type="text" class="column-filter form-control"
											placeholder="Filter Date" ng-model="filters.requestedOn"
											ng-change="applyFilter()"></th>
										<th><input type="text" class="column-filter form-control"
											placeholder="Filter Status" ng-model="filters.status"
											ng-change="applyFilter()"></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="row in filteredData">
										<td>{{ row.requestId }}</td>
										<td>{{ row.patient.uhid }}</td>
										<td>{{ row.patient.fullname }}</td>
										<td>{{ row.patient.contactNo }}</td>
										<td>{{ row.patient.address }}</td>
										<td>{{ getFormattedDate(row.requestedOn) }}</td>
										<td>{{ row.status }}</td>
										<td><button class="btn btn-sm login-outline-primary"
												ng-click="addPriority(row.requestId)">Set Priority
											</button></td>
									</tr>
								</tbody>
                           </table>-->
						</div>
					</div>
				</div>
			</div>
			<!--Booking Request Modal -->
			<div class="modal fade" id="bookingRequestModal" tabindex="-1"
				aria-labelledby="bookingRequestModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="bookingRequestModalLabel">New
								Booking Request</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form>
								<div class="row mb-2">
									<div class="col-lg-4">
										<div class="form-group">
											<label>UHID No <span class="text-danger">*</span></label> <input
												type="number" class="form-control"
												ng-model="newBookingRequest.patient.uhid"
												ng-change="fetchData()" name="uhid" id="uhid"
												placeholder="UHID" />
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label>Patient Name <span class="text-danger">*</span></label>
											<input type="text" class="form-control"
												ng-model="newBookingRequest.patient.fullname"
												name="fullname" id="fullname" placeholder="Patient Name"
												readonly />
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label>Contact No <span class="text-danger">*</span></label>
											<input type="text" class="form-control"
												ng-model="newBookingRequest.patient.contactNo"
												name="contactNo" id="contactNo" placeholder="Contact Number"
												readonly />
										</div>
									</div>
								</div>
								<div class="row mb-2">
									<div class="col-lg-4">
										<div class="form-group">
											<label>Requested By <span class="text-danger">*</span></label>
											<input type="text" class="form-control"
												ng-model="newBookingRequest.requestedBy" name="requestedBy"
												id="requestedBy" placeholder="Requested By (Doctor)" />
										</div>
									</div>
									<div class="col-lg-4">
										<div class="form-group">
											<label>Address <span class="text-danger">*</span></label> <input
												type="text" class="form-control"
												ng-model="newBookingRequest.patient.address" name="address"
												id="address" placeholder="Address" readonly />
										</div>
									</div>

									<div class="col-lg-4">
										<div class="form-group">
											<label>Total Attendants</label> <input type="number"
												class="form-control"
												ng-model="newBookingRequest.totalAttendants"
												name="totalAttendants" id="attendants"
												placeholder="Total Attendants" />
										</div>
									</div>
								</div>
								<div class="row mb-3">


									<div class="col-lg-12">
										<ul class="list-inline mb-0">
											<li class="list-inline-item"><b>PREFERENCES: </b></li>
											<li class="list-inline-item"
												ng-repeat="category in categoryList"><input
												type="checkbox" id="{{'category_'+category.id}}"
												ng-model="categoryList[$index].checked"
												ng-init="categoryList[$index].checked=isCategoryExist(category)"
												ng-change="addRemoveCategory(category,$index)">
												&nbsp;<label for="{{'category_'+category.id}}">
													{{category.name}}</label></li>
										</ul>
									</div>

								</div>
								<div class="row mb-2">
									<div class="col-lg-12">
										<ul class="list-inline mb-0">
											<li class="list-inline-item"><b>VISHRAM SADAN: </b></li>
											<li class="list-inline-item"
												ng-repeat="vishramSadan in vishramSadanList"><input
												type="checkbox" id="{{'sadan_'+vishramSadan.id}}"
												ng-model="vishramSadanList[$index].checked"
												ng-init="vishramSadanList[$index].checked=isVishramSadanExist(vishramSadan)"
												ng-change="addRemoveVishramSadan(vishramSadan,$index)">
												&nbsp;<label for="{{'sadan_'+vishramSadan.id}}">
													{{vishramSadan.name}}</label></li>
										</ul>
									</div>
								</div>
								<p class="text-danger mt-2" id="book-msg"></p>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="button" class="btn custom-primary"
								ng-click="makeBookingRequest()">Make Booking Request</button>
						</div>
					</div>
				</div>
			</div>

			<!-- image model -->
			<div class="modal fade" id="imageModal" tabindex="-1"
				aria-labelledby="bookingRequestModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="bookingRequestModalLabel">New
								Booking Request</h1>
							<button type="button" class="btn-close" ng-click="closeCamera()"
								data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="container text-center">
							<h2>Webcam Face Capture</h2>

							<div class="camera-container" ng-show="cameraOpen">
								<video id="video" autoplay></video>
								<canvas id="canvas" style="display: none;"></canvas>
								<div class="overlay"></div>

							</div>
							<div ng-show="capturedImage">
								<h3>Captured Image</h3>
								<img id="captured" src="">

							</div>
							<p class="text-danger mt-2" id="image-msg"></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal" ng-click="closeCamera()">Close</button>
							<button class="btn custom-primary" ng-click="openCamera()"
								ng-show="!cameraOpen && !capturedImage">Open Camera</button>
							<button class="btn login-outline-primary"
								ng-click="recaptureImage()" ng-show="capturedImage">Recapture</button>
							<button class="btn custom-primary" ng-click="submitImage()"
								ng-show="capturedImage && !cameraOpen">Submit Image</button>
							<button class="btn login-outline-primary"
								ng-click="captureImage()" ng-show="cameraOpen">Capture
								Photo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>










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
    <script src="<%= contextPath %>/assets/js/jquery.dataTables.js"></script>
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
	<script src="<%= contextPath %>/assets/js/referer.js"></script>
	<script
		src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.6/dist/jquery.fancybox.min.js"></script>
	<script src="<%= contextPath %>/assets/js/sweetalert2.min.js"></script>



</body>

</html>