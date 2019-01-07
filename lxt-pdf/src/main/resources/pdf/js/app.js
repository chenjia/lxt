var app = angular.module("app", []);
	
app.config(function($httpProvider) {
	$httpProvider.defaults.transformRequest = function(obj) {
		var str = [];
		for ( var p in obj) {
			str.push(encodeURIComponent(p) + "="
					+ encodeURIComponent(obj[p]));
		}
		return str.join("&");
	}

	$httpProvider.defaults.headers.post = {
		'Content-Type' : 'application/x-www-form-urlencoded'
	}

});

app.directive('qrcode', function() { 
	return {
		restrict:'E',
		replace:true,
		template:'<div></div>',
		link:function(scope, element, attrs, ctrls){
			var element = $(element);
			var options = {
				render:'table',
				width:attrs.size||160,
				height:attrs.size||160,
				text:attrs.text||''
			};

			element.qrcode(options);
		}
	};
}).directive('barcode', function() { 
	return {
		restrict:'E',
		replace:true,
		template:'<div></div>',
		link:function(scope, element, attrs, ctrls){
			var element = $(element);
			
			var options = {
				barWidth:attrs.width||1,
				barHeight:attrs.height||50
			};

			element.barcode(attrs.text || '', attrs.type||'ean13',options);
		}
	};
});