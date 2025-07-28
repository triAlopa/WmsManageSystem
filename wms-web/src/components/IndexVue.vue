<template>

  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_width" style="height: 100vh; background-color: rgb(238, 241, 246); margin-left: -1px ">
      <AsideVue :isCollapse="isCollapse"></AsideVue>
    </el-aside>

    <el-container style="height:100%">
      <el-header style="text-align: right; font-size: 12px;border-bottom: #a9a9a9 1px solid  " >
        <HeaderVue @doCollapse="doCollapse" :icon="icon" ></HeaderVue>
      </el-header>

      <el-main style="height:100%">
<!--        <main-vue></main-vue>-->
        <router-view></router-view>
      </el-main>
    </el-container>
    <up-vue></up-vue>
  </el-container>
</template>
<script>


import AsideVue from "@/components/AsideVue.vue";
// import MainVue from "@/components/MainVue.vue";
import HeaderVue from "@/components/HeaderVue.vue";


export default {
  name: "IndexVue",
  components: { HeaderVue, AsideVue},
  comments: {
    AsideVue,
    HeaderVue,
  },
  data() {
    return {
      isCollapse: false,
      aside_width:'200px',
      icon:'el-icon-s-fold'
    }
  },
  methods: {
    doCollapse() {
      this.isCollapse =!this.isCollapse
      if(!this.isCollapse){// 边界Aside展开状态
        this.aside_width='200px'
        this.icon='el-icon-s-fold'
      }else {
        this.aside_width='64px'
        this.icon='el-icon-s-unfold'
      }
    },
  }
}

</script>
<style scoped>
.el-header {
  color: #333;
  line-height: 60px;
}

.el-main {
  padding: 5px;
}

.el-aside {
  color: #333;
}

</style>