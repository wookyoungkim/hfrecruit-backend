var userInfo = {
    init : function () {
        var _this = this;
        'use strict';
        // window.addEventListener('load', function() {
        //     // Fetch all the forms we want to apply custom Bootstrap validation styles to
        //     var forms = document.getElementsByClassName('needs-validation');
        //     // Loop over them and prevent submission
        //     var validation = Array.prototype.filter.call(forms, function(form) {
        //         form.addEventListener('submit', function(event) {
        //             if (form.checkValidity() === false) {
        //                 event.preventDefault();
        //                 event.stopPropagation();
        //             }
        //             form.classList.add('was-validated');
        //         }, false);
        //     });
        // }, false);
        $('#btn-userInfo-save').on('click', function (){
            _this.save();
        });
        $('#btn-userInfo-update').on('click', function (){
            _this.update();
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
    },
    update : function () {
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
            url: '/mypage/' + userNo,
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