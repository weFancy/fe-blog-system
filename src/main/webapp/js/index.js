$(function () {
    indexTextLoad();
    let temp = parseInt(10000*Math.random()); 
    $('.avatar_img').attr('src','http://localhost:8080/fe_blog/images/avatar/avatar.jpg'+'?temp='+temp);
    $('.banner_btn').on('click', function () {
        $('html, body').animate({
            scrollTop: $('#content').offset().top
        }, 500);
    })

})

function indexTextLoad() {
    $.ajax({
        type: 'GET',
        url: '/fe_blog/FindHomePage',
        data: {},
        dataType: 'json',
        size: 2048,
        success: function (res) {
            console.log(res);
            $('#index_title').text(res[0]['title']);
            $('#index_welcome').text(res[0]['welcome']);
            $('#index_description').text(res[0]['description']);
            $('#index_announcement').text(res[0]['announcement']);
            $('.banner').css({
                'background': `url("images/media/${res[0]['banner']}") no-repeat`,
                'background-size': 'cover',
                'background-attachment': 'fixed'
            })
           
            pageBlogLoad();
            isLogin();



        },
        error: function () {
            console.log('获取失败');
        }
    })
}

function isLogin() {
    $.ajax({
        type: 'GET',
        url: '/fe_blog/UserLoginController',
        data: {},
        dataType: 'json',
        success: function (res) {
            console.log(res);
            if (res.msg == '没有登陆') {
                $('.user_img_frame').on('click', function () {
                    window.open('/fe_blog/login.html');
                })

                $('.user_func').empty();
            } else {
                let element = `
                <a class="user_func_box" href="./pages/newblog.html">
                <i class="fa fa-pencil"></i>
                <span>新建博客</span>
            </a>
            <a class="user_func_box" href="./pages/manageblog.html">
                <i class="fa fa-bars"></i>
                <span>管理文章</span>
            </a>
            <a class="user_func_box" href="./pages/userinfo.html">
                <i class="fa fa-user"></i>
                <span>我的信息</span>
            </a>
            <a class="user_func_box" href="./pages/userinfo.html">
                <i class="fa fa-tachometer"></i>
                <span>进入后台</span>
            </a>
                `
                $('.user_func').empty().append(element);
                userInfoLoad();
            }
        },
        error: function (res) {
            console.log('请求出错');
        }
    })
}
/* function loginPage(){
    $('.user_img_frame')
} */

function pageBlogLoad() {
    $.ajax({
        type: 'GET',
        url: '/fe_blog/AllBlogCountServlet',
        data: {},
        dataType: 'json',
        success: function (res) {
            console.log(res);
            allCount = res.data;
            layui.use(['laypage', 'layer'], function () {
                let laypage = layui.laypage;
                let layer = layui.layer;
                let curr = 1;
                laypage.render({
                    elem: 'laypage',
                    count: allCount,
                    limit: 8,
                    jump: function (obj, first) {
                        //obj包含了当前分页的所有参数，比如：
                        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        console.log(obj.limit); //得到每页显示的条数
                        curr = obj.curr;


                        if (!first) {
                            //do something
                            layer.load(1);
                            $('html, body').animate({
                                scrollTop: $('#content').offset().top - 600
                            }, 0);

                        }
                        $.ajax({
                            type: 'GET',
                            url: '/fe_blog/SelectLimitBlogController',
                            data: {
                                'userId': 1,
                                'page': (curr - 1) * 8,
                                'size': 8
                            },
                            dataType: 'json',
                            success: function (res) {
                                console.log(res.data);
                                let data = res.data;
                                let blog_list = [];
                                for (let key in data) {
                                    let tags = [];
                                    for (let i = 0; i < data[key].tag.length; i++) {
                                        let tag_element = `<span>${data[key].tag[i]['name']}</span>`;
                                        tags.push(tag_element);
                                    }
                                    let element = `
                                    <li class="blog_list_item animal-left" id = '${data[key].blog['blogId']}'>
                                    <div class="blog_img_frame">
                                        <img src="./images/media/default-banner.jpg" alt="封面图片" class="blog_fm">
                                    </div>
                                    <div class="blog_info_frame">
                                        <div class="blog_title">${data[key].blog['title']}</div>
                                        <div class="blog_info">
                                            <p>
                                                发表时间：<span class="time">${data[key].blog['createTime']}</span> |
                                                分类：<span class="field">${data[key].field}</span>
                                            </p>
                                        </div>
                                        <div class="blog_description">
                                            <p>${data[key].blog['description']}</p>
                                        </div>
                                        <div class="blog_tags">
                                            <div><i class="fa fa-tags"></i>${tags.join('')}</div>
                                        </div>
                                    </div>
                                     </li>
                                    `
                                    blog_list.push(element);
                                    
                                    

                                }

                                $('.content_blog_list').empty().append(blog_list.join(''));
                                parent.layer.closeAll();
                                if (first) {
                                    load();
                                }
                                animate();
                                fm();
                                
                                $('.blog_list_item').each(function () {
                                    $(this).on('click', function () {
                                        window.location.href = 'article.html' + '?' + 'blogId=' + $(this).attr('id');
                                    })
                                })

                            }

                        })
                    }
                })
            })
        }
    })



}

function load() {

    setTimeout(function () {
        $('.load_cover').animate({
            'top': '-100%'
        }, 100, function () {
            $(document.body).css({
                'overflow': 'auto'
            })
            $('.welcome').fadeIn('slow');
            $('.other_text').fadeIn('slow');
            $('.banner_btn').fadeIn('slow');
        })
    }, 200)



}

function fm() {
    let imgList = ['fm0.jpg','fm1.jpg', 'fm2.jpg', 'fm3.jpg', 'fm4.jpg', 'fm5.jpg', 'fm6.jpg', 'fm7.jpg', 'fm8.jpg','fm9.jpg','fm10.jpg','fm11.jpg','fm11.jpg'];
    $('.blog_fm').each(function () {
        let index = Math.floor(Math.random()*12);
        $(this).attr('src','./images/media/'+imgList[index]);
    })
}
function userInfoLoad(){
    $.ajax({
        type: 'GET',
        url: '/fe_blog/SelectUserServlet',
        data: {},
        dataType: 'json',
        success: function (res) {
            $('.setNickname').val(res.nick);
            $('.setProfile').val(res.profile);
        }
    })
}
function animate() {
    ScrollReveal().reveal('.animal-left', {
        //delay延迟
        delay: 200,
        //移动距离
        distance: '100px',
        //持续时间
        duration: 600,
        //动画效果
        easing: 'ease-out',
        //动画间隔
        interval: 200,
        //透明度
        opicaty: 0,
        //起始位置
        origin: 'left',
        //旋转角度
        rotate: {
            x: 0,
            Y: 0,
            z: 0
        },
        //reset
        reset: false
    })
    ScrollReveal().reveal('.animal-top', {
        //delay延迟
        delay: 100,
        //移动距离
        distance: '200px',
        //持续时间
        duration: 500,
        //动画效果
        easing: 'ease-out',
        //动画间隔
        interval: 100,
        //透明度
        opicaty: 0,
        //起始位置
        origin: 'top',
        //旋转角度
        rotate: {
            x: 0,
            Y: 0,
            z: 0
        },
        //reset
        reset: false
    })
    ScrollReveal().reveal('.animal-bottom', {
        //delay延迟
        delay: 200,
        //移动距离
        distance: '100px',
        //持续时间
        duration: 600,
        //动画效果
        easing: 'ease-out',
        //动画间隔
        interval: 100,
        //透明度
        opicaty: 0,
        //起始位置
        origin: 'bottom',
        //旋转角度
        rotate: {
            x: 0,
            Y: 0,
            z: 0
        },
        //reset
        reset: false
    })
}