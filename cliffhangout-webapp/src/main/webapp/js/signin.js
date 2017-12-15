$(function(){
    $('form[data-toggle="validator"]').validator({
        custom: {
            filesize: function ($el) {
                var maxKilobytes = $el.data('filesize') * 1024;
                if ($el[0].files[0].size > maxKilobytes) {
                    return "Le fichier doit faire moins de " + $el.data('filesize') + " kB."
                }
            },

            //custom file type validation
            filetype: function ($el) {
                var acceptableTypes = $el.prop('accept').split(',');
                var fileType = $el[0].files[0].type;

                if (!$.inArray(fileType, acceptableTypes)) {
                    return "Invalid file type"
                }
            }
        }
    })
});