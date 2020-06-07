import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedSatte from 'vuex-persistedstate'
Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userId: "",
        phone:"",
        username:""
    },
    mutations: {
        setUserId(state, id) {
          state.userId=id
        },
        setPhone(state, phone) {
            state.phone=phone
        },
        setUsername(state, username) {
            state.username=username
        }
    },
    actions: {},
    modules: {},
    plugins:[createPersistedSatte({
        storage:window.sessionStorage
    })],
})
