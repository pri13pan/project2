/**
 * HomeController
 */

app.controller('HomeController',function($scope,$rootScope,BlogPostService){
	function getApprovalStatus(){
		
		BlogPostService.getApprovalStatus().then(function(response){
			$rootScope.approvalStatus=response.data
			console.log($rootScope.approvalStatus.length)
		
		
	},function(response){
		console.log(response.status)
	})
	
	}
		
	getApprovalStatus();
})