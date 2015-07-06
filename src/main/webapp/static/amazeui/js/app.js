(function($) {
	'use strict';

	$(function() {
		var $fullText = $('.admin-fullText');
		$('#admin-fullscreen').on('click', function() {
			$.AMUI.fullscreen.toggle();
		});

		$(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
			$fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
		});

	});

})(jQuery);

$(function() {
	// 移除标签页
	$('#main_tab_nav').on('click', '.am-icon-close', function() {
		var $item = $(this).closest('li');
		var index = $('#main_tab_nav').children('li').index($item);
		$item.remove();
		$('#main_tab_bd').find('.am-tab-panel').eq(index).remove();
		$('#main_tab').tabs('open', index > 0 ? index - 1 : index + 1);
		$('#main_tab').tabs('refresh');
	});
	// 点击导航链接
	$('ul.admin-sidebar-list li').on('click', 'a', function() {
		$('ul.admin-sidebar-list li').find('a').each(function() {
			$(this).removeClass('am-active');
		});
		$(this).addClass('am-active');
	});
});

function loadTab(title, url) {
	var exist = false;
	var loadGif;
	$('#main_tab_nav').find('li a').each(function() {
		if (title == $(this).text())
			exist = true;
	});
	if (exist) {
		chooseTab(title, '#main_tab', '#main_tab_nav');
		return false;
	}
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : url,
		beforeSend : function() {
			$.AMUI.progress.start();
			loadGif = layer.load(0, {time: 10*1000});
		},
		complete : function() {
			$.AMUI.progress.done();
			layer.close(loadGif);
		},
		success : function(data) {
			addTab(title, data, '#main_tab', '#main_tab_nav', '#main_tab_bd');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			changeProgressColor('red');
			layer.alert(textStatus, {
				icon : 2
			});
		}
	});
}

var tabCounter = 0;

function addTab(tabName, tabContent, tab, tab_nav, tab_bd) {
	var nav = '<li><span class="am-icon-close"></span>'
			+ '<a href="javascript: void(0)">' + tabName + '</a></li>';
	var content = '<div class="am-tab-panel">' + tabContent + '</div>';
	$(tab_nav).append(nav);
	$(tab_bd).append(content);
	tabCounter++;
	$(tab).tabs('refresh');
	chooseTab(tabName, '#main_tab', '#main_tab_nav');
}

function chooseTab(tabName, tab, tab_nav) {
	var $item = $(tab_nav).find('a:contains("' + tabName + '")').parent('li');
	var index = $(tab_nav).children('li').index($item);
	if (index >= 0)
		$(tab).tabs('open', index);
}

function changeProgressColor(color) {
	if (!color)
		color = '#5eb95e';
	$('#nprogress .nprogress-bar').css('background', color);
}