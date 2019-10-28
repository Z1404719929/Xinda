$(function() {
	var img = document.getElementsByClassName("img")[0];
	img.src = "imgGetCode";
})
function imgChange() {
	var img = document.getElementsByClassName("img")[0];
	var time = new Date().getTime();
	img.src = "imgGetCode?t=" + time;
}