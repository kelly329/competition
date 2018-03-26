/**
 * Created by Chan on 17/3/9.
 */
$("#users").submit(function () {
    if ($('#pwd').val() != $('#pwds').val()) {
        alert('两次输入密码不一致。请重新输入。');
    } else {
        if (!$('#user').parent().hasClass('has-error')) {
            $.ajax({
                type: "post",
                url: '__CONTROLLER__/addusers',
                data: {
                    usr: $('#user').val(),
                    username: $('#name').val(),
                    pwd: hex_md5($('#pwd').val()),
                    role: $('#level1').val(),
                    school: $('#level2').val(),
                },
                dataType: "json",
//                async: true,
                success: function (data) {
                    switch (data['tag']) {
                        case 0:
                            alert('添加成功');
                            break;
                        case 2:
                            alert('用户名存在');
                            break;
                        default:
                            alert('网络错,请稍后重试');
                            break;
                    }
                },
                error: function (data) {
                    alert('网络错误');
                }
            });
        } else {
            alert('账户已存在。');
        }
    }
});

$(function () {
    $('#level1').change(function () {
        val = $(this).val();
        //学校管理员
        if (val == 3) {
            $.ajax({
                type: "GET",
                url: '__CONTROLLER__/findSchool',
//                    data: {fid: '44'},
                beforeSend: function () {
                    zeroModal.loading(6);
                },
                success: function (data) {
                    if (data['msg'] != false) {
                        $('#zhanwei').next().remove();
                        $('#zhanwei').after(data['str']);
                    } else {
                        $('#zhanwei').next().remove();
                    }
                    $('#level2').change(function () {
                        $('#name').val($("#level2 option:selected").text() + '-学校管理员');
                    });
                    zeroModal.closeAll();
                }
            });
        }
        //年级管理员
        else if (val == 4) {
            $.ajax({
                type: "GET",
                url: '__CONTROLLER__/findGrade',
                beforeSend: function () {
                    zeroModal.loading(6);
                },
                success: function (data) {
                    if (data['msg'] != false) {
                        $('#zhanwei').next().remove();
                        $('#zhanwei').after(data['str']);
                    } else {
                        $('#zhanwei').next().remove();
                    }
                    $('#level2').change(function () {
                        $('#name').val($("#level2 option:selected").text() + '-年级管理员');
                    });
                    zeroModal.closeAll();
                }
            });
        }
        else {
            $('#zhanwei').next().remove();
        }

    });
    $('#user').change(function () {
        $.ajax({
            type: "GET",
            url: '__CONTROLLER__/checkName',
            data: {name: $('#user').val()},
            success: function (data) {
                console.log(data);
                if (data['tag'] == '1') {
                    if ($('#user').parent().hasClass('has-error')) {
                        $('#user').parent().removeClass('has-error');
                        $('#result').removeClass('result-error');
                    }
                } else {
                    $('#user').parent().addClass('has-error');
                    $('#result').addClass('result-error');
                }
            }
        });
    });

})

