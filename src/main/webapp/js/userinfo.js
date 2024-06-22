$(function () {
    userInfoLoad();
    updateUserInfo();

    let temp = parseInt(10000 * Math.random());
    $('#user_avatar').attr('src', 'http://localhost:8080/fe_blog/images/avatar/avatar.jpg' + '?temp=' + temp);
    $('.user_avatar>img').attr('src', 'http://localhost:8080/fe_blog/images/avatar/avatar.jpg' + '?temp=' + temp);
    layui.use(['laydate', 'upload', 'form'], function () {
        let laydate = layui.laydate;
        let upload = layui.upload;
        let form = layui.form;


        //日期选择
        laydate.render({
            elem: "#date",
            value: "2001-09-06",
            format: "yyyy-MM-dd"
        })


        //头像上传
        upload.render({
            elem: "#upload_avatar",
            url: "/fe_blog/UserUpdateAvatarController",
            field: 'avatar',
            accept: "images",
            size: 4096,
            data: {
                'user_id': 1
            },
            done: function (res) {
                //请求成功回掉函数
                layui.use('layer', function () {
                    let layer = layui.layer;
                    layer.msg('上传成功！', {
                        icon: 6,
                        time: 1000
                    })
                })

            },
            progress: function (n, elem, res, index) {
                var percent = n + '%' //获取进度百分比
                console.log(percent);
                let layer;
                layui.use('layer', function () {
                    layer = layui.layer;
                    layer.load();
                })
                if (percent == '100%') {
                    setTimeout(function () {
                        parent.layer.closeAll()
                        let temp = parseInt(10000 * Math.random());
                        $('#user_avatar').attr('src', 'http://localhost:8080/fe_blog/images/avatar/avatar.jpg' + '?temp=' + temp);
                        $('.user_avatar>img').attr('src', 'http://localhost:8080/fe_blog/images/avatar/avatar.jpg' + '?temp=' + temp);
                    }, 2000);

                }
            },
            error: function (res) {
                //请求失败回调函数
                layui.use('layer', function () {
                    let layer = layui.layer;
                    layer.msg('上传失败！', {
                        icon: 2,
                        time: 1000
                    })
                })
            }
        })
    })
})

function updateUserInfo() {
    $('#update_userInfo').on('submit', function (e) {
        e.preventDefault();
        let data = $(this).serializeArray();
        console.log(data[1].value);
        $.ajax({
            type: "POST",
            url: '/fe_blog/userUpdateServlet',
            data: {
                'nick': $('input[name="nickname"]').val(),
                'sex':  data[1].value,
                'date':  $('input[name="date"]').val(),
                'profile':  $('textarea[name="profile"]').val()
            },
            dataType: 'json',
            success: function (res) {
                layui.use('layer', function () {
                    let layer = layui.layer;
                    layer.msg('修改成功！', {
                        icon: 6,
                        tiem: 1000
                    })
                    userInfoLoad();
                })
            },
            error: function () {
                layui.use('layer', function () {
                    let layer = layui.layer;
                    layer.msg('服务器错误！', {
                        icon: 2,
                        tiem: 1000
                    })
                })
            }
        })
    })
}


function userInfoLoad() {
    $.ajax({
        type: 'GET',
        url: '/fe_blog/SelectUserServlet',
        data: {},
        dataType: 'json',
        success: function (res) {
            console.log(res);
            let date = new Date(res.birthday).toLocaleDateString().replace(/\//g, '-');
            layui.use('laydate', function () {
                let laydate = layui.laydate;
                laydate.render({
                    elem: "#date",
                    value: date,
                    format: "yyyy-MM-dd"
                })
            })
            $('.setNickname').val(res.nick);
            let sex;
            if(res.sex == '女'){
                sex = 'girl';
            }else{
                sex = 'boy';
            }
            console.log(sex);
            $('input[name="sex"]').each(function () {
                console.log($(this).val());
                if ($(this).val() == sex) {
                    $(this).attr('checked', 'checked');
                    
                }
            })

            $('.setProfile').val(res.profile);
            layui.use('form',function(){
                let form = layui.form;
                form.render();
            })
        }
    })
}