const contextPath = 'http://localhost:8080/vishram-sadan';
var app = angular.module('App', []);

app.controller('HomeController', function($scope, $http) { 
	
});
app.controller('checkoutController', function($scope, $http) {
	$scope.checkoutdate='';
	$scope.extendBookingOn = '';
	$scope.retrievedBooking = false;
	$scope.confirmRetrievedBooking = false;
	$scope.extendPDF = false;
	$scope.checkOutProceed = false;
	$scope.extendsBookingProceed = false;
	$scope.currentBookingRequest = {};
	$scope.rooms = [];
	$scope.extends_rooms = [];
	$scope.checkOutBookings = [];
	$scope.extendsBookings = [];
	$scope.extendsBookingDays = 7;
	$scope.today = new Date().toISOString().split('T')[0];
	const today = (new Date().toISOString().slice(0, 10));
	$http({
		url:'verify-date/'+today,
		method:'POST'
	})
	.then(function(response){
		console.log(response);
	},function(error){
		alert(error.data.message);
		window.location.href="home";
	});
	$scope.getFormattedDate = function(date){
		if(date != undefined){
		console.log(date);
		//console.log(date.getDate()+"").padStart(2, '0')+"-"+(date.getMonth()+"").padStart(2, '0')+"-"+date.getFullYear();
			return (date.getDate()+"").padStart(2, '0')+"-"+((date.getMonth()+1)+"").padStart(2, '0')+"-"+date.getFullYear();
			
		}else
		    return "";
	}
	$scope.getDateString = function(dateStr){
		if(dateStr != undefined && dateStr != ""){
		   return (new Date(dateStr)).toISOString();
		}else{
		   return undefined;
		}
	}
	$scope.computeBalanceDays = function(fromDate,toDate,checkoutDate){
		console.log(fromDate);
		console.log(toDate);
		console.log(checkoutDate);
		var paidDays = $scope.countDaysBetweenDates(fromDate,toDate);
		var actualDays = $scope.countDaysBetweenDates(fromDate,checkoutDate);
		actualDays = (actualDays>0)?actualDays:1;
		var balanceDays = actualDays-paidDays;
		console.log(balanceDays);
		return balanceDays;
	}
	$scope.countDaysBetweenDates = function(d1,d2){
		if(d1 != undefined && d2 != undefined){
		   let date1 = new Date(d1.split('T')[0]).setHours(11, 0, 0, 0);
		   let date2 = new Date(d2.split('T')[0]).setHours(11, 0, 0, 0);
		   return (date2-date1)/(1000*60*60*24);
		}
		else
		    return 0;
	}
	$scope.getBookingDetails = function() {
		$(".requiredFields").each(function(index) {
			if ($(this).val() == "" || $(this).val() == undefined) {
				alert("Please fill required fields");
				$(this).focus();
				return false;
			}
			else if (index == $(".requiredFields").length - 1) {
				$http({
					url: contextPath + '/reception/get-booking-record/' + $scope.checkout.criteria + '/' + $scope.checkout.value,
					method: 'GET'
				})
					.then(function(response) {
						console.log(response);
						$scope.currentBookingRequest = response.data;
						$scope.retrievedBooking = true;
					}, function(error) {
						alert(error.data.message);
					});
			}
		});
	}
	$scope.totalSelectedRooms = function(type) {
		var total = 0;
		if(type === 'checkout'){
			$(".checkout-room").each(function(index) {
				if ($(this).is(':checked')) {
					total++;
				}
			});
			return total;
		}
		else if(type === 'extends'){
		    $(".extends-room").each(function(index) {
				if ($(this).is(':checked')) {
					total++;
				}
			});
			return total;	
		}
		else
		    return total;
	}
	$scope.confirmRetrievedData = function(status) {
		if (status) {
			$scope.confirmRetrievedBooking = true;
		} else {
			$scope.resetRetrievedData();
		}
	}
	$scope.resetRetrievedData = function() {
		$scope.confirmRetrievedBooking = false;
		$scope.retrievedBooking = false;
		$scope.checkout = {};
		$scope.currentBookingRequest = {};
		$scope.extendsBookingProceed = false;
		$scope.checkOutProceed = false;
		if($scope.totalSelectedRooms('extends') > 0){
			$scope.extendsBookings = [];
			for(var i=0;i<$scope.extends_rooms.length;i++){
			   $scope.extends_rooms[i] = false;
		   }
		}
		if($scope.totalSelectedRooms('checkout') > 0){
		   $scope.checkOutBookings = [];	
		   for(var i=0;i<$scope.rooms.length;i++){
			   $scope.rooms[i] = false;
		   }
		}
	}
	$scope.addRemoveCheckoutRoom = function(requestedBooking) {
		const index = $scope.checkOutBookings.findIndex(booking => booking.id === requestedBooking.id);
		if (index !== -1) {
			$scope.checkOutBookings.splice(index, 1);
		} else {
			$scope.checkOutBookings.push(requestedBooking);
			if($scope.totalSelectedRooms('extends') > 0){
				$scope.extendsBookings = [];
				for(var i=0;i<$scope.extends_rooms.length;i++){
				   $scope.extends_rooms[i] = false;
			   }
			}
		}
	}
	$scope.addRemoveExtendsRoom = function(requestedBooking){
		const index = $scope.extendsBookings.findIndex(booking => booking.id === requestedBooking.id);
		if (index !== -1) {
			$scope.extendsBookings.splice(index, 1);
		} else {
			$scope.extendsBookings.push(requestedBooking);
		
			if($scope.totalSelectedRooms('checkout') > 0){
			   $scope.checkOutBookings = [];	
			   for(var i=0;i<$scope.rooms.length;i++){
				   $scope.rooms[i] = false;
			   }
			}
		}
	}
	//totalSelectedRooms()
	$scope.proceesdCheckout = function() {
		$scope.checkoutdate=new Date().toISOString().split('T')[0];
		for (var i = 0; i < $scope.checkOutBookings.length; i++) {
			let checkinDate = new Date($scope.checkOutBookings[i].fromDate.split('T')[0]).setHours(11, 0, 0, 0);
			let checkOutDate = new Date($scope.checkOutBookings[i].toDate.split('T')[0]).setHours(11, 0, 0, 0);
			let today = (new Date()).setHours(11, 0, 0, 0);
			
			let paidForDays = (today-checkinDate)/(1000*60*60*24);
			paidForDays= (paidForDays==0)?1:paidForDays;
			let actualPay = 0;
			if( paidForDays > 7){
			    actualPay += 7*$scope.checkOutBookings[i].room.category.price;
			    actualPay += (paidForDays-7)*$scope.checkOutBookings[i].room.category.maxPrice;
			}else{
				actualPay += paidForDays*$scope.checkOutBookings[i].room.category.price;
			}
			if($scope.checkOutBookings[i].attendants.length > $scope.checkOutBookings[i].room.category.capacity){
				//actualPay += (($scope.checkOutBookings[i].attendants.length - $scope.checkOutBookings[i].room.category.capacity)*7*$scope.checkOutBookings[i].room.category.extraCharge);
				//actualPay += (($scope.checkOutBookings[i].attendants.length - $scope.checkOutBookings[i].room.category.capacity)*(paidForDays-7)*$scope.checkOutBookings[i].room.category.extraCharge);
			    actualPay += (($scope.checkOutBookings[i].attendants.length - $scope.checkOutBookings[i].room.category.capacity)*paidForDays*$scope.checkOutBookings[i].room.category.extraCharge);
			}
			if (today >= checkOutDate){
				$scope.checkOutBookings[i].balanceReceipt = { "amount": actualPay-$scope.getTotalPaidAmount($scope.checkOutBookings[i]),"fromDate":$scope.checkOutBookings[i].toDate.split('T')[0],"toDate":new Date().toISOString().split('T')[0]};
			}else{
				$scope.checkOutBookings[i].balanceReceipt = { "amount": actualPay-$scope.getTotalPaidAmount($scope.checkOutBookings[i]),"fromDate":new Date().toISOString().split('T')[0],"toDate":$scope.checkOutBookings[i].toDate.split('T')[0]};
			}
		}
		$scope.checkOutProceed = true;
	}
	$scope.proceedExtendBooking = function(){
		for (var i = 0; i < $scope.extendsBookings.length; i++) {
			let checkInDate = (new Date($scope.extendsBookings[i].fromDate.split('T')[0])).setHours(11, 0, 0, 0);
			let scheduleCheckoutDate = (new Date($scope.extendsBookings[i].toDate.split('T')[0])).setHours(11, 0, 0, 0);
			let newCheckoutDate = (new Date(scheduleCheckoutDate)).setHours((new Date(scheduleCheckoutDate)).getHours() + (24 * $scope.extendsBookingDays));
			let amount = 0;
			if($scope.extendsBookings[i].attendants.length <= $scope.extendsBookings[i].room.category.capacity){
				amount =  $scope.extendsBookingDays*parseInt($scope.extendsBookings[i].room.category.maxPrice);
			}else{
				amount =  ($scope.extendsBookingDays*parseInt($scope.extendsBookings[i].room.category.maxPrice))+
				         (($scope.extendsBookings[i].attendants.length - $scope.extendsBookings[i].room.category.capacity)*$scope.extendsBookingDays*$scope.extendsBookings[i].room.category.extraCharge);
			}
			if((newCheckoutDate-checkInDate)/(1000*60*60*24) >= 21){
				alert("Booking for room: "+$scope.extendsBookings[i].room.name+" can't be extends beyond 21 days");
				return false;
			}
			if($scope.extendsBookings[i].room.category.extraAllowed && $scope.extendsBookings[i].attendants.length > $scope.extendsBookings[i].room.category.capacity){
				
			}
			$scope.extendsBookings[i].fromDate = new Date(checkInDate);
			$scope.extendsBookings[i].toDate = new Date(newCheckoutDate);
			$scope.extendsBookings[i].balanceReceipt = { "amount": amount,"fromDate":new Date(scheduleCheckoutDate),"toDate":new Date(newCheckoutDate)};
			$scope.extendsBookings[i].scheduleCheckOutDate = new Date(scheduleCheckoutDate);
		}
		$scope.extendBookingOn = new Date();
		$scope.extendsBookingProceed = true;
	}
	$scope.getTotalPaidAmount = function(booking){
		var total = 0;
		if(booking != undefined && booking != null){
			for (var i = 0; i < booking.paymentHistory.length; i++) { 
				 total += booking.paymentHistory[i].amount;
			}
			return total;
		}
		else
		    return total;
	}
	  $scope.getAmountDescription = function(booking,format){
		if(booking == undefined || booking.attendants == undefined){
		   return;
		}
		else if(format == 'string'){
			if(booking.attendants.length <= booking.room.category.capacity){
				return parseInt(booking.totalDays)+"*"+parseInt(booking.room.category.price);
			}else{
				return (parseInt(booking.totalDays)+"*"+parseInt(booking.room.category.price))+"+"+((booking.attendants.length - booking.room.category.capacity)+"*"+booking.totalDays+"*"+booking.room.category.extraCharge);
			}
		}else{
			if(booking.attendants.length <= booking.room.category.capacity){
				return parseInt(booking.totalDays)*parseInt(booking.room.category.price);
			}else{
				return (parseInt(booking.totalDays)*parseInt(booking.room.category.price))+((booking.attendants.length - booking.room.category.capacity)*booking.totalDays*booking.room.category.extraCharge);
			}
		}
	}
	$scope.getTotalBookingAmount = function(bookings) {
		let total = 0;
		bookings.forEach((booking) => {
			total += $scope.getAmountDescription(booking,'number');
		});
		return total;
	}
	
	$scope.getTotalBalanceAmount = function(bookings){ 
		var total = 0;
		for(var i=0; i< bookings.length; i++){
			total += bookings[i].balanceReceipt.amount;
		}
		return total;
	}
	$scope.confirmBookingExtension = function(){ 
		alert($scope.currentBookingRequest.requestId);
		if(confirm("Please make sure you have successfully ACCEPTED CASH amount worth Rs. "+$scope.getTotalBalanceAmount($scope.extendsBookings)+" from user as per booking extension receipt") == true){ 
			$http({
				url:'extend-booking/'+$scope.currentBookingRequest.requestId,
				method:'POST',
				data:$scope.extendsBookings
			})
			.then(function(response){
				 console.log(response);
				sessionStorage.setItem("extendpdfdata",  JSON.stringify(response.data));
				 $scope.extendPDF=true;
				 alert("Booking extends successfully");
				
				  

			},function(error){
				console.log(error);
				alert("error: "+error.data.message);
			});
		}
	}
	$scope.extendData = JSON.parse(sessionStorage.getItem("extendpdfdata"));
	
	
	$scope.confirmCheckout = function(){
		var balance = $scope.getTotalBalanceAmount($scope.checkOutBookings);
		var confirmMessage = (balance >= 0)?"Please make sure you have successfully RETURNED CASH amount worth Rs. "+balance+" to user as per final balance receipt":
		                                    "Please make sure you have successfully ACCEPTED CASH amount worth Rs. "+balance+" from user as per final balance receipt";
		if(confirm(confirmMessage) == true){
			$http({
				url:'proceed-booking-check-out/'+$scope.currentBookingRequest.requestId,
				method:'POST',
				data:$scope.checkOutBookings
			})
			.then(function(response){
				 console.log(response);
				 alert("Checkout successfully");
				  
					sessionStorage.setItem("checkOutData",  JSON.stringify(response.data));
				window.location.href = contextPath + '/reception/checkOutpdf';

			},function(error){
				console.log(error);
				alert("error: "+error.data.message);
			});
		}
	}
	$scope.checkOutData = JSON.parse(sessionStorage.getItem("checkOutData"));
	console.log("333");
	console.log($scope.checkOutData);
	$scope.totalPrice= function(items){ return items.reduce(function(sum, payment) {
      if (payment.receiptType=='New_Booking'||payment.receiptType=='Re_New_Booking') {
        return sum + payment.amount;
      }
      return sum;
    }, 0);
    }
	$scope.generatePDF = function(responseData) {
   
  const button = document.getElementById('generatepdf');
            button.style.display = 'none';
    const paymentReceipt = document.getElementById('paymentReceipt');
   
    // Clone the booking div (deep clone to include all child elements)
    

    const blankRow = document.createElement('div');
    blankRow.className = 'row'; // Bootstrap row class
    blankRow.style.height = '20px'; // Add some height for spacing, you can adjust this

    // Find the parent of both #pdfHeader and #below
    
     $("#pdfHeader").prepend(blankRow);
    $("#pdfHeader").append(blankRow);
    
    
const pdfContent = paymentReceipt.cloneNode(true);

  
    // Generate the PDF using html2pdf
    html2pdf().from(pdfContent).set({
        filename: `BookingConfirmation_${responseData.requestId}.pdf`,
        // Add custom font and increase clarity
        html2canvas: { 
            scale: 3, // Increase scale for better clarity (higher scale = clearer text)
        },
        jsPDF: {
            unit: 'pt', 
            format: 'a4',
            orientation: 'portrait' // Portrait can be set if needed
        }
    }).save().then(function() {
        // Once the PDF is generated, clear session storage
        //sessionStorage.clear();

        // Redirect to the booking page
        //window.location.href = contextPath + '/reception/booking';
    });
};
	
});
app.controller('BookingController', function($scope, $http) {
	$scope.pdfData={};
	$scope.pdfshot=1;
	$scope.floorMap = new Map();
	$scope.floorIndex = new Map();
	$scope.imageVerified = false;
	$scope.contact = {};
	$scope.filter = {
		floors: new Map(),
		categories: [],
		available: true,
		not_available: false
	};
	$scope.requestList = [];
	$scope.newBookingRequest = {
		bookings: [],
		days: 0,
		patient: { uhid: '', fullname: '', contactNo: '', address: '' },
		attendants: [],
		preferences: [],
		sadans: []
	};
	
	$scope.categoryMaster = [];
	$scope.vishramSadanList = [];
	$scope.selected_floors = [];
	$scope.roomSelected = false;
	$scope.today = new Date().toISOString().split('T')[0];
	$scope.todayDate = new Date();
	const tomorrowDate = new Date();
	tomorrowDate.setDate(tomorrowDate.getDate() + 1);
	$scope.minToDate = tomorrowDate.toISOString().split('T')[0];
	tomorrowDate.setDate(tomorrowDate.getDate() + 7);
	$scope.maxToDate = tomorrowDate.toISOString().split('T')[0];
	$scope.newReminder = { waitingHour: undefined };
	$scope.reminderForRequest = undefined;
	$scope.bookingStep = 1;
	$scope.finalBookings = [];
	$scope.currentBookings = [];
	$scope.bookingDetails = undefined;
	$scope.fromDateValue = null;
    $scope.minToDate = null;
    $scope.fromDate = null;

    $scope.reportData = [];
    
    $scope.bookingReceipt = undefined;
	const today = (new Date().toISOString().slice(0, 10));
	$http({
		url:'verify-date/'+today,
		method:'POST'
	})
	.then(function(response){
		console.log(response);
	},function(error){
		alert(error.data.message);
		window.location.href="home";
	});
	
	$http({
		url: contextPath + '/reception/get-master-data',
		method: 'GET'

	}).then(function(response) {
		console.log(response.data);
		$scope.categoryMaster = JSON.parse(JSON.stringify(response.data.categoryList));
		$scope.vishramSadanList = response.data.vishramSadanList;
		$scope.floorIndex = new Map(Object.entries(response.data.floorIndex).map(([key, value]) => [Number(key), value]));
		$scope.floorMap = new Map(Object.entries(response.data.floorMap).map(([key, value]) => [Number(key), value]));
		$scope.filter.floors = new Map(Object.entries(response.data.selectedFloors).map(([key, value]) => [Number(key), value]));
		$scope.filter.categories = JSON.parse(JSON.stringify(response.data.categoryList));
		$scope.currentBookings = response.data.bookings;
		$scope.newBookingRequest.preferences = JSON.parse(JSON.stringify(response.data.categoryList));
	}, function(error) {
		alert("Error: " + error.message);
	});


    $http({
		method: 'GET',
		url: contextPath + '/reception/get-all-booking-request'
	}).then(function(response) {
		console.log(response);
		$scope.allList = response.data;
	}, function(error) {
		console.log(error);
	});
	


	// Fetch booking requests
	$http({
		method: 'GET',
		url: contextPath + '/reception/get-booking-request'
	}).then(function(response) {
		console.log(response);
		$scope.requestList = response.data;
		if ($scope.requestList.length > 0) {
			$scope.newBookingRequest = $scope.requestList[0]; // Optionally select the first item
		}
	}, function(error) {
		console.log(error);
	});

	// Add or remove attendants
	$scope.addRemoveAttendants = function(action) {
		$scope.newBookingRequest.bookings = [];
		if (action === 'add') {
			$scope.newBookingRequest.attendants.push({ fullname: '', relation: '', adhaarNumber: 0, addOn:false });
		} else if (action === 'remove') {
			$scope.newBookingRequest.attendants.pop();
		}
	};
	
	$scope.addRemoveGuest = function(action,index){
		if(!$scope.newBookingRequest.bookings[index].room.category.extraAllowed){
			return false;
		}
		else if (action === 'add' && $scope.newBookingRequest.bookings[index].attendants.length < $scope.newBookingRequest.bookings[index].room.category.capacity+4 ) {
			$scope.newBookingRequest.bookings[index].attendants.push({ fullname: '', relation: '', adhaarNumber: 0, addOn:true });
		}
		else if (action === 'remove' && $scope.newBookingRequest.bookings[index].attendants.length > $scope.newBookingRequest.bookings[index].room.category.capacity) {
			$scope.newBookingRequest.bookings[index].attendants.pop();
		}
	}
    $scope.getAmountDescription = function(booking,format){
		if(booking == undefined || booking.attendants == undefined){
		   return;
		}
		else if(format == 'string'){
			if(booking.attendants.length <= booking.room.category.capacity){
				return parseInt(booking.totalDays)+"*"+parseInt(booking.room.category.price);
			}else{
				return (parseInt(booking.totalDays)+"*"+parseInt(booking.room.category.price))+"+"+((booking.attendants.length - booking.room.category.capacity)+"*"+booking.totalDays+"*"+booking.room.category.extraCharge);
			}
		}else{
			if(booking.attendants.length <= booking.room.category.capacity){
				return parseInt(booking.totalDays)*parseInt(booking.room.category.price);
			}else{
				return (parseInt(booking.totalDays)*parseInt(booking.room.category.price))+((booking.attendants.length - booking.room.category.capacity)*booking.totalDays*booking.room.category.extraCharge);
			}
		}
	}
	// Add or remove category filter
	$scope.addRemoveCategoryFilter = function(category, ind) {
		console.log($scope.categoryMaster[ind]);
		const index = $scope.newBookingRequest.preferences.findIndex(item => item.id === category.id);
		if (index !== -1) {
			//$scope.categoryList[ind].checked = false;
			$scope.newBookingRequest.preferences.splice(index, 1);
		} else {
			// $scope.categoryList[ind].checked = true;
			$scope.newBookingRequest.preferences.push(category);
		}
	};

	$scope.isPreference = function(category) {
		var exists = false;
		for (var i = 0; i < $scope.newBookingRequest.preferences.length; i++) {
			if ($scope.newBookingRequest.preferences[i].id === category.id) {
				exists = true;
				break;
			}
		}
		return exists;
	};

	// Add or remove category from preferences



	// Check availability of a room
	$scope.checkAvailablity = function(room) {
		return checkAvailablity(room, $scope.currentBookings);
	};

	// Count availability of rooms
	$scope.countAvailablity = function(rooms) {
		let counts = 0;
		rooms.forEach(room => {
			if (checkAvailablity(room, $scope.currentBookings)) {
				counts++;
			}
		});
		return counts;
	};

	// Get rooms of a specific category
	$scope.getRoomsOfCategory = function(sadan, category) {
		return sadan.rooms.filter(room => room.category.name === category.name);
	};

	// Get total number of rooms of a specific category
	$scope.getTotalRoomsOfCategory = function(category) {
		let count = 0;
		$scope.vishramSadanList.forEach(sadan => {
			sadan.rooms.forEach(room => {
				if (room.category.name === category.name) {
					count++;
				}
			});
		});
		return count;
	};

	// Check if a room is selected
	$scope.isRoomSelected = function(room) {
		return $scope.newBookingRequest.bookings.some(booking => booking.room.id === room.id);
	};

	// Toggle room selection
	$scope.toggelRoomSelect = function(room, sadan) {
		const index = $scope.newBookingRequest.bookings.findIndex(booking => booking.room.id === room.id);
		if (index !== -1) {
			$scope.newBookingRequest.bookings.splice(index, 1);
		} else if ($scope.newBookingRequest.attendants.length <= $scope.getSelectedAccomodationCapacity()) {
			alert("Selected rooms are sufficient for accommodation");
			return;
		} else {
			if (checkAvailablity(room, $scope.currentBookings)) {
				const tempDate = new Date($scope.today);
				tempDate.setDate(tempDate.getDate() + $scope.newBookingRequest.days);
				const toDate = tempDate.toISOString().split('T')[0];
				const date1 = new Date($scope.today);
				const date2 = new Date(toDate);
				const daysDifference = (date2.getTime() - date1.getTime()) / (1000 * 3600 * 24);
				$scope.newBookingRequest.bookings.push({
					request: $scope.newBookingRequest,
					room: room,
					sadan: sadan,
					fromDateValue: $scope.todayDate,
					toDateValue: date2,
					totalDays: daysDifference,
					paymentHistory: [{ amount: 0 }]
				});
			} else {
				const bookingDetail = $scope.getBookingDetails(room);
				alert("This room is not available for booking as it is already allotted to " + bookingDetail.patient.fullname + ' (' + bookingDetail.patient.uhid + ')');
			}
		}
	};
	
	$scope.callFunctionForNotAvailableRoom = function(room) {
   const bookingDetail = $scope.getBookingDetails(room);
	alert("This room is not available for booking as it is already allotted to " + bookingDetail.patient.fullname + ' (' + bookingDetail.patient.uhid + ')');
};

	// Get selected accommodation capacity
	$scope.getSelectedAccomodationCapacity = function() {
		return $scope.newBookingRequest.bookings.reduce((count, booking) => count + booking.room.category.capacity, 0);
	};

	// Proceed to booking
	$scope.proceedBooking = function() {
		$scope.newBookingRequest.bookings.forEach((booking, index) => {
			const attendantsList = [];
			for (let i = 0; i < booking.room.category.capacity; i++) {
				attendantsList.push({ fullname: '', relation: '', adhaarNumber: 0, addOn:false });
				if (i === booking.room.category.capacity - 1) {
					$scope.newBookingRequest.bookings[index].attendants = attendantsList;
				}
			}
			$scope.changeCheckInCheckOutDate(index);
		});
		$scope.bookingStep = 2;
	};

	// Reset booking
	$scope.resetBooking = function() {
		$scope.roomSelected = false;
		$scope.imageVerified = false;
		$scope.newBookingRequest.bookings = [];
	};

	// Get booking details for a room
	$scope.getBookingDetails = function(room) {
		return $scope.currentBookings.find(booking => booking.room.id === room.id) || {};
	};

	// Go back from booking step
	$scope.backFromBooking = function() {
		$scope.roomSelected = false;
		$scope.bookingStep = 1;
		$scope.newBookingRequest.bookings = [];
	};
	$scope.backFromPayment = function() {
		$scope.bookingStep = 2;
	}
	// Open reminder form
	$scope.openReminderForm = function(reminderRequest) {
		$scope.newReminder = { waitingHour: undefined };
		$scope.reminderForRequest = reminderRequest;
	};

	// Send reminder
	$scope.sendReminder = function() {
		if ($scope.newReminder.waitingHour == 0 || $scope.newReminder.waitingHour === undefined) {
			alert("Please mention waiting hour");
			return;
		} else if (confirm("Are you sure to send waiting reminder")) {
			$http({
				method: 'POST',
				url: contextPath + '/reception/send-waiting-reminder/' + $scope.reminderForRequest.requestId,
				data: $scope.reminderForRequest
			}).then(function(response) {
				alert("Reminder sent successfully");
			}, function(error) {
				console.log(error);
				alert("Error: " + error.message);
			});
		}
	};
	$scope.proceedForPayment = function() {
		for (let i = 0; i < $scope.newBookingRequest.bookings.length; i++) {
			if (!checkValidAttendant($scope.newBookingRequest.bookings[i].attendants)) {
				alert("Please fill valid attendants fields for room: "
					+ $scope.floorIndex.get(this.newBookingRequest.bookings[i].room.floor) + $scope.newBookingRequest.bookings[i].room.name
					+ '(' + $scope.newBookingRequest.bookings[i].room.category.name + ')');
				return;
			} else if (i == $scope.newBookingRequest.bookings.length - 1) {
				$scope.bookingStep = 3;
			}
		}
	}
	// Format datetime
	$scope.getDateTime = function(dateTime) {
		const dateTimeArr = dateTime.split('T');
		return dateTimeArr[0] + ' ' + dateTimeArr[1].substring(0, 5);
	};

	// Set booking status as not interested
	$scope.setBookingStatusAsNotInterested = function(requestId) {
		if (confirm("Are you sure patient is NOT INTERESTED in booking")) {
			$http({
				method: 'POST',
				url: contextPath + '/reception/update-booking-status-not-interested/' + requestId
			}).then(function(response) {
				alert("Booking Cancelled");
			}, function(error) {
				console.log(error);
				alert("Error");
			})
				.finally(function() {
					window.location.reload();
				});
		}
	};

	// Change check-in and check-out dates
	$scope.changeCheckInCheckOutDate = function(index) {
		$scope.newBookingRequest.bookings[index].totalDays = 0;
		const date1 = new Date($scope.newBookingRequest.bookings[index].fromDateValue);
		const date2 = new Date($scope.newBookingRequest.bookings[index].toDateValue);
		const timeDifference = date2.getTime() - date1.getTime();
		const daysDifference = Math.ceil(timeDifference / (1000 * 3600 * 24));
		if (daysDifference > 0) {
			$scope.newBookingRequest.bookings[index].totalDays = daysDifference;
			let payment = {"amount":$scope.getAmountDescription($scope.newBookingRequest.bookings[index],'number'),"fromDate":date1,"toDate":date2};
			$scope.newBookingRequest.bookings[index].paymentHistory[0] = payment;
			$scope.newBookingRequest.bookings[index].payment = payment;
		}
	};

	$scope.totalPayment = function() {
		let totalPayment = 0;
		$scope.newBookingRequest.bookings.forEach((booking) => {
			totalPayment += $scope.getAmountDescription(booking,'number');
			//booking.room.category.price * booking.totalDays
		});
		return totalPayment;
	}
	$scope.getTotalBookingAmount = function(bookings) {
		let total = 0;
		bookings.forEach((booking) => {
			total += $scope.getAmountDescription(booking,'number');
		});
		return total;
	}
	$scope.makeBooking = function(request) {
		$scope.finalBookings = [];
		for (var i = 0; i < request.bookings.length; i++) {
				let finalBooking = request.bookings[i];
				var fromDateArr = $("#fromDate_"+i).val().split('-');
				var toDateArr = $("#toDate_"+i).val().split('-');
				finalBooking.fromDate = fromDateArr[0]+"-"+fromDateArr[1]+"-"+fromDateArr[2];
				finalBooking.toDate =   toDateArr[0]+"-"+toDateArr[1]+"-"+toDateArr[2];
				finalBooking.request = undefined;
				$scope.finalBookings.push(finalBooking);
		}
		request.bookings = $scope.finalBookings;
		if (confirm("Are you sure to confirm this booking. Please make sure you have collected all of the cashes as per recipt") == true) {
				$http({
					url: 'proceed-booking-confirmation',
					method: 'POST',
					data: { "requestId": request.requestId, "bookings": request.bookings, "attendants": request.attendants }
				})
				.then(function(response) {
					console.log(response.data);
					 //let queryParams = new URLSearchParams(response).toString();
					 $scope.pdfData=response.data;
					 sessionStorage.setItem("pdfdata",  JSON.stringify(response.data));
					 
					window.location.href = contextPath + '/reception/bookingspdf';
					
					alert("Booking Confirm Successfully");
					
				}, function(error) {
					console.log(error);
				});
		}
	}
	
	//$scope.pdfData=sessionStorage.getItem("pdfdata");
	//console.log("shyam "+$scope.pdfData.patient.uhid);
	$scope.pdfData = JSON.parse(sessionStorage.getItem("pdfdata"));
	//




	
	
	
$scope.generatePDF = function(responseData) {
   
  const button = document.getElementById('generatepdf');
            button.style.display = 'none';
    const paymentReceipt = document.getElementById('paymentReceipt');
   
    // Clone the booking div (deep clone to include all child elements)
    

    const blankRow = document.createElement('div');
    blankRow.className = 'row'; // Bootstrap row class
    blankRow.style.height = '20px'; // Add some height for spacing, you can adjust this

    // Find the parent of both #pdfHeader and #below
    
     $("#pdfHeader").prepend(blankRow);
    $("#pdfHeader").append(blankRow);
    
    
const pdfContent = paymentReceipt.cloneNode(true);

  
    // Generate the PDF using html2pdf
    html2pdf().from(pdfContent).set({
        filename: `BookingConfirmation_${responseData.requestId}.pdf`,
        // Add custom font and increase clarity
        html2canvas: { 
            scale: 3, // Increase scale for better clarity (higher scale = clearer text)
        },
        jsPDF: {
            unit: 'pt', 
            format: 'a4',
            orientation: 'portrait' // Portrait can be set if needed
        }
    }).save().then(function() {
        // Once the PDF is generated, clear session storage
        sessionStorage.clear();

        // Redirect to the booking page
    window.location.href = contextPath + '/reception/booking';
    });
};



	
	$scope.submitContact = function() {
		
		$(".requiredFields").each(function(index) {
			if ($(this).val() == "" || $(this).val() == undefined) {
				alert("Please fill required fields");
				$(this).focus();
				return false;
			}
			else if (index == $(".requiredFields").length - 1) {
				$http({
					url: contextPath + '/reception/contact' ,
					method: 'POST',
					data:$scope.contact
				})
					 .then(function(response){
						  console.log(response);
						    Swal.fire({
							  title: "Contact Us",
							  html: `Thank You for contacting us <b class='text-success'></b><br/>`,
							  icon: "success"
							})
							.then(function(){
								window.location.reload();
							});
						
					}, function(error) {
						alert(error.data.message);
					});
			}
		});
		}


 
$scope.updateToDate = function() {
        if ($scope.fromDate) {
            // Create a date object from the selected fromDate
            var fromDateObj = new Date($scope.fromDate);
            
            // Increment the date by 1 to disable the same day
            fromDateObj.setDate(fromDateObj.getDate() + 1);
            
            // Set minToDate to the day after fromDate
            $scope.minToDate = fromDateObj.toISOString().split('T')[0];
            
            // Reset toDate if it's earlier than the new minToDate
            if ($scope.toDate && new Date($scope.toDate) < fromDateObj) {
                $scope.toDate = null; // Clear the toDate if it's invalid
            }
        } else {
            $scope.minToDate = null; // Reset if no fromDate is selected
        }
    };

	$scope.openCamera = function() {
		$("#image-msg").html("");
		//$("#imageModal").modal('hide');
		//$scope.closeCamera();
				//$scope.imageVerified = true;
		
		$scope.cameraOpen = true;
		navigator.mediaDevices.getUserMedia({ video: true })
			.then(function(mediaStream) {
				stream = mediaStream;
				var video = document.getElementById('video');
				video.srcObject = stream;
				video.play();
			})
			.catch(function(err) {
				console.error("Error accessing webcam: ", err);
			});
	};

	$scope.captureImage = function() {
		var video = document.getElementById('video');
		var canvas = document.getElementById('canvas');
		var context = canvas.getContext('2d');
		var width = video.videoWidth;
		var height = video.videoHeight;
		var radius = Math.min(width, height) * 0.3; // Adjust the size of the rectangle as needed

		canvas.width = width;
		canvas.height = height;
		context.clearRect(0, 0, width, height);

		// Draw the video frame to the canvas
		context.drawImage(video, 0, 0, width, height);

		// Create a rectangular clipping area around the circle
		var rectWidth = radius * 2;
		var rectHeight = radius * 2;
		var x = (width - rectWidth) / 2;
		var y = (height - rectHeight) / 2;

		// Create a new canvas to hold the rectangular region
		var rectCanvas = document.createElement('canvas');
		rectCanvas.width = rectWidth;
		rectCanvas.height = rectHeight;
		var rectContext = rectCanvas.getContext('2d');
		rectContext.drawImage(canvas, x, y, rectWidth, rectHeight, 0, 0, rectWidth, rectHeight);

		// Set the captured image as the extracted rectangular region
		var imageData = rectCanvas.toDataURL();
		document.getElementById('captured').src = imageData;
		var base64Image = imageData.split(',')[1];
		$scope.base64Image = base64Image;  // Save the base64 image
		$scope.capturedImage = true;
		$scope.cameraOpen = false;
		$scope.$apply();
		$scope.closeCamera();

	};

	$scope.recaptureImage = function() {
		$("#image-msg").html("");
		$scope.capturedImage = false;
		$scope.base64Image = "";  // Clear the base64 image
		$scope.openCamera();
	};


	$scope.closeCamera = function() {
		if (stream) {
			stream.getTracks().forEach(track => track.stop());
		}
		$scope.cameraOpen = false;
		$scope.capturedImage = false;
		$scope.base64Image = "";
	};
    $scope.resetImageVerfication = function(){
		$scope.imageVerified = false;
	}
	$scope.submitImage = function() {

        $scope.imageVerified = false;
		// Use the dummy API function instead of $http.post
		$http({
			url: 'match-face',
			method: 'POST',
			data: {
				"image1": $scope.base64Image,
				"image2": $scope.newBookingRequest.patientImage
			}
		}).then(function(response) {
			console.log(response);
			$("#image-msg").html(response.data.message);
			if(response.data.success){
				$scope.newBookingRequest.patientImage = $scope.base64Image;
				$("#imageModal").modal("hide");
				$scope.closeCamera();
				$scope.imageVerified = true;
			}else{
				$scope.imageVerified = false;
			}
			
			//$("#bookingRequestModal").modal("show");
		}, function(error) {
			console.log(error);
			$("#image-msg").html(error.data.message);

		});
	}
    $scope.slides = function(end) {
         return Array.from({ length: end+1 }, (v, i) => i);
    };
    $scope.roomsList = function(end,vishramSadan, category){
         var arr = [];
	    for (var i = end*5; i < end*5+5; i++) {
	      arr.push($scope.getRoomsOfCategory(vishramSadan, category)[i]);
	    }
	    console.log(arr);
	    return arr;
	 }
});

app.controller('PaymentController', function($scope, $http) {
	$scope.paymentReport=  [];
	$scope.receiptDetails=[];
	 $scope.filters = {};
$scope.generateReport = function() {
    $(".requiredFields").each(function(index) {
        if ($(this).val() == "" || $(this).val() == undefined) {
            alert("Please fill required fields");
            $(this).focus();
            return false;
        } else if (index == $(".requiredFields").length - 1) {
            var fromDate = $("#fromDate").val();
            var toDate = $("#toDate").val();

            $http({
                url: contextPath + '/reception/get-payment-reciept?fromDate=' + fromDate + '&toDate=' + toDate,
                method: 'GET'
            }).then(function(response) {
                console.log(response.data);

                // Use $scope.$apply() to manually trigger a digest cycle for scope updates
               
                    $scope.paymentReport = response.data;
                    $scope.filteredData = $scope.paymentReport.sort((a, b) => new Date(b.txnDate) - new Date(a.txnDate));
             

                // Handle DataTable initialization after the digest cycle
                setTimeout(function() {
                    $('#paymentHistory').DataTable().destroy();
                    new DataTable('#paymentHistory', {
                        initComplete: function() {
                            this.api()
                                .columns()
                                .every(function() {
                                    let column = this;
                                    let title = column.footer().textContent;
                                    if (title != '') {
                                        let input = document.createElement('input');
                                        input.placeholder = title;
                                        column.footer().replaceChildren(input);

                                        input.addEventListener('keyup', () => {
                                            if (column.search() !== this.value) {
                                                column.search(input.value).draw();
                                            }
                                        });
                                    }
                                });
                                $scope.$apply();
                        }
                    });
                }, 500);

            }, function(error) {
                if (error.status === 404) {
                    var errorMessage = error.body || "An error occurred.";
                    alert(errorMessage);
                } else {
                    alert("An unexpected error occurred. Status: " + error.status);
                }
            });
        }
    });
};
	
	$scope.generateExcel = function() {
            if ($scope.filteredData && $scope.filteredData.length) {
                    var worksheet = XLSX.utils.json_to_sheet($scope.filteredData);
                    var workbook = XLSX.utils.book_new();
                    XLSX.utils.book_append_sheet(workbook, worksheet, 'Payment Report');
                    XLSX.writeFile(workbook, 'Payment_Report.xlsx');
                } else {
                    alert("No data available to export!");
                }   
     
    };
	 /*  $scope.filters = {
            bookingId: '',
            txnId: '',
            type:'',
            txnDate: ''
        };
	
	           $scope.applyFilter = function() {
            $scope.filteredData = $scope.paymentReport.filter(function(item) {
                return Object.keys($scope.filters).every(function(key) {
                    if (key === 'bookingId') {
                        return String(item.request.requestId).toLowerCase().includes(String($scope.filters[key]).toLowerCase());
                    } else {
                        return String(item[key]).toLowerCase().includes(String($scope.filters[key]).toLowerCase());
                    }
                });
            });
        };*/
	
	 $scope.viewReceipt = function(txnId) {
    // Filter the transaction by txnId
    $scope.receiptDetails = $scope.paymentReport.find(function(transaction) {
      return transaction.txnId === txnId;
    });

    if ($scope.receiptDetails) {
      $('#receiptModal').modal('show');
    } else {
      alert("Transaction not found");
    }
  };

  $scope.getTotalAmount = function(data) {
        let total = 0; 
        if(data.payments != undefined){
	        for(var i=0; i<data.payments.length; i++){
				if(data.payments[i].txnType==='CREDIT'){
				   total += data.payments[i].amount;
				}else{
				   total -= data.payments[i].amount;
				}
			}
		}
        return total;
    };
    
    $scope.calculateOverallTotal = function() {
		let total = 0;
		for(var i=0; i<$scope.paymentReport.length; i++){ 
			total += $scope.getTotalAmount($scope.paymentReport[i]);
		}
		return total;
    };
    $scope.countDaysBetweenDates = function(d1,d2){
		if(d1 != undefined && d2 != undefined){
		let date1 = new Date(d1.split('T')[0]).setHours(11, 0, 0, 0);
		let date2 = new Date(d2.split('T')[0]).setHours(11, 0, 0, 0);
		return (date2-date1)/(1000*60*60*24);
		}
		else
		    return 0;
	}
	$scope.getTotalAmountss = function(receiptDetails) {
      let total = 0; 
        if(receiptDetails.payments != undefined){
	        for(var i=0; i<receiptDetails.payments.length; i++){
				if(receiptDetails.payments[i].txnType==='CREDIT'){
				   total += receiptDetails.payments[i].amount;
				}else{
				   total -= receiptDetails.payments[i].amount;
				}
			}
		}
        return total;
  
};
});
	
app.controller('BookingListController', function($scope, $http) {
				$http({
				    url: contextPath + '/reception/getConfirmRequest',
				    method: 'GET'
				}).then(function(response) {
				    console.log(response.data);
				    $scope.allRequest = response.data;
				  
				    $scope.filteredData = $scope.allRequest.confirmBooking;
				       $scope.sortFilteredDataByToDate();
					setTimeout(function(){
					      $('#myTable').DataTable().destroy(); 
						   new DataTable('#myTable', {
							   order: [[0, 'desc']],
						    initComplete: function () {
						        this.api()
						            .columns()
						            .every(function () {
						                let column = this;
						                let title = column.footer().textContent;
						                if(title != ''){
							                // Create input element
							                let input = document.createElement('input');
							                input.placeholder = title;
							                column.footer().replaceChildren(input);
							 
							                // Event listener for user input
							                input.addEventListener('keyup', () => {
							                    if (column.search() !== this.value) {
							                        column.search(input.value).draw();
							                    }
							                });
						                }
						            });
						    }
						});
					},500);
						       
				    $scope.filteredDatas = $scope.allRequest.closeBooking;
				     setTimeout(function(){
						 $('#myTableClose').DataTable().destroy(); 
							new DataTable('#myTableClose', {
							    initComplete: function () {
							        this.api()
							            .columns()
							            .every(function () {
							                let column = this;
							                let title = column.footer().textContent;
							                if(title != ''){
								                // Create input element
								                let input = document.createElement('input');
								                input.placeholder = title;
								                column.footer().replaceChildren(input);
								 
								                // Event listener for user input
								                input.addEventListener('keyup', () => {
								                    if (column.search() !== this.value) {
								                        column.search(input.value).draw();
								                    }
								                });
							                }
							            });
							    }
							});
						},500);
				    
				}, function(error) {
				    console.log(error.data.message); 
				});
				  
				  
				  $scope.sortFilteredDataByToDate = function() {
                $scope.filteredData.sort(function(a, b) {
            if (a.bookings.length > 0 && b.bookings.length > 0) {
                var dateA = new Date(a.bookings[0].toDate); // Access first item in bookings array
                var dateB = new Date(b.bookings[0].toDate);
                return dateA - dateB; // Ascending order
            }
            return 0; // In case bookings array is empty, keep the order unchanged
        });
    };
				
			
	$scope.shouldHighlight = function(bookings) {
   
    if (bookings=== undefined|| bookings === null ) {
       
        return false;
    } else {
        // Get today's date as a string in yyyy-MM-dd format
        var today = new Date().toISOString().split('T')[0];

        // Check if the status is 'confirm' or if toDate is today for the first booking
        // Assuming 'bookings' is an array, check the first item in the array
        return bookings[0].status === 'CONFIRM' && bookings[0].toDate === today;
    }
};

$scope.generateCloseReport = function() {
	 $(".requiredFields").each(function(index) {
			if ($(this).val() == "" || $(this).val() == undefined) {
				alert("Please fill required fields");
				$(this).focus();
				return false;
			} else if (index == $(".requiredFields").length - 1) {
	
				var fromDate = $("#fromDate").val();
	          var toDate = $("#toDate").val();
	
	$http({
		url: contextPath + '/reception/get-close-request?fromDate=' + fromDate + '&toDate=' + toDate,
		method: 'GET'

	}).then(function(response) {
		console.log(response.data);
		$scope.closeRequestReport=response.data;
		 setTimeout(function(){
			 $('#closeReport').DataTable().destroy(); 
				new DataTable('#closeReport', {
					 
				    initComplete: function () {
				        this.api()
				            .columns()
				            .every(function () {
				                let column = this;
				                let title = column.footer().textContent;
				                if(title != ''){
					                // Create input element
					                let input = document.createElement('input');
					                input.placeholder = title;
					                column.footer().replaceChildren(input);
					 
					                // Event listener for user input
					                input.addEventListener('keyup', () => {
					                    if (column.search() !== this.value) {
					                        column.search(input.value).draw();
					                    }
					                });
				                }
				            });
				    }
				});
			},500);
			
			 
	}, function(error) {
		   if (error.status === 404) {
            // Extract error message from the response body
            var errorMessage = error.body || "An error occurred.";
            alert(errorMessage); // Show the error message
        } else {
            // Handle other error status codes if needed
            alert("An unexpected error occurred. Status: " + error.status);
        }
	});
	}
  });
	}

	});
	
function checkAvailablity(room, currentBookings) {
	return (currentBookings.find(booking => booking.room.id == room.id) !== undefined) ? false : true;
}
function checkValidAttendant(attendants) {
	return attendants.some(user => isValidUser(user));
}
function isValidUser(attendant) {
	const isValidUsername = typeof attendant.fullname === 'string' && attendant.fullname.trim().length > 0;
	const isValidGender = typeof attendant.gender === 'string' && attendant.gender.trim().length > 0;
	const isValidRelation = typeof attendant.relation === 'string' && attendant.relation.trim().length > 0;
	const isValidDob = !isNaN(Date.parse(attendant.dob));
	const isValidId = typeof attendant.adhaarNumber === 'string' && parseInt(attendant.adhaarNumber) > 0;
	return isValidUsername && isValidDob && isValidId && isValidGender && isValidRelation;
}
