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
           console.log(id);
		    var name=$(".service_name").val();
 			var cellphone=$(".service_cellphone").val();
 			var weixin=$(".service_weixin").val();
 			var qq=$(".service_qq").val();
 			var email=$(".service_email").val();
 			console.log(name,cellphone,weixin,qq,email);
 			 $.ajax({
 				//请求类型
 				type:"post",
 				//请求路径
 				url:"/provider/massageregister",
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