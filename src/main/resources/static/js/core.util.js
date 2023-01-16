var CoreUtil = (function (){
    var coreUtil = {}
    coreUtil.sendAjax = function (url,params,ft,method,headers,async,contentType){
        var roleSaveLoading = top.layer.msg('数据提交中，请稍后...',{icon:16,time:false,shade:0.8})
        layui.jquery.ajax({
            url:url
            ,cache:false
            ,data:params
            ,type:method == undefined ? 'post' : method
            ,async: async == undefined ? true : async
            ,contentType: contentType == null ? 'application/json;charset=utf-8' : contentType
            ,dataType:'json'
            ,beforeSend:function (XMLHttpRequest) {
                let accessToken = CoreUtil.getData("accessToken");
                if(accessToken != null && accessToken != undefined){
                    XMLHttpRequest.setRequestHeader("authorization",accessToken)
                }
                let refreshToken = CoreUtil.getData("refreshToken");
                if(refreshToken != null && accessToken != undefined){
                    XMLHttpRequest.setRequestHeader("refreshToken",refreshToken)
                }
            }
            ,success:function (res){
                if(typeof ft == 'function'){
                    if(res.code == '0'){
                        if(ft != null && ft != undefined){
                            ft(res)
                            top.layer.close(roleSaveLoading);
                        }
                    }else{
                        layer.msg(res.msg)
                    }
                }
            }
            ,error:function (XMLHttpRequest){
                top.layer.close(roleSaveLoading);
                if(XMLHttpRequest.status == 404){
                    top.window.location.href = '/index/404'
                }else{
                    layer.msg("服务器出了点问题，请稍后重试")
                }
        }

        })
    };
    coreUtil.setData = function (key,value){
        layui.sessionData('token',{key:key,value:value})
    }
    coreUtil.getData = function (key){
        let data = layui.sessionData('token');
        return data[key];
    }
    return coreUtil;
})(CoreUtil,window)