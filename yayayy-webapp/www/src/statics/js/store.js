import Vue from 'vue'
 import Vuex from 'vuex'

 Vue.use(Vuex)
const store = new Vuex.Store({
    strict: true,
    //  定义状态
    state: {
        bank: '',
        bankCard: '',
        how_long: '',
        how_way: '',
        money: ''
    },
    // 计算属性
    getters: {

    },
    // 修改状态事件，必须同步函数
    mutations: {
        setUserId(state,msg){
            state.bank = msg;
        },
        newFooterId (state,msg) {
            state.bankCard = msg;
        },
        setHowLong(state,msg){
            state.how_long = msg;
        },
        setHowWay(state,msg){
            state.how_way = msg;
        },
        setMoney(state,msg){
            state.money = msg;
        }
    },
    //  提交mutation，可以异步操作
    actions: {

    },
    //  模块
    modules: {

    }
})

    export default store