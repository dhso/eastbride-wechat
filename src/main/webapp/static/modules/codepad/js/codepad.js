var duoshuoQuery = {
    short_name: "codepad"
};
var new_list_menu_li = "";
var search_value = '';
$(document).ready(function() {
	init_layout();
	load_directPage();
});

//初始化布局
function init_layout(){
	$.parser.onComplete = function(){
		close_loading();
	}
	load_list_tree(search_value);
	reg_search_input();
	reg_security_button();
	reg_login_win_enter();
	reg_add_file_editor();
	reg_update_file_editor();
	reg_list_blank_menu();
	reg_content_tab_menu();
	close_loading();
}

function reg_zclip(){
	$('body').zclip({
		path: baseUrl+'/static/zclip/ZeroClipboard.swf',
		copy:function(){
			return 'copy';
		},
		afterCopy:function(){
			$.messager.alert('提示', '链接复制成功！', 'info');
		}
	});
}
//关闭初始化遮罩页面
function close_loading(){
	$("#loading").fadeOut("slow",function(){
		$(this).remove();
	});
}
//展示加载/处理提示条
function showProgress(title, text) {
	parent.$.messager.progress({
		title : title,
		text : text
	});
}
//关闭加载/处理提示条
function closeProgress(){
	parent.$.messager.progress('close');
}
//展示信息板
function showMessageBoard(title, msg) {
	$.messager.show({
		title : title,
		msg : msg,
		timeout : 5000,
		showType : 'slide'
	});
}
/* 加载资源列表 */
function load_list_tree(search_value) {
	var url = baseUrl + '/getTree';
    if (search_value ) {
    	url = baseUrl + '/getTree?&search=' + search_value;
    }
    $('#list_tree').tree({
        url: url,
        animate: true,
        dnd:true,
        formatter: function(node) {
            var s = '<a href="javascript:void(0);" title="'+node.text+' '+node.create_id+' '+node.create_dt+'">'+node.text+' <span class="tree-info">'+node.create_id+'</span></a>';
            return s;
        },
        onContextMenu: function(e, node) {
            e.preventDefault();
            $(this).tree('select', node.target);
            $('#list_tree_menu').menu('show', {
                left: e.pageX,
                top: e.pageY,
            });
            if(isLogin()){
            	if (node.node == '0') {
                    $('#list_tree_menu').menu('hideItem', $('#add_tree_ff')[0]);
                } else {
                    $('#list_tree_menu').menu('showItem', $('#add_tree_ff')[0]);
                };
                $('#list_tree_menu').menu('showItem', $('#update_tree_ff')[0]);
                $('#list_tree_menu').menu('showItem', $('#delete_tree_ff')[0]);
            }else{
            	 $('#list_tree_menu').menu('hideItem', $('#add_tree_ff')[0]);
                 $('#list_tree_menu').menu('hideItem', $('#update_tree_ff')[0]);
                 $('#list_tree_menu').menu('hideItem', $('#delete_tree_ff')[0]);
            }
            
        },
        onClick: function(node) {
            if (node.node == '0') {
            	open_tab(node.id, node.text);
            }
        },
        onBeforeDrop:function(target,source,point){
        	if(isLogin()){
        		console.log(point);
        		var targetNode = $('#list_tree').tree('getNode', target);
        		if(point == 'append' && targetNode.node == '1'){
                	$.post(baseUrl + '/updateTree',{
                		id:source.id,
                		pid:targetNode.id,
                		update_id:$.cookie('c_nick')
                	},function(res){
                		if(res && res.resCode == '200'){
                			showMessageBoard('提示', '改变节点成功！');
                		}else{
                			$.messager.alert('错误', '未知错误！', 'error');
                			return false;
                		}
                	});
            	}else{
            		return false;
            	}
        	}else{
        		$.messager.alert('提示', '您没有权限修改此节点！','info');
        		return false;
        	}
        }
    });
};

/*在新tab页打开事件*/
function open_tab(id, text) {
    if ($('#content_tab').tabs('exists', id + '.' + text)) {
        $('#content_tab').tabs('select', id + '.' + text);
    } else {
    	showProgress('加载中','正在加载中，请耐心等待...');
        $('#content_tab').tabs('add', {
            id: 'tab_page_'+id,
            title: id + '.' + text,
            href: baseUrl + '/getArticle?id='+id,
            closable: true,
            cls: 'content-tab-header',
            bodyCls: 'content-tab-content',
            extractor: function(data) {
            	var res = JSON.parse(data);
                return do_makeContent(res);
            },
            onLoad: function() {
            	after_tab_load(id);
            }
        });
    }
};
/*搜索功能*/
function reg_search_input() {
    $('#search_input').searchbox({
        prompt: '关键词',
        searcher: function(value, name) {
            search_value = value;
            load_list_tree(search_value);
        }
    });
};
/*注册添加文件编辑器*/
function reg_add_file_editor(){
	CKEDITOR.replace('win_add_file_editor',{
		height:335,
		resize_enabled:false
	});
}

/*注册更新文件编辑器*/
function reg_update_file_editor(){
	CKEDITOR.replace('win_update_file_editor',{
		height:335,
		resize_enabled:false
	});
}

/* 登录注销功能*/
function reg_security_button(){
	if(isLogin()){
		$('#security_button').linkbutton({
			iconCls:'icon-m-power-off',
			text:$.cookie('c_nick'),
			onClick:function(){
				$.get(baseUrl+'/security/appSignout',function(res){
					if(res && res.msgCode == '200'){
						$.messager.alert('提示', '注销成功！','info');
						$.cookie('c_nick',null);
						reg_security_button();
					}
				});
			}
		});
	}else{
		$('#security_button').linkbutton({
			iconCls:'icon-m-user',
			text:'游客',
			onClick:function(){
				$('#login_win').window('open');
			}
		});
	}
}

/*注册登录框回车事件*/
function reg_login_win_enter() {
	$('#usr_name').textbox('textbox').keydown(function(e) {
		if (e.keyCode == 13) {
			do_login();
		}
	});
	$('#usr_password').textbox('textbox').keydown(function(e) {
		if (e.keyCode == 13) {
			do_login();
		}
	});
}

/* 执行登录功能 */
function do_login(){
	$.post(baseUrl+'/security/appSignin',{
		username:$('#usr_name').val(),
		password:$('#usr_password').val()
	}, function(res) {
		if (res && res.msgCode == '200') {
			$.cookie('c_nick',res.msgDesc);
			$('a.panel-tool-close').trigger("click");
			reg_security_button();
			$('#usr_password').textbox('setValue','');
            DesktopNotify('Codepad','','欢迎 '+res.msgDesc+' 登录！');
		}
		if (res && res.msgCode == '500') {
			$.messager.alert(res.msgType, res.msgDesc, res.msgType);
		}
	});
}

//添加page页右键菜单
function reg_tab_page_menus(){
	$('div[id^="tab_page_"]').bind('contextmenu', function(e) {
        e.preventDefault();
        $('#content_tab_page_menu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    });
}
/*判断登录*/
function isLogin(){
	if($.cookie('c_nick') && $.cookie('c_nick') != 'null'){
		return true;
	}
	return false;
}
/*添加文件*/
function add_file() {
    $('#win_add_file').window('center');
    $('#win_add_file').window('open');
    $("#win_add_file_nodeid").textbox('setValue',$('#list_tree').tree('getSelected').id);
    $('#win_add_file_title').textbox('setValue','');
    $('#win_add_file_checkbox').removeAttr("checked");
    CKEDITOR.instances.win_add_file_editor.setData('');
};
/*执行添加文件功能*/
function do_add_file_submit(){
	var open = setDefault($('input[name="win_add_file_checkbox"]:checked').val(),'1');
	var iconCls = 'icon-m-file';
	if(open == '0'){
		iconCls = 'icon-m-file-lock';
	}
	$.post(baseUrl + '/addArticle', {
		pid : $('#win_add_file_nodeid').textbox('getValue'),
		text : $('#win_add_file_title').textbox('getValue'),
		iconCls : iconCls,
		open : open,
		article : CKEDITOR.instances.win_add_file_editor.getData(),
		create_id : $.cookie('c_nick')
	},function(res){
		if(res && res.resCode == '200'){
			$('a.panel-tool-close').trigger("click");
			appendTree(res.resDesc,true);
			showMessageBoard('提示', '文件添加成功！');
		}else{
			$.messager.alert('错误', '未知错误！', 'error');
		}
	});
}
/*更新文件*/
function do_update_file_submit(){
	var open = setDefault($('input[name="win_update_file_checkbox"]:checked').val(),'1');
	var iconCls = 'icon-m-file';
	if(open == '0'){
		iconCls = 'icon-m-file-lock';
	}
	var id = $('#win_update_file_id').textbox('getValue');
	var text = $('#win_update_file_title').textbox('getValue');
	$.post(baseUrl + '/updateArticle', {
		id : id,
		text : text,
		iconCls : iconCls,
		open : open,
		article : CKEDITOR.instances.win_update_file_editor.getData(),
		update_id : $.cookie('c_nick')
	},function(res){
		if(res && res.resCode == '200'){
			updateTree(id,text,iconCls);
			reload_tab_con(id);
			$('a.panel-tool-close').trigger("click");
			showMessageBoard('提示', '文件更新成功！');
		}else{
			$.messager.alert('错误', '未知错误！', 'error');
		}
	});
}
/*添加文件夹*/
function add_folder() {
	$.messager.prompt('提示', '请输入文件夹名称：', function(r){
        if (r){
        	$.post(baseUrl + '/addTree', {
        		pid : $('#list_tree').tree('getSelected').id,
        		text : r,
        		iconCls : 'icon-m-folder',
        		create_id : $.cookie('c_nick')
        	},function(res){
        		if(res && res.resCode == '200'){
        			appendTree(res.resDesc,true);
        			showMessageBoard('提示', '文件夹添加成功！');
        		}else{
        			$.messager.alert('错误', '未知错误！', 'error');
        		}
        	}); 
        }
    });
};
/*删除节点*/
function delete_ff() {
	var node = $('#list_tree').tree('getSelected');
	if ($.cookie('c_nick') == node.create_id) {
		if(!$('#list_tree').tree('isLeaf',node.target)){
			$.messager.alert('警告', '该节点有子文件，不能删除！', 'error');
			return false;
		}
		$.messager.confirm('确认', '确认删除节点？', function(r) {
			if (r) {
				var id = node.id;
				$.post(baseUrl + '/delTree', {
					id : node.id
				}, function(res) {
					if (res && res.resCode == '200') {
						removeTree();
						showMessageBoard('提示', '删除成功！');
					} else {
						$.messager.alert('错误', '未知错误！', 'error');
					}
				});
			}
		});
	} else {
		$.messager.alert('Message', '您没有权限删除此节点！', 'error');
	}
}
/*修改内容*/
function update_ff() {
	var node = $('#list_tree').tree('getSelected');
    if ($.cookie('c_nick') == node.create_id) {
    	if(node.node == '1'){
    		$.messager.prompt('修改文件夹', '请输入新的文件夹名称：', function(r){
                if (r){
                	$.post(baseUrl + '/updateTree', {
                		id : node.id,
                		text : r,
                		update_id : $.cookie('c_nick')
                	},function(res){
                		if(res && res.resCode == '200'){
                			updateTree(node.id,r,node.iconCls);
                			showMessageBoard('提示', '文件夹更新成功！');
                		}else{
                			$.messager.alert('错误', '未知错误！', 'error');
                		}
                	});
                }
            });
    	}
    	if(node.node == '0'){
    		$.get(baseUrl + '/getArticle?id='+node.id,function(data){
                CKEDITOR.instances.win_update_file_editor.setData(data.article);
                $("#win_update_file_id").textbox('setValue',data.id);
                $('#win_update_file_title').textbox('setValue',data.text);
                if(data.open == '1'){
                	$('#win_update_file_checkbox').removeAttr("checked");
                }else{
                	$('#win_update_file_checkbox').attr("checked",true);
                }
                $('#win_update_file').window('center');
                $('#win_update_file').window('open');
            });
    	}
    } else {
        $.messager.alert('Message', '您没有权限修改此节点内容！');
    }

};
/*设置默认值*/
function setDefault(val,def){
	if(val){
		return val;
	}
	return def;
}
/*添加子叶*/
function appendTree(data, expand) {
	$('#list_tree').tree('append', {
		parent : $('#list_tree').tree('getSelected').target,
		data : data
	});
	if (expand) {
		var target = $('#list_tree').tree('find', data.id).target;
		$('#list_tree').tree('expandTo', target).tree('select', target);
	}
}
/*添加树干*/
function insertTree(data, expand) {
	$('#list_tree').tree('insert', {
		after : $('#list_tree').tree('getSelected').target,
		data : data
	});
	if (expand) {
		var target = $('#list_tree').tree('find', data.id).target;
		$('#list_tree').tree('expandTo', target).tree('select', target);
	}
}
/*更新树*/
function updateTree(id, text, iconCls) {
	$('#list_tree').tree('update', {
		target : $('#list_tree').tree('find', id).target,
		text : text,
		iconCls : iconCls
	});
}
/*删除树*/
function removeTree(){
	$('#list_tree').tree('remove', $('#list_tree').tree('getSelected').target);
}
/*重载tab内容*/
function reload_tab_con(id) {
    if (id && $('#tab_page_' + id)) {
    	$('#tab_page_' + id).panel('refresh', baseUrl + '/getArticle?id=' + id);
    } else {
    	var tab = $('#content_tab').tabs('getSelected');
    	var id = tab.panel('options').id.replace('tab_page_','');
        tab.panel('refresh', baseUrl + '/getArticle?id=' + id);
    }
};
/*注册tabs菜单*/
function reg_content_tab_menu() {
	$('#content_tab').tabs({
		onContextMenu : function(e, title, index) {
			e.preventDefault();
			$('#content_tab_menu').menu('show', {
				left : e.pageX,
				top : e.pageY,
			}).data("tabTitle", title);
		}
	});
};
/*关闭tabs菜单*/
function do_close_tab(option) {
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
/*注册树形列表空白菜单*/
function reg_list_blank_menu() {
    $('#region_west').bind('contextmenu', function(e) {
    	if($('#list_tree').html()){
    		return false;
    	}
        e.preventDefault();
        $('#list_blank_menu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        if(isLogin()){
        	$('#list_blank_menu').menu('showItem',$('#add_blank_folder')[0]);
        	$('#list_blank_menu').menu('showItem',$('#add_blank_file')[0]);
        }else{
        	$('#list_blank_menu').menu('hideItem',$('#add_blank_folder')[0]);
        	$('#list_blank_menu').menu('hideItem',$('#add_blank_file')[0]);
        }
    });
};
/*应用高亮代码*/
function apply_highlighting(id) {
	$('#' + id).find('pre code').each(function(i, block) {
		hljs.highlightBlock(block);
	});
}
/*加载跳转页面*/
function load_directPage() {
    var aid = getUrlParam('aid');
    if (aid) {
    	showProgress('加载中','正在加载中，请耐心等待...');
    	$.get(baseUrl + '/getArticle?id='+aid,function(res){
    		$('#content_tab').tabs('add', {
    			id:'tab_page_'+res.id,
    			title:res.id+'.'+res.text,
    		    closable:true,
    		    cls: 'content-tab-header',
                bodyCls: 'content-tab-content',
                content: do_makeContent(res),
                onOpen: function() {
                	after_tab_load(res.id);
                }
    		});
    	})
    }
}
/*tab打开之后*/
function after_tab_load(id){
	reg_tab_page_menus();
	apply_highlighting('tab_page_'+id);
	closeProgress();
    //toggleDuoshuoComments('#comment-box-' + id, id, window.location.host + '?cid=' + id);
}
/*获取URL参数*/
function getUrlParam(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}




function toggleDuoshuoComments(container, content_id, content_url) {
    var el = document.createElement('div'); //该div不需要设置class="ds-thread"
    el.setAttribute('data-thread-key', content_id); //必选参数 文章的本地ID
    el.setAttribute('data-url', content_url); //必选参数 你网页的网址
    //el.setAttribute('data-title', content_title);//可选参数 你网页的标题
    el.setAttribute('data-author-key', ''); //可选参数 作者的本地用户ID
    DUOSHUO.EmbedThread(el);
    jQuery(container).append(el);
};




/*格式化文章*/
function do_makeContent(res){
	var meat_title = '<h2>'+res.text+'</h2>';
	var meat_sub_title = '<h5>创建：'+res.create_id+' / '+res.create_dt+'&nbsp;&nbsp;更新：'+setDefault(res.update_id,'-')+' / '+setDefault(res.update_dt,'-')+'</h5>';
	var page_url = window.location.protocol +'//'+ window.location.host +window.location.pathname+ '?aid=' + res.id;
	var meat_url = '<br /><p>本文地址：'+ page_url + '</p>';
    var duoshuo = '<br /><div id="comment-box-' + res.id + '" class="comment-box"></div>';
    var pattern = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
    var matches = pattern.exec(res.article);
    var content = '';
    if (matches) {
        content = matches[1];
    } else {
        content = res.article;
    }
    return meat_title + meat_sub_title + content + meat_url + duoshuo;
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
	if(!nIcon || nIcon == ''){
		nIcon = baseUrl+'/static/modules/codepad/img/codepad.ico';
	}
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