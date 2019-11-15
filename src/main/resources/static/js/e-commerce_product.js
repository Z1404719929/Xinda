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

$(".content-nav li").on("click", function(event){
    $(".content-nav li").removeClass("nav-active");
    $(event.target).addClass("nav-active");
})

$( function(){
	    $.ajax({
	                url: "/product/gmgm",
	                type: "get",
	              dataType:"json",
	              success:function(data){
	      			var userid=sessionStorage.getItem("id")
	      			console.log("成功后返回的数据",userid);
	    			var username=sessionStorage.getItem("name")
	    	          	console.log("成功后返回的数据",data);
	    	        //	alert(data.msg);
	    	          	var providerProductInfo=data.providerProductInfo;
	    	        	$(".list").html("");
	    	          	var txt="";
	    	          	for(var i=0;i<providerProductInfo.length;i++){
	    	          		txt+= `    	          		
	    	          		    <div class="article" value="${providerProductInfo[i].id}">	    	          		    
	    	          		    <img src="" alt="图片" />
            <ul class="article-info">
                <li>${providerProductInfo[i].serviceName}</li>
                <li>${providerProductInfo[i].serviceContent}</li>
                <li>${providerProductInfo[i].providerName}</li>
            </ul>
            <ul class="article-price">
                <li>￥${providerProductInfo[i].price}	</li>
                <li>
                    <a href="redirect?page=e-commerce_settlement">立即购买</a>
                    <a href="redirect?page=e-commerce_shoping-car" class="fa fa-shopping-cart fa-lg" onclick=zt('${providerProductInfo[i].id}')>  加入购物车</a>
                  
                </li>     
            </ul></div>  `;
	    	          	}
	    	        	$(".list").append(txt);
	    	  
	                },
	            		error:function(data){
			console.log("失败返回后的数据",data);	
		}              
	})
})


//模糊查询
$(".search-btn").on("click",function(){
	
	console.log("查询内容",$(".select").val());
	var name=$(".select").val();
	    $.ajax({
	                url: "/product/select",
	                type: "get",
		data:{
			name:name,
		},
	              dataType:"json",
	              success:function(data){
	      			var userid=sessionStorage.getItem("id")
	      			console.log("成功后返回的数据",userid);
	    			var username=sessionStorage.getItem("name")
	    	          	console.log("成功后返回的数据",data);
	    	        //	alert(data.msg);
	    	          	var providerProductInfo=data.providerProductInfo;
	    	        	$(".list").html("");
	    	          	var txt="";
	    	          	for(var i=0;i<providerProductInfo.length;i++){
	    	          		txt+= `    	          		
	    	          		    <div class="article" value="${providerProductInfo[i].id}">	    	          		    
	    	          		    <img src="" alt="图片" />
            <ul class="article-info">
                <li>${providerProductInfo[i].serviceName}</li>
                <li>${providerProductInfo[i].serviceContent}</li>
                <li>${providerProductInfo[i].providerName}</li>
            </ul>
            <ul class="article-price">
                <li>￥${providerProductInfo[i].price}	</li>
                <li>
                    <a href="redirect?page=e-commerce_settlement">立即购买</a>
                    <a href="redirect?page=e-commerce_shoping-car" class="fa fa-shopping-cart fa-lg" onclick=zt('${providerProductInfo[i].id}')>  加入购物车</a>
                  
                </li>     
            </ul></div>  `;
	    	          	}
	    	        	$(".list").append(txt);
	    	  
	                },
	            		error:function(data){
			console.log("失败返回后的数据",data);	
		}              
	})
})






//网页得到id
 function zt(id){
	console.log("id",id);
	var userid=sessionStorage.getItem("id")
	var username=sessionStorage.getItem("name")
	$.ajax({
		type: "post",
		url: "/product/jkk",			
		data:{
			id:id,
			userid:userid,
		},
		dataType: "json",
		success: function(data){

			console.log("成功后返回的数据",data);
			 location.href="redirect?page=e-commerce_shoping-car"
				
		},error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
}

