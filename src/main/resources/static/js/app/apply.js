var main = {
    init : function () {
        var _this = this;
        $('#btn-application-save').on('click', function () {
            _this.save();
        })
    },
    save : function () {
        var data = {
            bit: 0,
            q1Comment : $("#q1Comment").val(),
            q2Comment : $("#q2Comment").val(),
            q3Comment : $("#q3Comment").val()
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
    }
};

main.init();