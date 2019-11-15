$(".search-product").on("click", function(){
    $(".search-product").addClass("font-aqua");
    $(".search-service").removeClass("font-aqua");
})
$(".search-service").on("click", function(){
    $(".search-service").addClass("font-aqua");
    $(".search-product").removeClass("font-aqua");
})

$(".banner a").on("click", function(event){
    $(".banner a").removeClass("border-b");
    $(event.target).addClass("border-b");
})

$(".user-action a").on("click", function(event){
    $(".user-action a").removeClass("bg-gray");
    $(event.target).addClass("bg-gray");
})




$( function(){
	var userid=sessionStorage.getItem("id")
	var str3=sessionStorage.getItem("str3")
	
	$.ajax({
		type: "post",
		url: "/product/mmm",			
		data:{	
			userid:userid,			
		},
		dataType: "json",
		success: function(data){

			$(".list2").html("");
          	var list=data.list;
        	console.log("55555",list);
			var txt="";
          	for(var i=0;i<list.length;i++){
    			console.log("777",list[i].serviceNo);
          		
          		if(list[i].status==2){
          			txt+=`
          		       <div class="order-num">订单号：${list[i].serviceNo} 下单时间：${list[i].createTime}</div>
          		                <ul class="order-details">
          		                    <li>
          		                        <img src="./images/user-lg.png" alt="图片">
          		                        <ul>
          		                            <li>${str3}</li>          		                         
          		                        </ul>
          		                    </li>
          		                    <li class="font-aqua">¥${list[i].totalPrice}</li>
          		                    <li class="font-aqua">已付款</li>
          		                    <li>交易完成</li>
          		                </ul>
          						 `
          		}else{
          			txt+=`
           		       <div class="order-num">订单号：${list[i].serviceNo} 下单时间：${list[i].createTime}</div>
           		                <ul class="order-details">
           		                    <li>
           		                        <img src="./images/user-lg.png" alt="图片">
           		                        <ul>
           		                            <li>${str3}</li>          		     
           		                        </ul>		           
           		                    </li>
           		                    <li class="font-aqua">¥${list[i].totalPrice}</li>
           		                    <li class="font-aqua">未付款</li>
           		                      <li>
                            <p onclick="pay('${list[i].serviceNo}')">付款</p>
                            <span  value="${list[i].serviceNo}" onclick="dl('${list[i].serviceNo}')">删除订单</span>
                        </li>
           		                </ul>
       						 `
          		}		
          	}
			$(".list2").append(txt);		
			
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})

//付款
function pay(serviceNo){
	
	   sessionStorage.setItem("serviceNo",serviceNo);
		    location.href="redirect?page=e-commerce_settlement2";
}


//删除商品
 function dl(serviceNo){
    	$.ajax({
				//请求类型
				type:"post",
				//请求路径
				url:"/product/Productdelete",
				//返回数据类型
				data:{
					serviceNo:serviceNo,

				},
				dataType:"json",
				//请求成功后调用函数
				success:function(data){
					console.log("成功后返回的数据",data);
		 			location.href="redirect?page=e-commerce_order"
				},
				//请求失败后调用函数
				error:function(data){
					console.log("请求失败",data);
				}
})
    }
   


