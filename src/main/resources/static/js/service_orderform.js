var pageNum=1;
var nowpage=1;
var status1=1;	//未支付订单==1已支付==2


$(function(){
	page2(1,1);
})



function page(i,status){
	console.log(i);
	nowpage=i;
	status1=status;
	var name=$(".select").val();
	var userid=sessionStorage.getItem("id");
	$.ajax({
		type: "post",
		url: "/oo/getlist",
		data:{
			status:status,
			name:name,
			pageSize:i,
			pageNum:2,
			userid:userid,
		},
		dataType: "json",
		success: function(data){
			var soList = data.soList;
			
			$("#sysuser").html("");
			var txt="";
			txt +=sessionStorage.getItem("name")
			$("#sysuser").append(txt);
			
			/////////////////////////////////////////////////////////
			$("#list").html("");
			txt="";
			if((nowpage-1)*2+2==soList.length+1){
			for(var i = (nowpage-1)*2;i<(nowpage-1)*2+1;i++){
				console.log(soList[i].serviceId);
				txt += `<tr>
                <td>${soList[i].serviceNo}</td>
                <td>${soList[i].serviceId}</td>
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
			}else{
				for(var i = (nowpage-1)*2;i<(nowpage-1)*2+2;i++){
					console.log(soList[i].serviceId);
					txt += `<tr>
	            <td>${soList[i].serviceNo}</td>
	            <td>${soList[i].serviceId}</td>
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
			
			pageNum = data.pageNum;
			$(".page").html("");
			txt="";
			for(var i = 1;i<=pageNum;i++){
				txt +=`<span class="main-pagination-page" onclick="page(${i},${status})" >${i}</span>`
			}
			$(".page").append(txt);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}

function page2(i,status){
console.log(i);
nowpage=i;
status1=status;
var name=$(".select").val();
var userid=sessionStorage.getItem("id");

$(".search").html("");
var txt="";
txt +=`<input class="select"  placeholder="请输入服务名称查询" /><button onclick="page(1,${status})" ><i class="fa fa-search fa-fw"></i></button>`
$(".search").append(txt);

$.ajax({
	type: "post",
	url: "/oo/getlist",
	data:{
		status:status,
		name:name,
		pageSize:i,
		pageNum:2,
		userid:userid,
	},
	dataType: "json",
	success: function(data){
		
		var soList = data.soList;
		
		$("#sysuser").html("");
		txt="";
		txt +=sessionStorage.getItem("name")
		$("#sysuser").append(txt);
		
		
		/////////////////////////////////////////////////////////
		$("#list").html("");
		txt="";
		if((nowpage-1)*2+2==soList.length+1){
		for(var i = (nowpage-1)*2;i<(nowpage-1)*2+1;i++){
			console.log(soList[i].serviceId);
			txt += `<tr>
            <td>${soList[i].serviceNo}</td>
            <td>${soList[i].serviceId}</td>
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
		}else{
			for(var i = (nowpage-1)*2;i<(nowpage-1)*2+2;i++){
				console.log(soList[i].serviceId);
				txt += `<tr>
            <td>${soList[i].serviceNo}</td>
            <td>${soList[i].serviceId}</td>
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
		
		pageNum = data.pageNum;
		$(".page").html("");
		txt="";
		for(var i = 1;i<=pageNum;i++){
			txt +=`<span class="main-pagination-page" onclick="page(${i},${status})" >${i}</span>`
		}
		$(".page").append(txt);
	},
	error: function(data){
		console.log("失败后返回的数据",data);
	}
})
	
}

//上一页
$(".last").on("click",function(){
	if(nowpage!=1){	
		page(nowpage-1,status1);}
})

//下一页
$(".next").on("click",function(){
	if(nowpage!=pageNum){	
		page(nowpage+1,status1);}
})

//首页
$(".pagefirst").on("click",function(){
		page(1,status1);
})

//尾页
$(".pagelast").on("click",function(){
		page(pageNum,status1);
})

//查询
$(".select-btn").on("click",function(){
		page(1,status1);
})




$(".user-arrow-down").on("click",function(){
    if($(".dropdown").is(":hidden")){
        $(".dropdown").show();
 }else{
       $(".dropdown").hide();
 }
})
$(".order1").on("click", function(){

    $(".order1").addClass("border-red");
    $(".order2").removeClass("border-red");
    $(".main-top li").eq(3).text("正常用户");
    page2(1,1);
})
$(".order2").on("click", function(){

    $(".order2").addClass("border-red");
    $(".order1").removeClass("border-red");
    $(".main-top li").eq(3).text("停用用户");
    page2(1,2);
})
$(".order3").on("click", function(){
    $(".main-top li").eq(3).text("未通过用户");
})
