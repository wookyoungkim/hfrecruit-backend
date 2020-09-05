var recruit = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });
    },
    save : function (){
        var data = {
            closedDate: $('#closedDate').val(),
            question1: $('#question1').val(),
            question2: $('#question2').val(),
            question3: $('#question3').val(),
            question4: $('#question4').val(),
            question5: $('#question5').val(),
            recruitContent: $('#recruitContent').val(),
            recruitTitle: $('#recruitTitle').val(),
            startDate: $('#startDate').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/recruit',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('채용공고가 등록되었습니다.');
            window.location.href = '/recruit';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
recruit.init();