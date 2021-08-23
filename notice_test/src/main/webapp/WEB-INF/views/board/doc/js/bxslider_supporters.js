$(function(){
    $('.family_site').bxSlider({
        ticker:true,
        tickerHover:true,
        useCSS:false,
        minSlides: 3,
        maxSlides: 7,
        speed: 20000,
         slideWidth: 170,
        slideMargin: 10
    });
});
$(window).scroll(function(){
    var scrollPos = $(window).scrollTop() / 3;
    $('.main_visual .slides li').css('background-position','50% '+scrollPos+'px');
});

