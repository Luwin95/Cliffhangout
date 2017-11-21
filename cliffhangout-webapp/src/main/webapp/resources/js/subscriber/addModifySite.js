$(function(){
    var cptSector= $('.sectorItem').length-1;

    var sectorTemplate = $('#sectorTemplate').html();
    var wayTemplate = $('#wayTemplate').html();


    addDeleteSector(cptSector);
    $('#addSector').click(function(e)
    {
        addSector(sectorTemplate);
        $('.addWay').click(function(e)
        {
            var currentSector = $(this).attr("id");
            currentSector = currentSector.match(/\[(.*?)\]/);
            console.log(currentSector);
            var cptWay = $('#sector['+currentSector+']Ways').length;
            addWay(wayTemplate, currentSector, cptWay);
        })
    });

    function addSector(sectorTemplate)
    {
        var sectorHTML = sectorTemplate.replace(/__IDX__/g, cptSector+1).replace(/__REALIDX__/g, (cptSector+1));
        $('#sectors').append(sectorHTML);
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

    function addWay(wayTemplate, currentSector, cptWay)
    {
        var wayHTML = wayTemplate.replace(/__IDX__/g, cptWay+1).replace(/__REALIDX__/g, cptWay+1).replace(/__SECTORIDX__/g, currentSector);
        $('#sector['+currentSector+']ways').append(wayHTML);
    }

    function addDeleteWay()
    {
        
    }


});