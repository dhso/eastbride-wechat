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

$(function(){
	//移除标签页
	$('#main_tab_nav').on('click', '.am-icon-close', function() {
		var $item = $(this).closest('li');
		var index = $('#main_tab_nav').children('li').index($item);
		$item.remove();
		$('#main_tab_bd').find('.am-tab-panel').eq(index).remove();
		$('#main_tab').tabs('open', index > 0 ? index - 1 : index + 1);
		$('#main_tab').tabs('refresh');
	});
});

function loadTab(title, url) {
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : url,
		success : function(data) {
			addTab(title, data);
		},
		error : function() {
		}
	});
}
var tabCounter = 0;

function addTab(tabName, tabContent) {
	var noexist = true;
	$('#main_tab_nav').find('li a').each(function() {
		if (tabName == $(this).text())
			noexist = false;
	});
	if (!noexist)
		$('#main_tab').tabs('open', $('#main_tab_nav').find("a:contains("+tabName+")").index());
		return;
	var nav = '<li><span class="am-icon-close"></span>'
			+ '<a href="javascript: void(0)">' + tabName + '</a></li>';
	var content = '<div class="am-tab-panel">' + tabContent + '</div>';
	$('#main_tab_nav').append(nav);
	$('#main_tab_bd').append(content);
	tabCounter++;
	$('#main_tab').tabs('refresh');
	$('#main_tab').tabs('open', tabCounter - 1);
}