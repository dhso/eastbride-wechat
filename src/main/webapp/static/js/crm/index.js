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
Ext.Ajax.on('beforerequest', function(conn, options, eopts) {
	Ext.Ajax.extraParams = {
		'app' : '169'
	};
});
Ext.Ajax.on('requestexception', function(conn, response, options, eopts) {
	if (response.status === 500) {
		Ext.Msg.alert('提示', '在线演示系统为只读模式，部分功能可能不流畅。完美体验请搭建本地环境。');
	} else if (response.status === 0) {
		Ext.Msg.alert('提示', '在线演示系统为只读模式，部分功能可能不流畅。完美体验请搭建本地环境。');
	} else {
		Ext.Msg.alert('提示', '在线演示系统为只读模式，部分功能可能不流畅。完美体验请搭建本地环境。');
	}
});
var x_field_required = '<span class="mustinput-label">*</span>';
Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'under';
			var _id_b4cb5d24_cfg = {
				id : '_id_b4cb5d24',
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
				header : false,
				app : 169
			};
			var _id_b4cb5d24 = Ext.create('Ext.panel.Panel', _id_b4cb5d24_cfg);
			var _id_a3de2835_cfg = {
				id : '_id_a3de2835',
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
				header : false,
				app : 169
			};
			var _id_a3de2835 = Ext.create('Ext.panel.Panel', _id_a3de2835_cfg);
			var _id_e0204221_cfg = {
				id : '_id_e0204221',
				xtype : 'triggerfield',
				width : 185,
				fieldCls : 'app-form-fieldcls',
				emptyText : '查找功能菜单...',
				trigger1Cls : 'x-form-search-trigger',
				app : 169
			};
			var _id_e0204221 = Ext.create('Ext.form.field.Trigger',
					_id_e0204221_cfg);
			var _id_232bd388 = {
				id : '_id_232bd388',
				xtype : 'tbfill',
				app : 169
			};
			var _id_3638200e_cfg = {
				id : '_id_3638200e',
				xtype : 'menu',
				loader : {},
				app : 169
			};
			var _id_3638200e = Ext.create('Ext.menu.Menu', _id_3638200e_cfg);
			var _id_acb74c05_cfg = {
				id : '_id_acb74c05',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/config1.png',
				text : '<span class="app-normal">首选项</span>',
				handler : function() {
					_fn_preference();
				},
				app : 169
			};
			_id_3638200e.add(_id_acb74c05_cfg);
			var _id_f2b63335_cfg = {
				id : '_id_f2b63335',
				xtype : 'menuseparator',
				app : 169
			};
			_id_3638200e.add(_id_f2b63335_cfg);
			var _id_c0af89f4_cfg = {
				id : '_id_c0af89f4',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/key.png',
				text : '<span class="app-normal">锁定离开</span>',
				app : 169
			};
			_id_3638200e.add(_id_c0af89f4_cfg);
			var _id_1521b8cd_cfg = {
				id : '_id_1521b8cd',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/exit2.png',
				text : '<span class="app-normal">安全退出</span>',
				handler : function() {
					_fn_logout();
				},
				app : 169
			};
			_id_3638200e.add(_id_1521b8cd_cfg);
			var _id_f32bda24 = {
				id : '_id_f32bda24',
				xtype : 'button',
				icon : baseUrl + '/static/img/icon/icon141.png',
				text : '',
				menu : _id_3638200e,
				tooltip : '更多选项',
				app : 169
			};
			var _id_b9f16c60_cfg = {
				id : '_id_b9f16c60',
				xtype : 'toolbar',
				height : 27,
				loader : {},
				enableOverflow : true,
				app : 169
			};
			var _id_b9f16c60 = Ext.create('Ext.toolbar.Toolbar',
					_id_b9f16c60_cfg);
			Ext.getCmp('_id_b9f16c60').add(_id_e0204221);
			Ext.getCmp('_id_b9f16c60').add(_id_232bd388);
			Ext.getCmp('_id_b9f16c60').add(_id_f32bda24);
			Ext.util.CSS.createStyleSheet(
					'#_id_b9f16c60 {border-top-width: 0px !important;}',
					'_id_09d1531f');
			Ext.util.CSS.createStyleSheet(
					'#_id_b9f16c60 {border-right-width: 0px !important;}',
					'_id_123741ac');
			Ext.util.CSS.createStyleSheet(
					'#_id_b9f16c60 {border-bottom-width: 1px !important;}',
					'_id_86e48e34');
			Ext.util.CSS.createStyleSheet(
					'#_id_b9f16c60 {border-left-width: 0px !important;}',
					'_id_5503e93d');
			var _id_card_183_store = Ext.create('Ext.data.TreeStore', {
				nodeParam : 'cascade_id_',
				proxy : {
					type : 'ajax',
					url : 'getModuleTree.jhtml'
				},
				root : {
					text : '根节点',
					id : '0.001',
					expanded : true
				}
			});
			var _id_card_183 = Ext.create('Ext.tree.Panel', {
				id : '_id_card_183',
				xtype : 'treepanel',
				icon : baseUrl + '/static/img/icon/folder22.png',
				animate : false,
				loader : {},
				title : '<span class="app-container-title-normal">控制台</span>',
				rootVisible : false,
				useArrows : true,
				store : _id_card_183_store,
				app : 169
			});
			_id_card_183.on('itemclick',
					function(view, record, item, index, e) {
						if (record.isExpanded()) {
							record.collapse();
						} else {
							record.expand();
						}
					});
			_id_card_183.on('expand', _fn_card_onexpand);
			_id_card_183.on('itemclick', fn_node_click);
			var _id_card_184_store = Ext.create('Ext.data.TreeStore', {
				nodeParam : 'cascade_id_',
				proxy : {
					type : 'ajax',
					url : 'getModuleTree.jhtml'
				},
				root : {
					text : '根节点',
					id : '0.002',
					expanded : true
				}
			});
			var _id_card_184 = Ext.create('Ext.tree.Panel', {
				id : '_id_card_184',
				xtype : 'treepanel',
				icon : baseUrl + '/static/img/icon/folder23.png',
				animate : false,
				loader : {},
				title : '<span class="app-container-title-normal">工作台</span>',
				rootVisible : false,
				useArrows : true,
				store : _id_card_184_store,
				app : 169
			});
			_id_card_184.on('itemclick',
					function(view, record, item, index, e) {
						if (record.isExpanded()) {
							record.collapse();
						} else {
							record.expand();
						}
					});
			_id_card_184.on('expand', _fn_card_onexpand);
			_id_card_184.on('itemclick', fn_node_click);
			var _id_card_236_store = Ext.create('Ext.data.TreeStore', {
				nodeParam : 'cascade_id_',
				proxy : {
					type : 'ajax',
					url : 'getModuleTree.jhtml'
				},
				root : {
					text : '根节点',
					id : '0.004',
					expanded : true
				}
			});
			var _id_card_236 = Ext.create('Ext.tree.Panel', {
				id : '_id_card_236',
				xtype : 'treepanel',
				icon : baseUrl + '/static/img/icon/folder27.png',
				animate : false,
				loader : {},
				title : '<span class="app-container-title-normal">演示</span>',
				rootVisible : false,
				useArrows : true,
				store : _id_card_236_store,
				app : 169
			});
			_id_card_236.on('itemclick',
					function(view, record, item, index, e) {
						if (record.isExpanded()) {
							record.collapse();
						} else {
							record.expand();
						}
					});
			_id_card_236.on('expand', _fn_card_onexpand);
			_id_card_236.on('itemclick', fn_node_click);
			var _sys_nav_listeners = {
				afterrender : {
					fn : function() {
						Ext.getCmp('_sys_nav').addDocked(_id_b9f16c60);
					}
				}
			};
			var _sys_nav_cfg = {
				id : '_sys_nav',
				xtype : 'tab',
				listeners : _sys_nav_listeners,
				animate : false,
				layout : 'accordion',
				loader : {},
				title : '<span class="app-container-title-normal">系统导航</span>',
				app : 169
			};
			var _sys_nav = Ext.create('Ext.panel.Panel', _sys_nav_cfg);
			Ext.getCmp('_sys_nav').add(_id_card_183);
			Ext.getCmp('_sys_nav').add(_id_card_184);
			Ext.getCmp('_sys_nav').add(_id_card_236);
			var _id_4fbda4c9_cfg = {
				id : '_id_4fbda4c9',
				xtype : 'triggerfield',
				width : 185,
				fieldCls : 'app-form-fieldcls',
				emptyText : '查找功能菜单...',
				trigger1Cls : 'x-form-search-trigger',
				app : 169
			};
			var _id_4fbda4c9 = Ext.create('Ext.form.field.Trigger',
					_id_4fbda4c9_cfg);
			var _id_41bff19f = {
				id : '_id_41bff19f',
				xtype : 'tbfill',
				app : 169
			};
			var _id_31ab206c_cfg = {
				id : '_id_31ab206c',
				xtype : 'menu',
				loader : {},
				app : 169
			};
			var _id_31ab206c = Ext.create('Ext.menu.Menu', _id_31ab206c_cfg);
			var _id_325b9647_cfg = {
				id : '_id_325b9647',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon152.png',
				text : '<span class="app-normal">首选项</span>',
				handler : function() {
					_fn_preference();
				},
				app : 169
			};
			_id_31ab206c.add(_id_325b9647_cfg);
			var _id_5d18a87a_cfg = {
				id : '_id_5d18a87a',
				xtype : 'menuseparator',
				app : 169
			};
			_id_31ab206c.add(_id_5d18a87a_cfg);
			var _id_f9c66812_cfg = {
				id : '_id_f9c66812',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/key.png',
				text : '<span class="app-normal">锁定离开</span>',
				app : 169
			};
			_id_31ab206c.add(_id_f9c66812_cfg);
			var _id_3d34e3a7_cfg = {
				id : '_id_3d34e3a7',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/exit2.png',
				text : '<span class="app-normal">安全退出</span>',
				handler : function() {
					_fn_logout();
				},
				app : 169
			};
			_id_31ab206c.add(_id_3d34e3a7_cfg);
			var _id_670f5099 = {
				id : '_id_670f5099',
				xtype : 'button',
				icon : baseUrl + '/static/img/icon/icon141.png',
				text : '',
				menu : _id_31ab206c,
				tooltip : '更多选型',
				app : 169
			};
			var _id_d862af25_cfg = {
				id : '_id_d862af25',
				xtype : 'toolbar',
				height : 27,
				loader : {},
				enableOverflow : true,
				app : 169
			};
			var _id_d862af25 = Ext.create('Ext.toolbar.Toolbar',
					_id_d862af25_cfg);
			Ext.getCmp('_id_d862af25').add(_id_4fbda4c9);
			Ext.getCmp('_id_d862af25').add(_id_41bff19f);
			Ext.getCmp('_id_d862af25').add(_id_670f5099);
			Ext.util.CSS.createStyleSheet(
					'#_id_d862af25 {border-top-width: 0px !important;}',
					'_id_5cb545ba');
			Ext.util.CSS.createStyleSheet(
					'#_id_d862af25 {border-right-width: 0px !important;}',
					'_id_e401076d');
			Ext.util.CSS.createStyleSheet(
					'#_id_d862af25 {border-bottom-width: 1px !important;}',
					'_id_c83d1bb8');
			Ext.util.CSS.createStyleSheet(
					'#_id_d862af25 {border-left-width: 0px !important;}',
					'_id_1333f0a3');
			var _id_9f3f2aa6_cfg = {
				id : '_id_9f3f2aa6',
				xtype : 'menu',
				border : false,
				loader : {},
				plain : false,
				floating : false,
				app : 169
			};
			var _id_9f3f2aa6 = Ext.create('Ext.menu.Menu', _id_9f3f2aa6_cfg);
			var _id_d8c2e939_cfg = {
				id : '_id_d8c2e939',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon75.png',
				text : '<span class="app-normal">功能模块</span>',
				handler : function() {
					fn_quick_click('197', '功能模块', 'system/module/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_d8c2e939_cfg);
			var _id_8a366412_cfg = {
				id : '_id_8a366412',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_8a366412_cfg);
			var _id_2bfdb243_cfg = {
				id : '_id_2bfdb243',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/page_office.png',
				text : '<span class="app-normal">流文件</span>',
				handler : function() {
					fn_quick_click('615', '流文件', 'system/byteObj/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_2bfdb243_cfg);
			var _id_eb83e6ec_cfg = {
				id : '_id_eb83e6ec',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_eb83e6ec_cfg);
			var _id_b46c0b62_cfg = {
				id : '_id_b46c0b62',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon134.png',
				text : '<span class="app-normal">角色与授权</span>',
				handler : function() {
					fn_quick_click('304', '角色与授权', 'system/role/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_b46c0b62_cfg);
			var _id_39a731c2_cfg = {
				id : '_id_39a731c2',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_39a731c2_cfg);
			var _id_1df57757_cfg = {
				id : '_id_1df57757',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon137.png',
				text : '<span class="app-normal">岗位与授权</span>',
				handler : function() {
					fn_quick_click('200', '岗位与授权', 'system/post/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_1df57757_cfg);
			var _id_efbd2fd4_cfg = {
				id : '_id_efbd2fd4',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_efbd2fd4_cfg);
			var _id_66b3df63_cfg = {
				id : '_id_66b3df63',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/email2.png',
				text : '<span class="app-normal">我的消息</span>',
				handler : function() {
					fn_quick_click('489', '我的消息', '');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_66b3df63_cfg);
			var _id_ee73cf3a_cfg = {
				id : '_id_ee73cf3a',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_ee73cf3a_cfg);
			var _id_1308fb14_cfg = {
				id : '_id_1308fb14',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon154.png',
				text : '<span class="app-normal">数据字典</span>',
				handler : function() {
					fn_quick_click('193', '数据字典',
							'system/dictionary/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_1308fb14_cfg);
			var _id_3e256e4a_cfg = {
				id : '_id_3e256e4a',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_3e256e4a_cfg);
			var _id_de2412fc_cfg = {
				id : '_id_de2412fc',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/id.png',
				text : '<span class="app-normal">序列号</span>',
				handler : function() {
					fn_quick_click('195', '序列号', 'system/idMgr/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_de2412fc_cfg);
			var _id_c31137b5_cfg = {
				id : '_id_c31137b5',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_c31137b5_cfg);
			var _id_21fcb74b_cfg = {
				id : '_id_21fcb74b',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/sql.png',
				text : '<span class="app-normal">WebSQL</span>',
				handler : function() {
					fn_quick_click('225', 'WebSQL', 'system/webSql/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_21fcb74b_cfg);
			var _id_8efff2b7_cfg = {
				id : '_id_8efff2b7',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_8efff2b7_cfg);
			var _id_9af9a937_cfg = {
				id : '_id_9af9a937',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon56.png',
				text : '<span class="app-normal">组织架构</span>',
				handler : function() {
					fn_quick_click('199', '组织架构', 'system/org/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_9af9a937_cfg);
			var _id_681a2e6d_cfg = {
				id : '_id_681a2e6d',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_681a2e6d_cfg);
			var _id_22446e55_cfg = {
				id : '_id_22446e55',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/page_picture.png',
				text : '<span class="app-normal">图标大全</span>',
				handler : function() {
					fn_quick_click('393', '图标大全', 'system/icon/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_22446e55_cfg);
			var _id_7c405575_cfg = {
				id : '_id_7c405575',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_7c405575_cfg);
			var _id_677fa19b_cfg = {
				id : '_id_677fa19b',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/user6.png',
				text : '<span class="app-normal">用户与授权</span>',
				handler : function() {
					fn_quick_click('201', '用户与授权', 'system/user/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_677fa19b_cfg);
			var _id_92c58c35_cfg = {
				id : '_id_92c58c35',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_92c58c35_cfg);
			var _id_b59ac003_cfg = {
				id : '_id_b59ac003',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon5.png',
				text : '<span class="app-normal">流程实例监管</span>',
				handler : function() {
					fn_quick_click('486', '流程实例监管', 'bpm/procInst/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_b59ac003_cfg);
			var _id_6dece08d_cfg = {
				id : '_id_6dece08d',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_6dece08d_cfg);
			var _id_7bca095d_cfg = {
				id : '_id_7bca095d',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/database_add.png',
				text : '<span class="app-normal">数据源</span>',
				handler : function() {
					fn_quick_click('530', '数据源', '');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_7bca095d_cfg);
			var _id_0dc1fbe6_cfg = {
				id : '_id_0dc1fbe6',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_0dc1fbe6_cfg);
			var _id_76006339_cfg = {
				id : '_id_76006339',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon79.png',
				text : '<span class="app-normal">参数表</span>',
				handler : function() {
					fn_quick_click('192', '参数表', 'system/param/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_76006339_cfg);
			var _id_96d689f3_cfg = {
				id : '_id_96d689f3',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_96d689f3_cfg);
			var _id_46b13a93_cfg = {
				id : '_id_46b13a93',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/config1.png',
				text : '<span class="app-normal">首选项</span>',
				handler : function() {
					fn_quick_click('207', '首选项', 'system/preference/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_46b13a93_cfg);
			var _id_81502291_cfg = {
				id : '_id_81502291',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_81502291_cfg);
			var _id_bc793349_cfg = {
				id : '_id_bc793349',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon146.png',
				text : '<span class="app-normal">分类科目</span>',
				handler : function() {
					fn_quick_click('194', '分类科目', 'system/catalog/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_bc793349_cfg);
			var _id_1391896d_cfg = {
				id : '_id_1391896d',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_1391896d_cfg);
			var _id_06ce4ebe_cfg = {
				id : '_id_06ce4ebe',
				xtype : 'menuitem',
				icon : baseUrl + '/static/img/icon/icon59.png',
				text : '<span class="app-normal">页面组件</span>',
				handler : function() {
					fn_quick_click('454', '页面组件', 'system/page/init.jhtml');
				},
				app : 169
			};
			_id_9f3f2aa6.add(_id_06ce4ebe_cfg);
			var _id_25626951_cfg = {
				id : '_id_25626951',
				xtype : 'menuseparator',
				app : 169
			};
			_id_9f3f2aa6.add(_id_25626951_cfg);
			var _id_9fa86b69_listeners = {
				afterrender : {
					fn : function() {
						Ext.getCmp('_id_9fa86b69').addDocked(_id_d862af25);
					}
				}
			};
			var _id_9fa86b69_cfg = {
				id : '_id_9fa86b69',
				xtype : 'tab',
				listeners : _id_9fa86b69_listeners,
				layout : 'fit',
				loader : {},
				title : '<span class="app-container-title-normal">快捷菜单</span>',
				app : 169
			};
			var _id_9fa86b69 = Ext.create('Ext.panel.Panel', _id_9fa86b69_cfg);
			Ext.getCmp('_id_9fa86b69').add(_id_9f3f2aa6);
			var _id_8555a79e_cfg = {
				id : '_id_8555a79e',
				xtype : 'tab',
				loader : {},
				title : '<span class="app-container-title-normal">发现</span>',
				app : 169
			};
			var _id_8555a79e = Ext.create('Ext.panel.Panel', _id_8555a79e_cfg);
			var _west_cfg = {
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
				},
				app : 169
			};
			var _west = Ext.create('Ext.tab.Panel', _west_cfg);
			Ext.getCmp('_west').add(_sys_nav);
			Ext.getCmp('_west').add(_id_9fa86b69);
			Ext.getCmp('_west').add(_id_8555a79e);
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
				title : '<span class="app-container-title-normal">欢迎</span>',
				app : 169
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
			var _test_cfg = {
				id : '_test',
				xtype : 'viewport',
				layout : 'border',
				loader : {},
				app : 169
			};
			var _test = Ext.create('Ext.container.Viewport', _test_cfg);
			Ext.getCmp('_test').add(_id_b4cb5d24);
			Ext.getCmp('_test').add(_id_a3de2835);
			Ext.getCmp('_test').add(_west);
			Ext.getCmp('_test').add(_tabs);
			function _fn_card_onexpand(me, eOpts) {
				var nav_mode_ = '1';
				if (nav_mode_ === '1') {
					var id_ = me.id.substr(9)
					_fn_nav_btn_click(id_);
				} else {
				}
			}
			function fn_node_click(view, record, item, index, e) {
				var url = record.raw.a;
				if (!Ext.isEmpty(url)) {
					fnaddtab(record.raw.id, record.raw.text, url);
				} else {
					if (record.raw.leaf) {
						App.tip('没有配置菜单的请求地址。');
					}
				}
			}
			function fn_quick_click(id, name, url) {
				fnaddtab(id, name, url);
			}
			function _t_quick_load() {
				refreshnode = _t_quick.getRootNode();
				_t_quick_store.load({
					callback : function() {
						_t_quick.expandAll();
					}
				});
			}
			_west.setActiveTab(0);
		});
// 打开菜单功能页面
function fnaddtab(menuid, menuname, url) {
	if (Ext.isEmpty(url)) {
		return;
	}
	var id = "_id_tab_" + menuid;
	url = '/aos/' + url;
	var index = url.indexOf('?');
	// 一级菜单的主页面所属的页面元素其page_id_同module_id_。
	url = url + (index === -1 ? '?' : '&') + 'aos_module_id_=' + menuid
			+ '&aos_page_id_=' + menuid;
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
				iframe.load(url);
				tempflag = 1;
			}
		});
	}
	// 激活新增Tab
	_tabs.setActiveTab(tab);
}

// 当前已按下的导航按钮
var _g_visited_domid = '';
// 横向导航和左侧导航的互动
function _fn_nav_btn_click(id_) {
	// 重定位当前卡片位
	var _sys_nav = Ext.getCmp('_sys_nav');
	if (!_sys_nav.isVisible()) {
		Ext.getCmp('_west').setActiveTab(_sys_nav);
	}
	var domid = '_id_nav_' + id_;
	var _dom_obj = Ext.getDom(domid);
	if (!App.empty(_g_visited_domid)) {
		var _dom_visited = Ext.getDom(_g_visited_domid);
		_dom_visited.className = 'button button-pill button-border-blue';
		// 停止ICON转动
		Ext.get(_dom_visited).down('i').removeCls('fa-spin');
	}
	// ICON转动
	Ext.get(_dom_obj).down('i').addCls('fa-spin');
	_dom_obj.className = 'button button-pill button-border-blue button-border-blue-visited';
	_g_visited_domid = domid;
	var nav_mode_ = '1';
	if (nav_mode_ === '1') {
		_fn_nav_left_mode1(id_);
	} else {
		// TODO 导航模式2没实现
	}
}

// 当导航模式为1时，水平和左侧导航的互动模式
function _fn_nav_left_mode1(id_) {
	var cmpid = '_id_card_' + id_;
	var _cmp_card = Ext.getCmp(cmpid);
	if (_cmp_card.getCollapsed()) {
		_cmp_card.expand(true)
	}
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
	if (!App.empty('183')) {
		var nav_tab_index_ = '0';
		if (nav_tab_index_ === '0') {
			_fn_nav_btn_click('183');
		}
	}
}