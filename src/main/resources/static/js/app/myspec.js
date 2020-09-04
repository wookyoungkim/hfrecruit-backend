var spec = {
    init : function () {
        var _this = this;
        $('#btn-spec-save').on('click', function (){
            alert('clicked');
            _this.save();
        });
    },
    save : function (){
        var data = {
            specId : $('#selectSpecId option:selected').attr('id'),
            authDate: $('#authDate').val(),
            score: $('#score').val(),
            certifiedDate: $('#certifiedDate').val()
        };
        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/specs/save/1',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('spec added');
            window.location.href = '/specs/1';
        }).fail(function (error){
            alert('error occured');
            alert(JSON.stringify(error));
        });
    }
};
spec.init();