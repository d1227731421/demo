import Vue from 'vue'
import VueRouter from 'vue-router'
import index from "../views/index";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import( '../views/login')
    },
    {
        path: "/resg",
        name: "resg",
        component: () => import("../views/resg")
    },
    {
        path: "/index",
        name: "index",
        component: index,
        children:[

            { path:"homebox",name:"homebox", component:()=>import("../components/homebox")},
            { path:"standingMedicine",name:"standingMedicine", component:()=>import("../components/standingMedicine")},
            {path:"reminder",name:"reminder", component:()=>import("../components/reminder")},
            { path:"personCenter",name:"personCenter", component:()=>import("../components/personCenter"),
                children:[
                    { path:"changpasswd",name:"changpasswd" ,component:()=>import("../components/changpasswd")},
                    { path:"question",name:"question" ,component:()=>import("../components/question")}

                ]
            }
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router
