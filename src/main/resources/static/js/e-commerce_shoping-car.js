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

