<template>
    <div class="box">
        <div>
            <div class="list">
                <div class="menber">
                    <div v-for="(item,index) in persion" @click="indexhandle(index)" class="list-childern"
                         :style=" number==index?style:''" v-text="item.name"></div>
                </div>
            </div>
        </div>
        <div>
            <div style="margin-top: 20px;margin-left: 30px">
                <el-row style="margin-bottom: 15px">
                    <el-col :span="14" :push="1" >
                        <el-input v-model="search"  style="width:60%"   clearable placeholder="输入关键字搜索"/>
                    </el-col>
                    <el-col :span="1" :pull="3" >
                        <el-button type="primary" @click="dialogMed=true">新增药品</el-button>
                    </el-col>
                </el-row>
                <el-table
                        ref="multipleTable"
                        :data="tableData.filter(data => !search || data.medicineName.toLowerCase().includes(search.toLowerCase()))"
                        tooltip-effect="dark"
                        style="width: 100%"
                        height="620"
                        :border="border"
                        :header-cell-style="{background:'#009AC8',color:'white'}"
                       >
                    <el-table-column
                            label="序号"
                            type="index"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            prop="medicineName"
                            label="药品名称" width="211">
                    </el-table-column>
                    <el-table-column
                            prop="expiryDate"
                            label="有效期" width="211">
                    </el-table-column>
                    <el-table-column
                            prop="surplus"
                            label="剩余量" width="211">
                    </el-table-column>
                    <el-table-column label="操作" width="211">
                        <template slot-scope="scope">
                            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            <el-button size="mini" type="primary" @click="handleShow(scope.$index, scope.row)">查看</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <el-dialog title="提示" :visible.sync="dialogMed" width="30%" :before-close="handleClose">
            <el-form label-position="right" label-width="80px" :model="formdata">
                <el-form-item label="药品名称">
                    <el-input  :disabled="disabled" v-model="formdata.medicineName"></el-input>
                </el-form-item>
                <el-form-item label="主治病症">
                    <el-input :disabled="disabled"v-model="formdata.disease"></el-input>
                </el-form-item>
                <el-form-item label="用法用量">
                    <el-input :disabled="disabled"v-model="formdata.usages"></el-input>
                </el-form-item>
                <el-form-item label="有 效 期">
                    <el-date-picker :disabled="disabled" v-model="formdata.expiryDate"  style="width: 100%"  value-format="yyyy-MM-dd" type="date" placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="禁  忌">
                    <el-input :disabled="disabled" v-model="formdata.taboo"></el-input>
                </el-form-item>
                <el-form-item label="储存方式">
                    <el-input :disabled="disabled" v-model="formdata.storage"></el-input>
                </el-form-item>
                <el-form-item  label="剩 余 量">
                    <el-input type="number" :disabled="disabled" v-model="formdata.surplus"></el-input>
                </el-form-item>
                <el-form-item label="描  述">
                    <el-input :disabled="disabled" v-model="formdata.description"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                     <el-button type="primary" @click="addMed">确 认</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import medicineApi from "../api/medicine";
    import util from "../util/util";

    export default {
        name: "standingMedicine",
        data() {
            return {
                search:"",
                dialogMed:false,
                disabled:false,
                tableData: [],
                border:true,
                persion: [
                    {name: "常备口服药"},
                    {name: "常备外用药"},
                    {name: "心脑急救药"},
                    {name: "营养补充品"},
                    {name: "常备医用物品"}
                ],
                formdata:{
                    medicineName: "",
                    disease: "",
                    usages: "",
                    expiryDate: "",
                    taboo: "",
                    storage: "",
                    surplus: "",
                    description:"",
                    type:""
                },
                style: "background-color: rgba(0, 0, 0, 0.3);",
                number: 0
            }
        },
        mounted() {
            this.query()
        },
        methods: {

            handleDelete(index,row){
              medicineApi.del(this.tableData[index].id).then(res=>{

                  this.$message({
                      message:res.data.message,
                      type:res.data.flag?"success":"error"
                  })
                  if(res.data.flag){
                      this.query()
                  }
              })
            },
            handleShow(index,row){
                this.formdata=this.tableData[index]
                this.dialogMed=true
                this.disabled=true
            },
            handleEdit(index,row){
                this.formdata=this.tableData[index]
                this.dialogMed=true
            },
            addMed(){
                if(this.disabled==true){
                    this.disabled=false
                    this.dialogMed=false
                    return;
                }
                this.formdata.type=this.persion[this.number].name
                if(util.hasEmpty(this.formdata)){
                    this.$message.error("不能包含空值")
                    return
                }
                if(this.formdata.id==undefined){
                if(util.hasExist(this.formdata.medicineName,this.tableData,'medicineName')){
                    this.$message.error("该药品以及存在")
                    return
                }
                medicineApi.add(this.$store.state.userId,this.formdata).then(res=>{
                    this.$message({
                        message:res.data.message,
                        type:res.data.flag?"success":"error"
                    })
                    if(res.data.flag){
                        this.clearData()
                        this.query()
                        this.dialogMed=false
                    }
                })
                }else {
                    if(util.hasExistByUpdate(this.formdata.medicineName,this.tableData,'medicineName')){
                        this.$message.error("该药品以及存在")
                        return
                    }
                    medicineApi.update(this.$store.state.userId,this.formdata).then(res=>{
                        this.$message({
                            message:res.data.message,
                            type:res.data.flag?"success":"error"
                        })
                        if(res.data.flag){
                            this.clearData()
                            this.query()
                            this.dialogMed=false
                        }
                    })
                }
            },
            indexhandle(index) {
                this.number = index
                this.query()
            },
            handleClose(done){
                done()
                this.clearData()
                this.query()
            },
            clearData(){
                this.formdata={
                    medicineName: "",
                        disease: "",
                        usages: "",
                        expiryDate: "",
                        taboo: "",
                        storage: "",
                        surplus: "",
                        description:"",
                        type:""
                }
            },
            query(){
                medicineApi.query(this.$store.state.userId,{ type:this.persion[this.number].name}).then(res=>{
                    if(res.data.flag){
                        this.tableData=res.data.data
                    }
                })
            },



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