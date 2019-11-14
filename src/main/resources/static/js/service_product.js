$(".user-arrow-down").on("click",function(){
    if($(".dropdown").is(":hidden")){
        $(".dropdown").show();
 }else{
       $(".dropdown").hide();
 }
})  
$(".add-product-action").on("click", function(event){
    $(".masking").show();
})
$(".save").on("click", function(event){
    $(".masking").hide();
    console.log("保存");
			//处理用户数据
            var id=$(".product_id").val();
			var serviceName=$(".service_name").val();
			var serviceContent=$(".service_content").val();
			var price=$(".service_price").val();
			console.log(id,serviceName,serviceContent,price);
			var userid=sessionStorage.getItem("id")
			var uuid=sessionStorage.getItem("uuid")
			var sn=sessionStorage.getItem("name")
			 $.ajax({
				//请求类型
				type:"post",
				//请求路径
				url:"/provider/providerregister",
				//请求参数
				data:{
					id:id,
					serviceName:serviceName,
					serviceContent:serviceContent,
					price:price,
					uuid:uuid,
					userid:userid,
					sn:sn,
				},
				//返回数据类型
				dataType:"json",
				//请求成功后调用函数
				success:function(data){
					console.log("成功后返回的数据",data);
					alert(data.msg);
					location.href="redirect?page=service_product"
				},
				error:function(data){
					console.log("失败后返回的数据",data);
				}
			}) 
})
$(".cancel").on("click", function(event){
    $(".masking").hide();
    console.log("取消");
    var uuid=sessionStorage.getItem("uuid")
    $.ajax({
		//请求类型
		type:"post",
		//请求路径
		url:"/provider/providerdelete",
		//返回数据类型
		data:{
			id:uuid,
		},
		dataType:"json",
		//请求成功后调用函数
		success:function(data){
			console.log("成功后返回的数据",data);
			location.href="redirect?page=service_product"
		},
		//请求失败后调用函数
		error:function(data){
			console.log("请求失败",data);
		}
})
})


 		$(function(){
 			$.ajax({
 				//请求类型
 				type:"get",
 				//请求路径
 				url:"/provider/getproviderinfo",
 				//返回数据类型
 				dataType:"json",
 				//请求成功后调用函数
 				success:function(data){
 					console.log("成功后返回的数据",data);
 					var providerInfo=data.providerInfo;
 					//每次清空table
 					$("#table").html("");
 					var txt="";
 					for(var i=0;i<providerInfo.length;i++){
 						if(providerInfo[i].status==1){
 						    txt +=`
 						   <tr>
 						   <td><input type="checkbox" value="${providerInfo[i].id}" class="checkbox" name="product"></td>						  
 						  <td>${providerInfo[i].serviceName}</td>
 						 <td>${providerInfo[i].serviceContent}</td>
 						<td>${providerInfo[i].price}</td>
 	                        <td><span class="up-line-mark up-line-mark-red">上线</span></td>
 	                        <td>
 	                            <span class="handle-btn"><i class="fa fa-edit fa-fw"></i>编辑</span>
 	                            <span class="handle-btn" onclick="dl('${providerInfo[i].id}')"><i class="fa fa-close fa-fw"></i>删除</span>
 	                            <span class="handle-btn" onclick="down('${providerInfo[i].id}')"><i class="fa fa-arrow-up fa-fw"></i>下线</span>
 	                        </td>
 	                    </tr>`
 						}else{
 							txt +=`
 	 						   <tr>
 	 						   <td><input type="checkbox" value="${providerInfo[i].id}" class="checkbox"></td>						  
 	 						  <td>${providerInfo[i].serviceName}</td>
 	 						 <td>${providerInfo[i].serviceContent}</td>
 	 						<td>${providerInfo[i].price}</td>
 	 	                        <td><span class="down-line-mark down-line-mark-orange">下线</span></td>
 	 	                        <td>
 	 	                            <span class="handle-btn" onclick="on('${providerInfo[i].id}')"><i class="fa fa-arrow-up fa-fw"></i>上线</span>
 	 	                        </td>
 	 	                    </tr>`
 						}
 					}
 					console.log(txt);
 					$("#table").append(txt);
 				},
 				//请求失败后调用函数
 				error:function(data){
 					console.log("失败后返回的数据",data);
 				}
 			})
 		})
 		
 		
 		
 		$(".all_check").click(function () {
        $(".checkbox").prop("checked", $(this).prop("checked"))
    });
    $(document).on("click", ".checkbox", function () {
        var flag = $(".checkbox:checked").length == $(".checkbox").length;
        $(".all_check").prop("checked",flag)
    })
 	
    
 function dl(id){
    	$.ajax({
				//请求类型
				type:"post",
				//请求路径
				url:"/provider/providerdelete",
				//返回数据类型
				data:{
					id:id,
				},
				dataType:"json",
				//请求成功后调用函数
				success:function(data){
					console.log("成功后返回的数据",data);
					location.href="redirect?page=service_product"
				},
				//请求失败后调用函数
				error:function(data){
					console.log("请求失败",data);
				}
})
    }
   
    function on(id){
    	$.ajax({
				//请求类型
				type:"post",
				//请求路径
				url:"/provider/provideroline",
				//返回数据类型
				data:{
					id:id,
				},
				dataType:"json",
				//请求成功后调用函数
				success:function(data){
					console.log("成功后返回的数据",data);
					location.href="redirect?page=service_product"
				},
				//请求失败后调用函数
				error:function(data){
					console.log("请求失败",data);
				}
})
    }
    
    function down(id){
    	$.ajax({
				//请求类型
				type:"post",
				//请求路径
				url:"/provider/providerdownline",
				//返回数据类型
				data:{
					id:id,
				},
				dataType:"json",
				//请求成功后调用函数
				success:function(data){
					console.log("成功后返回的数据",data);
					location.href="redirect?page=service_product"
				},
				//请求失败后调用函数
				error:function(data){
					console.log("请求失败",data);
				}
})
    }
 		
    $(function(){
		$("#sysuser").html("");
		var txt="";
		txt +=sessionStorage.getItem("name")
		$("#sysuser").append(txt);
})

$(".apa").on("click", function(){
	$.ajax({
		//请求类型
		type:"get",
		//请求路径
		url:"/provider/apa",
		//返回数据类型
		dataType:"json",
		//请求成功后调用函数
		success:function(data){
			console.log("成功后返回的数据",data);
			sessionStorage.setItem("uuid",data.providerInfo)
			$(".aaa").html("");
			var txt="";
			txt +=`<input name="id" class="userid" type="hidden" value="${data.providerInfo}"/>`
			$(".aaa").append(txt);
		},
		//请求失败后调用函数
		error:function(data){
			console.log("请求失败",data);
		}
})
})

$("#form3").ajaxForm(function(data) {
	console.log("str:" + JSON.stringify(data));
	}
);