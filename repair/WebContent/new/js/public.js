$(function(){
    $(".index-menu div").click(function(){
        $(".index-menu span").removeClass("on");
        $(this).find("span").addClass("on");
    });
    var listBgHight = $(".list-main-bg img").height();
    $(".list-main-bg").height(listBgHight);
	$(window).resize(function() {
        var listBgHight = $(".list-main-bg img").height();
        $(".list-main-bg").height(listBgHight);
});
})