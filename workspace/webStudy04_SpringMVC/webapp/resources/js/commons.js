/**
 * 
 */
$(document).on("click", ".linkBtn", function(){
	let href = $(this).data("href");
	if(href)
		location.href=href;
}).css("cursor", "pointer");