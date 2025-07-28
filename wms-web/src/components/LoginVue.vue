<template>
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">用户登录</h1>
        <el-form :model="loginForm" label-width="100px"
                 :rules="rules" ref="loginForm">
          <el-form-item label="账号" prop="no">
            <el-input type="text" v-model="loginForm.no"
                      autocomplete="off" size="small"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input  type="password" v-model="loginForm.password"
                      show-password autocomplete="off" size="small" @keydown.enter.native="confirm"></el-input>
          </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="confirm"  :disabled="confirm_disabled">确定</el-button>
              <el-button type="primary" @click="register"  style="margin-left: 100px">注册</el-button>
            </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginVue",//Component name "login" should always be multi-word
  data() {
    return {
      confirm_disabled: false,
      loginForm: {
        no: '',
        password: '',
      },
      rules: {
        no: [
          {required: true, message: '请输入账号', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      },
    }
  },
  methods: {
    register(){
      this.$router.replace('/RegisterVue');
    },
    confirm() {
      this.confirm_disabled = true;
      this.$refs.loginForm.validate((valid) => {
        if (valid) {//valid 验证登录是否成功变量 //去后端login方法去验证账号密码
          this.$axios.post(this.$httpUrl + '/user/login', this.loginForm).then(res => res.data).then(res => {
            console.log(res)
            if (res.code == 200) {
              //存储 ??作用是
              //1.首页展示该登陆人 名字
              sessionStorage.setItem("CurUser", JSON.stringify(res.data.user))
              this.$store.commit("setMenu",res.data.menu)
              this.$notify({
                title: '成功',
                message: '账号密码校验成功',
                type: 'success',
                duration: 2000
              });
              //跳转到index
              this.$router.replace('/IndexVue');
            } else {
              this.confirm_disabled = false;
              this.$notify.error({
                title: '错误',
                message: 'error :(',
                duration: 2000
              });
              return false;
            }
          })
        }else {
          this.confirm_disabled=false;
          console.log('校验失败');
          return false;
        }
      })
    },
  },
}


</script>

<style scoped>
.loginBody {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #7a857b
}

.loginDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 450px;
  height: 330px;
  background: #fff;
  border-radius: 5%;
}

.login-title {
  margin: 20px 0;
  text-align: center;
}

.login-content {
  width: 400px;
  height: 250px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>