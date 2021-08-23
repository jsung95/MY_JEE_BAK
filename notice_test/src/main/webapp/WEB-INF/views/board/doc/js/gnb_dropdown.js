$(function() {
    $(".gnb_bar").hide();

    $("#gnb_menu_gnb").hover(
        function() {
                           
            $(".gnb_bar").animate({height: "300px"},'fast').clearQueue();
            $(".gnb_bar").fadeIn('slow',function(){$(this).stop();});

        },

        function() {
            $(".gnb_bar").fadeOut('fast');
            $(".gnb_bar").animate({height: "80px"},'fast').clearQueue();
        }
    );
});