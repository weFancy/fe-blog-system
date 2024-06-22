layui.use(['form'],function(){
    let form = layui.form;


})

$(function(){
    login();
})

function login(){
    $('#load_form').on('submit',function(e){
        e.preventDefault();
        let data = $(this).serializeArray();
        console.log(data);
        $.ajax({
            type: 'POST',
            url:'/fe_blog/UserLoginController',
            data:{
                'username':data[0].value,
                'password':data[1].value,
                'vericode': data[2].value
            },
            dataType: 'json',
            success: function(res){
                if(res.msg == '验证码错误'){
                    layui.use(['layer'],function(){
                        let layer = layui.layer;
                        layer.msg(res.msg,{
                            icon: 2,
                            time:1000
                        })
                    
                    })
                }
                else if(res.msg == '用户名或密码错误'){
                    layui.use(['layer'],function(){
                        let layer = layui.layer;
                        layer.msg(res.msg,{
                            icon: 2,
                            time:1000
                        })
                    
                    })
                }
                else{
                    layui.use(['layer'],function(){
                        let layer = layui.layer;
                        layer.msg('登陆成功！',{
                            icon: 6,
                            time:1000
                        })
                    
                    })
                    setTimeout(function(){
                        window.location.href = 'index.html';
                    },1000);
                }
            }
        })
    })
}