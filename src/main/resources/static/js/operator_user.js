var pageNum=1;
var nowpage=1;

//查询所有
$(function(){
	img();
	$.ajax({
		type: "get",
		url: "/ou/getlist",
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
			
			console.log("成功后返回的数据",data);
			var mList = data.mList;
			$("#list").html("");
			txt="";
			for(var i = 0;i<2;i++){
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
			
			pageNum = data.pageNum;
			$(".page").html("");
			txt="";
			for(var i = 1;i<=pageNum;i++){
				txt +=`<span class="main-pagination-page" value="${i}" onclick="page(${i})" >${i}</span>`
			}
			$(".page").append(txt);
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

//分页模糊查询
function page(i){
	console.log(i)
	nowpage=i;
	var name=$(".select").val();
	$.ajax({
		type: "post",
		url: "/ou/paging",
		data:{
			name:name,
			pageSize:i,
			pageNum:2,
		},
		dataType: "json",
		success: function(data){
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
			pageNum = data.pageNum;
			console.log(pageNum);
			$(".page").html("");
			txt="";
			for(var i = 1;i<=pageNum;i++){
				txt +=`<span class="main-pagination-page" value="${i}" onclick="page(${i})" >${i}</span>`
			}
			$(".page").append(txt);
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}
//上一页
$(".last").on("click",function(){
	if(nowpage!=1){
	page(nowpage-1);}
})

//下一页
$(".next").on("click",function(){
	if(nowpage!=pageNum){
	page(nowpage+1);}
})

//首页
$(".page1").on("click",function(){
	page(1);
})

//尾页
$(".pagelast").on("click",function(){
	page(pageNum);
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