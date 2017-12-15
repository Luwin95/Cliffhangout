$(function(){
    $("#siteSectorNb").on("keyup keydown change",function(event){
        var nbSectorToAdd = $(this).val();
        $('#sectors').html("");
        for(var i=0; i<nbSectorToAdd; i++)
        {
            var sectorHTML = $('#sectorsTemplate').html().replace(/__IDX__/g, i).replace(/__REALIDX__/g, (i+1));
            $('#sectors').append(sectorHTML);
        }
        $('.waysNb').each(function(){
            $(this).on("keyup keydown change",function(event){
                var currentWay = $(this).attr("id");
                console.log(currentWay);
                currentWay = currentWay.match(/\[(.*?)\]/)[1];
                console.log(currentWay);
                var nbWayToAdd = $(this).val();
                console.log(nbWayToAdd);
                var lengthsContainer = $('#ways['+currentWay+']');
                console.log(lengthsContainer.html());
                lengthsContainer.html("");
                for(var i=0; i<nbWayToAdd; i++)
                {
                    var wayHTML = $('#waysTemplate').html();
                    wayHTML.replace(/__IDX__/g, i).replace(/__REALIDX__/g, (i+1)).replace(/__WAYIDX__/g, currentWay);
                    console.log(wayHTML);
                    lengthsContainer.append(wayHTML);
                }
            });
        });
    });
});