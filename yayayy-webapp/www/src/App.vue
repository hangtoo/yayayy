<template>
    <div id="app">
		<keep-alive>
    		<router-view v-if="$route.meta.keepAlive">
    		</router-view>
		</keep-alive>
		<router-view v-if="!$route.meta.keepAlive"></router-view>
    </div>
</template>
<style>
    @import "../static/css/main.css";
    @import "../static/css/color-dark.css";     /*深色主题*/
    /*@import "../static/css/theme-green/color-green.css";   浅绿色主题*/
    .el-dialog--small{
    	width:70% !important;
    }
</style>
<script>
     sessionStorage.setItem("keepTCIflag", 0);  
     sessionStorage.setItem("keepTCflag", 0);  
    
</script>