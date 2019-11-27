$(function() {
	var img = document.getElementsByClassName("img")[0];
	img.src = "imgGetCode";
})
function imgChange() {
	var img = document.getElementsByClassName("img")[0];
	var time = new Date().getTime();
	img.src = "imgGetCode?t=" + time;
}

//退出登录
$(".exit").on("click",function(){
	sessionStorage.setItem("id","")
	sessionStorage.setItem("name","")
	sessionStorage.setItem("status",2)
	 location.href="redirect?page=service_login"
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

$(".login-btn").on("click",function(){
	var province = $("#province").val();
	var county = $("#county").val();
	var district = $("#district").val();
	console.log(province,county,district);
	
	var loginId = $(".loginId").val();
	var password = $(".password").val();
	var code = $(".code").val();
	
	
	$.ajax({
		type: "post",
		url: "/provider2/register",
		data:{
			loginId:loginId,
			password:password,
			code:code,
			district:district,
		},
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			alert(data.msg);
			 location.href="redirect?page=service_login"; 
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})

