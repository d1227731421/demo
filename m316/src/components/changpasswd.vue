<template>
    <div style="width: 100%;height: 266px">
        <el-form label-position="top" label-width="80px" :model="formLabelAlign">
            <el-form-item label="新 密 码">
                <el-input v-model="formLabelAlign.passwd_1"  show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码">
                <el-input v-model="formLabelAlign.passwd_2"  show-password></el-input>
            </el-form-item>
        </el-form>
        <el-row>

            <el-col span="4" push="5">
                <el-button @click="update">确认</el-button>
            </el-col>
            <el-col span="4" push="12">
                <el-button @click="cancel">取消</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import userApi from "../api/user";

    export default {

        name: "changpasswd",
        data() {
            return {
                formLabelAlign: {
                    passwd_1: "",
                    passwd_2: ""
                }
            }
        },
        methods: {
            cancel() {
                this.formLabelAlign = {
                    passwd_1: "",
                    passwd_2: ""
                }
            },
            update() {
                if (this.formLabelAlign.passwd_1 != this.formLabelAlign.passwd_2) {
                    this.$message.error("两次密码不一致")
                    return
                }
                if (this.formLabelAlign.passwd_1 == "") {
                    this.$message.error("密码不能为空")
                    return
                }

                userApi.update({
                    id: this.$store.state.userId,
                    password: this.formLabelAlign.passwd_2,
                    phone: this.$store.state.phone,
                    username:this.$store.state.username
                }).then(res => {
                    this.$message({
                        message: res.data.message,
                        type: res.data.flag ? "success" : "error"
                    })
                    this.cancel()
                })
            }
        }
    }
</script>

<style scoped>

</style>