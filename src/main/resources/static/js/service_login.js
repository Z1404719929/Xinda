$(function() {
	var img = document.getElementsByClassName("img")[0];
	img.src = "imgGetCode";
	console.log(img.src);
})
function imgChange() {
	var img = document.getElementsByClassName("img")[0];
	var time = new Date().getTime();
	img.src = "imgGetCode?t=" + time;
	console.log(img.src);
}


$(".login-btn").on("click", function(){
	var loginId=$(".loginId").val();
	var password=$(".password").val();
	var code=$(".code").val();
	console.log("账号===",loginId);
	$.ajax({
		type: "post",
		url: "provider/providerlogin",
		data:{
			loginId:loginId,
			password:password,
			code:code,
		},
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			if(data.code!=1){
				alert(data.msg);
			}
			if(data.code==1){
			sessionStorage.setItem("id",data.providerid),
			sessionStorage.setItem("name",data.providername),
			sessionStorage.setItem("status",data.code),
			 location.href="redirect?page=service_product"
			}
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})
