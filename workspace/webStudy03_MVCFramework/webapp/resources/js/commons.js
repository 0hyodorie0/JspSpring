/**
 * 
 */
$(".linkBtn").on("click", function(){
	let href = $(this).data("href");
	if(href)
		location.href=href;
}).css("cursor", "pointer");