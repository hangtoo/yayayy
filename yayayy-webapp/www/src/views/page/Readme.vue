<style scoped>

.ms-doc {
    width: 100%;
    max-width: 980px;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif;
    
}

.ms-doc h3 {
    padding: 9px 10px 10px;
    margin: 0;
    font-size: 14px;
    line-height: 17px;
    background-color: #f5f5f5;
    border: 1px solid #d8d8d8;
    border-bottom: 0;
    border-radius: 3px 3px 0 0;
}

.ms-doc article {
    padding: 45px;
    word-wrap: break-word;
    background-color: #fff;
    border: 1px solid #ddd;
    border-bottom-right-radius: 3px;
    border-bottom-left-radius: 3px;
}

.ms-doc article h1 {
    font-size: 32px;
    padding-bottom: 10px;
    margin-bottom: 15px;
    border-bottom: 1px solid #ddd;
}

.ms-doc article h2 {
    margin: 24px 0 16px;
    font-weight: 600;
    line-height: 1.25;
    padding-bottom: 7px;
    font-size: 24px;
    border-bottom: 1px solid #eee;
}

.ms-doc article p {
    margin-bottom: 15px;
    line-height: 1.5;
}

.ms-doc article .el-checkbox {
    margin-bottom: 5px;
}

.ms-doc {
    background: #fff;
    padding: 10px 0 10px 0;
}

.ms-doc h3 {
    border: none;
    background: #fff;
    font-size: 28px;
}

.backActive {
    background: #fff !important;
    border-radius: 10px;
}

p.border {
    background: #fff !important;
    padding:10px 0 10px 20px !important;
    border:1px solid #e0e0e0 !important;
    border-radius:10px;
    margin-left:10px;
    font-size:24px !important;
    box-shadow: 5px 5px 5px #e0e0e0;
    //color:#fff !important;
}

.ms-doc>p {
    font-size: 22px;
    width: 100%;
    //font-weight: 600;
    padding: 0 30px;
}

.ms-doc>p>.spanLast {
    margin-left: 100px;
}

.ms-doc>p.pMargin {
    margin-top: 30px;
}

.box-card {
    margin: 10px; 
    padding: 0px;
  }

</style>

<template>

<div>
    <div class="crumbs">
        <el-breadcrumb separator="/">

        </el-breadcrumb>
    </div>
    <div class="ms-doc backActive">
        <p class="border">平台概况</h3>
        <p class="pMargin"><span class="spanLast">总用户数量： {{statistics.customercount}}</span><span class="spanLast">总交易量： {{statistics.totalmoney}}</span></p>
        <p class="pMargin"><span class="spanLast">今日注册人数： {{statistics.todayrecustomer}}</span></p>
    </div>

    <div class="ms-doc" style="margin-top:30px;">
        <p class="border">每日数据统计</h3>

        <div class="handle-box">
            <span>&#12288</span>
        </div>

        <div class="handle-box" style="text-align:right">
            <span>&#12288&#12288</span>
            <el-button type="primary" @click="exportTable()">导出表格</el-button>
        </div>

		<el-card class="box-card">
	        <div class="schart">
	            <schart canvasId="line" width="930" height="400" :data="data1" type="line" :options="options2"></schart>
	        </div>
        </el-card>
        <el-card class="box-card">
	        <div class="schart">
	            <schart canvasId="line1" width="930" height="400" :data="data2" type="line" :options="options2"></schart>
	        </div>
        </el-card>
        <el-card class="box-card">
	        <div class="schart">
	            <schart canvasId="line2" width="930" height="400" :data="data3" type="line" :options="options3"></schart>
	        </div>
        </el-card>
    </div>


</div>

</template>

<script>

import Schart from 'vue-schart';
import http from '@/utils/http';
export default {
    components: {
        Schart
    },
    data: () => ({
        data1: [],
        data2: [],
        data3: [],
        data4: [],
        options1: {
            title: '每日注册人数（人）',
            //bgColor: '#fff',
            titleColor: '#222',
           // fillColor: '#666',
            //axisColor: '#222',
            //contentColor: '#ccc',
            showValue: false,               // 是否在图表中显示数值
    //topPadding: 50,                 // canvas 上边距
   // bottomPadding: 50,              // canvas 下边距
   // leftPadding: 50,                // canvas 左边距
   // rightPadding: 0,                // canvas 右边距
    //yEqual: 5,                      // y轴分成5等分
    bgColor: '#ffffff',             // 默认背景颜色
    fillColor: '#409eff',           // 默认填充颜色
    axisColor: '#fff',           // 坐标轴颜色
    contentColor: '#fff',        // 内容横线颜色
    //title: '',                      // 图表标题
    titleColor: '#212121',          // 图表标题颜色
   // titlePosition: 'top'            // 图表标题位置: top / bottom
        },
        options2: {
            title: '每日交易人数（人）',
            bgColor: '#fff',
            titleColor: '#222',
            fillColor: '#666',
            axisColor: '#222',
            contentColor: '#ccc'
         
        },
        options3: {
            title: '每日成交量（元）',
            bgColor: '#fff',
            titleColor: '#222',
            fillColor: '#666',
            axisColor: '#222',
            contentColor: '#ccc'
        },
        statistics: {
            customercount: '',
            todayrecustomer: '',
            totalmoney: ''
        }
    }),
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
                let self = this;
                http.post(process.env.BASE_API + 'tCustomer/readmedata.do', {},
                    response => {

                      if(!response||!response.data){
                        return;
                      }

                        self.statistics.customercount = response.data.customercount;
                        self.statistics.todayrecustomer = response.data.todayrecustomer;
                        self.statistics.totalmoney = response.data.totalmoney;
                        self.data1 = response.data.monthrecustomerlist;
                        self.data2 = response.data.monthcountlist;
                        self.data3 = response.data.monthmoenylist;
                        self.data4 = response.data.exclist;
                        console.log(response.data);
                    }).catch(err => {
                    console.log(err);
                    self.$eventHub.$emit('error', err);
                    return;
                });
            },
            exportTable() {
                require.ensure([], () => {
                    var myDate = new Date().toDateString();
                    const {
                        export_json_to_excel
                    } = require('../../vendor/Export2Excel');
                    const tHeader = ['日期', '每日注册人数（人）', '每日交易人数（人）', '每日成交量（元）'];
                    const filterVal = ['date', 'customeritem', 'countitem', 'moenyitem'];
                    const list = this.data4;
                    const data = this.formatJson(filterVal, list);
                    export_json_to_excel(tHeader, data, '每日数据统计' + myDate);
                })
            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]))
            }
    }




}

</script>
