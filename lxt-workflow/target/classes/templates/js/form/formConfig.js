$(function(){
	FormUtils.init();
	var zIndex = 1,selection = 'form-panel';
	
	$('.form-component').draggable({
		proxy:function(source){
			var p = $(source).clone().append('<img class="drag-icon" src="../../images/icons/no.png">');
			return p.appendTo('body');
		},
		revert:true,
		cursor:'auto',
		deltaX:-5,
		deltaY:-15,
		onStartDrag:function(e){
			$(this).draggable('options').cursor='move';
			$(this).draggable('proxy').css({border:'2px dashed red','zIndex':1000,position:'absolute'});
		},
		onDrag:function(e){
			var x = e.pageX;
			var y = e.pageY;
			$(this).draggable('proxy').css({position:'absolute',left:x,top:y});
			$(this).data({x:x,y:y});
		},
		onStopDrag:function(e){
			$(this).draggable('options').cursor='auto';
		}
	});
	
	$('#drawPanel').droppable({
		accept:'.form-component',
		onDragEnter:function(e,source){
			$(source).draggable('options').cursor='auto';
			$(source).draggable('proxy').css('border','2px dashed green').find('.drag-icon').attr('src','../../images/icons/yes.png');
			$(this).css({'background-color':'#FBEC88'});
		},
		onDragLeave:function(e,source){
			$(source).draggable('options').cursor='move';
			$(source).draggable('proxy').css('border','2px dashed red').find('.drag-icon').attr('src','../../images/icons/no.png');
			$(this).css({'background-color':'white'});
		},
		onDrop:function(e,source){
			$(this).css({'background-color':'white'});
			$('.selection').removeClass('selection');
			var target = $('<div class="form-dropped-component selection"></div>');
			var offset = $(this).offset();
			var x = $(source).data('x')-offset.left-2;
			var y = $(source).data('y')-offset.top-12;
			$(this).append(target.css({left:x,top:y,'z-index':zIndex++,display:'none'}));
			var data = $.extend({},$(source).data('component'));
			target.data('component',data);
			eval('FormUtils.'+data.type+'Dropped(target)');
			
			target.fadeIn(function(){
				$('#eastPanel').panel('setTitle',data.title).panel('refresh','../../pages/form/'+data.type+'.jsp');
				$(this).click(function(){
					if(e && e.stopPropagation) {
						e.stopPropagation();
					} else {
						window.event.cancelBubble = true;
					}
					return false;
				}).draggable({
					onStartDrag:function(e){
						$(this).draggable('options').cursor='move';
						$('.selection').removeClass('selection');
						$(this).css({'z-index':zIndex++}).addClass('selection');
					},
					onDrag:function(e){
						var d = e.data;
						if (d.left < 0){d.left = 0;}
						if (d.top < 0){d.top = 0;}
						
						if (d.left + $(d.target).outerWidth() > $(d.parent).width()){
							d.left = $(d.parent).width() - $(d.target).outerWidth();
						}
						if (d.top + $(d.target).outerHeight() > $(d.parent).height()){
							d.top = $(d.parent).height() - $(d.target).outerHeight();
						}
					},
					onStopDrag:function(){
						$(this).draggable('options').cursor='auto';
						if(FormUtils.selected!=this){
							FormUtils.selected = this;
							$('#eastPanel').panel('setTitle',data.title).panel('refresh','../../pages/form/'+data.type+'.jsp');
						}
					}
				});
			});
		}
	});
});

var FormUtils = {
	selected:null,
	init:function(){
		$('#splash').fadeOut('fast');
		$('#drawPanel').data('component',{formName:'表单名称',title:'表单配置',type:'form',name:'customForm',taskName:'任务名称',action:'../../form!submitForm.action',method:'post',enctype:''});
		$('#component-label').data('component',{title:'文本内容配置',type:'label',text:'请输入文本内容'});
		$('#component-button').data('component',{title:'按钮配置',type:'button',text:'按钮'});
		$('#component-text').data('component',{title:'文本框配置',type:'text',label:'文本框名称：'});
		$('#component-textarea').data('component',{title:'文本域配置',type:'textarea',label:'文本域名称：'});
		$('#component-select').data('component',{title:'下拉框配置',type:'select',label:'下拉框名称：'});
		$('#component-radio').data('component',{title:'单选框配置',type:'radio',label:'单选项'});
		$('#component-checkbox').data('component',{title:'多选框配置',type:'checkbox',label:'多选项'});
		var formId = location.href.getParam("formId");
		if(formId){
			alert('update');
		}
		$('#drawPanel').click(function(){
			if(FormUtils.selected!=this){
				var t = this;
				setTimeout(function(){
					$('.selection').removeClass('selection');
					var data = $('#drawPanel').data('component');
					FormUtils.selected = t;
					$('#eastPanel').panel('setTitle',data.title).panel('refresh','../../pages/form/form.jsp');
				},1);
			}
		});
	},
	repair:function(v){
		var r = parseInt(v/4)*4;
		if (Math.abs(v % 4) > 2){
			r += v > 0 ? 4 : -4;
		}
		return r;
	},
	labelDropped:function(label){
		label.html('<span class="field-label">'+$('#component-label').data('component').text+'</span>');
	},
	buttonDropped:function(label){
		var btn = $('<a href="#" class="field-button">'+$('#component-button').data('component').text+'</a>');
		label.append(btn);
		btn.linkbutton({});  
	},
	textDropped:function(text){
		text.html('<label class="field-label">'+$('#component-text').data('component').label+'</label><input type="text" class="field-text"/>');
	},
	textareaDropped:function(textarea){
//		text.html('<span class="field-label">文本内容</span>');
	},
	selectDropped:function(select){
//		text.html('<span class="field-label">文本内容</span>');
	},
	radioDropped:function(radio){
		radio.html('<input class="field-radio" type="radio" checked="checked"><label class="field-label" style="position:relative;top:3px;">'+$('#component-radio').data('component').label+'</label>');
	},
	checkboxDropped:function(checkbox){
		checkbox.html('<input class="field-checkbox" type="checkbox" checked="checked"><label class="field-label" style="position:relative;top:1px;">'+$('#component-checkbox').data('component').label+'</label>');
	}
};

var FormHandler = {
	save:function(){
		var form = $('#drawPanel');
		var fields = [];
		$('.form-dropped-component').each(function(index){
			fields[index] = $(this).data('component');
			fields[index]['left'] = $(this).css('left');
			fields[index]['top'] = $(this).css('top');
			fields[index]['z-index'] = $(this).css('z-index');
		});
		var param = {form:form.data('component'),fields:fields};
		$.ajax({
			url:'form!save.action',
			data:{'param.formConfig':$.toJSON(param)},
			success:function(data){
				if(data.status==0){
					MessageUtils.success('保存表单', '恭喜你，表单['+$('#formTitle').html()+']保存成功！');
				}else{
					MessageUtils.fail(title, content);
				}
			},
			error:function(){
				
			}
		});
	},
	print:function(){
		
	},
	remove:function(){
		var selection = $('.selection');
		if(selection.attr('id')!='drawPanel'){
			selection.remove();
			$('#formTitle').click();
		}
	},
	toggleGrid:function(){
		$('#drawPanel').toggleClass('grid');
	}
};