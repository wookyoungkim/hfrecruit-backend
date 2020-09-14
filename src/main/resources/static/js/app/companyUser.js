var companyUser = {
    init : function () {
        var _this = this;
        $('#btn-companyUser-save').on('click', function (){
            _this.save();
        });
    },
    save : function (){
        var data = {
            companyNo: $('#selectMyCompany option:selected').attr('id'),
            companyUserCode: $('#companyUserCode option:selected').attr('id')
        };
        var companyNo = $('#selectMyCompany option:selected').attr('id')
        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/companyUser/save/'+companyNo,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('기업 회원 등록이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
companyUser.init();