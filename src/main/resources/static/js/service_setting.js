$(".user-arrow-down").on("click",function(){
    if($(".dropdown").is(":hidden")){
        $(".dropdown").show();
 }else{
       $(".dropdown").hide();
 }
})
$(".order1").on("click", function(){
    $(".main-content").show();
    $("table").show();
    $(".main-pagination").show();
    $(".main-sercive").hide();
    $(".order1").addClass("border-red");
    $(".order2").removeClass("border-red");
    $(".main-top li").eq(3).text("正常用户");
})
$(".order2").on("click", function(){
    $(".main-content").hide();
    $("table").hide();
    $(".main-pagination").hide();
    $(".main-sercive").show();
    $(".order2").addClass("border-red");
    $(".order1").removeClass("border-red");
    $(".main-top li").eq(3).text("停用用户");
})
$(".order3").on("click", function(){
    $(".main-top li").eq(3).text("未通过用户");
})
$(".change-info").on("click", function(event){
    $(".masking").show();
})
$(".save").on("click", function(event){
    $(".masking").hide();
           var id=sessionStorage.getItem("id")
           var placeholder= $("#search").attr("placeholder");
           console.log(id);
		    var name=$(".service_name1").val();
 			var cellphone=$(".service_cellphone1").val();
 			var weixin=$(".service_weixin1").val();
 			var qq=$(".service_qq1").val();
 			var email=$(".service_email1").val();
 			console.log("保存"+name,cellphone,weixin,qq,email);
 			
 			var province = $("#province").val();
 			var county = $("#county").val();
 			var district = $("#district").val();
 			console.log("生"+province,county,district);
 				
 			 $.ajax({
 				//请求类型
 				type:"post",
 				//请求路径
 				url:"/provider/informationUpdate",
 				//请求参数
 				data:{
 					name:name,
 					id:id,
 					cellphone:cellphone,
 					weixin:weixin,
 					qq:qq,
 					email:email,
 				},
 				//返回数据类型
 				dataType:"json",
 				//请求成功后调用函数
 				success:function(data){
 					console.log("保存",data);
 					location.href="redirect?page=service_setting"
 					alert(data.msg);
 				},
 				error:function(data){
 					console.log("失败后返回的数据",data);
 				}
 			}) 
})
$(".cancel").on("click", function(event){
    $(".masking").hide();
    console.log("取消");
})


$(function(){
	var loginId=sessionStorage.getItem("id")
    console.log(loginId);
	    var name=$(".service_name").val();
	    var regionId=$(".service_regionId").val();
		var cellphone=$(".service_cellphone").val();
		var weixin=$(".service_weixin").val();
		var qq=$(".service_qq").val();
		var email=$(".service_email").val();
		console.log(name,regionId,cellphone,weixin,qq,email);
	$.ajax({
			//请求类型
			type:"get",
			//请求路径
			url:"/provider/getprovider",
			//请求参数
			data:{
				name:name,
				loginId:loginId,
				regionId:regionId,
				cellphone:cellphone,
				weixin:weixin,
				qq:qq,
				email:email,
			},
			//返回数据类型
			dataType:"json",
			//请求成功后调用函数
			success:function(data){
					console.log("成功后返回的数据",data);
					var provider=data.provider;
					console.log(provider);
					//每次清空table
					$(".service_name").html("");
					var txt="";
					txt+=provider[0].name;
						    	console.log(txt);
		 					$(".service_name").append(txt);
		 			$(".service_regionId").html("");
							var txt="";
							txt+=provider[0].regionId;
								    	console.log(txt);
				 			$(".service_regionId").append(txt);
				 	$(".service_cellphone").html("");
							var txt="";
							txt+=provider[0].cellphone;
								    	console.log(txt);
				 			$(".service_cellphone").append(txt);
				 	$(".service_weixin").html("");
							var txt="";
							txt+=provider[0].weixin;
								    	console.log(txt);
				 			$(".service_weixin").append(txt);
				 	$(".service_qq").html("");
									var txt="";
									txt+=provider[0].qq;
										    	console.log(txt);
						 	$(".service_qq").append(txt);
					$(".service_email").html("");
							var txt="";
							txt+=provider[0].email;
								    	console.log(txt);
				 	       $(".service_email").append(txt);
		 				},
			error:function(data){
				console.log("失败后返回的数据",data);
			}
		}) 
})

$(function(){
	$.ajax({
		type: "post",
		url: "/provider2/province",
		dataType: "json",
		success: function(data){
			var province = data.province;
			console.log("成功后返回的数据",province);
			$("#province").html("");
			txt="";
			txt +=`<option value="">省</option>`
			for(var i = 0;i<province.length;i++){
				txt +=`
                        <option onclick="provinceid('${province[i].id}')"  value="${province[i].id}">${province[i].name}</option>`
			}
			$("#province").append(txt);
			$("#county").html("");
			txt="";
			txt +=`<option value="">市</option>`
			$("#county").append(txt);
			$("#district").html("");
			txt="";
			txt +=`<option value="">区</option>`
			$("#district").append(txt);
			
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})

function changesheng(){
	var id = $("#province").val();
	console.log("省",id);
	$.ajax({
		type: "post",
		url: "/provider2/county",
		data:{
			id:id,
		},
		dataType: "json",
		success: function(data){
			var county = data.county;
			$("#county").html("");
			txt="";
			txt +=`<option value="">市</option>`
			for(var i = 0;i<county.length;i++){
				txt +=`
                        <option class="countyid" value="${county[i].id}">${county[i].name}</option>`
			}
			$("#county").append(txt);
			$("#district").html("");
			txt="";
			txt +=`<option value="">区</option>`
			$("#district").append(txt);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}


function changeshi(){
	var id = $("#county").val();
	console.log("市id",id);
	$.ajax({
		type: "post",
		url: "/provider2/district",
		data:{
			id:id,
		},
		dataType: "json",
		success: function(data){
			var district = data.district;
			$("#district").html("");
			txt="";
			txt +=`<option value="">区</option>`
			for(var i = 0;i<district.length;i++){
				txt +=`
                        <option class="districtid" value="${district[i].id}">${district[i].name}</option>`
			}
			$("#district").append(txt);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}


$(function(){
	$("#sysuser").html("");
	var txt="";
	txt +=sessionStorage.getItem("name")
	$("#sysuser").append(txt);
	var id=sessionStorage.getItem("id");
	var id1=$(".userid").val(id);
	console.log(id1);
	$("#ss").html("");
	var txt="";
	txt +=`
	<img class="img" name="id" src="/provider2/provider_Img?id=${id}" onerror="defaultImg(this)"/>`
		$("#ss").append(txt);
})

//图像展示
	function defaultImg(img){
	var id=sessionStorage.getItem("id")
		img.src="/images/user-lg.png";
	}

$("#form1").ajaxForm(function(data) {
	console.log(data);
	location.href="redirect?page=service_setting"
	console.log("str:" + JSON.stringify(data));
	}
);


$(function(){
	img();
})
function img(){
	var userid=sessionStorage.getItem("id")
	$(".img").html("");
	var txt="";
	txt +=`<img  src="/provider/headImg?id=${userid}" onerror="defaultImg(this)" style="
    width: 50px;
    height: 50px;
    border-radius: 50px;
    display: inline-block;
    border: 1px solid #e1e1e1;
	"/>`
	$(".img").append(txt);
}