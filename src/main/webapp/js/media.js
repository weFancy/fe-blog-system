$(function(){
    img_load();
    music_load();
    imgDelete();
    $('.del_music').on('submit',function(e){
        e.preventDefault();
        let data = $('.del_music').serializeArray();
        console.log(JSON.stringify(data));
    })
    layui.use(['upload'],function(){
        let upload = layui.upload;
        upload.render({
            elem: "#drag_upload",
            url: '/fe_blog/addMedia',
            accept: 'image',
            field: 'image',
            data:{
                type: 'image'
            },
            done: function(res){
                //请求成功回掉函数
                if(res.msg == '添加失败'){
                    let layer = layui.layer;
                        layer.msg('上传失败！文件名称不合法！',{
                            icon : 2,
                            time : 2000
                        })
                }
                else
                {
                    layui.use('layer',function(){
                        let layer = layui.layer;
                        layer.msg('上传成功！',{
                            icon : 6,
                            time : 1000
                        })
                    })
                }
                
            },
            progress: function(n, elem, res, index){
                var percent = n + '%' //获取进度百分比
                console.log(percent);
                let layer;
                layui.use('layer',function(){
                    layer=layui.layer;
                    layer.load();
                })
                if(percent == '100%'){
                   setTimeout(function(){
                        parent.layer.closeAll()
                        img_load();
                   },1000);
                    
                }
            }
        })

        let upload2 = layui.upload;
        upload2.render({
            elem:'#music_upload',
            url: '/fe_blog/addMedia',
            data:{
                type: 'music'
            },
            field:'music',
            accept: 'audio',
            done: function(res){
                //请求成功回掉函数
                if(res.msg == '添加失败'){
                    let layer = layui.layer;
                        layer.msg('上传失败！文件名称不合法！',{
                            icon : 2,
                            time : 2000
                        })
                }
                else
                {
                    layui.use('layer',function(){
                        let layer = layui.layer;
                        layer.msg('上传成功！',{
                            icon : 6,
                            time : 1000
                        })
                    })
                }
                
            },
            progress: function(n, elem, res, index){
                var percent = n + '%' //获取进度百分比
                console.log(percent);
                let layer;
                layui.use('layer',function(){
                    layer=layui.layer;
                    layer.load();
                })
                if(percent == '100%'){
                   setTimeout(function(){
                        parent.layer.closeAll()
                        music_load();
                   },1000);
                    
                }
            }
        })
        
    })
})

function img_load(){
    $.ajax({
        type:'GET',
        url:'/fe_blog/FindAllMedia',
        data: {
            type: 'image'
        },
        dataType: 'json',
        success: function(res){
            console.log(res);
            let img_list =[];
            for(let i=0;i<res.length;i++){
                let temp = parseInt(10000*Math.random()); 
                let element =`
                <label for="${res[i]['image']}" class="img_show_frame">
                        <input type="checkbox" id="${res[i]['image']}" class="img_show_input" name="image" value="${res[i]['image']}">
                        <div class="img_frame">
                             <img src="http://localhost:8080/fe_blog/images/media/${res[i]['image']}?temp=${temp}" alt="${res[i]['image']}" class="image">
                         </div>
                    <p class="img_name">${res[i]['image']}</p>
                </label>
                `
                img_list.push(element);
            }
            $('.picture_show').empty().append(img_list.join(''));
            layui.use('form',function(){
                let form = layui.form;
                form.render();
                
            })
        }
    })
}

function music_load(){
    $.ajax({
        type:'GET',
        url:'/fe_blog/FindAllMedia',
        data: {
            type: 'music'
        },
        dataType: 'json',
        success: function(res){
            console.log(res);
            let music_list =[];
            for(let i=0;i<res.length;i++){
                let element =`
                <li class="music_show_list">
                <input type="checkbox" name="music" class="layui-input " value="${res[i]['music']}"
                lay-skin="primary" title="${res[i]['music']}"></li>
                `
                music_list.push(element);
                layui.use('form',function(){
                    let form = layui.form;
                    form.render();
                    
                })
            }
            $(".music_show_item").empty().append(music_list.join(''));
        }
    })
}


function imgDelete() {
    $('#img_delete').on('submit',function(e){
        e.preventDefault();
        let data =$(this).serializeArray();
        console.log(JSON.stringify(data));
        $.ajax({
            type:'POST',
            url: 'http://localhost:8080/fe_blog/deleteMedia?type=image',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function(res){
                console.log(res);
            }

        })
    })
}
