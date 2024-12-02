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

</head>

<body class="d-flex flex-column h-100 bg-light " ng-app="App"
	ng-controller="BookingController">
	<header>


		<section class="bg-white">
			<div class="container ">
				<h1 class="logo">
					<a href="home" title="Home" rel="home" class="header_logo"
						id="logo"> <img class="national_emblem"
						src="<%= contextPath %>/assets/images/aiims4.png"
						alt="national emblem">
						<p>अ.भा.आ.सं - विश्राम सदन</p> <span class="vishram"> AIIMS - Vishram Sadan</span>
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
							class="fa fa-home" style="font-size:15px"></i></a>
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse" data-bs-target="#navbarNav"
							aria-controls="navbarNav" aria-expanded="false"
							aria-label="Toggle navigation">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</button>
						<div class="collapse navbar-collapse" id="navbarNav">
							<ul class="navbar-nav ">
							<li class="nav-item"><a class="nav-link text-white active"
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
	<section class="container my-5">
		<div class="container-fluid">
			<div class="card card-shadow p-3">
				<div class="card-header">
					<h4 class="mb-0">Schedule New Booking</h4>
				</div>
				<div class="card-body" ng-if="bookingStep == 1">
					<div class="row mb-3">
						<div class="col-lg-2">
							<h5>
								<i class="fa fa-filter"></i> Filter
							</h5>
							<hr />
							<h6 class="text-secondary">FLOORS</h6>
							<ul class="list-type-none">
								<li class="list-inline-item"
									ng-repeat="(key, value) in floorMap"><input
									type="checkbox" id="{{'floor_' + key}}"
									ng-checked="filter.floors[key]"> &nbsp;<label
									for="{{'floor_' + key}}"> {{value}}</label></li>
							</ul>
							<h6 class="text-secondary">AVAILABLITY</h6>
							<ul class="list-type-none">
								<li class="mb-0"><input type="checkbox" id="available"
									ng-checked="filter.available" ng-model="filter.available"
									ng-change="toggleAvailabilityFilter('available')">&nbsp;<label
									for="available"> Available </label></li>
								<li class="mb-0"><input type="checkbox" id="not_available"
									ng-checked="filter.not_available"
									ng-model="filter.not_available"
									ng-change="toggleAvailabilityFilter('not_available')">&nbsp;<label
									for="not_available"> Not Available </label></li>
							</ul>
						</div>
						<div class="col-lg-10">
							<h5 class="font-weight-bold">WAITING LIST</h5>
							<div class="row mb-3">
								<div class="col-lg-5">
									<ul class="list-inline mb-0 w-100">
										<li class="list-inline-item w-100">
											<form>
                                                <select class="form-control text-black w-100"
													ng-model="newBookingRequest" name="newBookingRequest"
													ng-options="request as (request.requestId + ' | ' + request.status + ' | ' + request.patient.contactNo + ' | ' + request.patient.uhid + ' | ' + request.patient.fullname + ' | ' + request.attendants.length) for request in requestList">
													<option value="">Select a request</option>
												</select>
											</form>
										</li>
									</ul>
								</div>
								<div class="col-lg-1">
								<button id="infoBtn" class="info-button" data-bs-toggle="modal" data-bs-target="#listModal">i</button>
								</div>
								<div class="col-lg-2" ng-show="newBookingRequest && !imageVerified">
									<div class="btn-group w-100" role="group"
										aria-label="Basic example">
										<button class="btn login-outline-primary w-100"
											data-bs-toggle="modal" data-bs-target="#imageModal">Capture
											Image</button>

									</div>
								</div>
								<div class="col-lg-2" ng-show="newBookingRequest && !imageVerified">
									<button class="btn login-outline-primary w-100"
										data-bs-toggle="modal" data-bs-target="#reminderModal"
										ng-click="openReminderForm(newBookingRequest)">Call
										{{newBookingRequest.reminders.length + 1}}</button>
								</div>
								<div class="col-lg-2" ng-show="newBookingRequest && !imageVerified">
									<button class="btn btn-outline-danger w-100"
										ng-click="setBookingStatusAsNotInterested(newBookingRequest.requestId)">Not
										Interested</button>
								</div>
								<div class="col-lg-2" ng-show="newBookingRequest && imageVerified">
									<button class="btn btn-outline-secondary w-100"
										ng-click="resetImageVerfication()">Reset</button>
								</div>
							</div>
							<div>
							<div class="row mb-3" ng-show="newBookingRequest && imageVerified">
								<div class="col-lg-10">
									<ul class="list-inline mb-0">
										<li class="list-inline-item"><b>PREFERENCES: </b></li>
										<li class="list-inline-item"
											ng-repeat="category in categoryMaster track by $index">
											<input type="checkbox" id="{{'category_' + category.id}}"
											ng-model="filter.categories[$index]"
											ng-init="filter.categories[$index]=isPreference(category)"
											ng-change="addRemoveCategoryFilter(category,$index)">
											&nbsp;<label for="{{'category_' + category.id}}">
												{{category.name}}</label>
										</li>
									</ul>
								</div>
								<div class="col-lg-2" ng-show="newBookingRequest && imageVerified">
									<div class="btn-group w-100" role="group"
										aria-label="Basic example">
										<button type="button" class="btn btn-secondary"
											ng-click="addRemoveAttendants('remove')">-</button>
										<button type="button" class="btn btn-outline-secondary"
											disabled>
											<i class="fa fa-users"></i>
											{{newBookingRequest.attendants.length}}
										</button>
										<button type="button" class="btn btn-secondary"
											ng-click="addRemoveAttendants('add')">+</button>
									</div>
								</div>
							</div>

							<div class="row" ng-show="imageVerified">
								<div class="col-lg-12">
									<ul class="nav nav-tabs" id="myTab" role="tablist">
										<li class="nav-item" role="presentation"
											ng-repeat="vishramSadan in vishramSadanList">
											<button class="nav-link" ng-class="($index==1)?'active':''"
												id="{{vishramSadan.name + '-tab'}}" data-bs-toggle="tab"
												data-bs-target="{{'#'+vishramSadan.name}}" type="button"
												role="tab" aria-controls="home" aria-selected="true">
												{{vishramSadan.name}}</button>
										</li>
									</ul>
									<div class="tab-content" id="myTabContent" >
										<div ng-class="($index==1)?'show active':''"
											ng-repeat="vishramSadan in vishramSadanList"
											class="tab-pane fade p-3" id="{{vishramSadan.name}}"
											role="tabpanel"
											aria-labelledby="{{vishramSadan.name + '-tab'}}">
											<h5 class="mb-2">{{vishramSadan.fullname}}</h5>
											<br />
											<div ng-repeat="category in vishramSadan.categoryList">
												<div class="row my-2 border p-1"
													ng-if="isPreference(category)">
													<div class="col-lg-2 border-right">
														<p class="mb-0 font-weight-bold">{{category.name}}</p>
														<p class="mb-0" ng-if="filter.available">{{'Available:
															' + countAvailablity(getRoomsOfCategory(vishramSadan,
															category)) + '/' + getRoomsOfCategory(vishramSadan,
															category).length}}</p>
														<p class="mb-0"
															ng-if="!filter.available && filter.not_available">{{'Not
															Available: ' + (getRoomsOfCategory(vishramSadan,
															category).length -
															countAvailablity(getRoomsOfCategory(vishramSadan,
															category))) + '/' + getRoomsOfCategory(vishramSadan,
															category).length}}</p>
													</div>
													<div class="col-lg-10 d-flex min-height-100">
														<div
															ng-repeat="room in getRoomsOfCategory(vishramSadan, category)">
															<button
																ng-show="(checkAvailablity(room) && filter.available && filter.floors.get(room.floor)) || (!checkAvailablity(room) && filter.not_available && filter.floors.get(room.floor))"
																ng-click="toggelRoomSelect(room, vishramSadan)"
																class="mx-1"
																ng-class="{'border-success custom-bg-success bg-gradient': checkAvailablity(room),'border-danger custom-bg-danger bg-gradient': (!checkAvailablity(room) && room.status != 'DISABLED'),'border-secondary custom-bg-secondary bg-gradient': (!checkAvailablity(room) && room.status == 'DISABLED'),
                                                                        'selected-room': isRoomSelected(room),'not-selected-room': !isRoomSelected(room)}">
																<div class="w-100 text-center px-2 py-1 text-white">
																	<p class="mb-0 font-weight-bold">
																		{{floorIndex[room.floor] + room.name}} <b
																			class="text-success" ng-if="isRoomSelected(room)">&nbsp;
																			&#10004;</b>
																	</p>
																	<p class="mb-0">
																		<small
																			ng-if="room.status == 'AVAILABLE' || room.status == 'ACTIVE'">AVAILABLE</small>
																		<small ng-if="room.status == 'DISABLED'">DISABLED</small>
																		<small ng-if="room.status == 'NOT_AVAILABLE'">{{'NOT
																			AVAILABLE'}}</small>
																	</p>
																</div>
															</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row mb-2" ng-show="newBookingRequest && imageVerified">
								<div class="col-lg-12">
									<h5>Selected Accommodation Capacity:
										{{getSelectedAccomodationCapacity()}} | Required Accommodation
										Capacity: {{newBookingRequest.attendants.length}}</h5>
								</div>
							</div>
							<div class="row mb-2" ng-show="newBookingRequest && imageVerified">
								<div class="col-lg-3">
									<button type="button" class="btn btn-outline-secondary w-100"
										ng-click="resetBooking()">Reset Booking</button>
								</div>
								<!--
								<div class="col-lg-3">
									<button type="button" class="btn btn-outline-secondary w-100"
										ng-click="goToPreviousBookingStep()">Previous</button>
								</div>
								<div class="col-lg-3">
									<button type="button" class="btn btn-outline-secondary w-100"
										ng-click="goToNextBookingStep()">Next</button>
								</div>-->
								<div class="col-lg-3">
									<button type="button" class="btn btn-outline-success w-100"
										ng-click="proceedBooking()"
										ng-disabled="newBookingRequest.attendants.length==0 || newBookingRequest.attendants.length > getSelectedAccomodationCapacity()">Proceed
										Booking</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
				<div class="card-body" ng-show="bookingStep == 2 && imageVerified">
					<div class="row">
						<div class="col-lg-2">
							<h5>Accomodations :{{newBookingRequest.bookings.length}}</h5>
							<hr />
							<div class="nav flex-column nav-pills me-3" id="v-pills-tab"
								role="tablist" aria-orientation="vertical">
								<button class="nav-link mb-2"
									ng-repeat="booking in newBookingRequest.bookings"
									id="{{'v-pills-tab-'+$index}}" data-bs-toggle="pill"
									data-bs-target="{{'#v-pills-'+$index}}" type="button"
									role="tab" aria-controls="{{'v-pills-'+$index}}"
									aria-selected="true" ng-class="($index==0)?'active':''">
									{{booking.sadan.name+' - '+
									floorIndex.get(booking.room.floor)+''+booking.room.name}}<br />
									<small>{{booking.room.category.name}}</small>
								</button>
							</div>
						</div>
						<div class="col-lg-10">
							<h5>Booking Request</h5>
							<table class="table table-bordered patient-details">
								<tr>
									<th>Patient Name</th>
									<td>{{newBookingRequest.patient.fullname}}</td>
									<th>UHID</th>
									<td>{{newBookingRequest.patient.uhid}}</td>
									<th>Total Guest</th>
									<td>{{newBookingRequest.attendants.length}}</td>
									<th>Requested On</th>
									<td>{{(newBookingRequest.requestedOn+'').substring(0,10)}}</td>
									<th>Days</th>
									<td>{{newBookingRequest.days}}</td>
								</tr>
							</table>
							<hr />
							<div class="d-flex align-items-start">
								<div class="tab-content w-100" id="v-pills-tabContent">
									<div class="tab-pane fade w-100"
										ng-class="($index==0)?'show active':''"
										id="{{'v-pills-'+$index}}" role="tabpanel"
										ng-repeat="booking in newBookingRequest.bookings"
										aria-labelledby="{{'v-pills-tab'+$index}}" tabindex="0">
										<h5>
											Booking of
											{{floorIndex.get(booking.room.floor)+booking.room.name}} <small>{{'('+booking.room.category.name+')'}}</small>
											in {{booking.sadan.name}}
										</h5>
										<div class="row">
											<div class="col-lg-3">
												<div class="form-group">
													<label>Check-In Date</label> <input type="date"
														ng-model="newBookingRequest.bookings[$index].fromDateValue" id="{{'fromDate_'+$index}}" disabled  min="{{todayDate}}" class="form-control" />
												</div>
											</div>
											<div class="col-lg-3">
												<div class="form-group">
													<label>Check-Out Date</label> <input type="date"
														ng-model="newBookingRequest.bookings[$index].toDateValue" id="{{'toDate_'+$index}}" min="{{minToDate}}" max="{{maxToDate}}"
														ng-change="changeCheckInCheckOutDate($index)"
														class="form-control" />
												</div>
											</div>
											<div class="col-lg-2">
												<div class="form-group">
													<label>Rs./Day</label> <input type="number"
														class="form-control"
														ng-model="newBookingRequest.bookings[$index].room.category.price"
														disabled>
												</div>
											</div>
											<div class="col-lg-2">
												<div class="form-group">
													<label>Total Days</label> <input type="number"
														class="form-control"
														ng-model="newBookingRequest.bookings[$index].totalDays"
														disabled value="0">
												</div>
											</div>
											<div class="col-lg-2">
												<div class="form-group">
													<label>Total amount</label> <span class="input-group-text">{{getAmountDescription(newBookingRequest.bookings[$index],'number')}}&nbsp;</span>
												</div>
											</div>
										</div>
										<hr />
										<div class="row">
										  <div class="col-lg-10">
										    <h5>Guest Details</h5>
										  </div>
										  <div class="col-lg-2" ng-if="newBookingRequest.bookings[$index].room.category.extraAllowed">
										    <div class="btn-group w-100" role="group"
												aria-label="Basic example">
												<button type="button" class="btn btn-secondary"
													ng-click="addRemoveGuest('remove',$index)">-</button>
												<button type="button" class="btn btn-outline-secondary"
													disabled>
													<i class="fa fa-users"></i>
													{{newBookingRequest.bookings[$index].attendants.length}}
												</button>
												<button type="button" class="btn btn-secondary"
													ng-click="addRemoveGuest('add',$index)">+</button>
											</div>
										  </div>
										</div>
										
										<div class="row">
											<div class="col-lg-12 table-responsive">
												<table class="table table-bordered attendants-details w-100">
													<tr>
														<th>Full Name</th>
														<th>Gender</th>
														<th>Relation</th>
														<th>D.O.B.</th>
														<th>Aadhaar No (XXXX XXXX 1234)</th>
													</tr>
													<tbody>
														<tr
															ng-repeat="attendants in newBookingRequest.bookings[$index].attendants">
															<td><input type="text" class="form-control"
																ng-model="newBookingRequest.bookings[$parent.$index].attendants[$index].fullname"
																placeholder="Full Name" /></td>
															<td><select
																ng-model="newBookingRequest.bookings[$parent.$index].attendants[$index].gender"
																class="form-control text-black">
																	<option value="">--select--</option>
																	<option value="MALE">MALE</option>
																	<option value="FEMALE">FEMALE</option>
															</select></td>
															<td><select
																ng-model="newBookingRequest.bookings[$parent.$index].attendants[$index].relation"
																class="form-control text-black">
																	<option value="">--select--</option>
																	<option value="SELF" >SELF</option>
																	<option value="Father"
																		ng-if="newBookingRequest.bookings[$parent.$index].attendants[$index].gender=='MALE'">
																		Father</option>
																	<option value="Mother"
																		ng-if="newBookingRequest.bookings[$parent.$index].attendants[$index].gender=='FEMALE'">
																		Mother</option>
																	<option value="Sister"
																		ng-if="newBookingRequest.bookings[$parent.$index].attendants[$index].gender=='FEMALE'">
																		Sister</option>
																	<option value="Brother"
																		ng-if="newBookingRequest.bookings[$parent.$index].attendants[$index].gender=='MALE'">
																		Brother</option>
																	<option value="Wife"
																		ng-if="newBookingRequest.bookings[$parent.$index].attendants[$index].gender=='FEMALE'">
																		Wife</option>
																	<option value="Husband"
																		ng-if="newBookingRequest.bookings[$parent.$index].attendants[$index].gender=='MALE'">
																		Husband</option>
																	<option value="Friend">Friend</option>
															</select></td>
															<td><input type="date" placeholder="D.O.B."
																class="form-control"
																ng-model="newBookingRequest.bookings[$parent.$index].attendants[$index].dob" />
															</td>
															<td>
																<div class="input-group">
																	<span class="input-group-text"
																		id="inputGroup-sizing-default-1"> XXXX </span> <span
																		class="input-group-text"
																		id="inputGroup-sizing-default-2"> XXXX </span> <input
																		type="text" class="form-control mw-100"
																		placeholder="Aadhaar Number" maxlength="4"
																		ng-model="newBookingRequest.bookings[$parent.$index].attendants[$index].adhaarNumber" />
																</div>


															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3">
									<button class="btn btn-outline-secondary w-100"
										ng-click="backFromBooking()">Back</button>
								</div>
								<div class="col-lg-3">
									<button class="btn btn-outline-success w-100"
										ng-click="proceedForPayment()" ng-disabled="totalPayment()==0">
										Proceed Payment of Rs.{{totalPayment()}}</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body" ng-if="bookingStep==3">
					<div class="row justify-content-center">
						<div class="col-lg-9 card">
							<div class="card-header">
								<div class="row">
									<div class="col-2"  ng-if="pdfshot==1">
										<img src="../assets/images/aiims4.png" class="w-75">
									</div>
									<div class="col-2" ng-if="pdfshot==2">
										<img src="../assets/images/aiims4.png" class="w-75">
									</div>
									<div class="col-10">
										<h3 class="font-weight-bold mb-0">Vishram Sadan</h3>
										<h4 class="mb-0">All India Institute Of Medical Sciences</h4>
										<h5 class="mb-0">Ansari Nagar, New Delhi 110029</h5>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col">
										<h5 class="text-danger">
											Booking reference no: <b><i>{{newBookingRequest.requestId}}</i></b>
										</h5>
									</div>
									<div class="col text-end">Dated: {{today}}</div>
								</div>
									<div class="row" ng-if="pdfshot==2">
									<div class="col">
										<h5 class="text-danger">
											Receipt No: <b><i>{{newBookingRequest.bookings[0].paymentHistory[0].txnId}}</i></b>
										</h5>
									</div>
									<div class="col text-end">Receipt Type: {{newBookingRequest.bookings[0].paymentHistory[0].txnType}}</div>
								</div>
								<table class="table payment-receipt">
									<tbody>
										<tr>
											<th colspan="8">Patient Details</th>
										</tr>
										<tr>
											<th>Name</th>
											<td>{{newBookingRequest.patient.fullname}}</td>
											<th>UHID</th>
											<td class="text-end">{{newBookingRequest.patient.uhid}}</td>
											<th>Contact No.</th>
											<td>{{newBookingRequest.patient.contactNo}}</td>
											<th>Address</th>
											<td class="text-end">{{newBookingRequest.patient.address}}</td>
										</tr>
									</tbody>
								</table>
								<div class="table-responsive">
								<table class="table payment-receipt">
									<tbody>
										<tr>
											<th colspan="7">Booking Description</th>
										</tr>
										<tr>
											<th>Sadan - Room & Category</th>
											<th>Guests</th>
											<th>Check-In</th>
											<th>Check-Out</th>
											<th>charges/day</th>
											<th>days</th>
											<th>Amount</th>
										</tr>
										<tr ng-repeat="booking in newBookingRequest.bookings">
											<td>{{booking.sadan.name+' -
												'+floorIndex.get(booking.room.floor)+booking.room.name+'
												('+booking.room.category.name+')'}}</td>
											<td>{{booking.attendants[0].fullname+' +'+(booking.attendants.length-1)}}</td>
											<td>{{booking.fromDateValue.toISOString().split('T')[0]}}</td>
											<td>{{booking.toDateValue.toISOString().split('T')[0]}}</td>
											<td>{{booking.room.category.price}}</td>
											<td>{{booking.totalDays}}</td>
											<td>{{getAmountDescription(booking,'string')}}</td>
										</tr>
										<tr>
											<th colspan="5"></th>
											<th>Total</th>
											<th>{{getTotalBookingAmount(newBookingRequest.bookings)}}</th>
										</tr>
									</tbody>
								</table>
								</div>
							</div>
							<div class="card-footer">
								<div class="row justify-content-center">
									<div class="col-4">
										<button class="btn btn-outline-secondary w-100"
											ng-click="backFromPayment()">Cancel</button>
									</div>
									<div class="col-4">
										<button class="btn btn-outline-success w-100"
											ng-click="makeBooking(newBookingRequest)">Confirm
											Booking</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<hr />
				</div>
				
				<!-- Reminder Modal -->
				<div class="modal fade" id="reminderModal" tabindex="-1"
					aria-labelledby="reminderModal" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="reminderModalLabel">
									Send waiting reminder {{reminderForRequest.reminders.length+1}}
									for <br />Booking Request No: {{reminderForRequest.requestId}}
								</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div ng-if="reminderForRequest.reminders.length> 0">
									<h5>Reminder {{reminderForRequest.reminders.length}} Sent
										On:
										{{getDateTime(reminderForRequest.reminders[reminderForRequest.reminders.length-1].sentOn)}}</h5>
									<h5>Waiting Time Till:
										{{getDateTime(reminderForRequest.reminders[reminderForRequest.reminders.length-1].waitingtill)}}</h5>
								</div>
								<h5>Sending waiting reminder
									{{reminderForRequest.reminders.length+1}} to patient:</h5>
								<div class="row">
									<div class="col-4">Name</div>
									<div class="col-8">{{reminderForRequest.patient.fullname}}</div>
								</div>
								<div class="row">
									<div class="col-4">Contact No</div>
									<div class="col-8">{{reminderForRequest.patient.contactNo}}</div>
								</div>
								<div class="row">
									<div class="col-4">Address</div>
									<div class="col-8">{{reminderForRequest.patient.address}}</div>
								</div>
								<div class="row">
									<div class="col-4">Waiting Hour</div>
									<div class="col-8">
										<input type="number" class="form-control"
											ng-model="newReminder.waitingHour" id="waitingHour"
											placeholder="Waiting Hours" name="waitingHour">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary"
									ng-click="sendReminder()">Send reminder
									{{reminderForRequest.reminders.length+1}}</button>
							</div>
						</div>
					</div>
				</div>
		</div>
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
					<div class="container">
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
						<button class="btn login-outline-primary" ng-click="recaptureImage()"
							ng-show="capturedImage">Recapture</button>
						<button class="btn custom-primary" ng-click="submitImage()"
							ng-show="capturedImage && !cameraOpen">Submit Image</button>
						<button class="btn login-outline-primary" ng-click="captureImage()"
							ng-show="cameraOpen">Capture Photo</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="listModal" tabindex="-1"
					aria-labelledby="listModal" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title font-weight-bold fs-5" id="reminderModalLabel">
									All Waiting List
								</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<table class="display" >
            <thead>
                <tr>
                    <th>Request ID</th>
                    <th>Request Date</th>
                    <th>Status</th>
                    <th>Contact No.</th>
                    <th>UHID</th>
                    <th>Name</th>
                    <th>Attendants</th>
                </tr>
            </thead>
                <tbody>
                <tr ng-repeat="request in allList">
                    <td>{{ request.requestId }}</td>
                    <td>{{ request.requestedOn.split('T')[0]+' '+request.requestedOn.split('T')[1].substring(0,5) }}</td>
                    <td>{{ request.status }}</td>
                    <td>{{ request.patient.contactNo }}</td>
                    <td>{{ request.patient.uhid }}</td>
                    <td>{{ request.patient.fullname }}</td>
                    <td>{{request.attendants.length}}</td>
                      
                </tr>
            </tbody>
        </table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script> -->
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