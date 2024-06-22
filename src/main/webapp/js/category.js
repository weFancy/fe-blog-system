
$(function () {
    $('.blog_item').on('click', function () {
        window.location.href = 'article.html';
    })
    blog_list_load();
    findAllField();
    
})

function load() {

    setTimeout(function () {
        $('.load_cover').animate({
            'top': '-100%'
        }, 100, function () {
            $(document.body).css({
                'overflow': 'auto'
            })
        })
    }, 200)



}

function blog_list_load() {
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
                    limit: 12,
                    jump: function (obj, first) {
                        //obj包含了当前分页的所有参数，比如：
                        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        console.log(obj.limit); //得到每页显示的条数
                        curr = obj.curr;


                        if (!first) {
                            //do something
                            layer.load(1);

                        }
                        $.ajax({
                            type: 'GET',
                            url: '/fe_blog/SelectLimitBlogController',
                            data: {
                                'userId': 1,
                                'page': (curr - 1) * 12,
                                'size': 12
                            },
                            dataType: 'json',
                            success: function (res) {
                                console.log(res);
                                let data = res.data;
                                let blog_list = [];
                                for (let key in data) {
                                    let element = `
                                    <li class="blog_item" id="${data[key].blog['blogId']}">
                                        <div class="blog_item_img">
                                        <img src="./images/media/default-banner.jpg" alt="封面图片" class="blog_fm">
                                        </div>
                                        <div class="blog_item_info">
                                            <h4>${data[key].blog['title']}</h4>
                                            <p>
                                                发表时间：<span class="time">${data[key].blog['createTime']}</span> |分类：<span class="field">${data[key].field}</span>
                                            </p>
                                        </div>
                                    </li>
                                    `
                                    blog_list.push(element);

                                }

                                $('.blog_box_list').empty().append(blog_list.join(''));
                                parent.layer.closeAll();
                                //首次不执行
                                if (first) {
                                    load();
                                }
                                fm();
                                $('.blog_item').each(function () {
                                    $(this).on('click', function () {
                                        window.location.href = 'article.html' + '?' + 'blogId=' + $(this).attr('id');
                                    })
                                })

                                $('.search_btn').on('click',function(){
                                    if($('.search_input').val()){
                                        let layer;
                                        layui.use('layer',function(){
                                            layer= layui.layer;
                                            layer.load(1);
                                        })
                                        layui.use('laypage',function(){
                                            let laypage = layui.laypage;
                                            laypage.render({
                                                elem: 'laypage',
                                                count: 60,
                                                limit: 12,
                                                jump: function(obj,first){
                                                    let curr = obj.curr;
                                                    if (!first) {
                                                        //do something
                                                        layer.load(1);
                                
                                                    }
                                                    $.ajax({
                                                        type: 'get',
                                                        url: '/fe_blog/FieldFindBlog',
                                                        data: {
                                                            'fieldid':'all',
                                                            'userid':1,
                                                            'page':(curr-1)*12,
                                                            'size': 12,
                                                            'blogtitle':$('.search_input').val()
                                                        },
                                                        success: function(res){
                                                            console.log(JSON.parse(res));
                                                            let res2 = JSON.parse(res);
                                                            let data = res2.data;
                                                            let list = [];
                                                            for(let i = 0;i<data.length;i++){
                                                                let element = 
                                                                `<li class="blog_item" id="${data[i]['blogId']}">
                                                                <div class="blog_item_img">
                                                                <img src="./images/media/default-banner.jpg" alt="封面图片" class="blog_fm">
                                                                </div>
                                                                <div class="blog_item_info">
                                                                    <h4>${data[i]['title']}</h4>
                                                                    <p>
                                                                        发表时间：<span class="time">${data[i]['createTime']}</span> |文章Id：：<span class="field">${data[i]['blogId']}</span>
                                                                    </p>
                                                                </div>
                                                            </li>`
                                                                list.push(element);
                                                            }
                                                            console.log(list);
                                                            fm();
                                                            parent.layer.closeAll();
                                                            $('#content_list').empty().append(list.join(''));
                                                            $('.blog_item').each(function () {
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

                        })
                    }
                })
            })
        }
    })
}

function findAllField(){
    $.ajax({
        type: 'GET',
        url: '/fe_blog/FindAllField',
        data: {},
        dataType: 'json',
        success: function(res){
            let data = res.data;
            let field = [];
            console.log(data);
            for(let i = 0;i<data.length;i++){
                let element = `<option value="${data[i]['name']}" id="${data[i]['fieldId']}">${data[i]['name']}</option>`;
                field.push(element);
            }
            console.log(field);
            $('#category_select').empty().append(`<option value="all">所有分类</option>`);
            $('#category_select').append(field.join(''));
            layui.use('form',function(){
                let form = layui.form;
                form.render();
                
            })
            field_change();
        }
    }) 
}

function field_change(){
    $('#category_select').on('change',function(){
        let selected = $(this).children('option:selected').val();
        if(selected == 'all'){
            blog_list_load();
        }else{
            let layer;
            layui.use('layer',function(){
                layer= layui.layer;
                layer.load(1);
            })            
            layui.use('laypage',function(){
                let laypage = layui.laypage;
                laypage.render({
                    elem: 'laypage',
                    count: 60,
                    limit: 12,
                    jump: function(obj,first){
                        let curr = obj.curr;
                        if (!first) {
                            //do something
                            layer.load(1);
    
                        }
                        $.ajax({
                            type: 'get',
                            url: '/fe_blog/FieldfindAllblog',
                            data: {
                                'fieldname':selected,
                                'userid':1,
                                'page':(curr-1)*12,
                                'size': 12
                            },
                            success: function(res){
                                console.log(JSON.parse(res));
                                let res2 = JSON.parse(res);
                                let data = res2.data;
                                let list = [];
                                for(let i = 0;i<data.length;i++){
                                    let element = 
                                    `<li class="blog_item" id="${data[i]['blogId']}">
                                    <div class="blog_item_img">
                                    <img src="./images/media/default-banner.jpg" alt="封面图片" class="blog_fm">
                                    </div>
                                    <div class="blog_item_info">
                                        <h4>${data[i]['title']}</h4>
                                        <p>
                                            发表时间：<span class="time">${data[i]['createTime']}</span> |文章Id：：<span class="field">${data[i]['blogId']}</span>
                                        </p>
                                    </div>
                                </li>`
                                    list.push(element);
                                }
                                console.log(list);
                                
                                parent.layer.closeAll();
                                $('#content_list').empty().append(list.join(''));
                                fm();
                                $('.blog_item').each(function () {
                                    $(this).on('click', function () {
                                        window.location.href = 'article.html' + '?' + 'blogId=' + $(this).attr('id');
                                    })
                                })
                                $('.search_btn').on('click',function(){
                                    if($('.search_input').val()){
                                        let layer;
                                        layui.use('layer',function(){
                                            layer= layui.layer;
                                            layer.load(1);
                                        })
                                        layui.use('laypage',function(){
                                            let laypage = layui.laypage;
                                            laypage.render({
                                                elem: 'laypage',
                                                count: 60,
                                                limit: 12,
                                                jump: function(obj,first){
                                                    let curr = obj.curr;
                                                    if (!first) {
                                                        //do something
                                                        layer.load(1);
                                
                                                    }
                                                    $.ajax({
                                                        type: 'get',
                                                        url: '/fe_blog/FieldFindBlog',
                                                        data: {
                                                            'fieldid': $('#category_select').children('option:selected').attr('id'),
                                                            'userid':1,
                                                            'page':(curr-1)*12,
                                                            'size': 12,
                                                            'blogtitle':$('.search_input').val()
                                                        },
                                                        success: function(res){
                                                            console.log(JSON.parse(res));
                                                            let res2 = JSON.parse(res);
                                                            let data = res2.data;
                                                            let list = [];
                                                            for(let i = 0;i<data.length;i++){
                                                                let element = 
                                                                `<li class="blog_item" id="${data[i]['blogId']}">
                                                                <div class="blog_item_img">
                                                                    <img src="./images/media/default-banner.jpg" alt="封面图片" class="blog_fm">
                                                                </div>
                                                                <div class="blog_item_info">
                                                                    <h4>${data[i]['title']}</h4>
                                                                    <p>
                                                                        发表时间：<span class="time">${data[i]['createTime']}</span> |文章Id：：<span class="field">${data[i]['blogId']}</span>
                                                                    </p>
                                                                </div>
                                                            </li>`
                                                                list.push(element);
                                                            }
                                                            console.log(list);
                                                            
                                                            parent.layer.closeAll();
                                                            $('#content_list').empty().append(list.join(''));
                                                            $('.blog_item').each(function () {
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
                            }) 
                    }
                })
            })
            
               
           
        }
    })
}

function fm() {
    let imgList = ['fm0.jpg','fm1.jpg', 'fm2.jpg', 'fm3.jpg', 'fm4.jpg', 'fm5.jpg', 'fm6.jpg', 'fm7.jpg', 'fm8.jpg','fm9.jpg','fm10.jpg','fm11.jpg'];
    $('.blog_fm').each(function () {
        let index = Math.floor(Math.random()*12);
        $(this).attr('src','./images/media/'+imgList[index]);
    })
}

