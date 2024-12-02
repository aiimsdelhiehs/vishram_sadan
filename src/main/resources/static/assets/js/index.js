var app = angular.module('VishramSadanApp', []);
app.controller('VishramSadanController', function($scope,$http) {
$scope.apiCalling = true;
		$http({
			 url:'/vishram-sadan/common/room-availability-by-category',
			 method:'GET'
		 })
		 .then(function(response){
			 console.log(response);
			 const data = response.data;
                        // Process data for Chart.js
                        const { categoryNames, sadanNames, availableData, notAvailableData } = processDataForChart(data);
                        
                        // Configure and create the Chart
                        const ctx = document.getElementById('chart-container').getContext('2d');
                        const chartConfig = {
                            type: 'bar',
                            data: {
                                labels: sadanNames,
                                datasets: [
                                    ...categoryNames.map((catName, index) => ({
                                        label: `${catName} Available`,
                                        data: availableData[index],
                                        backgroundColor: 'rgba(75, 192, 192, 0.5)',
                                        borderColor: 'rgba(75, 192, 192, 1)',
                                        borderWidth: 1
                                    })),
                                    ...categoryNames.map((catName, index) => ({
                                        label: `${catName} Not Available`,
                                        data: notAvailableData[index],
                                        backgroundColor: 'rgba(255, 99, 132, 0.5)',
                                        borderColor: 'rgba(255, 99, 132, 1)',
                                        borderWidth: 1
                                    }))
                                ]
                            },
                            options: {
                                responsive: true,
                                scales: {
                                    x: {
                                        stacked: true
                                    },
                                    y: {
                                        beginAtZero: true,
                                        stacked: true
                                    }
                                }
                            }
                        };

                        // Render the chart
                        new Chart(ctx, chartConfig);

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