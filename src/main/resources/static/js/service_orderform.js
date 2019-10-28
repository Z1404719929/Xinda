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
$(function(){
	$.ajax({
		type: "get",
		url: "/oo/getlist",
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
				console.log("成功",soList[i].ll);
				if(soList[i].ll==1){
					console.log("成功2",soList[i].serviceNo);
				txt += `<tr>
                <td>${soList[i].serviceNo}</td>
                <td>${soList[i].sName}</td>
                <td>${soList[i].memberId}</td>
                <td>${soList[i].cellphone}</td>
                <td>${soList[i].serviceNum}</td>
                <td>￥${soList[i].totalPrice}</td>
                <td>${soList[i].createTime}</td>
                <td>
                    <span class="handle-btn"><i class="fa fa-edit fa-fw"></i>详情</span>
                </td>
            </tr>`
				}
			}
			$("#list").append(txt);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})