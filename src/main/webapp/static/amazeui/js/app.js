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
	var exist = false;
	$('#main_tab_nav').find('li a').each(function() {
		if (title == $(this).text())
			exist = true;
	});
	if (exist) {
		chooseTab(title,'#main_tab','#main_tab_nav');
		return false;
	}
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : url,
		beforeSend :function(){
			$.AMUI.progress.start();
		},
		complete : function(){
			$.AMUI.progress.done();
		},
		success : function(data) {
			$.AMUI.progress.done();
			addTab(title, data,'#main_tab','#main_tab_nav','#main_tab_bd');
		},
		error : function() {
			changeProgressColor('red');
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
	chooseTab(tabName,'#main_tab','#main_tab_nav');
}

function chooseTab(tabName, tab, tab_nav){
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