var pageNum=1;
var nowpage=1;
var index1="create_time";

$(function(){
	page(1,index1);
})

//index排序方式
function page(i,index){
	console.log(i);
	nowpage=i;
	index1=index;
	var name=$(".select").val();
	console.log(name);
	$.ajax({
		type: "post",
		url: "/pp/recommend",
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
			txt +=`<button onclick="page(1,'${index}')"><i class="fa fa-search fa-fw"></i></button>`
			$(".select-btn").append(txt);
			
			
			var List = data.List;
			$("#list").html("");
			txt="";
			for(var i = 0;i<List.length;i++){
				var check1="";
				var check2="";
				var a=2;
				var b=2;
				if(List[i].recommend==1){
					check1="checked";
					a=1;
				}
				if(List[i].highQuality==1){
					check2="checked";
					b=1;
				}
				txt +=`<tr>
                        <td>${List[i].serviceName}</td>
                        <td>${List[i].serviceContent}</td>
                        <td>${List[i].saleNum}</td>
                        <td>${List[i].providerName}</td>
                        <td>${List[i].price}</td>
                        <td><input class="checkbox" type="checkbox" ${check1} onclick="check1('${List[i].id}',${a})"/></td>
                        <td><input class="checkbox" type="checkbox" ${check2} onclick="check2('${List[i].id}',${b})"/></td>
                    </tr>`
			}
			$("#list").append(txt);
			
			pageNum = data.pageNum;
			$(".page").html("");
			txt="";
			for(var i = 1;i<=pageNum;i++){
				txt +=`<span class="main-pagination-page" onclick="page(${i},'${index}')" >${i}</span>`
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
		page(nowpage-1,index1);}
})
//下一页
$(".next").on("click",function(){
	if(nowpage!=pageNum){	
		page(nowpage+1,index1);}
})

//首页
$(".pagefirst").on("click",function(){
		page(1,index1);
})

//尾页
$(".pagelast").on("click",function(){
		page(pageNum,index1);
})


function check1(id,a){
	$.ajax({
		type: "post",
		url: "/pp/check1",
		data:{
			id:id,
			a:a,
		},
		dataType: "json",
		success: function(data){
			 location.href="redirect?page=operator_recommend"
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
	console.log(id);
}

function check2(id,b){
	$.ajax({
		type: "post",
		url: "/pp/check2",
		data:{
			id:id,
			b:b,
		},
		dataType: "json",
		success: function(data){
			location.href="redirect?page=operator_recommend"
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
	console.log(id);
}


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
$(".sort li").eq(0).on("click", function () {
    $(".sort li").removeClass("font-red");
    $(this).addClass("font-red");
    page(1,'create_time');
})
$(".sort li").eq(1).on("click", function () {
    $(".sort li").removeClass("font-red");
    $(this).addClass("font-red");
    page(1,'sale_num');
})
$(".sort li").eq(2).on("click", function () {
    $(".sort li").removeClass("font-red");
    $(this).addClass("font-red");
    page(1,'price');
})