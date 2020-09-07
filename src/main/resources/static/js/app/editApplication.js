var main = {
    init : function () {
        var _this = this;
        $('#btn-application-edit').on('click', function () {
            _this.save();
        })
    },
    save : function () {
        var data = {
            q1Comment : $("#q1Comment").val(),
            q2Comment : $("#q2Comment").val(),
            q3Comment : $("#q3Comment").val()
        };
        var applicationId = $("#applicationId").val();
        console.log(data)

        $.ajax({
            type : 'PUT',
            url : '/application/edit/'+ applicationId,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/application/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();