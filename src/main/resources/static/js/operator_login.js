
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
	console.log("手机号==",cellphone);
	$.ajax({
		type: "post",
		url: "/su/sysuserlogin",
		data:{
			cellphone:cellphone,
			password:password,
			code:code,
		},
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			alert(data.msg);
			if(data.code==1){
			sessionStorage.setItem("id",data.userid),
			sessionStorage.setItem("name",data.username),
			 location.href="redirect?page=operator_product"
			}
			//alert(data.msg);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})

