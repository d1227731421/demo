import request from "../util/request";

export default {
    login(phone,password) {
        return request({
            url:"/user/login",
            method:"post",
            data:{
                phone,
                password
            }
        })
    },
    register(user) {
        return request({
            url:"/user/register",
            method:"post",
            data: user

        })
    },
    queryMedicineName(userId) {
        return request({
            url:`/user/${userId}`,
            method:"get",

        })
    },
    update(user) {
        return request({
            url:"/user/update",
            method:"post",
            data: user

        })
    },

}
