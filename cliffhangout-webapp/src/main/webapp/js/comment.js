
$(function(){
    //Gestion de l'ajout du formulaire lors de la création d'un commentaire enfant
    $('.answer').click(function()
    {
        //Dissimulation des boutons répondre du document au clic sur l'un d'entre eux
        $('.answer').hide();
        //Ajout du formulaire
        $(this).parent().parent().parent().after('<div class="row" id="answer"></div>');
        var formDOM = $('#commentForm').html();
        $('#answer').append(formDOM);
        $('#answer form').append('<input type="hidden" name="parent"/>');
        $("input[name='parent']").val($(this).attr('id'));
        $('#answer').append('<button type="button" class="btn btn-danger" style="margin-bottom:10px;" id="cancelAnswer">Annuler</button>');
        //On supprime le formulaire de réponse au clic sur un bouton annuler et on affiche les boutons répondre
        $('#cancelAnswer').click(function()
        {
            $('#answer').remove();
            $('.answer').show();
        });
    });
});