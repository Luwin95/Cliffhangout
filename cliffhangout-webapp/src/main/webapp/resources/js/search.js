$(document).ready(function(){
    $('#addCriteria').click(function(){
        if($(this).prop('checked') == true)
        {
            $('.criterias').show();
            $('.criterias-cotation').hide();
            $('.criterias-location').hide();
            $('.criterias-ways').hide();
        }else{
            $('#criteria-location').prop('checked', false);
            $('#criteria-cotation').prop('checked', false);
            $('#criteria-ways').prop('checked', false);
            $('.criterias').hide();

        }
    });

    $('#criteria-location').click(function(){
        if($(this).prop('checked') == true)
        {
            $('.criterias-location').show();

        }else{
            $('.criterias-location').hide();
        }
    });

    $('#criteria-cotation').click(function(){
        if($(this).prop('checked') == true)
        {
            $('.criterias-cotation').show();

        }else{
            $('.criterias-cotation').hide();
        }
    });

    $('#criteria-ways').click(function(){
        if($(this).prop('checked') == true)
        {
            $('.criterias-ways').show();

        }else{
            $('.criterias-ways').hide();
        }
    });
});