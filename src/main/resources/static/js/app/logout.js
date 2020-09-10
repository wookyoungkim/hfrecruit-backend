var main = {
    init : function () {
        var _this = this;
        $('#btn-logout').on('click', function () {
            _this.logout();
        });
        $('#btn-logoutbar').on('click', function () {
            _this.logout();
        })
    },
    logout : function () {
        if(confirm("로그아웃 하시겠습니까?")) {
            window.location.href = "/logout";
        }
    }
};

main.init();