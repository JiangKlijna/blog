window.cj = {
    onLoad: function () {
        for(var i = 0; i < 40; i++){
            $('#jqtest').append(i + '<br />')
        }
    },
}
$(cj.onLoad)