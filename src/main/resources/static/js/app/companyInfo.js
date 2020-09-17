var companyInfo = {
    init : function () {
        var _this = this;
        $('#btn-companyInfo-save').on('click', function (){
            _this.save();
        });
    },
    save : function (){
        var data = {
            companyName: $('#companyName').val(),
            companyEmail: $('#companyEmail').val(),
            companyPage: $('#companyPage').val(),
            managerId: $('#selectManager option:selected').attr('id'),
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