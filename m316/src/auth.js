import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import router from './router'
import store from './store'
const whiteList = ['/','/resg']
NProgress.configure({
    showSpinner: false,
    ease: 'ease',
    speed: 500
});
router.beforeEach((to, from, next) => {
    NProgress.start();
    console.log(store.state.userId)
    if(whiteList.indexOf(to.path) == -1){
        if(store.state.userId==""){
            next(`/?redirect=${to.path}`)
        }
    }

    next()
});
router.afterEach(() => {
    NProgress.done()
});