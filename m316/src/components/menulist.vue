<template>
    <div class="box">
        <img class="img-icon" src="../assets/images/4.png" height="40px">
        <el-menu
                router
                class="el-menu-demo"
                default-active="/index/homebox"
                mode="horizontal"
                background-color="#FFFFFF"
                text-color="#000000"
                active-text-color=" #43b3ff">
            <el-menu-item  index="/index/homebox">家庭药箱</el-menu-item>
            <el-menu-item index="/index/standingMedicine">常备药品</el-menu-item>
            <el-menu-item index="/index/reminder">服药提醒</el-menu-item>
            <el-menu-item index="/index/personCenter/changpasswd">个人中心</el-menu-item>
        </el-menu>
    </div>

</template>

<script>
    export default {
        name: "menulist"
    }
</script>

<style scoped>
    .box {
        box-shadow: 0 6px 6px rgba(0, 0, 0, 0.1);
        width: 100%;
    }

    .el-menu-demo >li{
        margin-left:72px;
    }
    .el-menu-demo {
        margin-left: 470px;

    }
    .img-icon{
        position: absolute;
        z-index: 1;
        left: 234px;
        top: 8px;
    }
</style>