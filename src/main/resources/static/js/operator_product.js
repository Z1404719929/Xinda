
var check =null;
var str="";

$(function(){
	
	$.ajax({
		type: "get",
		url: "/pp/getlist",
		dataType: "json",
		success: function(data){
			var userid=sessionStorage.getItem("id")
			var username=sessionStorage.getItem("name")
			$("#sysuser").html("");
			var txt="";
			txt +=username
			$("#sysuser").append(txt);
			
			console.log("成功后返回的数据",data);
			var ppList = data.ppList;
			$("#list").html("");
			txt="";
			for(var i = 0;i<ppList.length;i++){
				if(ppList[i].status==1){
				txt +=`<tr>
                        <td><input type="checkbox" class="checkbox" value="${ppList[i].id}" name="product"></td>
                        <td>${ppList[i].serviceName}</td>
                        <td>${ppList[i].serviceContent}</td>
                        <td>${ppList[i].price}元</td>
                        <td></td>
                        <td><span class="up-line-mark up-line-mark-red ">上线</span></td>
                        <td>
                            <span class="handle-btn"><i class="fa fa-edit fa-fw"></i>编辑</span>
                            <span class="handle-btn"><i class="fa fa-close fa-fw"></i>删除</span>
                            <span class="handle-btn" onclick="zt('${ppList[i].id}')"><i class="fa fa-arrow-down fa-fw"></i>下线</span>
                        </td>
				</tr>`
					}else{
						txt +=`<tr>
	                        <td><input type="checkbox" class="checkbox" value="${ppList[i].id}" name="product"></td>
	                        <td>${ppList[i].serviceName}</td>
	                        <td>${ppList[i].serviceContent}</td>
	                        <td>${ppList[i].price}元</td>
	                        <td></td>
	                         <td><span class="down-line-mark down-line-mark-orange">下线</span></td>
	                        <td>
	                            <span class="handle-btn" onclick="zt('${ppList[i].id}')"><i class="fa fa-arrow-up fa-fw"></i>上线</span>
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


function zt(id){
	console.log("下线id",id);
	$.ajax({
		type: "post",
		url: "/pp/us",			//修改状态
		data:{
			id:id,
		},
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			 location.href="redirect?page=operator_product"
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}

$(".checkall").on("change",function(){
	check=this.checked;
	$(":checkbox[name='product']").attr("checked",check);
	//判断是否全选
	var input=$(":checkbox[name='product']");
	if(check){
		for(var i=0;i<input.length;i++){
			if(input.length-1==i){
				str +=$(input[i]).val();
			}else{
				str +=$(input[i]).val()+",";
			}
		}
		console.log(str);
	}
})

$(".up-line").on("click",function(){
	console.log(str);
	$.ajax({
		type: "post",
		url: "/pp/allUpLine",			//修改状态
		data:{
			str:str,
		},
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			 location.href="redirect?page=operator_product"
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})

$(".down-line").on("click",function(){
	console.log(str);
	$.ajax({
		type: "post",
		url: "/pp/allDownLine",			//修改状态
		data:{
			str:str,
		},
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			 location.href="redirect?page=operator_product"
		},error: function(data){
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

    		
    		
    		
    		