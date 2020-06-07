import reminder from "./api/reminder";
import store from './store'
import {MessageBox} from 'element-ui'
import request from "./util/request";
import { Message } from 'element-ui';
let objs = []
setInterval(() => {
    if (store.state.userId != "") {
        reminder.query(store.state.userId).then(res => {
            let data = res.data.data
            objs = []
            data.filter(e => e.flag == true).forEach(e => {
                objs.push(e)
            })
            console.log(objs)
        })
    }
}, 1000*60)
setInterval(() => {
        if (objs.length > 0) {
            let date = new Date()
            let T = form(date.getHours(), date.getMinutes())
            objs.forEach((e,index)=>{
                if(e.date==T)
                {
                    MessageBox.alert("该吃"+e.reminderName+"啦", '标题名称', {
                        confirmButtonText: '确定',
                        showClose:false,
                        callback: action => {
                            request.get("/user/reduceMedicine",{
                                params:{
                                    userId:store.state.userId,
                                    number:e.number,
                                    name:e.reminderName
                                }
                            }).then(res=>{
                                if(res.data.data.length>0){
                                    res.data.data.forEach(e=>{
                                        Message.warning({
                                            message:e,
                                            duration:0,
                                            showClose:true
                                        })
                                    })

                                }
                            })
                        }
                    });
                    objs.splice(index,1)
                }
            })
        }
    }
    , 10000)

function form(h, m) {
    if (h < 10) {
        h = "0" + h
    }
    if (m < 10) {
        m = "0" + m
    }
    return "" + h + ":" + m
}

