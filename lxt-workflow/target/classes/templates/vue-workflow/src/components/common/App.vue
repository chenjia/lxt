<template>
    <router-view></router-view>
</template>

<script>
import Vue from 'vue'
import { mapGetters } from 'vuex'
import store from '../../vuex/store'

export default {
  name: 'app',
  data(){
    return {
      transitionName: 'animate-in'
    }
  },
  computed: {
    ...mapGetters([
      'isLoading'
    ])
  },
  methods:{
    
  },
  mounted(){
    setTimeout(()=>{
      require('../../lazyLibs')
    }, Config.preload)
  }
}
</script>

<style type="text/css">

</style>
