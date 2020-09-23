var recruit = {
    init : function () {
        var _this = this;
        //_this.closeChecker();
        $('#move-recruit-save').on('click', function (){
            _this.check_role();
        });
        $('#move-recruit-update').on('click', function (){
            _this.check_writer();
        });
        $('#btn-save').on('click', function (){
            if(_this.check_time()) {
                _this.save();
            }
        });
        $('#btn-update').on('click', function (){
            if(_this.check_time()) {
                _this.update();
            }
        });
        $('#btn-delete').on('click', function (){
            _this.delete();
        });
    },
    check_time : function (){
        var start = $('#startDate').val();
        var close = $('#closedDate').val();
        if(start>close) {
            alert('시간 입력이 올바르지 않습니다.');
            return false;
        }else return true;
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
    check_role : function(){
        $.ajax({
            type: 'GET',
            url: '/recruit/save',
            contentType: 'application/json; charset=utf-8'
        }).fail(function(error){
            alert('기업유저만 채용공고를 등록할 수 있습니다')
            window.location.href = '/recruit';
        });
    },
    check_writer : function(){
        var id = $('#recruitNo').val();
        $.ajax({
            type: 'GET',
            url: '/recruit/update/'+id,
            contentType: 'application/json; charset=utf-8'
        }).fail(function(error){
            alert('작성자만 수정할 수 있습니다.')
            window.location.href = '/recruit/'+id;
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
            alert('입력 값이 옳지 않습니다. 다시 입력해주세요.');
        });
    },
    "formatting": function () {
        return function (rawDate){
            return rawDate.toISOString();
        }
    },
    delete : function(){
        var id = $('#recruitNo').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/recruit/delete/'+id,
            contentType: 'application/json; charset=utf-8'
        }).done(function (){
            alert('채용공고가 삭제되었습니다.');
            window.location.href = '/recruit';
        }).fail(function(error){
            alert('작성자만 삭제할 수 있습니다.')
            window.location.href = '/recruit/'+id;
        });
    }
};
recruit.init();