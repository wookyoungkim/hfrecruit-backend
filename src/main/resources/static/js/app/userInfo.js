var userInfo = {
    init : function () {
        var _this = this;
        'use strict';
        $('#btn-userInfo-save').on('click', function (){
            if(_this.check()===true) {
                _this.save();
            }
        });
        $('#btn-userInfo-update').on('click', function (){
            _this.update();
        });
    },
    check: function () {
        if (document.getElementById("gridCheck").checked === false) {
            alert('개인정보제공 동의가 필요합니다.');
            return false;
        }else{
            return true;
        }
    },
    save : function (){
        var check_count = document.getElementsByName("gender").length;
        var gender = "";
        for (var i=0; i<check_count; i++) {
            if (document.getElementsByName("gender")[i].checked == true) {
                gender = document.getElementsByName("gender")[i].value;
            }
        }
        var data = {
            username: $('#username').val(),
            birth: $('#birth').val(),
            address: $('#address').val(),
            college: $('#college').val(),
            highschool: $('#highschool').val(),
            educationLevel: $('#educationLevel').val(),
            militaryService: $('#militaryService').val(),
            gender: gender
        };
        console.log(data);
        $.ajax({
            type: 'PUT',
            url: '/userInfo/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('회원가입이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var check_count = document.getElementsByName("gender").length;
        var gender = "";
        for (var i=0; i<check_count; i++) {
            if (document.getElementsByName("gender")[i].checked == true) {
                gender = document.getElementsByName("gender")[i].value;
            }
        }
        var data = {
            username: $('#username').val(),
            birth: $('#birth').val(),
            address: $('#address').val(),
            college: $('#college').val(),
            highschool: $('#highschool').val(),
            educationLevel: $('#educationLevel').val(),
            militaryService: $('#militaryService').val(),
            gender: gender
        };
        console.log(data);
        $.ajax({
            type: 'PUT',
            url: '/mypage/infoUpdate',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원정보 수정이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
userInfo.init();