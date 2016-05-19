var LoginInfoData;
var parentPower = [];
var childPower = {};
var childPowerList = [];

var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
showLeftPush = parent.document.getElementById('showLeftPush'),
body = document.body;

showLeftPush.onclick = function() {
	classie.toggle( this, 'active' );
	classie.toggle( body, 'cbp-spmenu-push-toright' );
	classie.toggle( menuLeft, 'cbp-spmenu-open' );
	disableOther( 'showLeftPush' );
};

$.ajax({   
       type: "POST",   
       url: "userBackStage/getLoginSession.do",  
       dataType: "json",
       async: false,
       success: function(data) {
       	LoginInfoData = data;
       	$("#userName").html(LoginInfoData.userName);
       	
       	var leftPower = LoginInfoData.powerList;
		var leftContent = "";
		
		$.each(leftPower, function(n,value) {
			if(value.parentPowerId) {
				if(childPower[value.parentPowerId]) {
					childPowerList = childPower[value.parentPowerId];
				} else {
					childPowerList = [];
				}
				childPowerList.push(value);
				childPower[value.parentPowerId] = childPowerList;
			} else {
				parentPower.push(value);
			}
		});
		
		$.each(parentPower, function(n,value) {
			leftContent += '<li>';
			
			if(childPower[value.id]) {
				leftContent += '<a href="#"><i class="fa fa-cogs nav_icon"></i>'+value.powerName+' <span class="nav-badge">12</span> <span class="fa arrow"></span></a>';
				leftContent += '<ul class="nav nav-second-level collapse">';
				$.each(childPower[value.id], function(m,childValue) {
					leftContent += '<li><a href="#">'+childValue.powerName+'</a></li>';
				});
				leftContent += '</ul>'
			} else {
				leftContent += '<a href="#"><i class="fa fa-home nav_icon"></i>'+value.powerName+'</a>';
			}
			leftContent += '</li>';
		});
		$("#side-menu").html(leftContent);
    }
});
	
function disableOther( button ) {
	if( button !== 'showLeftPush' ) {
		classie.toggle( showLeftPush, 'disabled' );
	}
}
	
function getBodyHeight() {
	var leftHieght = window.innerHeight-76.38-62 + "px";
	$(".cbp-spmenu-vertical").css("height", leftHieght);
	$("#menu").css("height", leftHieght);
	$("#content").css("height", leftHieght);
}

jQuery(document).ready(function() {
	jQuery('#vmap').vectorMap({
		map: 'world_en',
		backgroundColor: '#fff',
		color: '#696565',
		hoverOpacity: 0.8,
		selectedColor: '#696565',
		enableZoom: true,
		showTooltip: true,
		values: sample_data,
		scaleColors: ['#585858', '#696565'],
		normalizeFunction: 'polynomial'
	});
});