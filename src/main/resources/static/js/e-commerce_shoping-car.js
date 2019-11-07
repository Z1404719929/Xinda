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




$( function(){
	var userid=sessionStorage.getItem("id");
	$.ajax({
			type:"post",
			url:'/product/getproductinfo',
			   data: {
				userid:userid,
             },
			dataType:"json",
			success:function(data){
				console.log("成功返回后的数据",data);
		       	var providerProductInfo=data.providerProductInfo;
		    	console.log("成功返回后的数据",providerProductInfo);
				$("#cart").html("");
				var txt="";
				for(var i=0;i<providerProductInfo.length;i++){
					txt+=`
	    	    <ul class="merchandise">
            <li>
                <img src="" alt="图片">
            </li>
            <li>${providerProductInfo[i].providerName}</li>
            <li>￥${providerProductInfo[i].price}	</li>
            <li>
                <span onclick="reducenum('1234','order1')">-</span>
                <input class="order1" value="1"  onblur="changenum('1234')"/>
                <span onclick="addnum('1234')">+</span>
            </li>
            <li>
             ￥${providerProductInfo[i].price}	
            </li>
            <li>
                <span>删除</span>
            </li>
        </ul>
			 `;
				}

				$("#cart").append(txt);
						},
				error:function(data){
					console.log("失败返回后的数据",data);	
			}
		})
})



function reducenum(id){
	var e=window.event;
  	var id=$(".id").val();
	//var name=$(".name").val();
	var code=$(".code").val();
	 $.ajax({
		                url: '/login/shopping',
		                type: 'post',
		                data: {id:id, 
		    	                //  name:name,
		    	                  code:code,},
		    	          		dataType:"json",
		    	          		success:function(data){
		    	          			if(data.code==1){
		    	          				if($("."+id).val()<=1){
		    	          					alert("数量不能小于1");
		    	          				}else{
		    	          					$(e.target).next().val($(e.target).next().val()-1);
		    	          				}
		    	          			}
		    	          		},
		    	          		})
	
}

function addnum(id){
	var e=window.event;
	
	$(e.target).prev().val(+$(e.target).prev().val()+1);
}

function changenum(id){
	var e=window.event;
	
	$(e.target).val();
}

