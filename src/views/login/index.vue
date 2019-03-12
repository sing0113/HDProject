<template>
  <div>
    <el-card class="login-form-layout">
      <el-form autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left">
        <div style="text-align: center">
          <img :src="hd_logo">
        </div>

        <h2 class="login-title color-main">hjmall-admin-web</h2>

        <el-form-item prop="userName">
          <el-input name="userName" type="text" v-model="loginForm.userName" autoComplete="on" placeholder="请输入用户名">
            <span slot="prefix">
              <svg-icon icon-class="user" class="color-main" style="width: 24px;height: 20px;margin-top: 11px"></svg-icon>
            </span>
          </el-input>
        </el-form-item>

        <el-form-item v-if="visible" prop="password">
          <el-input name="password" type="password" v-model="loginForm.password" autoComplete="on" placeholder="请输入密码">
            <span slot="prefix">
              <svg-icon icon-class="password" class="color-main" style="margin-left: 5px"></svg-icon>
            </span>
            <span slot="suffix" @click="changePass('show')">
              <svg-icon icon-class="eye" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>

        <el-form-item v-else prop="password">
          <el-input name="password" type="text" v-model="loginForm.password" autoComplete="on" placeholder="请输入密码">
            <span slot="prefix">
              <svg-icon icon-class="password" class="color-main" style="margin-left: 5px"></svg-icon>
            </span>
            <span slot="suffix" @click="changePass('hide')">
              <svg-icon icon-class="eye_look" class="color-main"></svg-icon>
            </span>
          </el-input>
        </el-form-item>

        <el-form-item style="margin-bottom: 60px">
          <el-button style="width:100%" type="primary" :loading="loading" @click.native.prevent="handleLogin">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <img :src="background_image" class="login-center-layout">
  </div>
</template>

<script>
  import background_image from '@/assets/images/background_image.jpg'
  import hd_logo from '@/assets/images/hd_logo.png'

  export default {
    name: 'login',
    data() {
      const validateUserName = (rule, value, callback) => {
        if (!value){
          callback(new Error('用户名不能为空！'));
        } else {
          callback();
        }
      };
      const validatePassword = (rule, value, callback) => {
        if (value.length < 3 ) {
          callback(new Error('密码不能小于三位！'));
        } else {
          callback();
        }
      };
      return {
        background_image,
        hd_logo,
        visible: true,
        loading: false,
        loginForm: {
          userName:'admin',
          password:'123456'
        },
        loginRules: {
          userName: [
            {validator: validateUserName, trigger: 'blur', require:true}
          ],
          password: [
            {validator: validatePassword, trigger: 'blur', require:true}
          ]
        },
      }
    },
    methods: {
      changePass(value) {
        this.visible = !(value === 'show');
      },
      handleLogin() {
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.loading = true;
            this.$store.dispatch('Login',this.loginForm).then(() => {
              this.loading = false;
              this.$router.push({path: '/'});
            }).catch(() => {
              this.loading = false;
            })
          } else {
            alert('输入参数不合法！');
            return false;
          }
        })
      }
    }
  }
</script>

<style scoped>
  .login-form-layout {
    position: absolute;
    left: 0;
    right: 0;
    width: 360px;
    margin: 140px auto;
    border-top: 10px solid #409EFF;
  }

  .login-title {
    text-align: center;
  }

  .color-main {
    color: #409EFF;
  }

  .login-center-layout {
    width:100%;
    height: 785px;
  }
</style>
