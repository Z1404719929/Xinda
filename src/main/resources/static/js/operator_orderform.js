
    
$(function(){
	img();
	$.ajax({
		type: "get",
		url: "/oo1/getlist1",
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			var status=sessionStorage.getItem("status")
			if(status!=1){
				alert("请先登录");
				 location.href="redirect?page=operator_login"
			}
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

function img(){
	var userid=sessionStorage.getItem("id")
	$(".img").html("");
	var txt="";
	txt +=`<img  src="/pp/headImg?id=${userid}" onerror="defaultImg(this)" style="
    width: 50px;
    height: 50px;
    border-radius: 50px;
    display: inline-block;
    border: 1px solid #e1e1e1;
	"/>`
	$(".img").append(txt);
}
//图像展示
function defaultImg(img){
	img.src="/images/user-lg.png";
}
//模糊查询
$(".select-btn").on("click",function(){
	console.log("查询内容",$(".select").val());
	var name=$(".select").val();
	$.ajax({
		type: "post",
		url: "/oo1/select1",
		data:{
			name:name,
		},
		dataType: "json",
		success: function(data){
			var soList = data.soList;
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
			
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})


//退出登录
$(".exit").on("click",function(){
	sessionStorage.setItem("id","")
	sessionStorage.setItem("name","")
	sessionStorage.setItem("status",2)
	 location.href="redirect?page=operator_login"
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