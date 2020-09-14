var companyInfo = {
    init : function () {
        var _this = this;
        $('#btn-companyInfo-save').on('click', function (){
            console.log("nnnnnnn");

            _this.save();
        });
    },
    save : function (){
        var data = {
            companyName: $('#companyName').val(),
            companyEmail: $('#companyEmail').val(),
            managerId: $('#managerId').val(),
            companyLogo: $('#companyLogo').val()
        };
        var companyNo = $('#companyNo').val();
        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/companyInfo/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('기업등록이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
companyInfo.init();