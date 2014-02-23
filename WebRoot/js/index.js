+(function($){
	// the sidenav action
	var $sidenavTitle = $(".sidenav-title")
	$sidenavTitle.on("click",function(event){
		event.preventDefault();
		var $this = $(this);
		$("#conten-title").text($this.text());
		slideAnimate($this);
	})

	$sidenavTitle.next().find("a").on("click", function(event){
		event.preventDefault();
		var href = $(this).attr("href");
		var temptext = $(this).parents(".sidenav-item").find(".sidenav-title").text();
		$("#conten-title").text(temptext);
		$("iframe").attr("src",href);
	})

	// set the content width
	var screenWidth = $(window).width(),
		screenHeight = $(window).height();
	$("#content").css({"width":screenWidth-300});

	// set the body with
	$("body").css({"width":screenWidth,"height":screenHeight});

	// set the iframe height
	$("#frame").load(function(){
		$(this).height(screenHeight-150);
		var height = $(this).contents().height();
		$("#frame").height( height < 550 ? 550 : height ); 
	})
	// the slide animate
	function slideAnimate(element){
		element.next().slideToggle(200);
	}


})(window.jQuery)
