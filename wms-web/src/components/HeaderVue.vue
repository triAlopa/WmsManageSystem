<template>
  <div style="display: flex;line-height: 60px">

    <div>
      <i :class=icon style="font-size: 20px;line-height: 63px;  cursor: pointer" @click="collapse"></i>
    </div>
    <div style="flex: 1;text-align:center;font-size: 30px">
      <span>欢迎来到仓库管理系统</span>
    </div>

    <el-dropdown>

      <i class="el-icon-user" style="margin-right:3px "></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="outlogin">退出登录</el-dropdown-item>
      </el-dropdown-menu>
      <span>{{user.name}}</span>
    </el-dropdown>

  </div>
</template>


<script>
export default {
  name: "HeaderVue",
  props: {
    icon: String
  },
  data() {
    return {
      user :JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  methods: {
    toUser() {
      console.log('toUser')
      this.$router.push('/HomeVue')

    },
    outlogin() {
      this.$confirm('是否确认退出登录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '退出成功!',
        });
        this.$router.push('/')
        sessionStorage.clear()
      }).catch(() => {
        this.$message({
          type: "info",
          message: '没事干 厨房有煤气灶自己拧着玩😅',
        })
      });
    },
    collapse() {
      this.$emit('doCollapse')
    }
  },
  created() {
    this.$router.push('/HomeVue')
  }
}
</script>

<style scoped>

</style>