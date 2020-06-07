export default {
    hasEmpty(obj){
        let arr;
        if ((arr = Object.keys(obj).filter(v=>!obj[v])).length<=0) {
            return false
        }else{
            return true
        }
    },
    hasExist(name,obj,key){
        return (obj.filter(e=>{
            return e[key]==name
        })).length>0
    },
    hasExistByUpdate(name,obj,key){
        return (obj.filter(e=>{
            return e[key]==name
        })).length>1
    },
    checkPhone(phone){
        const rex=/^1[3|4|5|8]\d{9}$/
        return  rex.test(phone)
    }
}