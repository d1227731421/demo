<template>
    <div style="width: 100%;height: 266px">
        `
        <el-input
                type="textarea"
                :autosize="{ minRows: 8, maxRows: 8}"
                placeholder="意见及建议..."
                resize="none"
                 v-model="question.text">
        </el-input>
        <el-row style="margin-top: 12px">
            <el-col span="20" push="3" >
                <label>联系方式：</label>
                <el-input v-model="question.phone"  style="width:60%" placeholder="请输入电话号码"/>
                <el-button  type="primary" @click="submit">提交</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import questionApi from "../api/question"
    import util from "../util/util";
    export default {
        name: "question",
        data() {
            return {
                question:{
                    text: "",
                    phone:"",
                }
            }
        },
        methods:{
            submit(){
               if(util.hasEmpty(this.question)){
                   this.$message.error("不能包含空值")
                   return
               }
                questionApi.save(this.question).then(res=>{
                    this.$message({
                        message:res.data.message,
                        type:res.data.flag?"success":"error"
                    })
                    this.question.text= ""
                    this.question.phone=""
                })

            }
        }
    }
</script>

<style scoped>

</style>