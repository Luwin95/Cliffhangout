$(function(){
    var cptLength= $('.lengthItem').length;
    var lengthTemplate = $('#lengthsTemplate').html();

    $("#wayLengthsNb").on("keyup keydown change",function(event){
        var nbLengthToAdd = $(this).val();
        $('#lengths').html("");
        for(var i=0; i<nbLengthToAdd; i++)
        {
            var lengthsHTML = $('#lengthsTemplate').html().replace(/__IDX__/g, i).replace(/__REALIDX__/g, (i+1));
            $('#lengths').append(lengthsHTML);
        }
    });

    /*$('.deleteLength').each( function(){
        $(this).click(function(e)
        {
            if(cptLength>1)
            {
                $('#length['+cpt+']').remove();
                e.preventDefault();
                cptLength--;
            }else{
                alert('Une voie doit posséder au moins une longueur');
            }
        });
    });

    $('#addLength').click(function(e)
    {
        addLength(lengthTemplate);
    });

    function addLength(lengthTemplate)
    {
        var lengthHTML = lengthTemplate.replace(/__IDX__/g, cptSector+1).replace(/__REALIDX__/g, (cptSector+1));
        $('#lengths').append(lengthHTML);
        addDeleteLength(cptLength);
        //addWayAdding(cptSector);
        cptLength++;
    }

    function addDeleteLength(cpt)
    {
        $('#deleteLength['+cpt+']').click(function(e)
        {
            if(cptLength>1)
            {
                $('#length['+cpt+']').remove();
                e.preventDefault();
                cptLength--;
            }else{
                alert('Une voie doit posséder au moins une longueur');
            }
        });
    }*/
});