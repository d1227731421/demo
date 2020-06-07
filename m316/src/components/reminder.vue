<template>
    <div style="padding-top: 30px">
        <el-row>
            <el-col :span="10" :push="3">
                <el-input v-model="search" style="width:60%" placeholder="输入关键字搜索"/>
                <el-button type="primary" @click="dialogflag=true">新增</el-button>
            </el-col>

        </el-row>
        <div style="margin-left: 230px;margin-top: 12px">
            <el-table
                    ref="multipleTable"
                    :data="tableData.filter(data => !search || data.reminderName.toLowerCase().includes(search.toLowerCase()))"
                    tooltip-effect="dark"
                    style="width: 100%"
                    height="620"
                    :border="border"
                    highlight-current-row
                    :header-cell-style="{background:'#009AC8',color:'white'}">
                <el-table-column
                        label="序号"
                        type="index"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="reminderName"
                        label="药品名称" width="206">
                </el-table-column>
                <el-table-column
                        prop="number"
                        label="每次用量" width="206">
                </el-table-column>

                <el-table-column
                        label="每日次数"
                        prop="time" width="206">
                </el-table-column>
                <el-table-column
                        label="用药时间"
                        prop="date" width="206">
                </el-table-column>
                <el-table-column label="提醒" width="206">
                    <template slot-scope="scope">
                        <el-switch
                                :value="scope.row.flag"
                                active-color="#13ce66"
                                inactive-color="#ff4949"
                                disabled>
                        </el-switch>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="211">
                    <template slot-scope="scope">
                        <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog title="提示" :visible.sync="dialogflag" width="30%" :before-close="handleClose">
            <el-form label-position="right" label-width="80px" :model="formdata">
                <el-form-item label="药品名称">
                    <el-select style="width: 100%" v-model="formdata.reminderName"  placeholder="请选择">
                        <el-option v-for="item in options" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="每次用量">
                    <el-input type="number" :disabled="disabled" v-model="formdata.number"></el-input>
                </el-form-item>
                <el-form-item label="每日次数">
                    <el-input type="number" :disabled="disabled" v-model="formdata.time"></el-input>
                </el-form-item>
                <el-form-item label="用药时间">
                    <el-time-picker
                            v-model="formdata.date"
                            style="width: 100%"
                            value-format="HH:mm"
                            format="HH:mm"
                            placeholder="选择时间">
                    </el-time-picker>
                </el-form-item>
                <el-form-item label="提    醒">
                    <el-switch
                            v-model="formdata.flag"
                            active-color="#13ce66"
                            inactive-color="#ff4949"
                    >
                    </el-switch>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                     <el-button type="primary" @click="addReminder">确 认</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import reminderApi from "../api/reminder"
    import util from "../util/util";
    import userApi from "../api/user"
    export default {
        name: "reminder",
        data() {
            return {
                dialogflag: false,
                border: true,
                disabled: false,
                search: "",
                tableData: [],
                formdata: {
                    reminderName: "",
                    number: "",
                    time: "",
                    date: "",
                    flag: false,
                },
                options:""
            }
        },
        mounted() {
            this.queryReminder()
        },
        methods: {
            handleDelete(index, row) {
                reminderApi.del(this.tableData[index].id).then(res => {
                    this.$message({
                        message: res.data.message,
                        type: res.data.flag ? "success" : "error"
                    })
                    if (res.data.flag) {
                        this.clearData()
                        this.queryReminder()
                    }
                })
            },
            handleEdit(index, row) {
                this.dialogflag = true;
                this.formdata = this.tableData[index]
            },
            addReminder() {
                console.log(this.formdata)
                if (this.formdata.flag == false)
                    this.formdata.flag = "0";
                if (util.hasEmpty(this.formdata)) {
                    this.$message.error("不能包含空值")
                    return
                }
                if (this.formdata.flag == "0")
                    this.formdata.flag = false
                if (this.formdata.id == undefined) {
                    if (util.hasExist(this.formdata.reminderName, this.tableData, 'reminderName')) {
                        this.$message.error("该药品以及存在")
                        return
                    }
                    reminderApi.add(this.$store.state.userId, this.formdata).then(res => {
                        this.$message({
                            message: res.data.message,
                            type: res.data.flag ? "success" : "error"
                        })
                        if (res.data.flag) {
                            this.clearData()
                            this.queryReminder()
                            this.dialogflag = false
                        }
                    })
                } else {
                    if (util.hasExistByUpdate(this.formdata.reminderName, this.tableData, 'reminderName')) {
                        this.$message.error("该药品以及存在")
                        return
                    }
                    reminderApi.update(this.$store.state.userId, this.formdata).then(res => {
                        this.$message({
                            message: res.data.message,
                            type: res.data.flag ? "success" : "error"
                        })
                        if (res.data.flag) {
                            this.clearData()
                            this.queryReminder()
                            this.dialogflag = false
                        }
                    })
                }

            },
            queryReminder() {
                reminderApi.query(this.$store.state.userId).then(res => {
                    this.tableData = res.data.data
                })
                this.queryMedicineName()
            },
            handleClose(done) {
                done()
                this.clearData()
                this.queryReminder()
            },
            clearData() {
                this.formdata = {
                    reminderName: "",
                    number: "",
                    time: "",
                    date: "",
                    flag: false
                }
            },
            queryMedicineName(){
                userApi.queryMedicineName(this.$store.state.userId).then(res=>{
                    this.options=res.data.data
                })
            }
        }
    }
</script>

<style scoped>

</style>