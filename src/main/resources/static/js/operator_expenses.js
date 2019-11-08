var pageNum=1;
var nowpage=1;
var index1=1;
var time1=0;

$(function(){
	page(1);
})

function time(i,time){
	time1=time;
	nowpage=i;
	$.ajax({
		type: "get",
		url: "/oo1/getlist3",
		data:{
			pageSize:i,
			pageNum:2,
			time:time,
		},
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
			console.log(data);
			var paytype=null;
			$("#list").html("");
			txt="";
			for(var i = 0;i<soList.length;i++){
				if(soList[i].payType==1){paytype="银联支付"}
				if(soList[i].payType==2){paytype="微信支付"}
				if(soList[i].payType==3){paytype="支付宝支付"}
				txt +=`<tr>
            <td>${soList[i].memberId}</td>
            <td>${soList[i].createTime}</td>
            <td>${soList[i].serviceNo}</td>
            <td>￥${soList[i].totalPrice}</td>
            <td>${paytype}</td>
            <td>${soList[i].serviceId}</td>
        </tr>`
			}
			$("#list").append(txt);
			
			var allprice=data.allprice;
			$(".allprice").html("");
			txt="";
			txt +="￥"+allprice
			$(".allprice").append(txt);
			
			pageNum = data.pageNum;
			$(".page").html("");
			txt="";
			for(var i = 1;i<=pageNum;i++){
				txt +=`<span class="main-pagination-page" onclick="time(${i},${time})" >${i}</span>`
			}
			$(".page").append(txt);
			
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}

function page(i){
	nowpage=i;
	$.ajax({
		type: "get",
		url: "/oo1/getlist2",
		data:{
			pageSize:i,
			pageNum:2,
		},
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
			var paytype=null;
			$("#list").html("");
			txt="";
			for(var i = 0;i<soList.length;i++){
				if(soList[i].payType==1){paytype="银联支付"}
				if(soList[i].payType==2){paytype="微信支付"}
				if(soList[i].payType==3){paytype="支付宝支付"}
				txt +=`<tr>
            <td>${soList[i].memberId}</td>
            <td>${soList[i].createTime}</td>
            <td>${soList[i].serviceNo}</td>
            <td>￥${soList[i].totalPrice}</td>
            <td>${paytype}</td>
            <td>${soList[i].serviceId}</td>
        </tr>`
			}
			$("#list").append(txt);
			
			var allprice=data.allprice;
			$(".allprice").html("");
			txt="";
			txt +="￥"+allprice
			$(".allprice").append(txt);
			
			pageNum = data.pageNum;
			$(".page").html("");
			txt="";
			for(var i = 1;i<=pageNum;i++){
				txt +=`<span class="main-pagination-page" onclick="page(${i})" >${i}</span>`
			}
			$(".page").append(txt);
			
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}
//首页
$(".pagefirst").on("click",function(){
	if(time1!=0){
		time(1,time1);
	}else{
		page(1);
	}
})
//上一页
$(".last").on("click",function(){
	if(nowpage!=1){	
		if(time1!=0){
			time(nowpage-1,time1);
		}else{
			page(nowpage-1);
		}
	}
})
//下一页
$(".next").on("click",function(){
	if(nowpage!=pageNum){	
		if(time1!=0){
			time(nowpage+1,time1);
		}else{
			page(nowpage+1);
		}
	}
})
//尾页
$(".pagelast").on("click",function(){
	if(time1!=0){
		time(pageNum,time1);
	}else{
		page(pageNum);
	}
})

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
	time(1,1);
})
$(".search li").eq(1).on("click", function () {
    $(".search li").removeClass("font-red");
    $(this).addClass("font-red");
	time(1,7);
})
$(".search li").eq(2).on("click", function () {
    $(".search li").removeClass("font-red");
    $(this).addClass("font-red");
	time(1,30);
})
$(".search li").eq(3).on("click", function () {
    $(".search li").removeClass("font-red");
    $(this).addClass("font-red");
    time1=0;
    page(1);
})