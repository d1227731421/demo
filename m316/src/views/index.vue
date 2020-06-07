<template>
    <div>
        <el-container>
            <el-header>
                <menulist></menulist>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </div>

</template>

<script>
    import Menulist from "../components/menulist";
    export default {
        name: "index",
        components: {Menulist}
    }
</script>

<style scoped>
.el-header,.el-main{
        padding: 0;
    }
</style>