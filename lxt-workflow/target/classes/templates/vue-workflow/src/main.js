window.Config = {
  appVersion:'1.0.30',
  nativeVersion:'1.0',
  // server: 'http://localhost:8700/lxt-gateway',
  server: 'http://47.100.119.102/lxt-gateway',
  appDownloadUrl:'https://chenjia.github.io/vue-app/public/index.html',
  chcpUrl:'https://chenjia.github.io/vue-app/public/chcp.json',
  key:'ed26d4cd99aa11e5b8a4c89cdc776729',
  random:(''+Math.random()).substr(2),
  preload: 3000
}

import Vue from 'vue'
import 'vx-easyui/dist/themes/default/easyui.css';
import 'vx-easyui/dist/themes/icon.css';
import 'vx-easyui/dist/themes/vue.css';
import EasyUI from 'vx-easyui';
import { mapMutations } from 'vuex'
import utils from './utils'
import App from './components/common/App'
import router from './router'
import store from './vuex/store'
import '../static/css/main.css'

Vue.config.productionTip = false
window.utils = utils
Vue.use(EasyUI);

Vue.mixin({
  data() {
    return {
      screenWidth: document.documentElement.clientWidth,
      screenHeight: document.documentElement.clientHeight
    }
  },
  methods: {
    go(url) {
      this.$router.push(url)
    },
    back() {
      router.goBack()
    },
    ...mapMutations({
      
    })
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
