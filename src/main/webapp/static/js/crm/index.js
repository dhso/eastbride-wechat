Ext.Loader.setConfig({
	enabled : true,
	paths : {
		'Ext.ux' : baseUrl + '/static/extjs/ux',
		'App.ux' : baseUrl + '/static/extjs/myux'
	}
});
Ext.require('App.ux.Notification');
Ext.require('App.ux.IFrame');
Ext.require('App.ux.TreePicker');
Ext.Ajax.timeout = 120000;
Ext.Ajax.on('requestexception', function(conn, response, options, eopts) {
	console.log(response.status);
	if (response.status === 500) {
		Ext.Msg.alert('提示', '在线演示系统为只读模式，部分功能可能不流畅。完美体验请搭建本地环境。');
	} else if (response.status === 404) {
		Ext.Msg.alert('提示', '找不到请求的页面。');
	} else {
		Ext.Msg.alert('提示', '在线演示系统为只读模式，部分功能可能不流畅。完美体验请搭建本地环境。');
	}
});

var x_field_required = '<span class="mustinput-label">*</span>';

Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'under';
			var _head_navbar = Ext.create('Ext.panel.Panel', {
				id : '_head_navbar',
				xtype : 'panel',
				region : 'north',
				width : 200,
				height : 60,
				border : false,
				contentEl : '_id_north_el',
				maxHeight : 60,
				minHeight : 60,
				split : true,
				loader : {},
				collapsible : true,
				collapseMode : 'mini',
				header : false
			});
			var _status_toolbar = Ext.create('Ext.panel.Panel', {
					id : '_status_toolbar',
					xtype : 'panel',
					region : 'south',
					width : 200,
					height : 18,
					border : false,
					contentEl : '_id_south_el',
					loader : {},
					bodyStyle : {
						backgroundColor : '#DFE8F6'
					},
					header : false
				});
				
			var _menu_toolbar_search = Ext.create('Ext.form.field.Trigger',{
				id : '_menu_toolbar_search',
				xtype : 'triggerfield',
				width : 185,
				fieldCls : 'app-form-fieldcls',
				emptyText : '查找...',
				trigger1Cls : 'x-form-search-trigger'
			});
			var _menu_toolbar_blank = {
				id : '_menu_toolbar_blank',
				xtype : 'tbfill'
			};
			var _menu_toolbar_dorpdownMenu = Ext.create('Ext.menu.Menu', {
				id : '_menu_toolbar_dorpdownMenu',
				xtype : 'menu',
				loader : {}
			});
			_menu_toolbar_dorpdownMenu.add([{
				id : '_id_325b9647',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon152.png',
				text : '<span class="app-normal">首选项</span>',
				handler : function() {
					_fn_preference();
				}
			},{
				id : '_id_5d18a87a',
				xtype : 'menuseparator'
			},{
				id : '_id_f9c66812',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/key.png',
				text : '<span class="app-normal">锁定离开</span>'
			},{
				id : '_id_3d34e3a7',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/exit2.png',
				text : '<span class="app-normal">安全退出</span>',
				handler : function() {
					_fn_logout();
				}
			}]);
			var _menu_dorpdown = {
				id : '_menu_dorpdown',
				xtype : 'button',
				icon : baseUrl + '/static/img/icon/icon141.png',
				text : '',
				menu : _menu_toolbar_dorpdownMenu,
				tooltip : '更多选项'
			};
			
			var _menu_toolbar = Ext.create('Ext.toolbar.Toolbar',{
				id : '_menu_toolbar',
				xtype : 'toolbar',
				height : 27,
				loader : {},
				enableOverflow : true
			});
			
			Ext.getCmp('_menu_toolbar').add(_menu_toolbar_search,_menu_toolbar_blank,_menu_dorpdown);
			Ext.util.CSS.createStyleSheet(
					'#_menu_toolbar {border-top-width: 0px !important;}',
					'_id_5cb545ba');
			Ext.util.CSS.createStyleSheet(
					'#_menu_toolbar {border-right-width: 0px !important;}',
					'_id_e401076d');
			Ext.util.CSS.createStyleSheet(
					'#_menu_toolbar {border-bottom-width: 1px !important;}',
					'_id_c83d1bb8');
			Ext.util.CSS.createStyleSheet(
					'#_menu_toolbar {border-left-width: 0px !important;}',
					'_id_1333f0a3');
			
			var _id_9f3f2aa6 = Ext.create('Ext.menu.Menu', {
				id : '_id_9f3f2aa6',
				xtype : 'menu',
				border : false,
				loader : {},
				plain : false,
				floating : false,
				app : 169
			});
			
			Ext.Ajax.request({
			    url: baseUrl + '/crm/menus',
			    params: {
			    	type_id: 'wechat'
			    },
			    success: function(response){
			    	var data = Ext.decode(response.responseText);
			    	Ext.Array.each(data, function(element, index, countriesItSelf) {
			    		_id_9f3f2aa6.add({
							xtype : 'menuitem',
							icon : baseUrl + '/static/img/icon/icon75.png',
							text : '<span class="app-normal">'+element.TEXT+'</span>',
							handler : function() {
								fn_quick_click(element.ID, element.TEXT, element.URL);
							},
							app : 169
			    		});
		        	});
			    }
			});
			
			
			var _id_9fa86b69_listeners = {
				afterrender : {
					fn : function() {
						Ext.getCmp('_id_9fa86b69').addDocked(_menu_toolbar);
					}
				}
			};
			var _id_9fa86b69_cfg = {
				id : '_id_9fa86b69',
				xtype : 'tab',
				listeners : _id_9fa86b69_listeners,
				layout : 'fit',
				loader : {},
				title : '<span class="app-container-title-normal">菜单</span>',
				app : 169
			};
			var _id_9fa86b69 = Ext.create('Ext.panel.Panel', _id_9fa86b69_cfg);
			
			Ext.getCmp('_id_9fa86b69').add(_id_9f3f2aa6);
			
			var _west = Ext.create('Ext.tab.Panel', {
				id : '_west',
				xtype : 'tabpanel',
				region : 'west',
				width : 240,
				border : true,
				maxWidth : 300,
				minWidth : 220,
				split : true,
				loader : {},
				collapsible : true,
				collapseMode : 'mini',
				header : false,
				plain : true,
				tabPosition : 'top',
				tabBar : {
					height : 30,
					defaults : {
						height : 30 - 2
					}
				}
			});
			Ext.getCmp('_west').add(_id_9fa86b69);
			_west.setActiveTab(0);
			Ext.util.CSS
					.createStyleSheet(
							'.x-tab-default-top-active {border-top: 2px solid #0099FF;}',
							'_west_style');
			Ext.util.CSS.createStyleSheet(
					'#_west-body {border-top-width: 0px !important;}',
					'_id_6d370481');
			Ext.util.CSS.createStyleSheet(
					'#_west-body {border-right-width: 1px !important;}',
					'_id_eaff8223');
			Ext.util.CSS.createStyleSheet(
					'#_west-body {border-bottom-width: 1px !important;}',
					'_id_96899f3f');
			Ext.util.CSS.createStyleSheet(
					'#_west-body {border-left-width: 1px !important;}',
					'_id_e3f7831d');
			var _id_91044d31_cfg = {
				id : '_id_91044d31',
				xtype : 'tab',
				contentEl : '_div_center',
				loader : {},
				title : '<span class="app-container-title-normal">欢迎</span>'
			};
			var _id_91044d31 = Ext.create('Ext.panel.Panel', _id_91044d31_cfg);
			var _tabs_cfg = {
				id : '_tabs',
				xtype : 'tabpanel',
				region : 'center',
				width : 200,
				loader : {},
				plain : true,
				tabPosition : 'top',
				tabBar : {
					height : 30,
					defaults : {
						height : 30 - 2
					}
				},
				app : 169
			};
			var _tabs = Ext.create('Ext.tab.Panel', _tabs_cfg);
			Ext.getCmp('_tabs').add(_id_91044d31);
			_tabs.setActiveTab(0);
			Ext.util.CSS
					.createStyleSheet(
							'.x-tab-default-top-active {border-top: 2px solid #0099FF;}',
							'_tabs_style');
			Ext.util.CSS.createStyleSheet(
					'#_tabs-body {border-top-width: 0px !important;}',
					'_id_3e2696ae');
			Ext.util.CSS.createStyleSheet(
					'#_tabs-body {border-right-width: 1px !important;}',
					'_id_ca216074');
			Ext.util.CSS.createStyleSheet(
					'#_tabs-body {border-bottom-width: 1px !important;}',
					'_id_d8da8400');
			Ext.util.CSS.createStyleSheet(
					'#_tabs-body {border-left-width: 1px !important;}',
					'_id_04cbf780');
			var _test = Ext.create('Ext.container.Viewport', {
				id : '_test',
				xtype : 'viewport',
				layout : 'border',
				loader : {}
			});
			Ext.getCmp('_test').add(_head_navbar);
			Ext.getCmp('_test').add(_status_toolbar);
			Ext.getCmp('_test').add(_west);
			Ext.getCmp('_test').add(_tabs);

			_west.setActiveTab(0);
		});
// 打开菜单功能页面
function fn_quick_click(id, name, url) {
	fnaddtab(id, name, url);
}

function fnaddtab(menuid, menuname, url) {
	if (Ext.isEmpty(url)) {
		return;
	}
	var id = "_id_tab_" + menuid;
	var _tabs = Ext.getCmp('_tabs');
	var tab = _tabs.getComponent(id);
	var tempflag = 0;
	if (!tab) {
		var iframe = Ext.create('App.ux.IFrame', {
			mask : true,
			layout : 'fit',
			// 这个参数仅起到将iframe组件自带的mask调节到相对居中位置的作用
			height : document.body.clientHeight - 200,
			loadMask : '正在努力加载页面, 请稍候...'
		});
		tab = _tabs.add({
			id : id,
			title : '<span class="app-container-title-normal">' + menuname
					+ '</span>',
			closable : true,
			layout : 'fit',
			items : [ iframe ]
		});
		tab.on('activate', function() {
			// 防止已打开的功能页面切回时再次加载
			if (tempflag === 0) {
				iframe.load(baseUrl + url);
				tempflag = 1;
			}
		});
	}
	// 激活新增Tab
	_tabs.setActiveTab(tab);
}


// 移除首页正在加载的缓冲div
Ext.EventManager.on(window, 'load', function() {
	App.job(function() {
		Ext.get('loading').fadeOut({
			duration : 500, // 遮罩渐渐消失
			remove : true
		});
		Ext.get('loading-mask').fadeOut({
			duration : 500,
			remove : true
		});
	}, 50); // 做这个延时，只是为在Dom加载很快的时候GIF动画效果更显著一点

});

// 注销
function _fn_logout() {
	App.confirm('注销并安全退出系统吗？', function(btn) {
		if (btn === 'cancel') {
			App.tip('操作被取消。');
			return;
		}
		App.mask('正在注销, 请稍候...');
		App.ajax({
			url : baseUrl + '/security/appSignout',
			wait : false,
			ok : function(data) {
				App.unmask();
				window.location.href = baseUrl + '/security/login';
			}
		});
	});
}

// 打开首选项页面
function _fn_preference() {
	fnaddtab('207', '首选项', '/system/preference/init.jhtml');
}

// 按钮矢量图标动画控制
function _btn_onmouseout(me) {
	// 停止ICON转动
	Ext.get(me).down('i').removeCls('fa-spin');
}

// 按钮矢量图标动画控制
function _btn_onmouseover(me) {
	//ICON转动
	Ext.get(me).down('i').addCls('fa-spin');
}

//显示系统时钟
function showTime() {
	Ext.get('rTime').update(Ext.Date.format(new Date(), 'H:i:s'));
}

//加载完毕执行函数
window.onload = function() {
	showTime();
	App.task(showTime, 1000);
	//页面加载完毕后选中第一个导航按钮
}