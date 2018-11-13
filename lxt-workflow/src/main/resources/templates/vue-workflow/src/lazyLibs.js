import Vue from 'vue'

require.ensure([], () => {
  let fastclick = require('fastclick')
  fastclick.attach(document.body)
  
  window.lazyLibsLoaded = true
}, 'lazyLibs')