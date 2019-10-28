$(".user-arrow-down").on("click",function(){
    if($(".dropdown").is(":hidden")){
        $(".dropdown").show();
 }else{
       $(".dropdown").hide();
 }
})

function submit(){
 			//处理用户数据
		var id=sessionStorage.getItem("id")
    console.log(id);
 			var providerInfo=$(".service_info").val();
 			var workTime=$(".service_time").val();
 			var qq=$(".service_qq").val();
 			var cellphone=$(".service_cellphone").val();
 			console.log(providerInfo,workTime,qq,cellphone);
 			 $.ajax({
 				//请求类型
 				type:"post",
 				//请求路径
 				url:"/provider/storeregister",
 				//请求参数
 				data:{
 					id:id,
 					providerInfo:providerInfo,
 					workTime:workTime,
 					qq:qq,
 					cellphone:cellphone,
 				},
 				//返回数据类型
 				dataType:"json",
 				//请求成功后调用函数
 				success:function(data){
 					console.log("成功后返回的数据",data);
 					alert(data.msg);
 				},
 				error:function(data){
 					console.log("失败后返回的数据",data);
 				}
 			}) 
 		}