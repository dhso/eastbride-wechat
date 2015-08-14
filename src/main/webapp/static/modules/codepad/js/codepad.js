var update_editor;
var duoshuoQuery = {
    short_name: "codepad"
};
var new_list_menu_li = "";
var search_value = '';
$(document).ready(function() {
    init_login_menu();
    init_list_tree_menu();
    init_content_tab_menu();
    init_content_tab();
    init_search_input();
    init_cidian_menu();
    init_fanyi_menu();
    init_weather_menu();
    init_doubanFM_menu();
    init_kuaidi100_menu();
    init_exfile_menu();
    init_alimama_menu();
    init_win_update_file();
    //init_kindEditor();
    init_xheditor();
    index_direct();
    init_new_menu_list(10);
    setInterval(get_new_menu_list,60000);
});

/*在新tab页打开事件*/
function open_tab(id, text) {
    if ($('#content_tab').tabs('exists', id + '#' + text)) {
        $('#content_tab').tabs('select', id + '#' + text);
    } else {
        var direct_url = '<br /><div class="direct-url-box"><span>本文地址：</span>http://' + window.location.host + '?cid=' + id + '</div>';
        var duoshuo = '<br /><div id="comment-box-' + id + '" class="comment-box"></div>';
        $('#content_tab').tabs('add', {
            id: id,
            title: id + '#' + text,
            href: domain + 'codepad/getListing',
            closable: true,
            cls: 'content_tab_header',
            bodyCls: 'content_tab_content',
            extractor: function(data) {
                var pattern = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
                var matches = pattern.exec(data);
                var content = '';
                if (matches) {
                    content = matches[1];
                } else {
                    content = data;
                }
                return content + direct_url + duoshuo;
            },
            onLoad: function() {
                toggleDuoshuoComments('#comment-box-' + id, id, window.location.host + '?cid=' + id);
            }
        });
        $('#' + id).bind('contextmenu', function(e) {
            e.preventDefault();
            $('#content_tab_blank_menu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        });
    }
};
/*添加节点*/
function add_ff(type, s_node) {
    var c_nick = $.cookie('c_nick');
    if (c_nick) {
        var id = "0";
        if (s_node !== '0') {
            id = $('#list_tree').tree('getSelected').id;
        }
        var state = "open";
        if (type === "folder") {
            state = "closed";
        }
        $('#win-add-ff').window('center');
        $('#win-add-ff').window('open');
        $("#win-add-ff-state").val(state);
        $("#win-add-ff-nodeid").val(id);
        $('#win-add-ff-text').val('');
        $('input[name="win-add-ff-open"]:radio[value="1"]').attr("checked", "true");
    } else {
        $.messager.alert('Message', '请先登录！');
    }
};
/*添加节点按钮提交*/
function add_ff_button() {
    var text = $("#win-add-ff-text").val();
    var open = $('input[name="win-add-ff-open"]:checked').val();
    var id = $("#win-add-ff-nodeid").val();
    var state = $("#win-add-ff-state").val();
    var writer = $.cookie('c_nick');
    var data = '{"state":"' + state + '","text":"' + text + '", "open":"' + open + '", "writer":"' + writer + '"}';
    $.ajax({
        type: "post",
        url: domain + 'codepad/ui/menu_tree/add_menu?pid=' + id,
        async: false,
        data: data,
        success: function(data, textStatus) {
            if (data.responseDesc !== "Success") {
                $.messager.alert('Message', data.responseDesc);
            } else {
                init_list_tree(search_value);
                $('a.panel-tool-close').trigger("click");
                $.messager.show({
                    title: '提示',
                    msg: data.responseDesc,
                    showType: 'show'
                });
            }
        },
        error: function() {
            $.messager.alert('Message', '请求出错！');
        },
        complete: function(XMLHttpRequest, textStatus) {}
    });
};

/*修改文件属性*/
function update_ff() {
    var c_nick = $.cookie('c_nick');
    if (c_nick) {
        if (c_nick === $('#list_tree').tree('getSelected').attributes.writer) {
            var node = $('#list_tree').tree('getSelected');
            if (node) {
                var id = node.id;
                var pid = node.attributes.pid;
                var open = node.attributes.open;
                var text = node.text;
                $('#win-update-ff').window('center');
                $('#win-update-ff').window('open');
                $('#win-update-ff-text').val(text);
                $('input[name="win-update-ff-open"]:radio[value=' + open + ']').attr("checked", "true");
                init_node_parent(pid);
            }
        } else {
            $.messager.alert('Message', '您没有权限修改此节点！');
        }
    } else {
        $.messager.alert('Message', '请先登录！');
    }
}
/*修改文件按钮提交*/
function update_ff_button() {
    var id = $('#list_tree').tree('getSelected').id;
    var pid = $('#node_parent').combobox('getValue');
    var text = $("#win-update-ff-text").val();
    var open = $('input[name="win-update-ff-open"]:checked').val();
    var data = '{"pid":"' + pid + '", "text":"' + text + '", "open":"' + open + '"}';
    $.ajax({
        type: "post",
        url: domain + 'codepad/ui/menu_tree/update_menu?id=' + id,
        async: false,
        data: data,
        success: function(data, textStatus) {
            if (data.responseDesc !== "Success") {
                $.messager.alert('Message', data.responseDesc);
            } else {
                init_list_tree(search_value);
                $('a.panel-tool-close').trigger("click");
                $.messager.show({
                    title: '提示',
                    msg: data.responseDesc,
                    showType: 'show'
                });
            }
        },
        error: function() {
            $.messager.alert('Message', '请求出错！');
        },
        complete: function(XMLHttpRequest, textStatus) {}
    });
};

/*修改内容*/
function update_con() {
    var c_nick = $.cookie('c_nick');
    if (c_nick) {
        if (c_nick === $('#list_tree').tree('getSelected').attributes.writer) {
            $('#win-update-file').window('center');
            $('#win-update-file').window('open');
            var textarea_html = '';
            var node = $('#list_tree').tree('getSelected');
            if (node) {
                var id = node.id;
                $.ajax({
                    type: "get",
                    url: domain + 'codepad/ui/content_tab/select_content?callback=?&tid=' + id,
                    async: false,
                    success: function(data, textStatus) {
                        textarea_html = data;
                    },
                    error: function() {
                        $.messager.alert('Message', '请求出错！');
                    },
                    complete: function(XMLHttpRequest, textStatus) {}
                });
            }
            update_editor.setSource(textarea_html);
            /*if(KindEditor.instances.length>0){
                window.editor.html(textarea_html);
            }*/
        } else {
            $.messager.alert('Message', '您没有权限修改此节点内容！');
        }
    } else {
        $.messager.alert('Message', '请先登录！');
    }
};
/*修改内容窗口事件*/
function init_win_update_file() {
    $('#win-update-file').window({
        title: '更新',
        modal: true,
        width: 600,
        collapsible: false,
        minimizable: false,
        closed: true,
        resizable: false,
        shadow: false,
        tools: [{
            iconCls: 'icon-codepad-save',
            handler: function() {
                $.ajax({
                    type: "post",
                    url: domain + 'codepad/ui/content_tab/update_content?tid=' + $('#list_tree').tree('getSelected').id,
                    async: false,
                    data: update_editor.getSource(), //window.editor.html(),
                    success: function(data, textStatus) {
                        if (data.responseDesc !== "Success") {
                            $.messager.alert('Message', data.responseDesc);
                        } else {
                            reload_tab_con($('#list_tree').tree('getSelected').id);
                            $('a.panel-tool-close').trigger("click");
                            $.messager.show({
                                title: '提示',
                                msg: data.responseDesc,
                                showType: 'show'
                            });
                        }
                    },
                    error: function() {
                        $.messager.alert('Message', '请求出错！');
                    },
                    complete: function(XMLHttpRequest, textStatus) {}
                });
            }
        }],
        onMaximize: function() {
            /*if(KindEditor.instances.length>0){
                $('#win-update-file .ke-edit').css('height',$('#win-update-file').height()-$('#win-update-file .ke-toolbar').height()-20);
            } */
            $('#win-update-file td.xheIframeArea').css('height', $('#win-update-file').height() - $('#win-update-file td.xheTool').height() - 5)
        },
        onRestore: function() {
            /*if(KindEditor.instances.length>0){
                $('#win-update-file .ke-edit').css('height','290px');
            } */
            $('#win-update-file td.xheIframeArea').css('height', '400px');
        },
        onResize: function() {
            /*if(KindEditor.instances.length>0){
                $('#win-update-file .ke-container').css('width',$('#win-update-file').width()-2);
            } */
        }
    });
};
/*删除节点*/
function delete_ff() {
    var c_nick = $.cookie('c_nick');
    if (c_nick) {
        if (c_nick === $('#list_tree').tree('getSelected').attributes.writer) {
            $.messager.confirm('Message', '确认删除节点？', function(r) {
                if (r) {
                    var node = $('#list_tree').tree('getSelected');
                    if (node) {
                        var id = node.id;
                        $.ajax({
                            type: "post",
                            url: domain + 'codepad/ui/menu_tree/del_menu?id=' + id,
                            async: false,
                            success: function(data, textStatus) {
                                if (data.responseDesc !== "Success") {
                                    $.messager.alert('Message', data.responseDesc);
                                } else {
                                    init_list_tree(search_value);
                                    $('a.panel-tool-close').trigger("click");
                                    $.messager.show({
                                        title: '提示',
                                        msg: data.responseDesc,
                                        showType: 'show'
                                    });
                                }
                            },
                            error: function() {
                                $.messager.alert('Message', '请求出错！');
                            },
                            complete: function(XMLHttpRequest, textStatus) {}
                        });
                    }
                }
            });
        } else {
            $.messager.alert('Message', '您没有权限删除此节点！');
        }
    } else {
        $.messager.alert('Message', '请先登录！');
    }
};

/*初始化登录按钮*/
function init_login_menu() {
    var c_nick = $.cookie('c_nick');
    $('#login_menu').empty();
    if (c_nick) {
        $('#login_menu').linkbutton({
            iconCls: 'icon-codepad-user',
            plain: true,
            text: '注销'
        });
        $('#login_menu').unbind('click');
        $('#login_menu').bind('click', function() {
            $.cookie('c_nick', '');
            init_login_menu();
        });
        $('#login_nick').linkbutton({
            text: 'Hi,' + c_nick + '!'
        });
    } else {
        $('#login_menu').linkbutton({
            iconCls: 'icon-codepad-user',
            plain: true,
            text: '登录'
        });
        $('#login_menu').unbind('click');
        $('#login_menu').bind('click', function() {
            $('#win-login').window('center');
            $('#win-login').window('open');
        });
        $('#login_nick').linkbutton({
            text: 'Hi,游客!'
        });
    }
    init_list_tree(search_value);
};
/*登录按钮提交*/
function login_button() {
    var name = $('#usr_name').val();
    var password = $('#usr_password').val();
    var data = '{"nick":"' + name + '","password":"' + password + '"}';
    $.ajax({
        type: "post",
        url: domain + 'user/login?' + makeSignature(),
        contentType: 'text/plain;charset=UTF-8',
        data: data,
        success: function(data, textStatus) {
            if (data.responseDesc !== "Success") {
                $.messager.alert('Message', data.responseDesc);
            } else {
                $.cookie('c_nick', data.user.nick);
                init_login_menu();
                $('a.panel-tool-close').trigger("click");
                DesktopNotify('Codepad','static/img/favicon.ico','欢迎 '+data.user.nick+' 登录！');
            };
        },
        error: function() {
            $.messager.alert('Message', '请求出错！');
        },
        complete: function(XMLHttpRequest, textStatus) {}
    });
};
/*初始化词典按钮*/
function init_cidian_menu() {
    $('#cidian_menu').linkbutton({
        iconCls: 'icon-codepad-youdao-cidian',
        text: '有道词典'
    });
    $('#cidian_menu').bind('click', function() {
        $('#win-cidian').window('open');
    });
};
/*初始化翻译按钮*/
function init_fanyi_menu() {
    $('#fanyi_menu').linkbutton({
        iconCls: 'icon-codepad-youdao-fanyi',
        text: '有道翻译'
    });
    $('#fanyi_menu').bind('click', function() {
        $('#win-fanyi').window('open');
    });
};
/*初始化天气按钮*/
function init_weather_menu() {
    $('#weather_menu').linkbutton({
        iconCls: 'icon-codepad-zgtq-weather',
        text: '天气预报'
    });
    $('#weather_menu').bind('click', function() {
        $('#win-weather').window('open').panel('refresh', 'static/app/zgtqWEATHER.html');
    });
};
/*初始化豆瓣FM按钮*/
function init_doubanFM_menu() {
    $('#doubanFM_menu').linkbutton({
        iconCls: 'icon-codepad-douban-FM',
        text: '打开豆瓣FM'
    });
    $('#doubanFM_menu').bind('click', function() {
        var text = $('#doubanFM_menu').linkbutton('options').text;
        if (text === '打开豆瓣FM') {
            $('#win-doubanFM').window('open');
            $('#doubanFM_menu').linkbutton({
                text: '关闭豆瓣FM'
            });
        } else {
            $('#win-doubanFM').window('close');
            $('#doubanFM_menu').linkbutton({
                text: '打开豆瓣FM'
            });
        }
    });
};
/*初始化快递100按钮*/
function init_kuaidi100_menu() {
    $('#kuaidi100_menu').linkbutton({
        iconCls: 'icon-codepad-kuaidi100',
        text: '快递查询'
    });
    $('#kuaidi100_menu').bind('click', function() {
        $('#win-kuaidi100').window('open');
    });
};
/*初始化exfile按钮*/
function init_exfile_menu() {
    $('#exfile_menu').linkbutton({
        iconCls: 'icon-codepad-base',
        text: 'Ex网盘'
    });
    $('#exfile_menu').bind('click', function() {
        var c_nick = $.cookie('c_nick');
        if (c_nick) {
            $('#win-exfile').window('open');
        }else{
            $.messager.alert('Message', '请先登录！');
        }
    });
};
/*初始化alimama按钮*/
function init_alimama_menu() {
    $('#alimama_menu').linkbutton({
        iconCls: 'icon-codepad-shoppingcart',
        text: '淘宝转换'
    });
    $('#alimama_menu').bind('click', function() {
        $('#win-alimama').window('open');
    });
};
/*初始化树形列表*/
function init_list_tree(search_value) {
    if (search_value === '') {
        d_url = domain + '/codepad/getListing';
    } else {
        d_url = domain + '/codepad/getListing?&search=' + search_value;
    }
    $('#list_tree').tree({
        url: d_url,
        method: 'get',
        animate: true,
        formatter: function(node) {
            var s = node.text;
            if ($.cookie('c_nick') !== node.create_id && node.open === '0') {
                s = '<span style="color:#ccc">' + s + '</span>';
            };
            if (node.node === '0') {
                if (node.open === '0') {
                    s += '<span style="color:#ccc">&nbsp;私</span>';
                } else {
                    s += '<span style="color:#ccc">&nbsp;公</span>';
                }
                s += '<span style="color:#ccc">&nbsp;' + node.create_id + '</span>';
            }
            return s;
        },
        onContextMenu: function(e, node) {
            e.preventDefault();
            $(this).tree('select', node.target);
            $('#list_tree_menu').menu('show', {
                left: e.pageX,
                top: e.pageY,
            });
            if (node.node === '0') {
                $('#list_tree_menu').menu('disableItem', $('#add-ff')[0]);
                $('#list_tree_menu').menu('enableItem', $('#update-con')[0]);
            } else {
                $('#list_tree_menu').menu('enableItem', $('#add-ff')[0]);
                $('#list_tree_menu').menu('disableItem', $('#update-con')[0]);
            };
        },
        onClick: function(node) {
            if (node.node === '0') {
                if (node.open === '1' || node.create_id === $.cookie('c_nick')) {
                    open_tab(node.id, node.text);
                } else {
                    $.messager.alert('Message', '您没有权限查看此节点内容！');
                }

            }
        }
    });
};
/*初始化树形列表空白菜单*/
function init_list_tree_menu() {
    $('div[region="west"]').bind('contextmenu', function(e) {
        e.preventDefault();
        $('#list_menu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    });
};
/*初始化tabs菜单*/
function init_content_tab_menu() {
    $('#content_tab').tabs({
        onContextMenu: function(e, title) {
            e.preventDefault();
            $('#content_tab_menu').menu('show', {
                left: e.pageX,
                top: e.pageY,
            }).data("tabTitle", title);
        }
    });
};

function close_tab(option) {
    var curTabTitle = $('#content_tab_menu').data("tabTitle");
    if (option === "current") {
        if (curTabTitle !== '首页') {
            $('#content_tab').tabs("close", curTabTitle);
        }
        return;
    };
    var allTabs = $("#content_tab").tabs('tabs');
    var closeTabsTitle = [];
    $.each(allTabs, function() {
        var opt = $(this).panel('options');
        if (opt.closable && opt.title != curTabTitle && option === "other") {
            closeTabsTitle.push(opt.title);
        } else if (opt.closable && option === "all") {
            closeTabsTitle.push(opt.title);
        }
    });
    for (var i = 0; i < closeTabsTitle.length; i++) {
        $('#content_tab').tabs("close", closeTabsTitle[i]);
    };
}
/*初始化父节点下拉*/
function init_node_parent(value) {
    $('#node_parent').combobox({
        url: domain + 'codepad/ui/menu_tree/select_menu_p?callback=?',
        valueField: 'id',
        textField: 'text'
    });
    if (value === '0') {
        value = '';
    }
    $('#node_parent').combobox('setValue', value);
};

/*扩展到节点*/
function node_expandTo(node_id) {
    var node = $('#list_tree').tree('find', node_id);
    if (node) {
        $('#list_tree').tree('expand', node.target).tree('select', node.target);
    }
};
/*初始化tab栏事件*/
function init_content_tab() {
    $('#content_tab').tabs({
        onLoad: function(panel) {
            prettyPrint();
        }
    });
};
/*初始化查询事件*/
function init_search_input() {
    $('#search_input').searchbox({
        prompt: '关键词',
        searcher: function(value, name) {
            search_value = value;
            init_list_tree(search_value);
        }
    });
};
/*重载tab内容*/
function reload_tab_con(tab_id) {
    if (tab_id === '') {
        var tab = $('#content_tab').tabs('getSelected');
        tab.panel('refresh', domain + 'codepad/ui/content_tab/select_content?callback=?&tid=' + tab.panel('options').id);
    } else {
        $('#' + tab_id).panel('refresh', domain + 'codepad/ui/content_tab/select_content?callback=?&tid=' + tab_id);
    }
};
/*KindEditor编辑器初始化*/
function init_kindEditor() {
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_update-file', {
            width: $('#win-update_file').width() - 2,
            height: '400',
            //minWidth:$('#win-update-file').width()-2,
            //minHeight:'50%',
            minWidth: 1,
            minHeight: 1,
            resizeType: 1,
            filterMode: false,
            newlineTag: 'p',
            filterMode: false
        });
    });
};
/*xheditor编辑器初始化*/
function init_xheditor() {
    var allPlugin = {
        Code: {
            c: 'xheBtnPlugCode',
            t: '插入代码',
            h: 1,
            e: function() {
                var _this = this;
                var htmlCode = '<div><select id="xheCodeType"><option value="js">JavaScript</option><option value="html">HTML</option><option value="css">CSS</option><option value="php">PHP</option><option value="pl">Perl</option><option value="py">Python</option><option value="rb">Ruby</option><option value="java">Java</option><option value="vb">ASP/VB</option><option value="cpp">C/C++</option><option value="cs">C#</option><option value="xml">XML</option><option value="bsh">Shell</option><option value="">Other</option></select></div><div><textarea id="xheCodeValue" wrap="soft" spellcheck="false" style="width:300px;height:100px;" /></div><div style="text-align:right;"><input type="button" id="xheSave" value="确定" /></div>';
                var jCode = $(htmlCode),
                    jType = $('#xheCodeType', jCode),
                    jValue = $('#xheCodeValue', jCode),
                    jSave = $('#xheSave', jCode);
                jSave.click(function() {
                    _this.loadBookmark();
                    _this.pasteHTML('<pre class="prettyprint linenums lang-' + jType.val() + '">' + _this.domEncode(jValue.val()).replace(/[\r\n]/g, '<br />') + '</pre><br />');
                    _this.hidePanel();
                    return false;
                });
                _this.saveBookmark();
                _this.showDialog(jCode);
            }
        }
    };
    update_editor = $('#editor_update_file').xheditor({
        plugins: allPlugin,
        tools: 'full',
        skin: 'default',
        //clickCancelDialog:false,
        loadCSS: ['static/google-code-prettify/prettify_bootstrap.css'],
        width: $('#win-update_file').width() - 2,
        height: '400',
        disableContextmenu: true,
        hoverExecDelay: -1,
        upImgUrl: 'static/xheditor-1.2.1/xheditor_plugins/upload/upload.php',
        upLinkUrl: 'static/xheditor-1.2.1/xheditor_plugins/upload/upload.php',
        upFlashUrl: 'static/xheditor-1.2.1/xheditor_plugins/upload/upload.php',
        upMediaUrl: 'static/xheditor-1.2.1/xheditor_plugins/upload/upload.php'
    });
};

function toggleDuoshuoComments(container, content_id, content_url) {
    var el = document.createElement('div'); //该div不需要设置class="ds-thread"
    el.setAttribute('data-thread-key', content_id); //必选参数 文章的本地ID
    el.setAttribute('data-url', content_url); //必选参数 你网页的网址
    //el.setAttribute('data-title', content_title);//可选参数 你网页的标题
    el.setAttribute('data-author-key', ''); //可选参数 作者的本地用户ID
    DUOSHUO.EmbedThread(el);
    jQuery(container).append(el);
};

function init_new_menu_list(num){
    $.ajax({
        type: "get",
        url: domain + 'codepad/ui/menu_list/select_top_menu?num=' + num,
        async: false,
        success: function(data, textStatus) {
            if (data) {
                for(var i = 0; i<data.length;i++){
                    new_list_menu_li += '<li onClick="open_tab(\''+data[i].id+'\',\''+data[i].text+'\')"><p class="nomal">'+(i+1)+') '+data[i].text+'</p><p class="small">'+data[i].attributes.writer+' '+getLocalTime(data[i].attributes.update_dt)+'</p></li>';
                }
                $("#new_menu_list").html(new_list_menu_li);
                $('#new_menu_list_div').layout('resize', {});
            }
        },
        error: function() {
            $.messager.alert('Message', '请求出错！');
        },
        complete: function(XMLHttpRequest, textStatus) {}
    });
}

function get_new_menu_list(){
    $.ajax({
        type: "get",
        url: domain + 'codepad/ui/menu_list/select_top_menu?num=10',
        async: false,
        success: function(data, textStatus) {
            if (data) {
                var new_list_menu_lis = "";
                for(var i = 0; i<data.length;i++){
                    new_list_menu_lis += '<li onClick="open_tab(\''+data[i].id+'\',\''+data[i].text+'\')"><p class="nomal">'+(i+1)+') '+data[i].text+'</p><p class="small">'+data[i].attributes.writer+' '+getLocalTime(data[i].attributes.update_dt)+'</p></li>';
                }
                if(new_list_menu_li !== new_list_menu_lis){
                    $("#new_menu_list").html(new_list_menu_lis);
                    $('#new_menu_list_div').layout('resize', {});
                    new_list_menu_li = new_list_menu_lis;
                    DesktopNotify('Codepad动态','static/img/favicon.ico',data[0].text +'\n'+ data[0].attributes.writer +' '+ getLocalTime(data[0].attributes.update_dt));
                }
            }
        },
        error: function() {
            $.messager.alert('Message', '请求出错！');
        },
        complete: function(XMLHttpRequest, textStatus) {}
    });
}

function getUrlParam(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

function index_direct() {
    var cid = getUrlParam('cid');
    if (cid) {
        open_tab(cid, '节点文章');
    }
}

function getLocalTime(nS) {     
   return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
}

function makeSignature() {
    var timestamp = new Date().getTime(),
        appid = '2',
        appkey = 'codepad';
    var sortStr = new Array(timestamp, appid, appkey);
    sortStr.sort();
    var signatureStr = SHA1(sortStr[0]+sortStr[1]+sortStr[2]);
    var result = 'signature=' + signatureStr + "&timestamp=" + timestamp + "&appid=" + appid;
    return result;
}

function DesktopNotify(nTitle,nIcon,nBody) {
    var option = {icon:nIcon,body:nBody}
    if (("Notification" in window)) {
        if (Notification.permission === "granted") {
            var notification = new Notification(nTitle,option);
        }else if (Notification.permission !== 'denied') {
            Notification.requestPermission(function (permission) {
                if (!('permission' in Notification)) {
                    Notification.permission = permission;
                }
                if (permission === "granted") {
                    var notification = new Notification(nTitle,option);
                }
            });
        }
    }
}

/*start SHA1*/
function SHA1(sIn) {
    var x = AlignSHA1(sIn);
    var w = new Array(80);
    var a = 1732584193;
    var b = -271733879;
    var c = -1732584194;
    var d = 271733878;
    var e = -1009589776;
    for (var i = 0; i < x.length; i += 16) {
        var olda = a;
        var oldb = b;
        var oldc = c;
        var oldd = d;
        var olde = e;
        for (var j = 0; j < 80; j++) {
            if (j < 16) w[j] = x[i + j];
            else w[j] = rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
            t = add(add(rol(a, 5), ft(j, b, c, d)), add(add(e, w[j]), kt(j)));
            e = d;
            d = c;
            c = rol(b, 30);
            b = a;
            a = t;
        }
        a = add(a, olda);
        b = add(b, oldb);
        c = add(c, oldc);
        d = add(d, oldd);
        e = add(e, olde);
    }
    SHA1Value = SHA1hex(a) + SHA1hex(b) + SHA1hex(c) + SHA1hex(d) + SHA1hex(e);
    return SHA1Value.toLowerCase();
}

function add(x, y) {
    return ((x & 0x7FFFFFFF) + (y & 0x7FFFFFFF)) ^ (x & 0x80000000) ^ (y & 0x80000000);
}

function SHA1hex(num) {
    var sHEXChars = "0123456789abcdef";
    var str = "";
    for (var j = 7; j >= 0; j--)
        str += sHEXChars.charAt((num >> (j * 4)) & 0x0F);
    return str;
}

function AlignSHA1(sIn) {
    var nblk = ((sIn.length + 8) >> 6) + 1,
        blks = new Array(nblk * 16);
    for (var i = 0; i < nblk * 16; i++) blks[i] = 0;
    for (i = 0; i < sIn.length; i++)
        blks[i >> 2] |= sIn.charCodeAt(i) << (24 - (i & 3) * 8);
    blks[i >> 2] |= 0x80 << (24 - (i & 3) * 8);
    blks[nblk * 16 - 1] = sIn.length * 8;
    return blks;
}


function rol(num, cnt) {
    return (num << cnt) | (num >>> (32 - cnt));
}

function ft(t, b, c, d) {
    if (t < 20) return (b & c) | ((~b) & d);
    if (t < 40) return b ^ c ^ d;
    if (t < 60) return (b & c) | (b & d) | (c & d);
    return b ^ c ^ d;
}

function kt(t) {
    return (t < 20) ? 1518500249 : (t < 40) ? 1859775393 :
        (t < 60) ? -1894007588 : -899497514;
}
/*end SHA1*/