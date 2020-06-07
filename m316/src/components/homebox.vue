<template>
    <div class="box">
        <div>
            <div class="list">
                <div class="menber">
                    <div v-for="(item,index) in persion" @click="indexhandle(index)" class="list-childern"
                         :style=" number==index?style:''" v-text="item.memberName"></div>
                </div>
                <div class=" list-menu">
                    <div class="list-childern list-menu-childern" @click="dialogupdate=true">修改成员名称</div>
                    <div class="list-childern list-menu-childern" @click="dialogAdd=true">新增成员药箱</div>
                    <div class="list-childern list-menu-childern" @click="dialogdel=true">删除成员药箱</div>
                </div>
            </div>
        </div>
        <div>
            <div style="margin-top: 20px;margin-left: 30px">
                <transition name="el-fade-in-linear">
                <el-table
                        show-overflow-tooltip="true"
                        ref="multipleTable"
                        :data="tableData"
                        tooltip-effect="dark"
                        style="width: 100%"
                        height="620"
                        :border="border"
                        :header-cell-style="{background:'#009AC8',color:'white'}"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            prop="medicineChestName"
                            label="药品名称" width="121">
                    </el-table-column>
                    <el-table-column
                            prop="disease"
                            label="主治病症" width="121">
                    </el-table-column>
                    <el-table-column
                            prop="usages"
                            label="用法用量" width="121">
                    </el-table-column>
                    <el-table-column
                            label="有效期"
                            prop="expiryDate" width="121">
                    </el-table-column>
                    <el-table-column
                            prop="taboo"
                            label="禁忌" width="121">
                    </el-table-column>
                    <el-table-column
                            prop="storage"
                            label="储存方式" width="121">
                    </el-table-column>
                    <el-table-column
                            prop="surplus"
                            label="剩余量" width="121">
                    </el-table-column>
                </el-table>
                </transition>

                <el-row :gutter="10" style="margin-top: 15px">
                 <el-col :span="2" :push="12">
                     <el-button type="primary" @click="dialogMchest=true">添加</el-button>
                 </el-col>
                    <el-col :span="2"  :push="14">
                        <el-button type="primary" @click="updatemChect">修改</el-button>
                    </el-col>
                    <el-col :span="2"  :push="16">
                        <el-button type="primary" @click="deletemChect">删除</el-button>
                    </el-col>
                </el-row>
            </div>
        </div>
        <el-dialog title="提示" :visible.sync="dialogAdd" width="30%" :before-close="handleClose">
            <el-input type="text" placeholder="请输入成员名字" v-model="member.memberName"/>
            <span slot="footer" class="dialog-footer">
                     <el-button type="primary" @click="addMember">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="提示" :visible.sync="dialogupdate" width="30%" :before-close="handleClose">
            <el-row>
                <el-col :span="10">
                    <el-select v-model="member.id"  placeholder="请选择">
                        <el-option v-for="item in persion" :key="item.id" :label="item.memberName" :value="item.id"></el-option>
                    </el-select>
                </el-col>
                <el-col :span="2" style="padding-top: 12px">
                    修改为
                </el-col>
                <el-col :span="10">
                    <el-input type="text" placeholder="请输入成员名字" v-model="member.memberName"/>
                </el-col>
            </el-row>
            <span slot="footer" class="dialog-footer">
                     <el-button type="primary" @click="updateMember">修 改</el-button>
            </span>
        </el-dialog>
        <el-dialog title="提示" :visible.sync="dialogdel" width="30%" :before-close="handleClose">
            <el-row>
                <el-col :span="14" push="6">
                    <el-select v-model="member.id"  placeholder="请选择">
                        <el-option v-for="item in persion" :key="item.id" :label="item.memberName" :value="item.id"></el-option>
                    </el-select>
                </el-col>
            </el-row>
            <span slot="footer" class="dialog-footer">
                     <el-button type="primary" @click="deleteMember">删 除</el-button>
            </span>
        </el-dialog>
        <el-dialog title="提示" :visible.sync="dialogMchest" width="30%" :before-close="handleClose">
            <el-form label-position="right" label-width="80px" :model="formdata">
                <el-form-item label="药品名称">
                    <el-input v-model="formdata.medicineChestName"></el-input>
                </el-form-item>
                <el-form-item label="主治病症">
                    <el-input v-model="formdata.disease"></el-input>
                </el-form-item>
                <el-form-item label="用法用量">
                    <el-input v-model="formdata.usages"></el-input>
                </el-form-item>
                <el-form-item label="有 效 期">
                    <el-date-picker v-model="formdata.expiryDate"  style="width: 100%"  value-format="yyyy-MM-dd" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="禁  忌">
                    <el-input v-model="formdata.taboo"></el-input>
                </el-form-item>
                <el-form-item label="储存方式">
                    <el-input v-model="formdata.storage"></el-input>
                </el-form-item>
                <el-form-item label="剩 余 量">
                    <el-input type="number" v-model="formdata.surplus"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                     <el-button type="primary" @click="addMchest">确 认</el-button>
            </span>
        </el-dialog>
    </div>

</template>

<script>
    import memberApi from "../api/member";
    import mchestApi from "../api/mchest";
    import util from "../util/util";
    export default {
        name: "homebox",
        data() {
            return {
                member: {
                    memberName: "",
                    id: ""
                },
                dialogAdd: false,
                dialogupdate: false,
                dialogdel: false,
                tableData: [],
                border: true,
                persion: [],
                style: "background-color: rgba(0, 0, 0, 0.3);",
                number: 0,
                multipleSelection:[],
                dialogMchest:false,
                formdata:{
                    medicineChestName: "",
                    disease: "",
                    usages: "",
                    expiryDate: "",
                    taboo: "",
                    storage: "",
                    surplus: ""
                },

            }
        },
        mounted() {
            this.queryMember()
        },

        methods: {
            deletemChect(){
                if(this.multipleSelection.length==0){
                    this.$message.error("请选择要删除的行")
                    return
                }
                mchestApi.del(this.multipleSelection).then(res=>{
                    this.$message({
                        message: res.data.message,
                        type: res.data.flag ? "success" : "error"
                    })
                    this.queryMember()
                })
            },
            addMchest(){
                console.log(this.formdata.id)
                if(util.hasEmpty(this.formdata)){
                    this.$message.error("不能含有空置")
                    return
                }
                if(this.formdata.id==undefined){
                    if(this.persion.length==0){
                        this.$message.info("请选添加成员")
                        return
                    }
                    if(util.hasExist(this.formdata.medicineChestName,this.tableData,'medicineChestName')){
                        this.$message.error("以及有该药品")
                        return
                    }
                    mchestApi.add(this.persion[this.number].id,this.formdata).then(res=>{
                        this.$message({
                            message: res.data.message,
                            type: res.data.flag ? "success" : "error"
                        })
                        if (res.data.flag)
                            this.queryMember()
                        this.formdata={
                            medicineChestName: "",
                            disease: "",
                            usages: "",
                            expiryDate: "",
                            taboo: "",
                            storage: "",
                            surplus: ""
                        }
                        this.dialogMchest = false
                    })
                }
                else {
                    if(util.hasExistByUpdate(this.formdata.medicineChestName,this.tableData,'medicineChestName')){
                        this.$message.error("以及有该药品")
                        return
                    }
                    mchestApi.update(this.formdata,this.persion[this.number].id).then(res=>{
                        this.$message({
                            message: res.data.message,
                            type: res.data.flag ? "success" : "error"
                        })
                        if (res.data.flag)
                            this.queryMember()
                        this.formdata=""
                        this.dialogMchest = false
                    })
                }


            },
            updatemChect(){
                if(this.multipleSelection.length==0){
                    this.$message.error("请选择所要修改行")
                    return
                }
                if(this.multipleSelection.length>1){
                    this.$message.error("只能选择一行")
                    return
                }
                this.dialogMchest=true
                this.formdata=this.multipleSelection[0]
            },

            deleteMember(){
                if(this.member.id==""){
                    this.$message.error("请选择成员")
                    return
                }
                memberApi.deleteMember(this.member.id)
                .then(res => {
                    this.$message({
                        message: res.data.message,
                        type: res.data.flag ? "success" : "error"
                    })
                    if (res.data.flag)
                        this.queryMember()
                    this.clearData()
                })
                this.dialogdel=false
            },
            updateMember(){
                if(this.member.memberName=="")
                {
                    this.$message.error("名字不能为空")
                    return
                }
                if(this.checkName()){
                    this.$message.info("该成员以及存在")
                    return
                }
                memberApi.updateMember(this.$store.state.userId, this.member)
                    .then(res => {
                        this.$message({
                            message: res.data.message,
                            type: res.data.flag ? "success" : "error"
                        })
                        if (res.data.flag)
                            this.queryMember()
                        this.clearData()
                    })
                this.dialogupdate=false
            },
            queryMember() {
                memberApi.queryMember(this.$store.state.userId)
                    .then(res => {
                        this.persion = res.data.data
                        this.tableData=this.persion[this.number].medicineChestList
                    })
            },
            indexhandle(index) {
                this.number = index
                this.queryMember()
                this.tableData=this.persion[this.number].medicineChestList

            },
            addMember() {
                if(this.member.memberName=="")
                {
                    this.$message.error("名字不能为空")
                    return
                }
                if(this.checkName()){
                    this.$message.info("该成员以及存在")
                    return
                }
                memberApi.addMember(this.$store.state.userId, this.member)
                    .then(res => {
                        this.$message({
                            message: res.data.message,
                            type: res.data.flag ? "success" : "error"
                        })
                        if (res.data.flag)
                            this.queryMember()
                            this.clearData()
                            this.dialogAdd = false

                    })
            },
            clearData(){
                this.member.memberName = ""
                this.member.id = ""
            },
            handleClose(done){
                this.clearData()
                this.queryMember()
                done()
            },
            checkName(){
                let data= this.persion.filter(item=>{
                    if(item.memberName== this.member.memberName)
                        return true
                    return false
                })
                return data.length>0?true:false
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }


        }
    }
</script>

<style scoped>

    .box {
        display: inline-flex;
    }

    .list {
        width: 207px;
        height: 814px;
        background-color: #44b9ff;
        margin-left: 250px;
        padding-top: 25px;
    }

    .menber {
        min-height: 430px;
        height: auto;
    }

    .list-childern {
        width: 100%;
        text-align: center;
        color: #fff;
        font-size: 18px;
        height: 50px;
        line-height: 50px;
    }

    .list-menu {
        width: 207px;
        margin-top: 30px;
    }

    .list-menu-childern:hover {
        background-color: rgba(0, 0, 0, 0.3);
    }

</style>