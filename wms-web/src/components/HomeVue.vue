<template>

  <div class="centered-content">
    <el-card  class="box" shadow="hover" style="margin-top: -120px">
    <h1 align="center">{{'欢迎你'+user.name}}</h1>
    <el-descriptions :title="个人中心" :size="120" :column="2" border>
      <el-descriptions-item  label-class-name="my-label" content-class-name="my-content">
        <template slot="label">
          <i class="el-icon-s-custom"></i>
          账号
        </template>
        {{user.no}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          电话
        </template>
        {{user.phone}}
      </el-descriptions-item >
      <el-descriptions-item >
        <template slot="label">
          <i class="el-icon-location-outline"></i>
          性别
        </template>
        <el-tag
          :type="user.sex ===1?'primary':'danger'"
          disable-transitions><i :class="user.sex==1?'el-icon-male':'el-icon-female'"></i>{{user.sex==1?'男':'女'}}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item style="background-color: rgba(255,255,255,0)">
        <template slot="label">
          <i class="el-icon-tickets"></i>
          角色
        </template>
        <el-tag
          type="success"
          disable-transitions>{{user.roleId==0?'超级管理员':(user.roleId==1?"管理员":"普通用户")}}</el-tag>
      </el-descriptions-item>
    </el-descriptions>

    <DateUtils align="center"></DateUtils>
    </el-card>
  </div>
</template>

<script>
import DateUtils from "@/components/DateUtils.vue";
export default {
  name: "HomeVue",
   components:{DateUtils},
  data() {
    return {
      user :JSON.parse(sessionStorage.getItem('CurUser'))
      // user :{}
    }
  },
  computed(){
   this.init();
  },
  methods:{
    init(){
      this.user=JSON.parse(sessionStorage.getItem('CurUser'))
    },
  },
}
</script>



<style scoped>
.centered-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #ffffff;
}
.box{
    border-radius: 15px;
    background: rgba(255, 255, 255, 0.3);
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
}
</style>