import request from "../util/request";

export default {
    add(userId,pojo){
        return request({
            url:`/med/${userId}`,
            method:"post",
            data:pojo
        })
    },
    update(userId,pojo){
        return request({
            url:`/med/${userId}`,
            method:"put",
            data:pojo
        })
    },
    query(userId,pojo){
        return request({
            url:`/med/query/${userId}`,
            method:"post",
            data:pojo
        })
    },
    del(id){
        return request({
            url:`/med/${id}`,
            method:"delete"
        })
    }

}