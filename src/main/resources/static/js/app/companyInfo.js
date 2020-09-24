var companyInfo = {
    init: function () {
        var _this = this;
        $('#btn-companyInfo-save').on('click', function () {
            if(_this.check()===true) {
                _this.save();
            }
        });
        $('#btn-companyInfo-update').on('click', function () {
            if(_this.check()===true) {
                _this.update();
            }
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
    save: function () {
        var data = {
            companyName: $('#companyName').val(),
            companyEmail: $('#companyEmail').val(),
            companyPage: $('#companyPage').val(),
            managerId: $('#managerId').val(),
            companyLogo: $('#companyLogo').val()
        };
        $.ajax({
            type: 'POST',
            url: '/companyInfo/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('기업등록이 완료되었습니다.');
            window.location.href = '/companyUser';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data = {
            companyName: $('#companyName').val(),
            companyEmail: $('#companyEmail').val(),
            companyPage: $('#companyPage').val(),
            managerId: $('#managerId').val(),
            companyLogo: $('#companyLogo').val()
        };
        var companyNo = $('#companyNo').val()
        $.ajax({
            type: 'PUT',
            url: '/mypage/company-info-update/'+companyNo,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('기업 수정이 완료되었습니다.');
            window.location.href = '/mypage';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
companyInfo.init();