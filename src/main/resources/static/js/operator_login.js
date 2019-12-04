
$(function() {
	var img = document.getElementsByClassName("img")[0];
	img.src = "imgGetCode";
})
function imgChange() {
	var img = document.getElementsByClassName("img")[0];
	var time = new Date().getTime();
	img.src = "imgGetCode?t=" + time;
}

$(".login-btn").on("click", function(){
	var cellphone=$(".cellphone").val();
	var password=$(".password").val();
	var code=$(".code").val();
	console.log("手机号==",cellphone,password,code);
	$.ajax({
		type: "post",
		url: "su/sysuserlogin",
		data:{
			cellphone:cellphone,
			password:password,
			code:code,
		},
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			if(data.code==1){
			sessionStorage.setItem("id",data.userid),
			sessionStorage.setItem("name",data.username),
			sessionStorage.setItem("status",data.code),
			 location.href="redirect?page=operator_product"
			}else{
				alert(data.msg);
			}
//			alert(data.msg);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})

