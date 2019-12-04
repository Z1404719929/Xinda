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
 				url:"provider/storeregister",
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
 					location.href="redirect?page=service_store"
 				},
 				error:function(data){
 					console.log("失败后返回的数据",data);
 					alert(data.msg);
 					location.href="redirect?page=service_store"
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
	<img class="img" name="id" src="provider2/file_Img?id=${id}" onerror="defaultImg(this)"/>`
		$("#ss2").append(txt);
})


//图像展示
	function defaultImg(img){
	var id=sessionStorage.getItem("id")
		img.src="images/user-lg.png";
	}

$("#form2").ajaxForm(function(data) {
	console.log(data);
	location.href="redirect?page=service_store"
	console.log("str:" + JSON.stringify(data));
	}
);

$(function(){
	img();
	login();
	see();
})

function see(){
 			//处理用户数据
		var id=sessionStorage.getItem("id")
 			 $.ajax({
 				//请求类型
 				type:"post",
 				//请求路径
 				url:"p/see",
 				//请求参数
 				data:{
 					id:id,
 				},
 				//返回数据类型
 				dataType:"json",
 				//请求成功后调用函数
 				success:function(data){
 					var p = data.p;
 					console.log("成功后返回的数据",p);
 					$(".service_info").val(p[0].providerInfo);
 					$(".service_time").val(p[0].workTime);
 					$(".service_qq").val(p[0].qq);
 					$(".service_cellphone").val(p[0].cellphone);
 				},
 				error:function(data){
 					
 				}
 			}) 
 		}
//退出登录
$(".exit").on("click",function(){
	sessionStorage.setItem("id","")
	sessionStorage.setItem("name","")
	sessionStorage.setItem("status",2)
	 location.href="redirect?page=index"
})

function login(){
	var userid=sessionStorage.getItem("id");
	var username=sessionStorage.getItem("name");
	var status=sessionStorage.getItem("status");
	console.log(username);
	if(status!=1){
		alert("请先登录");
		 location.href="redirect?page=service_login"
	}
}

function img(){
	var userid=sessionStorage.getItem("id")
	$(".img").html("");
	var txt="";
	txt +=`<img  src="provider/headImg?id=${userid}" onerror="defaultImg(this)" style="
    width: 50px;
    height: 50px;
    border-radius: 50px;
    display: inline-block;
    border: 1px solid #e1e1e1;
	"/>`
	$(".img").append(txt);
}