<template>
  <div class="RegisterBody">
    <div  >
      <div  >
        <p >注册</p>
        <el-form
            status-icon
            label-width="0"
            class="demo-ruleForm"
        >
          <el-form-item>
            <el-input  placeholder="请输入手机号"></el-input>
          </el-form-item>
          <el-form-item prop="pass">
            <el-input type="password"  placeholder="输入密码"></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input type="password"  placeholder="确认密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"  style="width:100%;">注册</el-button>
            <p class="login" @click="gotoLogin">已有账号？立即登录</p>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "RegisterVue",
  methods: {
    gotoLogin() {
      this.$router.replace('/LoginVue');
    },
    },
};
</script>

<style scoped>
.RegisterBody{
  position: absolute;
  width: 100%;
  height: 100%;
 background: #7a857b;
}
.loading-wrapper {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  background: #aedff8;
  display: flex;
  align-items: center;
  justify-content: center;
}
.register-wrapper img {
  position: absolute;
  z-index: 1;
}
.register-wrapper {

  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
}
#register {
  max-width: 340px;
  margin: 60px auto;
  background: #fff;
  padding: 20px 40px;
  border-radius: 10px;
  position: relative;
  z-index: 9;
}
.title {
  font-size: 26px;
  line-height: 50px;
  font-weight: bold;
  margin: 10px;
  text-align: center;
}
.el-form-item {
  text-align: center;
}
.login {
  margin-top: 10px;
  font-size: 14px;
  line-height: 22px;
  color: #1ab2ff;
  cursor: pointer;
  text-align: left;
  text-indent: 8px;
  width: 160px;
}
.login:hover {
  color: #2c2fd6;
}
.code >>> .el-form-item__content {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.code button {
  margin-left: 20px;
  width: 140px;
  text-align: center;
}
.el-button--primary:focus {
  background: #409EFF;
  border-color: #409EFF;
  color: #fff;
}
</style>


