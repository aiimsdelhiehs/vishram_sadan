var app = angular.module('myApp', []);
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
})
 app.controller('updatePasswordCtrl', function($scope,$http) { 
	var contactNo = /^\d{10}$/;
	$scope.user={};
	$scope.base64URLEncode = function(arrayBuffer) {
			    return btoa(String.fromCharCode.apply(null, arrayBuffer))
			        .replace(/\+/g, '-')
			        .replace(/\//g, '_')
			        .replace(/=/g, '');
    }
    
    $scope.saveDetails = function(){
		$("#cherr-msg").html("");
		var contextPath='http://localhost:8080/vishram-sadan';
			
		 if( ($("#currentPassword").val() == "" || $("#currentPassword").val() == undefined) && $("#passwordNotExist").val() != "yes"){
			
			 
			$("#cherr-msg").html("Please enter current password");
		    $("#currentPassword").focus();
		    return false;
		    
		}
	
		 
		else if($("#newPassword").val() == "" || $("#newPassword").val() == undefined || $("#newPassword").val().length < 6){
			$("#cherr-msg").html("Please enter password of minimum length 6");
		    $("#newPassword").focus();
		    return false;
		}
		else if($("#newPassword").val() != $("#confirmPassword").val()){
			$("#cherr-msg").html("Please didn't matched.");
		    $("#confirmPassword").focus();
		    return false;
		}
		else{
					$scope.apiCalling = true;
				
			        $http({
						    url:contextPath+'/common/update-password',
						    method:'POST',
						    data:$scope.user
					}).then(function(response){
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
							  	$("#cherr-msg").html(error.data.message);
							  	$("#currentPassword").val("");
							  	$("#newPassword").val("");
							  	$("#confirmPassword").val("");
							});
		}
		}				
						
 });