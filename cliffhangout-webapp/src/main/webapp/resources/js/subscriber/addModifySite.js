
$(function(){
    var cptSector= 0;
    var cptWay=0;
    var cptLength=0;
    var cptPoint=0;

    var sectorTemplate = $('#sectorTemplate');
    if(cptSector<1)
    {
        addSector(sectorTemplate);
    }

    $('#addSector').click(function(e)
    {
        addSector(sectorTemplate);
    });

    function addSector(sectorTemplate)
    {
        var sectorHTML = sectorTemplate.replace(/__IDX__/, cptSector);
        addDeleteSector(sectorHTML);
        $('#sectors').append(sectorHTML);
        cptSector++;
    }

    function addDeleteSector(sectorHTML)
    {
        var deleteSector = $('<button id="deleteSector['+cptSector+']" class="btn btn-danger">Supprimer Secteur</button>');
        sectorHTML.append(deleteSector);
        deleteSector.click(function(e)
        {
            if(cptSector>1)
            {
                sectorHTML.remove();
                e.preventDefault();
                cptSector--;
            }else{
                alert('Un site doit posséder au moins un secteur');
            }
        });
    }
});