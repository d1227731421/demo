<template>
    <div id="zhuce">
        <img src="../assets/images/3.png">
        <div id="dv1">已有帐号,
            <a href="/">马上登录</a>
        </div>
        <div id="zhuti">
            <div id="dv2">会员注册</div>
            <div id="dv3">
                <input type="number" id="txt1"  v-model="user.phone" placeholder="请输入您的手机号">
                <span id="sp1">请输入您的手机号</span>
                <input type="text"   id="txt2" v-model="username"  placeholder="请输入用户名">
                <span id="sp2">请输入用户名</span>
                <input type="password" id="password1"  v-model="password"  placeholder="请输入密码">
                <span id="sp3">请输入您的密码</span>
                <input type="password" id="password2"  v-model="user.password" placeholder="再次输入密码">
                <span id="sp4">请再次输入密码</span>
                <div id="dv4"><a @click="registerHandle">立即注册</a></div>
            </div>
        </div>
    </div>
</template>

<script>
    import userApi from "../api/user"
    import util from "../util/util";
    export default {
        name: "resg",
        data() {
            return {
                password:"",
                user: {
                    phone:"",
                    password:"",
                    username:""
                }
            }
        },
        methods:{

            registerHandle(){
                if(this.password!=this.user.password)
                {
                    this.$message.error('两次密码不一致');
                    return
                }
                if(this.password==""||this.user.password==""||this.user.phone=="")
                {
                    this.$message.error('账号和密码不能为空')
                    return;
                }
                const loading = this.$loading({
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                userApi.register(this.user).then(res=>{
                    let data=res.data
                    if(data.flag==true){
                        setTimeout(() => {
                            loading.close();
                            this.$message({
                                message: '注册成功',
                                type: 'success'
                            });
                            this.$router.replace("/")
                        }, 500);
                    }else {
                        setTimeout(() => {
                            loading.close();
                            this.$message.error(data.message)
                        }, 500);

                    }
                })
            }
        }
    }
</script>

<style scoped>
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
    input[type="number"]{
        -moz-appearance: textfield;
    }
    #zhuce {
        background-color: #e6e6e6;
        background-size: 100% 100%;
        height: 900px;
    }
    a {
        text-decoration: none;
    }
    #zhuce {
        position: relative;

    }
    #zhuce img {
        width: 300px;
        position: absolute;
        left: 200px;
    }
    #dv1 {
        width: 200px;
        height: 20px;
        color: #9c9c9c;
        position: absolute;
        left: 1100px;
        top: 50px;
        font-size: 14px;
    }
    #dv1 a {
        color: #72d7ad;
    }
    #zhuti {
        width: 1000px;
        height: 550px;
        background-color: #fff;
        position: fixed;
        left: 50%;
        top: 100px;
        transform: translate(-50%);
    }
    #dv2 {
        width: 100%;
        height: 70px;
        background-color: #44b9ff;
        color: #fff;
        text-align: center;
        line-height: 70px;
        font-size: 20px;

    }
    #dv3 input {
        padding-left: 10px;
        width: 350px;
        height: 35px;
        outline: none;
        border: 1px solid #e6e6e6;
        position: absolute;
        left: 50%;
        transform: translate(-50%);
    }
    input::-webkit-input-placeholder {
        color: #ccc;
    }
    #dv3 span {
        font-size: 12px;
        position: absolute;
        left: 325px;
        font-weight: 600;
    }
    #dv3 a {
        color: #fff;
        font-weight: 500;
    }
    #txt1 {
        top: 120px;
    }
    #dv3 #txt2 {
        top: 200px;
        width: 365px;
        left: 508px;

    }
    #yzm {
        width: 124px;
        height: 39px;
        background-color: #44b9ff;
        position: absolute;
        top: 200px;
        left: 550px;
        color: #fff;
        line-height: 39px;
        text-align: center;
        font-weight: 500;
    }
    #password1 {
        top: 281px;
    }
    #password2 {
        top: 360px;
    }
    #sp1 {
        top: 165px;
    }
    #sp2 {
        top: 245px;
    }
    #sp3 {
        top: 330px;
    }
    #sp4 {
        top: 405px;
    }
    #dv4 {
        width: 350px;
        height: 35px;
        background-color: #44b9ff;
        position: absolute;
        line-height: 35px;
        text-align: center;
        font-weight: 500;
        left: 50%;
        transform: translate(-50%);
        top: 450px;
    }
</style>