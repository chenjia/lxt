webpackJsonp([1],{"6ALN":function(t,e,n){e=t.exports=n("UTlt")(!1),e.push([t.i,".list-group[data-v-66aed24a]{padding-top:15px}",""])},DVfa:function(t,e,n){"use strict";n("YtJ0");e.a={name:"about",data:function(){return{latest:!1}},methods:{logout:function(){var t=this;MessageBox.confirm("确定要退出登录吗?","确认退出").then(function(e){t.go("login")},function(t){console.log(t)})},check:function(){utils.version.checkForUpdate()}},mounted:function(){var t=this;utils.version.getServerVersion().then(function(e){Config.nativeVersion!=e.data.nativeVersion?MessageBox.alert("当前版本过低，请安装最新版本！","版本更新").then(function(){window.open(Config.appDownloadUrl)}):Config.appVersion!=e.data.appVersion&&(t.latest=!0)})}}},cEc9:function(t,e,n){"use strict";function i(t){n("nd2/")}Object.defineProperty(e,"__esModule",{value:!0});var a=n("DVfa"),s=n("iZDH"),o=n("C7Lr"),l=i,r=o(a.a,s.a,!1,l,"data-v-66aed24a",null);e.default=r.exports},iZDH:function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticStyle:{position:"absolute",background:"#eee",width:"100%",height:"100%"}},[n("mt-header",{attrs:{title:"关于app"}},[n("mt-button",{attrs:{slot:"left",icon:"back"},on:{click:t.back},slot:"left"},[n("span",[t._v("返回")])])],1),t._v(" "),n("div",{staticClass:"list-group"},[n("mt-cell",{attrs:{title:"评分鼓励","is-link":""}}),t._v(" "),n("mt-cell",{attrs:{title:"欢迎页","is-link":""}}),t._v(" "),n("mt-cell",{attrs:{title:"分享推荐","is-link":""}}),t._v(" "),n("mt-cell",{attrs:{title:"客服电话","is-link":""}}),t._v(" "),n("mt-cell",{attrs:{title:"问题反馈","is-link":""}})],1),t._v(" "),n("div",{staticClass:"list-group"},[n("mt-cell",{attrs:{"is-link":""},nativeOn:{click:function(e){t.check()}}},[n("span",{attrs:{slot:"title"},slot:"title"},[t._v("\n  \t\t\t检查更新\n  \t\t\t"),t.latest?n("mt-badge",{attrs:{type:"error",size:"small"}},[t._v("new")]):t._e()],1)])],1)],1)},a=[],s={render:i,staticRenderFns:a};e.a=s},"nd2/":function(t,e,n){var i=n("6ALN");"string"==typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);n("FIqI")("4244564c",i,!0,{})}});