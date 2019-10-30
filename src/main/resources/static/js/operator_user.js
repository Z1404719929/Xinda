

$(function(){
	$.ajax({
		type: "get",
		url: "/ou/getlist",
		dataType: "json",
		success: function(data){
			var userid=sessionStorage.getItem("id")
			var username=sessionStorage.getItem("name")
			$("#sysuser").html("");
			var txt="";
			txt +=username
			$("#sysuser").append(txt);
			
			console.log("成功后返回的数据",data);
			var mList = data.mList;
			$("#list").html("");
			txt="";
			for(var i = 0;i<mList.length;i++){
				txt +=`<tr>
                        <td value="${mList[i].id}" >${i+1}</td>
                        <td>${mList[i].name}</td>
                        <td>${mList[i].cellphone}</td>
                        <td>${mList[i].regionId}</td>
                        <td>${mList[i].registerTime}</td>
                        <td>${mList[i].buyNum}</td>
                        <td>¥${mList[i].totalPrice}</td>
                        <td>
                            <span class="handle-btn">查看</span>
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
    $(".main-pagination").show();
    $(".main-sercive").hide();
    $(".business-order").addClass("border-red");
    $(".service-order").removeClass("border-red");
    $(".main-top li").eq(3).text("注册用户");
})
$(".service-order").on("click", function(){
    $(".main-content").hide();
    $("table").hide();
    $(".main-pagination").hide();
    $(".main-sercive").show();
    $(".service-order").addClass("border-red");
    $(".business-order").removeClass("border-red");
    $(".main-top li").eq(3).text("服务商用户");
})