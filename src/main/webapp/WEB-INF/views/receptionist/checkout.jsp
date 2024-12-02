<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="aiims.vishram_sadan.entities.User" %>
    <% String contextPath = request.getContextPath(); %>
<% User loggedInUser = (User)session.getAttribute("user");
%>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>
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
								<li class="nav-item"><a class="nav-link text-white active"
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
                                    <a class="nav-link text-white "  href="contacts">Contact Us</a>
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
    <section class="container my-5" ng-controller="checkoutController">
      <div class="container-fluid">
	    <div class="card card-shadow p-3">
	        <div class="card-header">
	            <h4 class="mb-0">Checkout</h4>
	        </div>
	        <div class="card-body">
	          <div class="row mb-2">
	            <div class="col-lg-2">
	              <div class="form-group">
	                <label>Checkout by</label>
	                <select class="form-control requiredFields" ng-disabled="confirmRetrievedBooking" ng-model="checkout.criteria">
	                  <option value="">--select--</option>
	                  <option value="RequestId">Request Id</option>
	                  <option value="UHID">UHID</option>
	                  <option value="ContactNo">Contact No</option>
	                </select>
	              </div>
	            </div>
	            <div class="col-lg-2">
	              <div class="form-group">
	                <label ng-bind="'Enter '+checkout.criteria"></label>
	                <input type="number" ng-model="checkout.value" ng-disabled="confirmRetrievedBooking" class="form-control requiredFields" placeholder="{{'Enter '+checkout.criteria}}"/>
	              </div>
	            </div>
	            <div class="col-lg-2">
	              <div class="form-group">
	                <br/><button class="btn btn-outline-secondary w-100" ng-disabled="confirmRetrievedBooking" ng-click="getBookingDetails()"> Get Booking Details </button>
	              </div>
	            </div>
	            <div class="col-lg-4">
	              <div class="form-group">
	                <br/><h5 ng-if="retrievedBooking" ng-bind="(currentBookingRequest.patient.fullname).toUpperCase()+' [ '+currentBookingRequest.patient.uhid+' ] '"></h5>
	              </div>
	            </div>
	            <div class="col-lg-1" ng-if="retrievedBooking && !confirmRetrievedBooking">
	              <div class="form-group">
	                <br/><button class="btn btn-outline-success w-100" ng-click="confirmRetrievedData(true)"> YES </button>
	              </div>
	            </div>
	            <div class="col-lg-1" ng-if="retrievedBooking && !confirmRetrievedBooking">
	              <div class="form-group">
	                <br/><button class="btn btn-outline-danger w-100" ng-click="confirmRetrievedData(false)"> No </button>
	              </div>
	            </div>
	            <div class="col-lg-1" ng-if="retrievedBooking && confirmRetrievedBooking">
	              <div class="form-group">
	                <br/><button class="btn btn-outline-secondary w-100" ng-click="resetRetrievedData()"> Reset </button>
	              </div>
	            </div>
	          </div>
	          <div class="row mb-2" ng-if="confirmRetrievedBooking && !checkOutProceed &&!extendsBookingProceed">
	            <div class="col-lg-12 table-responsive">
	              <!-- <table class="table payment-receipt mt-3">
                    <tbody>
                        <tr><th colspan="9"><span ng-bind="currentBookingRequest.requestId"></span> &nbsp; Booking Description</th></tr>
                        <tr><th>Sadan - Room & Category</th><th>Guests</th><th>Check-In</th><th>Check-Out</th> <th>charges/day</th><th>days</th><th>Amount</th><th>Checkout</th><th>Extend Booking</th></tr>    
                        <tr ng-repeat="booking in currentBookingRequest.bookings">
                            <td>{{booking.sadan.name+' - '+floorIndex.get(booking.room.floor)+booking.room.name+' ('+booking.room.category.name+')'}}</td>
                            <td>{{booking.attendants.length}}</td>
                            <td>{{booking.fromDate.split('T')[0]}}</td>
                            <td>{{booking.toDate.split('T')[0]}}</td>
                            <td>{{booking.room.category.price}}</td>
                            <td>{{countDaysBetweenDates(booking.fromDate,booking.toDate)}}</td>
                            <td>{{getTotalPaidAmount(booking)}}</td> 
                            <td><input type="checkbox" id="{{'room_'+$index}}" class="checkout-room" ng-change="addRemoveCheckoutRoom(booking)" ng-model="rooms[$index]"> <label for="{{'room_'+$index}}">&nbsp;</label></td>
                            <td><input type="checkbox" id="{{'extends_room_'+$index}}" class="extends-room" ng-change="addRemoveExtendsRoom(booking)" ng-model="extends_rooms[$index]"> <label for="{{'extends_room_'+$index}}">&nbsp;</label></td>
                        </tr>
                    </tbody>
                 </table>-->
                 <table class="table payment-receipt mt-3">
                    <tbody>
                        <tr><th colspan="9"><span ng-bind="currentBookingRequest.requestId"></span> &nbsp; Booking Description</th></tr>
                        <tr><th>Sadan - Room & Category</th><th>Guests</th><th colspan="5">Payment History<th>Checkout</th><th>Extend Booking</th></tr>    
                        <tr ng-repeat="booking in currentBookingRequest.bookings">
                            <td>{{booking.sadan.name+' - '+floorIndex.get(booking.room.floor)+booking.room.name+' ('+booking.room.category.name+')'}}<br/>
                                {{'Check In '+booking.fromDate.split('T')[0]}}<br/>
                                {{'Check Out '+booking.toDate.split('T')[0]}}
                            </td>
                            <td>{{booking.attendants.length}}</td>
                            <td colspan="5">
                             <table>
                               <tr><td>From Date</td><td>To Date</td> <td>charges/day</td><td>days</td><td>Amount</td></tr>
                               <tr ng-repeat="payment in booking.paymentHistory">
                                 <td>{{payment.fromDate.split('T')[0]}}</td>
                                 <td>{{payment.toDate.split('T')[0]}}</td>
                                 <td>{{payment.rate}}</td>
                                 <td>{{countDaysBetweenDates(payment.fromDate,payment.toDate)}}</td>
                                 <td>{{payment.amount}}</td>
                               </tr>
                             </table>
                            </td>
                            <td><input type="checkbox" id="{{'room_'+$index}}" class="checkout-room" ng-change="addRemoveCheckoutRoom(booking)" ng-model="rooms[$index]"> <label for="{{'room_'+$index}}">&nbsp;</label></td>
                            <td><input type="checkbox" id="{{'extends_room_'+$index}}" class="extends-room" ng-change="addRemoveExtendsRoom(booking)" ng-model="extends_rooms[$index]"> <label for="{{'extends_room_'+$index}}">&nbsp;</label></td>
                        </tr>
                    </tbody>
                 </table>
	            </div>  
	          </div>
	          <div class="row mb-2" ng-if="checkOutProceed">
	            <div class="col-lg-12 table-responsive">
	              <table class="table payment-receipt mt-3">
                    <tbody>
                        <tr><th colspan="12"><span ng-bind="currentBookingRequest.requestId"></span> &nbsp; Final Balance Receipt <span class="float-end" ng-bind="'Checkout On:'+checkoutdate"></span></th></tr>
                        <tr><th >Sadan - Room & Category</th><th >Guests</th><th colspan="6">Payment History</th></tr>    
                        <tr ng-repeat="booking in checkOutBookings">
                            <td >{{booking.sadan.name+' - '+floorIndex.get(booking.room.floor)+booking.room.name+' ('+booking.room.category.name+')'}}<br/>
                            {{'Check In '+booking.fromDate.split('T')[0]}}<br/>
                                {{'Check Out '+booking.toDate.split('T')[0]}}
                            </td>
                            <td >{{booking.attendants.length}}</td>
                             <td colspan="6">
                             <table>
                               <tr><td>From Date</td><td>To Date</td><td>Paid Amount</td><td>Check Out Date</td><td>Actual Bill</td><td>Balance</td></tr>
                               <tr ng-repeat="payment in booking.paymentHistory">
                                 <td>{{payment.fromDate.split('T')[0]}}</td>
                                 <td>{{payment.toDate.split('T')[0]}}</td>
                                 <td>{{payment.rate+'*'+countDaysBetweenDates(payment.fromDate,payment.toDate)+' = '+payment.amount}}</td>
                                 <td>{{checkoutdate}}</td>
                                 <td>{{payment.rate+'*('+((countDaysBetweenDates(payment.fromDate,getDateString(checkoutdate))>0)?countDaysBetweenDates(payment.fromDate,getDateString(checkoutdate)):1)+') = '+
                                       (payment.rate*((countDaysBetweenDates(payment.fromDate,getDateString(checkoutdate))>0)?countDaysBetweenDates(payment.fromDate,getDateString(checkoutdate)):1))}}</td>
                                 <td><b ng-class="(computeBalanceDays(payment.fromDate,payment.toDate,getDateString(checkoutdate))<0)?'text-danger':'text-success'">{{payment.rate*computeBalanceDays(payment.fromDate,payment.toDate,getDateString(checkoutdate))}}</b></td>
                                 <!--  <td>{{payment.amount-((countDaysBetweenDates(payment.fromDate,getDateString(checkoutdate))==0)?1:countDaysBetweenDates(payment.fromDate,getDateString(checkoutdate)))*payment.rate }}</td>-->
                               </tr>
                             </table>
                            </td>
                       </tr>
               <!--          <tr><th colspan="9"><span ></span> &nbsp; Payment History <span class="float-end"></span></th></tr>
                         <tr><th colspan="2">Receipt No.</th><th colspan="2">Receipt Type</th><th>Check-In</th><th>Check-Out</th><th>charges/day</th><th>days</th><th>Amount</th></tr>
                              <tr ng-repeat="payment in checkOutBookings.bookings[0].paymentHistory">
                <td colspan="2">{{ payment.txnId }}</td>
                <td colspan="2">{{ payment.fromDate.split('T')[0] }}</td>
                <td>{{ payment.toDate.split('T')[0] }}</td>
                <td>{{ payment.rate }}</td>
                <td>{{ payment.totalDays }}</td>
                <td>{{ payment.amount }}</td>
            </tr> -->
        
                        <tr>
                          <td colspan="1">
											<b>Total Amount:</b></td>>
											<td><b ng-bind="getTotalBookingAmount(checkOutBookings)+' Rs.'" ng-class="(getTotalBookingAmount(checkOutBookings)>=0)?'text-success':'text-danger'"></b></td>
                          <td colspan="6">
					      <b>
							    <span ng-if="getTotalBalanceAmount(checkOutBookings) >= 0">Due Amount:</span>
							    <span ng-if="getTotalBalanceAmount(checkOutBookings) < 0">Refund Amount:</span>
							  </b>
							</td>
                          <td><b ng-bind="getTotalBalanceAmount(checkOutBookings)+' Rs.'" ng-class="(getTotalBalanceAmount(checkOutBookings)>=0)?'text-success':'text-danger'"></b></td>
                        </tr>
                    </tbody>
                 </table>
	            </div> 
	            <div class="row my-2 justify-content-center">
	              <div class="col-lg-3">
	                 <button ng-click="confirmCheckout()" ng-class="(getTotalBalanceAmount(checkOutBookings)>=0)?'btn-outline-success':'btn-outline-danger'" class="btn w-100" >
		                 <span ng-if="getTotalBalanceAmount(checkOutBookings)<0">Refund</span>
		                 <span ng-if="!extendsBookingProceed && getTotalBalanceAmount(checkOutBookings)>=0">Accept</span> 
		                 <span ng-bind="'Rs. '+getTotalBalanceAmount(checkOutBookings)"></span>
	                 </button>
	                </div>
	            </div> 
	          </div>
	          
	          <div class="row mb-2" ng-if="extendsBookingProceed">
	            <div class="col-lg-12 table-responsive">
	              <table class="table payment-receipt mt-3">
                    <tbody>
                        <tr><th colspan="9"><span ng-bind="currentBookingRequest.requestId"></span> &nbsp; Extend Booking Receipt <span class="float-end" ng-bind="'Extend On: '+getFormattedDate(extendBookingOn)"></span></th></tr>
                        <tr><th>Sadan - Room & Category</th><th>Guests</th><th>Check-In</th><th>Scheduled Check-Out</th><th>New CheckOut</th><th>charges/day</th><th>days</th><th>Amount</th></tr>    
                        <tr ng-repeat="booking in extendsBookings">
                            <td>{{booking.sadan.name+' - '+floorIndex.get(booking.room.floor)+booking.room.name+' ('+booking.room.category.name+')'}}</td>
                            <td>{{booking.attendants.length}}</td>
                            <td>{{getFormattedDate(booking.fromDate)}}</td>
                            <td>{{getFormattedDate(booking.scheduleCheckOutDate)}}</td>
                            <td>{{getFormattedDate(booking.toDate)}}</td>
                            <td>{{booking.room.category.maxPrice}}</td>
                            <td>{{extendsBookingDays}}</td>
                            <td>{{booking.balanceReceipt.amount}}</td>
                        </tr>
                        <tr>
                          <th colspan="7">Total</th>
                          <td><b class="text-success" ng-bind="getTotalBalanceAmount(extendsBookings)+' Rs.'"></b></td>
                        </tr>
                    </tbody>
                 </table>
	            </div> 
	            <div class="row my-2 justify-content-center">
	              <div class="col-lg-3">
	                 <button ng-click="confirmBookingExtension()"  class="btn btn-outline-success w-100" >
	                  Accept <span ng-bind="'Rs. '+getTotalBalanceAmount(extendsBookings)"></span>
	                 </button>
	                </div>
	            </div> 
	          </div>
	          <div class="row mb-2 justify-content-end" ng-if="confirmRetrievedBooking && !checkOutProceed &&!extendsBookingProceed">
	            <div class="col-lg-3">
	              <button class="btn btn-outline-danger w-100" ng-disabled="totalSelectedRooms('checkout')<=0" ng-click="proceesdCheckout()" ng-bind="'Checkout '+checkOutBookings.length+' Rooms'"></button>
	            </div>
	            <div class="col-lg-3">
	              <button class="btn btn-outline-success w-100" ng-disabled="totalSelectedRooms('extends')<=0" ng-click="proceedExtendBooking()" ng-bind="'Extends '+extendsBookings.length+' Booking'"></button>
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