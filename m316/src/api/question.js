import request from "../util/request";
export default {
    save(pojo){
        return request({
            url:`/question/save`,
            method:"post",
            data:pojo
        })
    }
}