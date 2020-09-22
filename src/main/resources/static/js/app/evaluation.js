var main = {
    init: function () {
        var _this = this;
        $('#btn-application-evaluate').on('click', function () {
            _this.evaluate();
        })
    },
    evaluate: function () {
        var passOrFail = 0;
        if ($("#passOrFail").val() == '합격') passOrFail = 1;

        var data = {
                bit: 1,
                q1Feedback: $("#q1Feedback").val(),
                q2Feedback: $("#q2Feedback").val(),
                q3Feedback: $("#q3Feedback").val(),
                score: 0,
                passStage: 0,
                passOrFail: passOrFail
    };

        var applicationId = $("#applicationId").val()
        var recruitNo = $("#recruitNo").val()

        $.ajax({
            type: 'PUT',
            url: '/evaluate/' + applicationId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('평가가 등록되었습니다.');
            window.location.href = '/application/list/' + recruitNo;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();