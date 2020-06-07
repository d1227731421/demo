import request from "../util/request";

export default {
    query(memberId){
        return request({
            url:`/mchest/query/${memberId}`,
            method:"get"
        })
    },
    add(memberId,pojo){
        return request({
            url:`/mchest/add/${memberId}`,
            method:"post",
            data:pojo
        })
    },
    update(pojo,memberId){
        return request({
            url:`/mchest/update/${memberId}`,
            method:"post",
            data:pojo
        })
    },
    del(pojo){
        return request({
            url:`/mchest/del/`,
            method:"delete",
            data:pojo
        })
    }
}