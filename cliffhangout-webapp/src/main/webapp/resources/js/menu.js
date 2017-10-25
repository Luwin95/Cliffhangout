$(document).ready(function(){
    $('.burger').click(function(){
        $(this).toggleClass('cross');
        $('.menu').toggleClass('isOpen');
        $('.page').toggleClass('isAside');
        $('.page-cache').toggleClass('isAside');
        $('body').toggleClass('isAside');
    });

    $('.page-cache').click(function(){
        $(this).toggleClass('isAside');
        $('.page').toggleClass('isAside');
        $('.burger').toggleClass('cross');
        $('.menu').toggleClass('isOpen');
        $('body').toggleClass('isAside');
    });
});