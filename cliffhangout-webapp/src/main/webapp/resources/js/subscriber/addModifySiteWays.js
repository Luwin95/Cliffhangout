$(function(){
    var cptSector= $('.sectorItem').length-1;
    var wayTemplate = $('#wayTemplate').html();

    $('.addWay').each(function () {
        $(this).click(function(e)
        {
            var currentSector = $(this).attr("id");
            currentSector = currentSector.match(/\[(.*?)\]/)[1];
            console.log(currentSector);
            var selectorName = "#ways"+currentSector;
            var cptWay = $(selectorName).length-1;
            addWay(wayTemplate, currentSector, cptWay);
        });
    });

    $('.deleteWay').each(function () {
        $(this).click(function(e)
        {
            var currentSector = $(this).attr("id");
            currentSector = currentSector.match(/\[(.*?)\]/)[1];
            console.log(currentSector);
            var selectorName = "#ways"+currentSector;
            //var currentWay = currentSector.match(/^.*\[\]\[(.*?)\]$/gm)[1];
            //console.log(currentWay);
            var wayToDelete = $(this).parent().parent();
            var ways = wayToDelete.parent();
            console.log(ways.children().length);
            if(ways.children().length > 1)
            {
                wayToDelete.remove();
                var cptWay=0;
                ways.find('h3').each(function() {
                    $(this).text('Voie n°'+(cptWay+1));
                    cptWay++;
                });
            }
        });
    });

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
        console.log($("#sector["+(currentSector)+"]Ways").html());
        //$('#sector['+currentSector+']Ways').append(wayHTML);
        var selectorName = "#ways"+currentSector;
        console.log(selectorName);
        var $waysSelector = $(selectorName);
        console.log($waysSelector.html());
        if($waysSelector.length)
        {
            console.log("ça marche");
        }
        $waysSelector.append(wayHTML);
        addDeleteWay(currentSector, cptWay);
        cptWay++;
    }

    function addDeleteWay(currentSector, wayToDelete, button)
    {
        button.click(function(e)
        {

            e.preventDefault();
        })
    }
});