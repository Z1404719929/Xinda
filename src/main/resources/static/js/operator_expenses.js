$(function(){
	
	$.ajax({
		type: "get",
		url: "/oo1/getlist2",
		dataType: "json",
		success: function(data){
			var userid=sessionStorage.getItem("id")
			var username=sessionStorage.getItem("name")
			var status=sessionStorage.getItem("status")
			if(status!=1){
				alert("请先登录");
				 location.href="redirect?page=operator_login"
			}
			$("#sysuser").html("");
			var txt="";
			txt +=username
			$("#sysuser").append(txt);
			
			
			var soList = data.soList;
			$("#list").html("");
			txt="";
			for(var i = 0;i<1;i++){
				txt +=`<tr>
            <td>${soList[i].memberId}</td>
            <td>${soList[i].createTime}</td>
            <td>${soList[i].serviceNo}</td>
            <td>￥${soList[i].totalPrice}</td>
            <td>银联支付</td>
            <td>${soList[i].serviceId}</td>
        </tr>`
			}
			$("#list").append(txt);
			
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})


//function page(){
//	$.ajax({
//		type: "get",
//		url: "/oo1/getlist1",
//		dataType: "json",
//		success: function(data){
//			var userid=sessionStorage.getItem("id")
//			var username=sessionStorage.getItem("name")
//			var status=sessionStorage.getItem("status")
//			if(status!=1){
//				alert("请先登录");
//				 location.href="redirect?page=operator_login"
//			}
//			$("#sysuser").html("");
//			var txt="";
//			txt +=username
//			$("#sysuser").append(txt);
//			
//			
//			var soList = data.soList;
//			$("#list").html("");
//			txt="";
//			for(var i = 0;i<2;i++){
//				txt +=`<tr>
//            <td>${soList[i].memberId}</td>
//            <td>${soList[i].createTime}</td>
//            <td>${soList[i].serviceNo}</td>
//            <td>￥${soList[i].totalPrice}</td>
//            <td>银联支付</td>
//            <td>${soList[i].serviceId}</td>
//        </tr>`
//			}
//			$("#list").append(txt);
//			
//		},
//		error: function(data){
//			console.log("失败后返回的数据",data);
//		}
//	})
//}



//退出登录
$(".exit").on("click",function(){
	sessionStorage.setItem("id","")
	sessionStorage.setItem("name","")
	sessionStorage.setItem("status",2)
	 location.href="redirect?page=operator_login"
})


$(".user-arrow-down").on("click", function () {
    if ($(".dropdown").is(":hidden")) {
        $(".dropdown").show();
    } else {
        $(".dropdown").hide();
    }
})
$(".business-order").on("click", function () {
    $(".main-content").show();
    $("table").show();
    $(".main-pagination").show();
    $(".main-sercive").hide();
    $(".business-order").addClass("border-red");
    $(".service-order").removeClass("border-red");
    $(".main-top li").eq(3).text("支付中心");
})
$(".service-order").on("click", function () {
    $(".main-content").hide();
    $("table").hide();
    $(".main-pagination").hide();
    $(".main-sercive").show();
    $(".service-order").addClass("border-red");
    $(".business-order").removeClass("border-red");
    $(".main-top li").eq(3).text("结算中心");
})
$(".search li").eq(0).on("click", function () {
    $(".search li").removeClass("font-red");
    $(this).addClass("font-red");
})
$(".search li").eq(1).on("click", function () {
    $(".search li").removeClass("font-red");
    $(this).addClass("font-red");
})
$(".search li").eq(2).on("click", function () {
    $(".search li").removeClass("font-red");
    $(this).addClass("font-red");
})
$(".search li").eq(3).on("click", function () {
    $(".search li").removeClass("font-red");
    $(this).addClass("font-red");
})