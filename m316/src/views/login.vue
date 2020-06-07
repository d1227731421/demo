<template>
    <div id="beijing">
        <div id="zhuti">
            <div class="left zhuti-l"></div>
            <div class="right zhuti-r">
                <div id="denglukuang">
                    <div>用户登录</div>
                    <input type="number" id="txt"  v-model="phone" placeholder="请输入账号">
                    <input type="password" id="pw"  v-model="password" placeholder="请输入密码">
                    <span id="sp1"></span><span id="sp2"></span>
                    <a id="a1" @click="loginHandle">登     录</a>
<!--                    <a href="#" id="a2">忘记密码？</a>-->
                    <a  @click="resgHandle" id="a3">免费注册</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import  userApi from "../api/user"
    export default {
        name: "login",
        data() {
            return {
                phone: "",
                password:"",
            }
        },

        methods:{
            resgHandle(){
                this.$router.push("/resg")
            },
            loginHandle(){
                if(this.password==""||this.phone=="")
                {
                    this.$message.error("密码和账号不能为空")
                    return
                }
                const loading=this.$loading({
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                userApi.login(this.phone,this.password)
                .then(res=>{
                    setTimeout(()=>{
                        this.$message({
                            message:res.data.message,
                            type:res.data.flag?"success":"error",
                            duration:800
                        })
                        loading.close()
                    },500)
                    if(res.data.flag){
                        this.$store.commit("setUserId",res.data.data.id)
                        this.$store.commit("setPhone",res.data.data.phone)
                        this.$store.commit("setUsername",res.data.data.username)
                        this.$router.replace("/index/homebox")
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
    @font-face {
        font-family: 'icomoon';
        src:  url('../assets/fonts/icomoon.eot?ar8rik');
        src:  url('../assets/fonts/icomoon.eot?ar8rik#iefix') format('embedded-opentype'),
        url('../assets/fonts/icomoon.ttf?ar8rik') format('truetype'),
        url('../assets/fonts/icomoon.woff?ar8rik') format('woff'),
        url('../assets/fonts/icomoon.svg?ar8rik#icomoon') format('svg');
        font-weight: normal;
        font-style: normal;
        font-display: block;
    }

    #beijing {
        background: url(../assets/images/1.jpg) no-repeat;
        background-size: 100% 100%;
        height: 900px;
    }
    .left {
        float: left;
    }
    .right {
        float: right;
    }
    #zhuti {
        width: 850px;
        height: 550px;
        position: fixed;
        left: 50%;
        top: 100px;
        background-color: #fff;
        border-radius: 20px;
        transform: translate(-50%);
        overflow: hidden;
    }
    .zhuti-l {
        width: 50%;
        height: 100%;
        background: url(../assets/images/2.png) no-repeat;
        background-size: 100% 100%;
        margin-top: 25px;
    }
    .zhuti-r {
        width: 50%;
        height: 100%;
        position: relative;
    }
    #denglukuang {
        position: absolute;
        width: 300px;
        height: 270px;
        background: rgba(0, 0, 0, 0.2);
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
    }
    #denglukuang div {
        position: absolute;
        top: 20px;
        left: 20px;
        font-size: 16px;
        color: #fff;
        border-left: 2px solid #43b3ff;
        padding-left: 5px;
    }
    #denglukuang input {
        outline: none;
        position: absolute;
        left: 50px;
        height: 30px;
        width: 170px;
        border: none;
        border-radius: 3px;
        padding-left: 30px;
    }
    #txt {
        top: 70px;
    }
    #pw {
        top: 120px;
    }
    input::-webkit-input-placeholder {
        color: #ccc;
    }
    #denglukuang span {
        font-family: "icomoon";
        color: #ccc;
        position: absolute;
        left: 55px;
    }
    #sp1 {
        top: 75px;
    }
    #denglukuang #sp2 {
        left: 58px;
        top: 126px;
    }
    #denglukuang a {
        color: #fff;
        text-decoration: none;
        position: absolute;
        display: block;
    }
    #denglukuang #a1 {
        width: 130px;
        height: 30px;
        background-color: #43b3ff;
        border-radius: 3px;
        top: 175px;
        left: 88px;
        text-align: center;
        line-height: 30px;
    }
    #denglukuang #a2 {
        left: 50px;
        top: 220px;
        font-size: 14px;
    }
    #denglukuang #a3 {
        left: 210px;
        top: 220px;
        font-size: 14px;
    }
</style>