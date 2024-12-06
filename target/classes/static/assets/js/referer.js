var app = angular.module('myApp', []);

// Directive to allow only alphabets
app.directive('alphabetsOnly', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attr, ngModelCtrl) {
      function fromUser(text) {
        var transformedInput = text.replace(/[^a-zA-Z\s]/g, ''); // Allow only alphabets and spaces
        if (transformedInput !== text) {
          ngModelCtrl.$setViewValue(transformedInput);
          ngModelCtrl.$render();
        }
        return transformedInput;
      }
      ngModelCtrl.$parsers.push(fromUser);
    }
  };
});



// ScheduleBookingController to handle booking logic
app.controller('ScheduleBookingController', function($scope,$http) {
var contextPath='http://localhost:8080/vishram-sadan';
       $scope.categoryList = [];
    $scope.vishramSadanList = [];
      $scope.filteredBookingRequests = [];
  $scope.filters = {};
  $scope.sortKey = 'requestedOn';
  $scope.reverse = true;
   $scope.cameraOpen = false;
    $scope.capturedImage = false;
    var stream;
    

    $scope.getFormattedDate = function(dateString){
		if(dateString !=undefined && dateString != '' && dateString !=null){
		   return dateString.split('T')[1].substring(0,5)+' '+dateString.split('T')[0];
		}
	} 
	
	    
    $scope.allBookingRequest = [];
    
          $http({
            url: contextPath + '/referrer/get-all-booking-request',
            method: 'GET'
        })
        .then(function(response) {
            console.log(response);
            $scope.allBookingRequest = response.data;
            setTimeout(function(){
				new DataTable('#example', {
					 ordering: false,
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
            // Handle error
            console.log(error);
        });


  /*  $scope.filterByStatus = function(status) {
    $scope.closeData = $scope.allBookingRequest.filter(function(item) {
        return item.status === status;
    });
    $scope.filteredData=  $scope.closeData;
    console.log('Filtered Data:', $scope.closeData);
};
   */

        $scope.filters = {
            requestId: '',
            uhid: '',
            name:'',
            contact: '',
            address: '',
            requestedOn: '',
            status: ''
        };

        // Function to apply filters to the data
     
               $scope.applyFilter = function() {
            $scope.filteredData = $scope.allBookingRequest.filter(function(item) {
                return Object.keys($scope.filters).every(function(key) {
                    if (key === 'name') {
                        return String(item.patient.fullname).toLowerCase().includes(String($scope.filters[key]).toLowerCase());
                    } else if (key === 'uhid') {
                        return String(item.patient.uhid).toLowerCase().includes(String($scope.filters[key]).toLowerCase());
                    } else if (key === 'contact') {
                        return String(item.patient.contactNo).toLowerCase().includes(String($scope.filters[key]).toLowerCase());
                    } else if (key === 'address') {
                        return String(item.patient.address).toLowerCase().includes(String($scope.filters[key]).toLowerCase());
                    } else {
                        return String(item[key]).toLowerCase().includes(String($scope.filters[key]).toLowerCase());
                    }
                });
            });
        };		
									
 
    $scope.newBookingRequest = {
        patient: {},
       totalAttendants:0,
        preferences: [],
       sadans: []
    };

    // Fetch category data from backend API
                               $http({
									  url: contextPath + '/common/get-master-data',
									  method: 'GET'
									})
									.then(function(response) {
									  // Successfully received the response
									  console.log(response);
									
									  // Populate the scope variables
									  $scope.vishramSadanList = response.data.vishramSadanList;
									  $scope.categoryList = response.data.categoryList;
									
									  // Add categories to preferences
									  for (var i = 0; i < $scope.categoryList.length; i++) {
									    $scope.newBookingRequest.preferences.push($scope.categoryList[i]);
									  }
									
									  // Add vishram sadans to vishramSadan
									  for (var j = 0; j < $scope.vishramSadanList.length; j++) {
									    $scope.newBookingRequest.sadans.push($scope.vishramSadanList[j]); // Ensure this key exists in newBookingRequest
									  }
									
									  // Log the updated newBookingRequest
									  console.log($scope.newBookingRequest);
									
									}, function(error) {
									  // Handle error
									  console.log(error);
									  $scope.ticketDepartmentList = [];
									})
									.finally(function() {
									  // Mark API call as finished
									  $scope.apiCalling = false;
									});
	
	
	
 $scope.fetchData = function() {
                    if ($scope.newBookingRequest.patient.uhid != undefined && ($scope.newBookingRequest.patient.uhid+'').length==9) {
                          $http({
						    url:contextPath+'/referrer/fetchdemographicdata/'+$scope.newBookingRequest.patient.uhid,
						    method:'GET',
						 
					}).then(function(response){
						  if (response.data) {
							  console.log(response.data);
                                    $scope.newBookingRequest.patient = response.data;
                                    $scope.errorMessage = '';
                                } else {
                                    $scope.errorMessage = 'No data found for the given UHID.';
                                }
                            }, function(error) {
                                $scope.errorMessage = 'Error fetching data.';
                            });
                    }
                };
  
  $scope.makeBookingRequest= function(){
	  $("#book-msg").html("");
  var nameRegex = /^[a-zA-Z\s]+$/;
    var uhidRegex = /^\d{9}$/; // Assumes UHID is a 9-digit number
    var contactNoRegex = /^\d{10}$/; // Assumes contact number is a 10-digit number
      console.log($scope.newBookingRequest);
    // Validation logic
    if (!$scope.newBookingRequest.patient.fullname|| !nameRegex.test($scope.newBookingRequest.patient.fullname)) {
      alert("Please enter a valid patient name (only alphabets allowed)");
      document.getElementById('fullname').focus();
      return;
    }
    else if (!$scope.newBookingRequest.patient.uhid || !uhidRegex.test($scope.newBookingRequest.patient.uhid)) {
      alert("Please enter a valid UHID (9-digit number)");
      document.getElementById('uhid').focus();
      return;
    }
    else if (!$scope.newBookingRequest.patient.contactNo || !contactNoRegex.test($scope.newBookingRequest.patient.contactNo)) {
      alert("Please enter a valid contact number (10-digit number)");
      document.getElementById('contactNo').focus();
      return;
    }
    else if (!$scope.newBookingRequest.patient.address) {
     $("#book-msg").html("Please enter a valid address");
      document.getElementById('address').focus();
      return;
    }
    else if ($scope.newBookingRequest.sadans.length === 0) {
      alert("Please select at least one vishram sadan");
      return;
    }
    else {
      // Prepare empty attendants array
      var attendants
       = [];
      for (var i = 0; i < $scope.newBookingRequest.totalAttendants; i++) {
        attendants.push({});
      }
      $scope.newBookingRequest.attendants = attendants;
    				$scope.apiCalling = true;
				
			        $http({
						    url:contextPath+'/referrer/add-booking-request',
						    method:'POST',
						    data:$scope.newBookingRequest
					}).then(function(response){
						  console.log(response);
						    Swal.fire({
							  title: "Booking Request  ",
							  html: `Your booking request has been successfully <b class='text-success'>RESET</b>,<br/>`,
							  icon: "success"
							})
							.then(function(){
								window.location.reload();
							});
					},function(error){
							  	console.log(error);   
							  	$("#book-msg").html(error.data.message);
							  	
							});
		}
   };
  
  
  
  
       $scope.isCategoryExist = function(category) {
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
  $scope.addRemoveCategory = function(category,ind) {
    var index = -1;
    console.log($scope.newBookingRequest.preferences);
    for (var i = 0; i < $scope.newBookingRequest.preferences.length; i++) {
      if ($scope.newBookingRequest.preferences[i].id === category.id) {
        index = i;
        break;
      }
    }
    if (index !== -1) {
	  $scope.categoryList[ind].checked = false;
      $scope.newBookingRequest.preferences.splice(index, 1);
    } else {
	  $scope.categoryList[ind].checked = true;
      $scope.newBookingRequest.preferences.push(category);
    }
    console.log($scope.newBookingRequest.preferences);
  };

  // Check if vishramSadan exists in sadans
  $scope.isVishramSadanExist = function(sadan) {
    var exists = false;
    for (var i = 0; i < $scope.newBookingRequest.sadans.length; i++) {
      if ($scope.newBookingRequest.sadans[i].id === sadan.id) {
        exists = true;
        break;
      }
    }
    return exists;
  };

  // Add or remove vishramSadan from sadans
  $scope.addRemoveVishramSadan = function(sadan,ind) {
    var index = -1;
    for (var i = 0; i < $scope.newBookingRequest.sadans.length; i++) {
      if ($scope.newBookingRequest.sadans[i].id === sadan.id) {
        index = i;
        break;
      }
    }
    if (index !== -1) {
		$scope.vishramSadanList[ind].checked = false;
      $scope.newBookingRequest.sadans.splice(index, 1);
    } else {
		$scope.vishramSadanList[ind].checked = true;
      $scope.newBookingRequest.sadans.push(sadan);
    }
    console.log($scope.newBookingRequest.sadans);
  };
  
  
  
 $scope.openCamera = function() {
	 $("#image-msg").html("");
	  
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
        document.getElementById('captured').src = imageData
        $scope.base64Image = imageData; 
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
         $("#image-msg").html("");
    };
    

    $scope.submitImage = function() {
       
        
        // Use the dummy API function instead of $http.post
                     $http({
						    url:contextPath+'/referrer/add-image',
						    method:'POST',
						    data:$scope.base64Image
					}).then(function(response){
						  console.log(response);
						   $scope.newBookingRequest.patientImage = $scope.base64Image;
						   $("#image-msg").html(response.data.message);
						   $("#imageModal").modal("hide");
						    $scope.closeCamera();
						   $("#bookingRequestModal").modal("show");
					},function(error){
							  	console.log(error);   
							  	$("#image-msg").html(error.data.message);
							  	
					});
    }
  
  $scope.addPriority = function(requestId){
	  let priority = prompt("Enter Required Priority Number For Booking Request Id: "+requestId);
	  if(!(/^[1-9]\d*$/.test(priority))){
		 alert("Please enter valid priority number"); 
		 return false;
	  }
	  else if(parseInt(priority) >= $scope.allBookingRequest.pendingBooking.length){
	     alert("Requested priority number:"+priority+" can't be change updated as waiting list have total requests "+$scope.filteredData.length); 
		 return false;
	  }
	  else if(confirm("Are you sure to update priority as "+priority+" for request id "+requestId)==true){
            $http({
			    url:contextPath+'/referrer/update-priority/'+requestId+'/'+priority,
			    method:'POST'
			}).then(function(response){
				  console.log(response);
				  alert("Priority updated successfully");
				  
			},function(error){
			      console.log(error);   
			  	  alert("Error: "+error.data.message);
			});
	  }
  }
});