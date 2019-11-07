$(".login-btn").on("click", function(){
    location.href="redirect?page=service_product"
})
$(function() {
	var img = document.getElementsByClassName("img")[0];
	img.src = "imgGetCode";
	console.log(img.src);
})
function imgChange() {
	var img = document.getElementsByClassName("img")[0];
	var time = new Date().getTime();
	img.src = "imgGetCode?t=" + time;
	console.log(img.src);
}