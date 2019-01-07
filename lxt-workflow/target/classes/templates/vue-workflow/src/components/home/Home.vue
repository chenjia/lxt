<template>
  <Layout>
    <LayoutPanel region="north" style="overflow: hidden;">
      <table width="100%">
        <tr height="24">
          <td rowspan="2">
            <img src="../../../static/images/logo.png">
          </td>
          <td>
            <a id="ddd" href="javascript:void(0)">top center</a>
          </td>
          <td align="right" style="padding:0px 6px;">
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-home'">首页</a>
            <span class="short-separator">|</span>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-desktop'">桌面</a>
            <span class="short-separator">|</span>
            <a id="message" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-message'" onclick="openTab('消息中心')"><span>(1)消息</span></a>
            <span class="short-separator">|</span>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-config'">设置</a>
            <span class="short-separator">|</span>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-help'">帮助</a>
            <span class="short-separator">|</span>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-logout'" onclick="logout()">退出</a>
          </td>
        </tr>
        <tr height="24">
          <td>
            bottom center
          </td>
          <td align="right" style="padding:0px 10px;">
            欢迎您，亲爱的用户[<span id="screenname"></span>]，系统时间：<span id="systime"></span>
          </td>
        </tr>
      </table>
    </LayoutPanel>
    <LayoutPanel region="south" style="height:40px;overflow: hidden;">
        <table width="100%" cellpadding="0" cellspacing="0">
        <tr height="35">
          <td align="left" style="padding:0px 6px;width:20%;">
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-favorite'" onclick="addFavorite();">收藏</a>
            <span class="short-separator">|</span>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-box'" onclick="suggest();">意见箱</a>
          </td>
          <td style="padding:0px 6px;width:60%;">
            <marquee id="noticeMarquee" direction="right" scrollamount="2" scrolldelay="50" onmouseover="this.stop()" onmouseout="this.start()">土豪，我们做朋友吧~~</marquee>
          </td>
          <td align="right" style="padding:0px 6px;width:20%;">
            <label for="changeSkinSel">更换皮肤：</label>
            <select id="changeSkinSel" class="easyui-combobox" style="width:100px;" data-options="panelWidth:100,panelHeight:300,editable:false,onChange:changeSkin">
              <option value="default" selected="selected">默认经典</option>
              <option value="black">黑色幽默</option>
              <option value="bootstrap">解靴带</option>
              <option value="gray">灰色空间</option>
              <option value="cupertino">蓝色忧郁</option>
              <option value="dark-hive">黑暗蜂房</option>
              <option value="pepper-grinder">纸张研磨</option>
              <option value="sunny">夏日阳光</option>
              <option value="metro">都市风情</option>
              <option value="metro-blue">蓝色都市</option>
              <option value="metro-gray">灰色都市</option>
              <option value="metro-green">绿色都市</option>
              <option value="metro-orange">橘色都市</option>
              <option value="metro-red">红色都市</option>
            </select>
          </td>
        </tr>
      </table>
    </LayoutPanel>
    <LayoutPanel region="west" style="width:207px;">
      <div class="easyui-accordion" data-options="fit:true" >
        <div title="流程管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
          <ul id="menuTree"></ul>
        </div>
        <div title="Help" data-options="iconCls:'icon-help'" style="padding:10px;">
          
        </div>
        <div title="TreeMenu" data-options="iconCls:'icon-search'" style="padding:10px;">
          
        </div>
      </div>
    </LayoutPanel>
    <LayoutPanel region="center">
      <div id="tabs" class="easyui-tabs" data-options="border:false,fit:true,headerCls:'background-color:white;'">
        <div data-options="iconCls:'icon-form',closable:true,href:'../form/formList.jsp'" title="表单列表">
          
        </div>
        <div data-options="iconCls:'icon-task',closable:true,href:'../workflow/taskList.jsp'" title="任务列表">
          
        </div>
        <div data-options="iconCls:'icon-task',closable:true,href:'../workflow/taskCompleteList.jsp'" title="已办任务">
          
        </div>
        <div data-options="iconCls:'icon-workflow',closable:true,href:'../workflow/workflowList.jsp'" title="流程列表">
          
        </div>
        <div data-options="iconCls:'icon-user',closable:true,href:'../system/userList.jsp'" title="用户列表">
          
        </div>
        <div data-options="iconCls:'icon-home',closable:true" title="系统首页" style="padding:10px">
          
        </div>
        <div data-options="iconCls:'icon-desktop',closable:true" title="我的桌面" style="padding:10px">
          
        </div>
      </div>
    </LayoutPanel>
</Layout>
</template>

<script>

export default {
  name: 'home',
  data() {
    return {
      
    }
  },
  methods: {
    
  },
  mounted(){

  }
}
</script>
<style type="text/css" scoped>

</style>