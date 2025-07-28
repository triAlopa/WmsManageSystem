import Vue from "vue";
import Vuex from "vuex"
import router,{restRouter} from "@/router";
import createPersistedState from 'vuex-persistedstate'
Vue.use(Vuex)

function addNewRoute(menuList){
    let routes= router.options.routes
    console.log(menuList)
    console.log(routes)
    routes.forEach(routeItem=>{
        if(routeItem.path =="/IndexVue"){
            menuList.forEach(menu=>{
                let childRoute={
                        path:'/'+menu.menuclick,
                        name:menu.menuname,
                        meta:{
                            title:menu.menuname
                        },
                        component:()=>import('../components/'+menu.menucomponent),
                    }
                    routeItem.children.push(childRoute)
            })
        }
    })
    restRouter()
    router.addRoutes(routes)



    // {
    //     path:'/Admin',
    //         name:'admin',
    //     meta:{
    //     title:"管理员管理"
    // },
    //     component:()=>import('../components/admin/AdminManage.vue'),
    //
    // },
    // {
    //     path:'/User',
    //         name:'user',
    //     meta:{
    //     title:"用户管理"
    // },
    //     component:()=>import('../components/user/UserManage.vue'),
    //
    // },
}

export default new Vuex.Store({
    state:{
        menu:[]
    },
    mutations:{
        setMenu(state,menuList) {
            state.menu =menuList
            addNewRoute(menuList);
        },
        setRouter(state,menuList) {
            addNewRoute(menuList);
        },
    },
    getters:{
        getMenu(state){
            return state.menu;
        }
    },
    plugins:[
        createPersistedState()
    ],
})

