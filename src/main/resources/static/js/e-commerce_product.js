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
	                url: '/product/gmgm',
	                type: 'get',
	              dataType:"json",
	              success:function(data){
	    	          	console.log("成功后返回的数据",data);
	    	          	login();
	    	        //	alert(data.msg);
	    	          	var providerProductInfo=data.providerProductInfo;
	    	        	$(".list").html("");
	    	          	var txt="";
	    	          	for(var i=0;i<providerProductInfo.length;i++){
	    	          		txt+= `    	          		
	    	          		    <div class="article" value="${providerProductInfo[i].id}">	    	          		    
	    	          		    <img class="ss" src="/pp/headImg2?id=${providerProductInfo[i].id}" onerror="defaultImg(this)" style="
    width: 50px;
    height: 50px;
    display: inline-block;
    border: 1px solid #e1e1e1;
	"/>
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
	$.ajax({
		type: "post",
		url: "/product/jkk",			
		data:{
			id:id,
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

function login(){
	var userid=sessionStorage.getItem("id");
	var username=sessionStorage.getItem("name");
	var status=sessionStorage.getItem("status");
	console.log(userid);
	if(status!=1){
		alert("请先登录");
		 location.href="redirect?page=operator_login"
	}
	
	$("#sysuser").html("");
	var txt="";
	txt +=username
	$("#sysuser").append(txt);
}

/*

$(".fa ").on("click", function(id){
	console.log("增加",id);
	
	$.ajax({
		type:"post",
		url:"/product/add",
		data:{
			id:id,
		}, 
		dataType:"json",
		
		
		success:function(data){
			console.log("成功返回后的数据",data);
			if(data.code==1){	
				$.ajax({
					type:"get",
					url:"/product/getproductinfo",
					data:{
						id:id,
					}, 
					dataType:"json",
					success:function(data){
						console.log("成功返回后的数据",data);
						var providerProductInfo=data.providerProductInfo;
						$("#cart").html("");
						var txt="";
						for(var i=0;i<providerProductInfo.length;i++){
							txt+=`<div class="article">
	    	     <img src="" alt="图片" />
                <ul class="article-info">
                <li>${providerProductInfo[i].serviceName}<</li>
                <li>${providerProductInfo[i].serviceContent}</li>
                <li>${providerProductInfo[i].providerName}</li>
                 </ul>
                 <ul class="article-price">
                <li>￥${providerProductInfo[i].price}	</li>
				 </ul>
			 </div> `;
						}
						console.log(txt);
						$("#cart").append(txt);
						},
						error:function(data){
							console.log("失败返回后的数据",data);	
					}
				})		
			}
				},
				
		
				error:function(data){
					console.log("失败返回后的数据",data);	
			}

	})	
})
*/
