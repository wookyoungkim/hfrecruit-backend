var recruit = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });
        $('#btn-update').on('click', function (){
            _this.update();
        });
        $('#btn-delete').on('click', function (){
            _this.delete();
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
    },
    update : function () {
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
        var recruitNo = $('#recruitNo').val();
        $.ajax({
            type: 'PUT',
            url: '/api/v1/recruit/update/'+recruitNo,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('채용공고가 수정되었습니다.');
            window.location.href = '/recruit';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var recruitNo = $('#recruitNo').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/recruit/delete/'+recruitNo,
            dataType: 'json',
            contentType: 'application/json; charset-utf-8'
        }).done(function (){
            alert('채용공고가 삭제되었습니다.');
            window.location.href = '/recruit';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
recruit.init();