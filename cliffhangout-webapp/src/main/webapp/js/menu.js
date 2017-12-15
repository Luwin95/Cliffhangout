$(document).ready(function(){
    $('.burger').click(function(){
        $(this).toggleClass('cross');
        $('.menu').toggleClass('isOpen');
        $('.page').toggleClass('isAside');
        $('.page-cache').toggleClass('isAside');
        $('body').toggleClass('isAside');
        if( $('body').hasClass('isAside') && $('.menu-barre').hasClass('isAbove'))
        {
            $('.menu-barre.isAbove').css("background-color", "rgba(0,0,0,0)")
        }else{
            $('.menu-barre.isAbove').css("background-color", "rgba(0,0,0,0.8)")
        }
    });

    $('.page-cache').click(function(){
        $(this).toggleClass('isAside');
        $('.page').toggleClass('isAside');
        $('.burger').toggleClass('cross');
        $('.menu').toggleClass('isOpen');
        $('body').toggleClass('isAside');
        if( $('body').hasClass('isAside') && $('.menu-barre').hasClass('isAbove'))
        {
            $('.menu-barre.isAbove').css("background-color", "rgba(0,0,0,0)")
        }else{
            $('.menu-barre.isAbove').css("background-color", "rgba(0,0,0,0.8)")
        }
    });

    //Lorsque l'utilisateur descend dans la page
    $(window).scroll(function (event) {
        // On récupère la position vertical à laquelle se trouve l'utilisateur
        var y = $(this).scrollTop();

        //Une fois que l'utilisateur a dépassé les 70 pixels de hauteur on affiche la barre de menu
        if (y >= 70) {
            $('.menu-barre').addClass('isAbove');
        } else {
            // Sinon on cache le menu
            $('.menu-barre').removeClass('isAbove');
        }
    });
});