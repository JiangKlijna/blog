window.cj = {
    onLoad: function () {
        for(var i = 0; i < 40; i++){
            $('#articles').append(i + '<br />')
        }
    },
}
$(cj.onLoad)