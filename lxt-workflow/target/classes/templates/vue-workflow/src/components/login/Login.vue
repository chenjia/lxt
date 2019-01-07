<template>
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="561" style="background: url(../../../static/images/lbg.gif)">
              <table width="940" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="238" style="background: url(../../../static/images/login01.jpg)">&nbsp;</td>
                </tr>
                <tr>
                  <td height="190">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="208" height="190" style="background: url(../../../static/images/login02.jpg)">&nbsp;</td>
                        <td width="518" style="background: url(../../../static/images/login03.jpg)">
                          <table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="50" height="50">
                                <img src="../../../static/images/user.png">
                              </td>
                              <td width="50" height="50">用户名：</td>
                              <td width="200" height="50">
                                <input v-model="model.username" style="width: 164px; height: 32px; line-height: 34px; background: url(../../../static/images/inputbg.gif) repeat-x; border: solid 1px #d1d1d1; font-size: 18px; font-family: Verdana, Geneva, sans-serif;padding-left:8px;">
                              </td>
                            </tr>
                            <tr>
                              <td height="50">
                                <img src="../../../static/images/password.png">
                              </td>
                              <td width="50" height="50">密　码：</td>
                              <td height="50">
                                <input v-model="model.password" type="password" onkeypress="onEnter(event.keyCode)" style="width: 164px; height: 32px; line-height: 34px; background: url(../../../static/images/inputbg.gif) repeat-x; border: solid 1px #d1d1d1; font-size: 16px;padding-left:8px;">
                              </td>
                            </tr>
                            <tr>
                              <td height="50">
                                <img src="../../../static/images/shield.png">
                              </td>
                              <td width="50" height="50">验证码：</td>
                              <td height="50">
                                <input v-model="model.captcha" type="tel" onkeypress="javascript:if(event.keyCode==13)login()" maxlength="4" style="width: 64px; height: 32px; line-height: 34px; background: url(../../../static/images/inputbg.gif) repeat-x; border: solid 1px #d1d1d1; font-size: 18px; font-family: Verdana, Geneva, sans-serif;padding-left:8px;">
                                <img :src="base64Img" @click="getCaptcha" title="看不清？换一张" style="vertical-align: middle;cursor:pointer;">
                              </td>
                            </tr>
                            <tr>
                              <td height="40">&nbsp;</td>
                              <td height="40">&nbsp;</td>
                              <td height="40">
                                <img @click="login" src="../../../static/images/login.gif" style="width:95px;height:34px;cursor:pointer;">
                              </td>
                            </tr>
                          </table>
                        </td>
                        <td width="214" style="background: url(../../../static/images/login04.jpg)">&nbsp;</td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td height="133" style="background: url(../../../static/images/login05.jpg)">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</template>

<script>
import store from '../../vuex/store'
import { mapGetters, mapMutations } from 'vuex'
export default {
  name: 'login',
  data() {
    return {
      model:{
        userId: 'admin',
        username: 'admin',
        password: 'admin',
        captcha: '',
        captchaToken: ''
      },
      server: Config.server,
      base64Img:''
    }
  },
  methods: {
    ...mapMutations({
      doLogin:'LOGIN',
      doLogout:'LOGOUT'
    }),
    getCaptcha(){
      utils.http.post('/lxt-manage/api/user/captcha').then(response => {
        this.model.captchaToken = response.data.body.data.captchaToken
        this.base64Img = 'data:image/png;base64, '+response.data.body.data.base64Img
      })
    },
    login(){
      if(this.model.captcha == ''){
        store.commit('TOGGLE_POPUP', {visible: true, text: '验证码不能为空', duration: 3000})
        return
      }

      setTimeout(()=>{
        utils.http.post('/lxt-manage/api/user/login', this.model).then(response => {
          if(response.data.body.data) {
            this.doLogin({
              user:response.data.body.data.user,
              userSetting:response.data.body.data.userSetting
            })
            this.go('/page/home')
          }else{
            store.commit('TOGGLE_POPUP', {visible: true, text: response.data.head.msg, duration: 3000})
            this.getCaptcha()
          }
        }, error => {
          this.go('/page/home')
        })
      },10)
    }
  },
  mounted(){
    
  },
  beforeRouteEnter(to, from, next){
    next(vm=>{
      vm.doLogout()
      vm.getCaptcha()
      vm.model.captcha = ''
    })
  }
}
</script>
