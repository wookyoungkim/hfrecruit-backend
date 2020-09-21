var main = {
    init : function () {
        var _this = this;
        $('#btn-logout').on('click', function () {
            _this.logout();
        });
        $('#btn-logoutbar').on('click', function () {
            _this.logout();
        });
        $('#btn-deletebar').on('click', function () {
            _this.withdrawal();
        });
        $('#btn-deleteUser').on('click', function () {
            _this.withdrawal();
        });

    },
    logout : function () {
        if(confirm("로그아웃 하시겠습니까?")) {
            window.location.href = "/logout";
        }
    },
    withdrawal : function () {

        if(confirm("정말로 회원 탈퇴 하시겠습니까?")) {
            $.ajax({
                type : 'PUT',
                url : '/user/delete'
            }).done(function () {
                alert('탈퇴 완료 되었습니다.');
                window.location.href = '/logout';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }
};

main.init();