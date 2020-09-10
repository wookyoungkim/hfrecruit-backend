var userInfo = {
    init : function () {
        var _this = this;
        $('#btn-userInfo-save').on('click', function (){
            _this.save();
        });
    },
    save : function (){
        var check_count = document.getElementsByName("gender").length;
        var gender = 0;
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
        var userNo = $('#userNo').val();
        console.log(data);
        $.ajax({
            type: 'PUT',
            url: '/userInfo/'+userNo,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('회원가입이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
userInfo.init();