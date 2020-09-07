var main = {
    init : function () {
        var _this = this;
        $('#btn-application-save').on('click', function () {
            _this.save();
        })
        $('#btn-application-edit').on('click', function () {
            _this.edit();
        })
        $('#btn-delete-application').on('click', function () {
            _this.delete();
        })
    },
    save : function () {
        var data = {
            bit : 0,
            q1Comment : $("#q1Comment").val(),
            q2Comment : $("#q2Comment").val(),
            q3Comment : $("#q3Comment").val(),
            score : 0,
            passStage : 0,
            passOrFail : 0
        };
        var recruitNo = $("#recruitNo").val()
        console.log(data)
        console.log(recruitNo)

        $.ajax({
            type : 'POST',
            url : '/application/apply/'+ recruitNo,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/mypage';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    edit : function () {
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
    },
    delete : function () {
        var applicationId = $("#applicationId").val();
        console.log(applicationId)
        $.ajax({
            type : 'DELETE',
            url : '/application/delete/'+ applicationId,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/application/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();