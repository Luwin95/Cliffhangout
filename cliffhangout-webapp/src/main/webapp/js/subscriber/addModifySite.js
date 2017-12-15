$(function(){
    var cptSector= $('.sectorItem').length-1;

    var sectorTemplate = $('#sectorTemplate').html();
    var wayTemplate = $('#wayTemplate').html();



    addDeleteSector(cptSector);
    $('#addSector').click(function(e)
    {
        addSector(sectorTemplate);
    });

    $('.addWay').each(function () {
        $(this).click(function(e)
        {
            var currentSector = $(this).attr("id");
            currentSector = currentSector.match(/\[(.*?)\]/)[1];
            console.log(currentSector);
            var selectorName = "#ways"+currentSector;
            var cptWay = $(selectorName).children().length;
            console.log(cptWay);
            addWay(wayTemplate, currentSector, cptWay);
        });
    });

    function addSector(sectorTemplate)
    {
        var sectorHTML = sectorTemplate.replace(/__IDX__/g, cptSector+1).replace(/__REALIDX__/g, (cptSector+1));
        $('#sectors').append(sectorHTML);
        addDeleteSector(cptSector);
        //addWayAdding(cptSector);
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

    function addWayAdding(cptSector)
    {
        $('#addWay['+(cptSector)+']').click(function(e)
        {
            var currentSector = $(this).attr("id");
            currentSector = currentSector.match(/\[(.*?)\]/)[1];
            console.log(currentSector);
            var cptWay = $('#sector['+currentSector+']Ways').length;
            console.log(cptWay);
            addWay(wayTemplate, currentSector, cptWay);
        });
    }

    function addWay(wayTemplate, currentSector, cptWay)
    {
        var wayHTML = wayTemplate.replace(/__IDX__/g, cptWay).replace(/__REALIDX__/g, cptWay+1).replace(/__SECTORIDX__/g, currentSector);
        console.log(wayHTML);
        var selectorName = "#ways"+currentSector;
        console.log(selectorName);
        $(selectorName).append(wayHTML);
        addDeleteWay(currentSector, cptWay);
    }

    function addDeleteWay(currentSector, cptWay)
    {
        $('#deleteSector['+currentSector+']Way['+cptWay+']').click(function(e)
        {
            $('#way['+cptWay+']').remove();
            e.preventDefault();
            cptWay--;
        })
    }
});