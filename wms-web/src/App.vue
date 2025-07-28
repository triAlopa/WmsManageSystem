<template>
  <div id="app">
    <router-view></router-view>
  </div>

</template>

<script>

// import IndexVue from "@/components/IndexVue.vue";



export default {
  name: 'App',
  components: {
    // IndexVue
  },
  data(){
    return {
      user :JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  watch:{
    '$store.state.menu':{
      handler(val,old){
        if(!old&& this.user && this.user.no){
          this.$store.commit("setRouter",val)
        }
      },
      immediate:true
    },

  },
  // watch: {
  //   '$store.state.menu': {  // 监听 Vuex 中 menu 状态的变化
  //     handler(val, old) {  // 变化时的回调函数
  //       if (!old && this.user && this.user.no) {
  //         this.$store.commit("setRouter", val);
  //       }
  //     },
  //     immediate: true      // 立即执行一次 handler
  //   }
  // }
}
</script>

<style>
#app {
 height: 100%;
}
</style>
