var app = angular.module('myApp', []);
app.controller('IndexController', function($scope,$http) { 
	$scope.login = {};
	$scope.newTicket = {};
	$scope.apiCalling = false;
	$scope.newTicketOTPSent = false;
	$scope.newTicketOTPVerify = false;
	$scope.ticketDepartmentList = [];
	$scope.ticketCategoryList = [];
	$scope.myTickets = [];
	$scope.currentUser=null;
	$scope.ticketStatus = {"searchBy":"Ref. No."};
	$scope.ticketStatusOTPSent = false;
	$scope.forgotPassword = false;
	$scope.forgotPasswordOTPSent = false;
	
	$scope.ticketOTPStatus = "";
	$scope.ticketStatusOTP = "";
	$scope.resetOTPStatus = "";
	
	$scope.generateCaptcha = function(htmlId) {
        var characters = '123456';
        var captcha = '';
        for (var i = 0; i < 1; i++) {
            captcha += characters.charAt(Math.floor(Math.random() * characters.length));
        }
        $('#'+htmlId).text(captcha);
    }
    $scope.validateInput = function(event) {
            var regex = /^[a-zA-Z0-9]*$/;
            var key = String.fromCharCode(event.which);
            if (!regex.test(key)) {
                event.preventDefault();
            }
    };
    $scope.validateDigits = function(event) {
            var regex = /^[0-9]*$/;
            var key = String.fromCharCode(event.which);
            if (!regex.test(key)) {
                event.preventDefault();
            }
    };
    $scope.setPasswordResetFlag = function(status){
		$scope.forgotPassword = status;
		$scope.login = {};
		$("#login-err").html("");
	}
	$scope.getPasswordResetOTP = function(){
		$("#login-err").html("");
		$(".required-login-fileds").each(function(index){
			if($(this).val()=="" || $(this).val() == undefined){
			   $(this).focus();
			   $("#login-err").html("Please enter all required fields");
			   return false;
			}
			else if(index == $(".required-login-fileds").length-1){
			     if($("#loginSecurityCode").val() != $("#loginCaptchaCode").text()){
					   $("#login-err").html("Please filed valid security code");
					   $("#loginSecurityCode").focus();
					   $scope.generateCaptcha('loginCaptchaCode');
					   $("#loginSecurityCode").val("");
					   return false;
				}
				else{
					$scope.apiCalling = true;
					$scope.resetOTPStatus = "";
					$http({
						url:'public/get-password-reset-otp/'+$scope.login.username,
						method:'POST'
					})
					.then(function(response){
						  console.log(response);
						  $scope.forgotPasswordOTPSent  = response.data.success;
						  $scope.resetOTPStatus = response.data.message;
					},function(error){
						console.log(error);
						$("#login-err").html(error.data.message);
					})
					.finally(function(){
						$scope.apiCalling = false;
						$scope.generateCaptcha('loginCaptchaCode');
						$("#loginSecurityCode").val("");
					}); 
				}
			}
		});
	}
	$scope.resetPasswordByOTP = function(){
		$("#login-err").html("");
		$(".required-login-fileds").each(function(index){
			if($(this).val()=="" || $(this).val() == undefined){
			   $(this).focus();
			   $("#login-err").html("Please enter all required fields");
			   return false;
			}
			else if(index == $(".required-login-fileds").length-1){
			     if($scope.login.password != $scope.login.confirm_password){
					   $("#login-err").html("Password didn't matched");
					   $("#confirm_password").focus();
					   return false;
				 }
				 else if($("#loginSecurityCode").val() != $("#loginCaptchaCode").text()){
					   $("#login-err").html("Please filed valid security code");
					   $("#loginSecurityCode").focus();
					   $scope.generateCaptcha('loginCaptchaCode');
					   $("#loginSecurityCode").val("");
					   return false;
				 } 
				 else{
					$scope.apiCalling = true;
					$http({
						url:'public/reset-password-by-otp',
						method:'POST',
						data:{"contactNo":$scope.login.username,"newPassword":$scope.login.password,"otp":$scope.login.otp}
					})
					.then(function(response){
						  console.log(response);
						    Swal.fire({
							  title: "Password Reset Successfully",
							  html: `Your password has been successfully <b class='text-success'>RESET</b>,<br/>You may try to login with new password`,
							  icon: "success"
							})
							.then(function(){
								window.location.reload();
							});
					},function(error){
						console.log(error);
						$("#login-err").html(error.data.message);
						$scope.generateCaptcha('loginCaptchaCode');
					    $("#loginSecurityCode").val("");
					})
					.finally(function(){
						$scope.apiCalling = false;
					}); 
				}
			}
		});
	}
      $scope.tryLogin = function(){
		$("#login-err").html("");
		$(".required-login-fileds").each(function(index){
			if($(this).val() == undefined || $(this).val() == ""){
			   $("#login-err").html("Please filed all required fileds");
			   $(this).focus();
			   return false;
			}
			else if(index == $(".required-login-fileds").length-1){
				if($("#login-username").val().length != 10){
					   $("#login-err").html("Please filed valid user id");
					   $("#login-username").focus();
					   return false;
				}
				else if($("#loginSecurityCode").val() != $("#loginCaptchaCode").text()){
					   $("#login-err").html("Please filed valid security code");
					   $("#loginSecurityCode").focus();
					   $scope.generateCaptcha('loginCaptchaCode');
					   $("#loginSecurityCode").val("");
					   return false;
				}
				else{
					$("#loginForm").submit();
				}
			}
		});	
	}
	$scope.getOTPToRaiseTicket = function(){
		$("#new-ticket-err").html("");
		$(".requiredNewTicketFiled").each(function(index){
			if($(this).val()=="" || $(this).val() == undefined){
			   $("#new-ticket-err").html("Please enter all required fields");
			   $(this).focus();
			   return false;
			}
			else if(index == $(".requiredNewTicketFiled").length-1){
				if($("#newTicketEmpId").val().length!=8){
				   $("#new-ticket-err").html("Please enter valid employee id");
				   $("#newTicketEmpId").focus();
				   return false;
				}
				else if($("#newTicketContactNo").val().length!=10){
				   $("#new-ticket-err").html("Please enter valid contact number");
				   $("#newTicketContactNo").focus();
				   return false;
				}
				else if($("#newTicketSecretCodeValue").val() != $("#newTicketCaptchaCode").text()){
				   $("#new-ticket-err").html("Please enter valid security code");
				   $scope.generateCaptcha('newTicketCaptchaCode');
				   $("#newTicketSecretCodeValue").focus();
				   return false;
				}
				else{
				   $scope.apiCalling = true;
				   $scope.ticketOTPStatus = "";
					$http({
						url:'public/check-employee-details',
						method:'POST',
						data:$scope.newTicket
					})
					.then(function(response){
						  console.log(response);
						  $scope.newTicketOTPSent = response.data.success;
						  $scope.ticketOTPStatus = response.data.message;
					},function(error){
						console.log(error);
						$("#new-ticket-err").html(error.data.message);
					})
					.finally(function(){
						$scope.apiCalling = false;
						$scope.generateCaptcha('newTicketCaptchaCode');
						$("#newTicketSecretCodeValue").val("");
					}); 
				}
			}
		});
	}
	$scope.verifyOTP = function(){
		$("#new-ticket-err").html("");
		$scope.newTicket.otp = parseInt($scope.newTicket.otp);
		if($("#newTicketEmpId").val().length!=8){
		   $("#new-ticket-err").html("Please enter valid employee id");
		   $("#newTicketEmpId").focus();
		   return false;
		}
		else if($("#newTicketContactNo").val().length!=10){
		   $("#new-ticket-err").html("Please enter valid contact number");
		   $("#newTicketContactNo").focus();
		   return false;
		}
		else if($("#newTicketOTP").val() == "" || $("#newTicketOTP").val() == undefined){
		   $("#new-ticket-err").html("Please enter OTP");
		   $("#newTicketOTP").focus();
		   return false;
		}
		else if($("#newTicketSecretCodeValue").val() != $("#newTicketCaptchaCode").text()){
		   $("#new-ticket-err").html("Please enter valid security code");
		   $scope.generateCaptcha('newTicketCaptchaCode');
		   $("#newTicketSecretCodeValue").focus();
		   return false;
		}
		else{
		        $scope.apiCalling = true;
		        $scope.newTicketOTPVerify = false;
				$http({
					url:'public/verify-otp',
					method:'POST',
					data:$scope.newTicket
				})
				.then(function(response){
					  console.log(response);
					  if(response.data != "" && response.data != undefined){
						  $scope.newTicketOTPVerify = true;
						  $scope.verfiedUser = response.data;
						  $scope.newTicket.otp = undefined;
						  $scope.newTicket.employeeId = response.data.employeeId;
						  $scope.newTicket.contactNo = response.data.contactNo;
						  $scope.apiCalling = true;
						   $http({
								url:'public/get-department-list',
								method:'GET'
						   })
						   .then(function(response){ 
								console.log(response);
								$scope.ticketDepartmentList = response.data;
						   },function(error){
								console.log(error);
								$scope.ticketDepartmentList = [];
						   })
						   .finally(function(){
								$scope.apiCalling = false;
						   });
						}
				},function(error){
					console.log(error);
					$("#new-ticket-err").html(error.data.message);
				})
				.finally(function(){
					$scope.apiCalling = false;
					$scope.generateCaptcha('newTicketCaptchaCode');
					$("#newTicketSecretCodeValue").val("");
				}); 
		}
	}
	$scope.raiseNewTicket = function(){
		$("#new-ticket-err").html("");
		$(".newTicketReqFileds").each(function(index){
			if($(this).val()==""||$(this).val()==undefined){
			   $(this).focus();
			   $("#new-ticket-err").html("Please enter all required fields");
			   return false;	
			}
			else if(index == $(".newTicketReqFileds").length -1){
				if($("#newTicketSecretCodeValue").val() != $("#newTicketCaptchaCode").text()){
				   $("#new-ticket-err").html("Please enter valid security code");
				   $scope.generateCaptcha('newTicketCaptchaCode');
				   $("#newTicketSecretCodeValue").focus();
				   return false;
				}
				else{
					     $scope.readFile("ticketImage1").then(function(image1){
						 $scope.newTicket.image1 = image1;
						 $scope.readFile("ticketImage2").then(function(image2){ 
							    $scope.newTicket.image2 = image2;
						        $scope.newTicket.departmentId = $scope.newTicket.department.id;
								$scope.newTicket.categoryId = $scope.newTicket.category.id;
								console.log($scope.newTicket);
								Swal.fire({
								  title: "Please Confirm",
								  text: "New ticket to `"+$scope.newTicket.department.name+'` for `'+$scope.newTicket.category.name+'`. Your Concern is: '+$scope.newTicket.concern,
								  icon: "warning",
								  showCancelButton: true,
								  confirmButtonColor: "#3085d6",
								  cancelButtonColor: "#d33",
								  confirmButtonText: "Yes, Confirm!"
								}).then((result) => {
								  if (result.isConfirmed) {
								        $scope.apiCalling = true;
									    $http({
											url:'public/add-grievance',
											method:'POST',
											data:$scope.newTicket
									    })
									    .then(function(response){ 
											Swal.fire({
											  title: "Ticket Raised Successfully",
											  html: `Please note your ticket reference no.<br/><b class='text-success'>`+response.data.refNo+`</b>,`,
											  icon: "success"
											})
											.then(function(){
												window.location.reload();
											});
									    },function(error){
											console.log(error);
											alert("Error "+error.data.message);
									    })
									    .finally(function(){
											$scope.apiCalling = false;
									    });
								  }
								});
						 });
					});
				}
			 }
		});
	}
	$scope.getOTPToCheckStatus = function(){
		$("#ticket-status-err").html("");
		$(".ticketStatusReqFileds").each(function(index){
			if($(this).val()=="" || $(this).val()==undefined){
			   $(this).focus();
			   $("#ticket-status-err").html("Please enter all required fields");
			   return false;
			}else if(index == $(".ticketStatusReqFileds").length-1 ){
				if($("#ticketStatusCaptchaValue").val() != $("#ticketStatusCaptchaCode").text()){
					 $("#ticketStatusCaptchaValue").focus();
				     $("#ticket-status-err").html("Please enter valid security code");
				     $scope.generateCaptcha('ticketStatusCaptchaCode');
				     return false;
				}
				else{
					$scope.apiCalling = true;
					$http({
						url:'public/send-otp-for-ticket',
						method:'POST',
						data:$scope.ticketStatus
				    })
				    .then(function(response){ 
						console.log(response);
						$scope.ticketStatusOTPSent = response.data.success;
						$scope.ticketStatusOTP = response.data.message;
					},function(error){
						console.log(error);
						$("#ticket-status-err").html(error.data.message);
					})
					.finally(function(){
						$scope.apiCalling = false;
						$scope.generateCaptcha('ticketStatusCaptchaCode');
						$("#ticketStatusCaptchaValue").val("");
					 });
				}
			}
		});
	}
	$scope.checkStatus = function(){
		$("#ticket-status-err").html("");
		$(".ticketStatusReqFileds").each(function(index){
			if($(this).val()=="" || $(this).val()==undefined){
			   $(this).focus();
			   $("#ticket-status-err").html("Please enter all required fields");
			   return false;
			}else if(index == $(".ticketStatusReqFileds").length-1 ){
				if($("#ticketStatusCaptchaValue").val() != $("#ticketStatusCaptchaCode").text()){
					 $("#ticketStatusCaptchaValue").focus();
				     $("#ticket-status-err").html("Please enter valid security code");
				     $scope.generateCaptcha('ticketStatusCaptchaCode');
				     return false;
				}
				else{
					$scope.ticketStatus.otp = parseInt($scope.ticketStatus.otp);
					$scope.apiCalling = true;
					$http({
						url:'public/check-ticket-status',
						method:'POST',
						data:$scope.ticketStatus
				    })
				    .then(function(response){ 
						console.log(response);
						$scope.myTickets = response.data;
						$scope.currentUser= response.data[0].userRaisedBy;
						setTimeout(function(){
							$("#myTickets").DataTable();
						},100);					
					},function(error){
						console.log(error);
						$("#ticket-status-err").html(error.data.message);
						$scope.myTickets = [];
						$scope.currentUser=null;
					})
					.finally(function(){
						$scope.apiCalling = false;
						$scope.generateCaptcha('ticketStatusCaptchaCode');
						$("#ticketStatusCaptchaValue").val("");
					 });
				}
			}
		});
	}
	$scope.addRemarks = function(refNo){
		if($("#remarksInput").val() =='' || $("#remarksInput").val() == undefined || $("#remarksInput").val().length > 250){
		   alert("Please add some valid remarks upto 50 characters");
		   return false;
		}
		else{
			Swal.fire({
					  title: "Are you sure?",
					  html: "You are adding remarks to ticket no:`"+refNo+'` as following <br/>`'+$("#remarksInput").val(),
					  icon: "warning",
					  showCancelButton: true,
					  confirmButtonColor: "#3085d6",
					  cancelButtonColor: "#d33",
					  confirmButtonText: "Yes, Confirm!"
					}).then((result) => {
					  if (result.isConfirmed) { 
						    $scope.apiCalling = true;
							$http({
								url:'public/add-remarks',
								method:'POST',
								data:{"grievanceRefNo":refNo,"remarks":$("#remarksInput").val(),"otp":$scope.ticketStatus.otp}
						    })
						    .then(function(response){ 
								console.log(response);
								Swal.fire({
								  title: "Response recorded",
								  html: `Your response recorded successfully for ticket no:<br/><b class='text-success'>`+refNo+`</b>,`,
								  icon: "success"
								})
								.then(function(){
									window.location.reload();
								});				
							},function(error){
								console.log(error);
								alert("Error "+error.data.message);
							})
							.finally(function(){
								$scope.apiCalling = false;
							 });
					  }
					});
		}
	}
	$scope.resetTicketStatusForm = function(){
		$scope.myTickets = [];
		$scope.currentUser=null;
		$scope.ticketStatus = {"searchBy":"Ref. No."};
		$scope.generateCaptcha('ticketStatusCaptchaCode');
	    $("#ticketStatusCaptchaValue").val("");
	    $scope.ticketStatusOTPSent = false;
		$scope.ticketStatusOTP = "";
	}
	$scope.staffRemarks = function(remarksData){
		if(remarksData != undefined && remarksData.fromUser != undefined && remarksData.fromUser.roles != undefined && remarksData.fromUser.roles.length > 0){
		   if(remarksData.fromUser.roles.some(role => (role.name == 'ROLE_HELPDESK' || role.name == 'ROLE_STAFF' || role.name == 'ROLE_ADMIN'))){
			  return true;
		   }
		    else
		   	  return false;
 		}
 		else
 		   return false;
	}
	$scope.viewTicket = function(ticket){
		  $scope.ticketView = ticket;
		  $("#viewTicketModal").modal("show");
	}
	
	$scope.readFile = function(fileInputId){
		return new Promise((resolve, reject) => {
			  const fileInput = document.getElementById(fileInputId);
			  const file = fileInput.files[0];
			  if (file) {
				    // Check file type (only jpg, jpeg, png)
				    const validTypes = ['image/jpeg', 'image/png'];
				    if (!validTypes.includes(file.type)) {
				         alert('Invalid file type. Only JPG, JPEG, and PNG are allowed.');
				         $("#"+fileInputId).focus();
				         reject(new Error('No file selected.'));
				         return;
				    }
				    //check allowed file size
				    if (file.size > 500 * 1024) {
				        alert('File is too large. Max size is 500KB.');
				        $("#"+fileInputId).focus();
				        reject(new Error('No file selected.'));
				        return;
				    }
				    const reader = new FileReader();
				    reader.onload = function(event) {
				       const base64String = event.target.result.split(',')[1];
				       resolve(base64String);
				    };
				    reader.onerror = function() {
					  alert('Getting erro while reading file');
				      $("#"+fileInputId).focus();
				      reject(new Error('Error reading file.'));
				    };
				    reader.readAsDataURL(file);
		    }else{
				resolve(null);
				return;
			}
		});
	}
    $scope.generateCaptcha('newTicketCaptchaCode');
    $scope.generateCaptcha('ticketStatusCaptchaCode');
    $scope.generateCaptcha('loginCaptchaCode');
});

