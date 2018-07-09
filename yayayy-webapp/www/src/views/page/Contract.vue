<template>
     <div>
        <div class="crumbs">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item><i class="el-icon-menu"></i>{{titleOne}}</el-breadcrumb-item>
            <el-breadcrumb-item :to="{path:path}" :replace="true" style="color:blue">{{titleTwe}}</el-breadcrumb-item>
            <el-breadcrumb-item>{{title}}</el-breadcrumb-item>
        </el-breadcrumb>
   		</div>
        <div v-html="html"></div>
    </div>
</template>

<script>
    import Schart from 'vue-schart';
    import http from '@/utils/http';
    import { Loading } from 'element-ui';

    export default {
   	   data () {
   	      	return {
                getInvestAgreement: process.env.BASE_API + 'tCustomer/investagreement.do',
                title:this.$route.query.title,
                path:this.$route.query.path,
                titleOne:this.$route.query.titleOne,
                titleTwe:this.$route.query.titleTwe,
                params:{
	                bank_no: this.$route.query.bank_no,
		    		custom_name:this.$route.query.custom_name,
		    		custom_no:this.$route.query.custom_no,
		    		invest_amt:this.$route.query.invest_amt,
	          		invest_no:this.$route.query.invest_no,
	          		type:this.$route.query.type
                },
                html:this.params,
            }

   	   },
   	   /*
   	   beforeRouteLeave(to, from, next) {
        	from.meta.keepAlive = false;
        	next();
    	},*/
   	   created() {
          this.getHtml();
          this.loading(false);
          console.log("=================", this.params, this.$route.query.invest_amt);

       },
       methods: {
			   getHtml() {
                	let self = this;
               		http.post(this.getInvestAgreement, this.params, (res) => {
               		console.log("=================12345123", res);
               		this.html = res.data;
               		this.loading(true);

               		}).catch(err => {
               		   console.log("=================******", self.custom_name);

                    	this.$eventHub.$emit('error', err);
                	})
           	   },
           	   loading(a) {
        			const loading = this.$loading({
         				lock: true,
         				text: 'Loading',
          				spinner: 'el-icon-loading',
         				background: 'rgba(0, 0, 0, 0.7)'
        			});
        			if (a == true) {
        				loading.close();
        			}
        			setTimeout(() => {
          				loading.close();
        			}, 2000);
     		   },
     		   handleView() {
					this.$router.go(-1);
     		   },
		}
    }
</script>

<style scoped>


</style>
