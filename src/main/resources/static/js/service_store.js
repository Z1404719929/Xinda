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


//控制登录名
$(function(){
	$("#sysuser").html("");
	var txt="";
	txt +=sessionStorage.getItem("name")
	$("#sysuser").append(txt);
	var id=sessionStorage.getItem("id");
	var id1=$(".userid").val(id);
	console.log(id1);
	$("#ss2").html("");
	var txt="";
	txt +=`
	<img class="img" name="id" src="/provider2/file_Img?id=${id}" onerror="defaultImg(this)"/>`
		$("#ss2").append(txt);
})


//图像展示
	function defaultImg(img){
	var id=sessionStorage.getItem("id")
		img.src="/images/user-lg.png";
	}

$("#form2").ajaxForm(function(data) {
	console.log(data);
	location.href="redirect?page=service_store"
	console.log("str:" + JSON.stringify(data));
	}
);
