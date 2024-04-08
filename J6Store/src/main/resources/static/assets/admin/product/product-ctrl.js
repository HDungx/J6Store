app.controller("product-ctrl",function($scope,$http){
	$scope.items=[];
	$scope.cates=[];
	$scope.form={};

	$scope.initialize=function(){
		//load Products
		$http.get("/rest/products").then(resp=>{
			$scope.items=resp.data;
			$scope.items.forEach(item=>{
				item.createDate=new Date(item.createDate)
			})
		});
		//load categories
		$http.get("/rest/categories").then(resp=>{
			$scope.cates=resp.data;
			
		});
	}

	//Khoi dau
	$scope.initialize();

	//xoa form
	$scope.reset=function(){
		$scope.form={
			createDate:new Date(),
			image:'cloud-upload.jpg',
			availabel:true,

		}
	}

	//hien thi len form
	$scope.edit=function(item){
		$scope.form=angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
		
	}

	//Them san pham
	$scope.create=function(){
		var item=angular.copy($scope.form);
		$http.post('/rest/products',item).then(resp=>{
			resp.data.createDate=new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset
			alert("Thêm thành công!")
		}).catch(error=>{
			alert("Lỗi thêm mới")
			console.log("Error",error);
		})
	}
	//xoa san phan
	$scope.delete=function(item){
		$http.delete('/rest/products/${item.id}').then(resp=>{
			var index=$scope.items.findIndex(p=>p.id==item.id);
			$scope.items.splice(index,1);
			$scope.reset();
			alert("Xóa thành công!")
		}).catch(error=>{
			alert("Lỗi xóa")
			console.log("Error",error);
		})
	}
	//update
	$scope.update=function(){
		var item=angular.copy($scope.form);
		$http.put('/rest/products/${item.id}',item).then(resp=>{
			var index=$scope.items.findIndex(p=>p.id==item.id);
			$scope.items[index]=item;
			alert("Cập nhật thành công!")
		}).catch(error=>{
			alert("Lỗi cập nhật")
			console.log("Error",error);
		})
	}
	
	$scope.imageChanged=function(files){
		var data= new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/images',data,{
			transformRequest:angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp=>{
			$scope.form.image=resp.data.name;
		}).catch(error=>{
			alert("Lỗi upload hình ảnh");
			console.log("Error",error);
		})
	}
});