angular.module("app").controller("PostController", PostController);

PostController.$inject = ["blogManager", "$routeParams", "$location", "LxNotificationService"];

function PostController(blogManager, $routeParams, $location, LxNotificationService) {

	var vm = this;
	
	//View model properties
	
	vm.post = {};
		
	//Controller logic
	
	vm.post = blogManager.getPost($routeParams.id);

	//Controller actions
	
	vm.deletePost = function(post) {
		
		LxNotificationService.confirm('Delete confirmation',
				'Are you sure you want to delete this post?', 
				{ cancel:'Cancel', ok:'Delete' }, 
				function(answer)
		        {
		            if(answer === true){
		            	blogManager.deletePost(post);
		        		$location.path("/");		            	
		            }					
		        });
	};
};

