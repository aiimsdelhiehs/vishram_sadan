var app = angular.module('VishramSadanApp', []);
app.controller('VishramSadanController', function($scope,$http) {
$scope.apiCalling = true;
		$http({
			 url:'/vishram-sadan/common/room-availability-by-category',
			 method:'GET'
		 })
		 .then(function(response){
			 console.log(response);
			 const ctx = document.getElementById('myChart').getContext('2d');
        
        var allAvailableData = new Map();
        var backgroundColors = ['rgba(255, 99, 132, 0.5)','rgba(54, 162, 235, 0.5)'];
        var borderColors = ['rgba(255, 99, 132, 1)','rgba(54, 162, 235, 1)'];
        var allLabels = [];
        for(var i=0;i<response.data.length; i++){
			if(!allAvailableData.has(response.data[i].sadanName)){
				var availableData = new Map();
				for(var j=0; j<response.data[i].categories.length; j++){
					if(!availableData.has(response.data[i].categories[j].categoryName)){ 
						availableData.set(response.data[i].categories[j].categoryName,response.data[i].categories[j].available);
					}
				}
				allAvailableData.set(response.data[i].sadanName,availableData);
			}
		}
		//console.log(sadans);
		console.log(allAvailableData);
		console.log(Array.from(allAvailableData.keys()));
		var dataSetArr = [];
		var index=0;
		for (const [sadan, availableData] of allAvailableData.entries()) {
		     var dataArr = [];
		     for (const [categoryName, data] of availableData.entries()) { 
				  if(!allLabels.includes(categoryName)){
					  allLabels.push(categoryName);
				  }
				  dataArr.push(data);
			 }
			 dataSetArr.push({
                    label: sadan,
                    data: dataArr, // Dataset 1 values
                    backgroundColor: backgroundColors[index], // Bar color
                    borderColor: borderColors[index],
                    borderWidth: 1
              });
             index++;
		}
        // Sample data
        const data = {
            labels: allLabels, // Your labels
            datasets: dataSetArr
        };

        // Chart configuration
        const config = {
            type: 'bar',
            data: data,
            options: {
                indexAxis: 'x', // Horizontal bars
                scales: {
                    x: {
                        beginAtZero: true // Start X-axis at zero
                    }
                },
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top' // Legend position
                    },
                    title: {
                        display: true,
                        text: 'Horizontal Bar Chart with Two Bars per Label'
                    }
                }
            }
        };

        // Render the chart
        new Chart(ctx, config);

			 },function(error){
			 console.log(error);
			 alert("Error "+error.data.message);
		 })
		 .finally(function(){
			 $scope.apiCalling = false;
		 });
		 
		 function processDataForChart(data) {
                const categoryNames = [...new Set(data.flatMap(sadan => sadan.categories.map(cat => cat.categoryName)))];
                const sadanNames = data.map(sadan => sadan.sadanName);
                
                const availableData = categoryNames.map(catName => data.map(sadan => sadan.categories.find(cat => cat.categoryName === catName)?.available || 0) );

                const notAvailableData = categoryNames.map(catName =>
                    data.map(sadan => sadan.categories.find(cat => cat.categoryName === catName)?.notAvailable || 0)
                );

                return { categoryNames, sadanNames, availableData, notAvailableData };
            }
		 });