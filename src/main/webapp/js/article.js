

$(function () {
    content_load();
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

function content_load() {
    let blogId = window.location.href.split('=')[1];
    $.ajax({
        type: 'GET',
        url: '/fe_blog/DetailBlogServlet',
        data: { 'blogId': blogId },
        dataType: 'json',
        success: function (res) {

            console.log(res);
            let blog = res.data.blog;
            $('#set_title').text(blog.title);
            $('.time').text(blog.createTime);
            $('.update').text(blog.updateTime);
            let element = `${blog.content.split('￥')[0]}`
            $('.article_content').empty().append(element);
            $('.author').text(blog.author);
            $('.field').text(res.data.field);
            let tags = [];
            for (let i = 0; i < res.data.tags.length; i++) {
                tags.push(res.data.tags[i]['name']);
            }
            $('.tags').append(tags.join(' | '));
            load();
            commentAdd(blog.blogId);
            findAllComment(blog.blogId);
        },
        error: function () {
            console.log('请求出错');
        }
    })


}


function commentAdd(blogId) {
    $('.comment_btn').on('click', function () {
        let commentator = parseInt(100000 * Math.random());
        let data = {
            'blog_id': blogId,
            'content': $('#comment').val(),
            'commentator':commentator
        }
        $.ajax({
            type: 'POST',
            url: '/fe_blog/AddCommentServlet',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (res) {
                console.log(res);
                layui .use('layer',function(){
                    layer.msg('发表评论成功！',{
                        icon: 6,
                        time: 1000
                    })
                    $('#comment').val('');
                    findAllComment(blogId);
                })
            }
        })
    })
}

function findAllComment(blogId) {

    layui.use('laypage',function(){
        let laypage = layui.laypage;
        let allCount = 0;
        laypage.render({
            elem:'commentPage',
            count: 100,
            limit: 20,
            jump: function(obj,first){
                curr = obj.curr;

                if(!first){

                }

                $.ajax({
                    type:'GET',
                    url:'/fe_blog/SelectAllCommentServlet',
                    data: {
                        'blog_id': blogId,
                        'page': (curr-1)*20,
                        'size': 20
                    },
                    dataType: 'json',
                    success: function(res){
                        let data =res.data;
                        let comment_list = [];
                        for(let i=0;i<data.length;i++){
                            let element = `<li class="comment_item" id='comment_${data[i]['id']}'> <span>游客${data[i]['commentator']}：</span> ${data[i]['content']}</li>`;
                            comment_list.push(element);
                        }
                        $('.comment_list').empty().append(comment_list.join(''));
                    }
                })
            }
        })
    })
        

}