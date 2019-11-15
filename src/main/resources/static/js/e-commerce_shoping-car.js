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
    $(".content-nav a").removeClass("nav-active");
    $(event.target).addClass("nav-active");
})
var totalprice1=0;
var cars=new Array();
var num1=new Array();
var carsname=new Array();
var n=0;

<<<<<<< HEAD
$( function(){
		var userid=sessionStorage.getItem("id")
			console.log("成功后返回的数据",userid);
		var username=sessionStorage.getItem("name")
	

   
	$.ajax({
			type:"get",
			url:"/product/getproductinfo",
			   data: {
				userid:userid,
			
             },
			dataType:"json",
			success:function(data){
//				{"product",[{name:"",product"{}}]}
				console.log("成功返回后的数据",data);
		       	var product=data.product;
		    	console.log("成功返数据",product[0].name);
				$(".list").html("");
				var txt="";		
				for(var i=0;i<product.length;i++){
					console.log("777",product[i].product[0].id);
//					car[${product[i].product[0].id}]
						
					 console.log("成功返数据",totalprice);
					 
					txt+=`
	      <p class="shop">店铺：${product[i].name}</p>`
	for(var j = 0; j < product[i].product.length ; j++){
		var totalprice=product[i].num*product[i].product[j].price;
		totalprice1+=product[i].num*product[i].product[j].price;
	
		cars[n]=product[i].product[j].id;
		num1[n]=product[i].product[j].num2;
		carsname[n]=product[i].product[j].serviceContent;
		n++;
		txt += `
	
        <div class="cart">
        <ul class="merchandise">
            <li>
                <img src="" alt="图片">
            </li>
            <li>${product[i].product[j].serviceContent}</li>
            <li>￥${product[i].product[j].price}</li>
            <li>
                <span onclick="reducenum('${product[i].product[j].id}','${product[i].product[j].num2}')">-</span>
                <input class="order1" value="${product[i].product[j].num2}"  onblur="changenum('${product[i].product[j].id}')"/>
                <span onclick="addnum('${product[i].product[j].id}','${product[i].product[j].num2}')">+</span>
            </li>
            <li>
     ${totalprice};
            </li>
            <li>
                <span value="${product[i].product[j].id}" onclick="dl('${product[i].product[j].id}')">删除</span>
            </li>
        </ul>
        </div>
         
        </div>
  
			 `;
				}
				}

				
				$(".list").append(txt);
				
				  sessionStorage.setItem("carsname",carsname);
					console.log("1111111111",carsname);	
			      sessionStorage.setItem("cars",cars);
			      sessionStorage.setItem("num1",num1);
			      
	  	$(".price").html("");
		var txt="";
		txt+=`
		       
		      <li>金额合计<span>¥${totalprice1}</span></li>
              <li> 
                <a href="redirect?page=e-commerce_product">继续购物</a>
                <a href="redirect?page=e-commerce_settlement" >去结算</a>
              </li>`;
	  	$(".price").append(txt);
	  	
	     sessionStorage.setItem("totalprice1",totalprice1);
	  	
						},
				error:function(data){
					console.log("失败返回后的数据",data);	
			}
		})
})

=======
>>>>>>> bfec8b7cdfd02ef53c978270ec49e3c1e8ba7e42

//删除商品
 function dl(id){
    	$.ajax({
				//请求类型
				type:"post",
				//请求路径
				url:"/product/providerProductdelete",
				//返回数据类型
				data:{
					id:id,

				},
				dataType:"json",
				//请求成功后调用函数
				success:function(data){
					console.log("成功后返回的数据",data);
		 			location.href="redirect?page=e-commerce_shoping-car"
				},
				//请求失败后调用函数
				error:function(data){
					console.log("请求失败",data);
				}
})
    }
   


//通过商品id减少商品数量
function reducenum(id,num){
	var userid=sessionStorage.getItem("id")
	console.log("成功后返回的数据",userid);
	
	var e=window.event;
//  	var id=$(".id").val();
  	console.log("serviceid",id);
	//var name=$(".name").val();
	var code=$(".code").val();
	 $.ajax({
		                url: "/product/reducenum",
		                type: "post",
		                data: {id:id, 
		    	                //  name:name,
		    	                  code:code,
		    	                  userid:userid,
		    						num:num,
		    	                  },
		    	          		dataType:"json",
		    	          		success:function(data){
		    	          			if(data.code==1){
		    	          				if($("."+id).val()<=1){
		    	          					alert("数量不能小于1");
		    	          				}else{
		    	          				$(e.target).next().val($(e.target).next().val()-1);
	
		    	          				}
		    	          			}
		    	          			location.href="redirect?page=e-commerce_shoping-car"
		    	          		},error:function(data){
		    						console.log("请求失败",data);
		    					}
		    	          		})
	
}


//通过商品id增加商品数量
function addnum(id,num){
	var userid=sessionStorage.getItem("id")
	console.log("成功后返回的数据",userid);
	
	var e=window.event;
//	var id=$(".id").val();
	console.log("serviceid",id);
	//var name=$(".name").val();
	var code=$(".code").val();
	 $.ajax({
		                url: "/product/addnum",
		                type: "post",
		                data: {id:id, 
		    	                //  name:name,
		    	                  code:code,
		    	                  userid:userid,
		    						num:num,
		    	                  },
		    	          		dataType:"json",
		    	          		success:function(data){
		    	          			if(data.code==1){
		    	          		
		    	          				$(e.target).prev().val(+$(e.target).prev().val()+1);
	
		    	          			}
		    	          			location.href="redirect?page=e-commerce_shoping-car"
		    	          		},error:function(data){
		    						console.log("请求失败",data);
		    					}
		    	          		})
	
}

function changenum(id){
	var e=window.event;
	console.log("8888888",e);
	$(e.target).val();
}







