$(function() {
    $('.slider').slick({
        dots: false,
        arrows: true,
        prevArrow: $('#prev'),
        nextArrow: $('#next'),
        infinite: true,
        autoplay: true,
        autoplaySpeed: 2000,
        speed: 300,
        // customPaging: function(slide, i) {
        //     return '<div class="slide-banner">' +
                        
        //                 '<span class="bn-item"> 테스트 버튼'+(i+1)+ '</span>' +
        //            '</div>'
        // }

        
    });
});