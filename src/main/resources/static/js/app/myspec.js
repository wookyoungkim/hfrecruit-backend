var spec = {
    init : function () {
        var _this = this;
        $('#btn-spec-save').on('click', function (){
            _this.save();
        });
        $('#btn-spec-delete').on('click', function (){
            _this.delete();
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
    },
    delete : function (){
        var personalSpecId = $('#btn-spec-delete').attr("name");
        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/specs/delete/'+personalSpecId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function (){
            alert('spec deleted');
            window.location.href = '/specs/1';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
spec.init();