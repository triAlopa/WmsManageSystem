

import VueRouter from "vue-router";


const routes = [
    {
        path:'/',
        name:'LoginVue',
        component:()=>import('../components/LoginVue.vue')
    },
    {
        path:'/IndexVue',
        name:'index',
        component:()=>import('../components/IndexVue.vue'),
        children:[
            {
                path:'/HomeVue',
                name:'HomeVue',
                meta:{
                    title:"首页"
                },
                component:()=>import('../components/HomeVue.vue')
            },
        ],
    },
    // {
    //     path:'/RegisterVue',
    //     name:'Register',
    //     component:()=>import('../components/RegisterVue.vue')
    // },
    {
        path:'/LoginVue',
        name:'LoginVue',
        component:()=>import('../components/LoginVue.vue'),

    },
]

const router =new VueRouter({
    mode:"history",
    routes
})

export function restRouter (){
    router.matcher =new VueRouter({
        mode:'history',
        routes:[],
    }).matcher
}

const VueRouterPush=VueRouter.prototype.push
VueRouter.prototype.push=function push (to){
    return VueRouterPush.call(this,to).catch(err=>err)
}

export default router;