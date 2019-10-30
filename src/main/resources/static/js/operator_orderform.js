$(".user-arrow-down").on("click",function(){
    if($(".dropdown").is(":hidden")){
        $(".dropdown").show();
 }else{
       $(".dropdown").hide();
 }
})
$(".business-order").on("click", function(){
    $(".main-content").show();
    $("table").show();
    $(".main-sercive").hide();
    $(".business-order").addClass("border-red");
    $(".service-order").removeClass("border-red");
    $(".main-top li").eq(3).text("业务订单");
})
$(".service-order").on("click", function(){
    $(".main-content").hide();
    $("table").hide();
    $(".main-sercive").show();
    $(".service-order").addClass("border-red");
    $(".business-order").removeClass("border-red");
    $(".main-top li").eq(3).text("服务订单");
})

$(function(){
	$.ajax({
		type: "get",
		url: "/oo1/getlist1",
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			var soList = data.soList;
			$("#sysuser").html("");
			var txt="";
			txt +=sessionStorage.getItem("name")
			$("#sysuser").append(txt);
			
			$("#list").html("");
			txt="";
			for(var i = 0;i<soList.length;i++){
				txt += `<tr>
                <td>${soList[i].serviceNo}</td>
                <td>${soList[i].memberId}</td>
                <td>${soList[i].serviceId}</td>
                <td>￥${soList[i].totalPrice}</td>
                <td>${soList[i].createTime}</td>
                <td>${soList[i].zt}</td>
                <td>
                    <span class="handle-btn"><i class="fa fa-edit fa-fw"></i>详情</span>
                </td>
            </tr>`
			}
			$("#list").append(txt);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})