$(function(){
    var cptSector= $('.sectorItem').length;
    var cptWay=0;
    var cptLength=0;
    var cptPoint=0;


    var sectorTemplate = $('#sectorTemplate').html();
    if(cptSector<1)
    {
        addSector(sectorTemplate)
    }

    addDeleteSector(cptSector);
    $('#addSector').click(function(e)
    {
        addSector(sectorTemplate);
    });

    function addSector(sectorTemplate)
    {
        var sectorHTML = sectorTemplate.replace(/__IDX__/g, cptSector).replace(/__REALIDX__/g, (cptSector+1));
        $('#sectors').append(sectorHTML);
        console.log(sectorHTML);
        addDeleteSector(cptSector);
        cptSector++;
    }

    function addDeleteSector(cpt)
    {
        $('#deleteSector['+cpt+']').click(function(e)
        {
            if(cptSector>1)
            {
                $('#sector['+cpt+']').remove();
                e.preventDefault();
                cptSector--;
            }else{
                alert('Un site doit posséder au moins un secteur');
            }
        });
    }
});