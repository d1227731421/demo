import request from "../util/request";
export default {
    add(userId,pojo){
        return request({
            url:`/remind/${userId}`,
            method:"post",
            data: pojo
        })
    },
    update(userId,pojo){
        return request({
            url:`/remind/${userId}`,
            method:"put",
            data: pojo
        })
    },
    query(userId){
        return request({
            url:`/remind/${userId}`,
            method:"get",
        })
    },
    del(id){
        return request({
            url:`/remind/${id}`,
            method:"delete",
        })
    },

}