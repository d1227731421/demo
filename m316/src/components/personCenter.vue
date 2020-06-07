<template>
    <div style="padding-top: 30px;background-color:#E6E6E6;height: 800px;">
            <el-row>
                <el-col :span="4" :push="5">
                    <el-card shadow="hover" body-style="{ border: '1px solid darkgray' }">
                        <el-row>
                            <el-col style="margin-bottom: 45px" push="7">
                                <img src="../assets/images/5.png" width="100px" />
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col style="margin-bottom: 25px;text-align: center" :pull="1">
                                <span > {{$store.state.username}}</span>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col style="margin-bottom: 25px" :push="7">
                                <el-button type="primary" > 个人资料</el-button>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col style="margin-bottom:25px" :push="7">
                                <el-button @click="loginOut" > 退出登录</el-button>
                            </el-col>
                        </el-row>
                    </el-card>
                </el-col>
                <el-col :span="8" :push="8">
                    <el-card shadow="hover">
                        <el-menu
                                router
                                class="el-menu-demo"
                                default-active="/index/personCenter/changpasswd"
                                mode="horizontal"
                                background-color="#FFFFFF"
                                text-color="#000000"
                                active-text-color=" #43b3ff">
                            <el-menu-item  index="/index/personCenter/changpasswd">修改密码</el-menu-item>
                            <el-menu-item index="/index/personCenter/question">问题反馈</el-menu-item>
                        </el-menu>
                        <router-view></router-view>
                    </el-card>
                </el-col>
            </el-row>
    </div>
</template>

<script>
    export default {
        name: "personCenter",
        methods:{
            loginOut(){
                this.$store.commit("setUserId","")
                this.$router.replace("/")
            },
        }
    }
</script>

<style scoped>
    .el-menu-demo >li{
        margin-left:88px;
    }
    .el-menu-demo {
        margin-left: 60px;
    }
</style>