import request from "../util/request";

export  default {
    queryMember(id){
        return request({
            url:`/member/${id}`,
            method:"get"
        })
    },
    addMember(id,member) {
        return request({
            url:`/member/add/${id}`,
            method:"post",
            data:member
        })
    },
    updateMember(id,member) {
        return request({
            url:`/member/add/${id}`,
            method:"put",
            data:member
        })
    },
    deleteMember(id){
        return request({
            url:`/member/del/${id}`,
            method:"delete"
        })
    }
}