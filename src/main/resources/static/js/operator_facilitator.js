var pageNum=1;
var nowpage=1;
var index1=1;

$(function(){
	img();
	page2(1,1);
})

function img(){
	var userid=sessionStorage.getItem("id")
	$(".img").html("");
	var txt="";
	txt +=`<img  src="/pp/headImg?id=${userid}" onerror="defaultImg(this)" style="
    width: 50px;
    height: 50px;
    display: inline-block;
    border: 1px solid #e1e1e1;
	"/>`
	$(".img").append(txt);
}

//用户分页模糊查询
function page2(i,index){
	console.log(i);
	nowpage=i;
	index1=index;
	var name=$(".select").val();
	$.ajax({
		type: "post",
		url: "/p/paging",
		data:{
			index:index,
			name:name,
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
			txt +=sessionStorage.getItem("name")
			$("#sysuser").append(txt);
			
			$(".select-btn").html("");
			var txt="";
			txt +=`<button onclick="page2(1,${index})"><i class="fa fa-search fa-fw"></i></button>`
			$(".select-btn").append(txt);
			
			
			var List = data.List;
			$("#list").html("");
			txt="";
			if(index==1){
			for(var i = 0;i<List.length;i++){
				txt +=`<tr>
            <td>${List[i].name}</td>
            <td>${List[i].regionId}</td>
            <td>${List[i].cellphone}</td>
            <td>${List[i].providerInfo}</td>
            <td>
                <span class="handle-btn"><i class="fa fa-edit fa-fw"></i>详情</span>
                <span class="handle-btn star"><i class="fa fa-circle-o fa-fw"></i>停用</span>
            </td>
        </tr>`
			}
			$("#list").append(txt);
			
			pageNum = data.pageNum;
			$(".page").html("");
			txt="";
			for(var i = 1;i<=pageNum;i++){
				txt +=`<span class="main-pagination-page" onclick="page2(${i},${index})" >${i}</span>`
			}
			$(".page").append(txt);
			}else{
				for(var i = 0;i<List.length;i++){
					txt +=`<tr>
	            <td>${List[i].name}</td>
	            <td>${List[i].regionId}</td>
	            <td>${List[i].cellphone}</td>
	            <td>${List[i].providerInfo}</td>
	            <td>
	                <span class="handle-btn"><i class="fa fa-edit fa-fw"></i>详情</span>
	                <span class="handle-btn stop"><i class="fa fa-circle-o fa-fw"></i>启用</span>
	            </td>
	        </tr>`
				}
				$("#list").append(txt);
				
				pageNum = data.pageNum;
				$(".page").html("");
				txt="";
				for(var i = 1;i<=pageNum;i++){
					txt +=`<span class="main-pagination-page" onclick="page2(${i},${index})" >${i}</span>`
				}
				$(".page").append(txt);
			}
			
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}

function page1(i,index){
	console.log(i);
	nowpage=i;
	index1=index;
//	var name=$(".select").val();
	$.ajax({
		type: "post",
		url: "/p/paging",
		data:{
			index:index,
			name:name,
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
			
			$(".search").html("");
			var txt="";
			txt +=`<input class="select" placeholder="请输入服务商名称查询" /><button onclick="page2(1,${index})"><i class="fa fa-search fa-fw"></i></button>`
			$(".search").append(txt);
			
			$("#sysuser").html("");
			var txt="";
			txt +=sessionStorage.getItem("name")
			$("#sysuser").append(txt);
			
			var List = data.List;
			$("#list").html("");
			txt="";
			if(index==1){
			for(var i = 0;i<List.length;i++){
				txt +=`<tr>
            <td>${List[i].name}</td>
            <td>${List[i].regionId}</td>
            <td>${List[i].cellphone}</td>
            <td>${List[i].providerInfo}</td>
            <td>
                <span class="handle-btn"><i class="fa fa-edit fa-fw"></i>详情</span>
                <span class="handle-btn"><i class="fa fa-circle-o fa-fw"></i>停用</span>
            </td>
        </tr>`
			}
			$("#list").append(txt);
			
			pageNum = data.pageNum;
			$(".page").html("");
			txt="";
			for(var i = 1;i<=pageNum;i++){
				txt +=`<span class="main-pagination-page" onclick="page1(${i},${index})" >${i}</span>`
			}
			$(".page").append(txt);
			
			
			}else{
				for(var i = 0;i<List.length;i++){
					txt +=`<tr>
	            <td>${List[i].name}</td>
	            <td>${List[i].regionId}</td>
	            <td>${List[i].cellphone}</td>
	            <td>${List[i].providerInfo}</td>
	            <td>
	                <span class="handle-btn"><i class="fa fa-edit fa-fw"></i>详情</span>
	                <span class="handle-btn"><i class="fa fa-circle-o fa-fw"></i>启用</span>
	            </td>
	        </tr>`
				}
				$("#list").append(txt);
				
				pageNum = data.pageNum;
				$(".page").html("");
				txt="";
				for(var i = 1;i<=pageNum;i++){
					txt +=`<span class="main-pagination-page" onclick="page1(${i},${index})" >${i}</span>`
				}
				$(".page").append(txt);
			}
			
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}
//上一页
$(".last").on("click",function(){
	if(nowpage!=1){	
		page2(nowpage-1,index1);}
})

//下一页
$(".next").on("click",function(){
	if(nowpage!=pageNum){	
		page2(nowpage+1,index1);}
})

//首页
$(".pagefirst").on("click",function(){
		page2(1,index1);
})

//尾页
$(".pagelast").on("click",function(){
		page2(pageNum,index1);
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
$(".order1").on("click", function(){
    $(".order1").addClass("border-red");
    $(".order2").removeClass("border-red");
    $(".main-top li").eq(3).text("正常用户");
    page1(1,1);
})
$(".order2").on("click", function(){
    $(".order2").addClass("border-red");
    $(".order1").removeClass("border-red");
    $(".main-top li").eq(3).text("停用用户");
    page1(1,2);
})