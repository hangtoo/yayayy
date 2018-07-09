<style>
.clr:before,
.clr:after {
    display: block;
    height: 0;
    overflow: hidden;
    clear: both;
}

.clr:after {
    clear: both;
}

.clr {
    font-style: 0;
    height: 0;
    line-height: 0;
    overflow: hidden;
    clear: both;
    zoom: 1;
}

.gMainCenter {
    width: 100%;
    margin: 0 auto;
    background: #fff;
    box-sizing: border-box;
    padding: 30px;
}

ul {
    margin: 0;
    padding: 0;
    list-style: none;
}

.prEditzt ul li {
    margin-bottom: 15px;
    /*line-height: 30px;*/
    float: left;
    margin-right: 15px;
    color: #5a5e66;
    font: 14px "宋体";
}

.prEditzt ul li span {
    display: inline-block;
    width: 70px;
    height: 30px;
    float: left;
    color: #5a5e66;
    font: 14px/30px "宋体";
}

.prEditSelect {
    width: 170px;
}

.fl {
    float: left;
}

.selectBox {
    display: inline-block;
    vertical-align: top;
    position: relative;
    cursor: pointer;
    width: 100%;
    height: 30px;
    line-height: 30px;
    top: 0px;
    box-sizing: border-box;
}

.selectBox p {
    position: relative;
    vertical-align: top;
    height: 30px;
    box-sizing: border-box;
    padding-left: 5px;
    background-size: 11px 11px;
    border: none;
    padding: 0 25px 0 10px;
    color: #5a5e66;
    font: 14px/30px  "宋体";
    display: block;
    overflow: hidden;
    border: 1px solid #e4e4e4;
    font-size: 16px;
    margin: 0;
    border-radius: 4px;
}

.selectBox p:after {
    content: "";
    position: absolute;
    right: 15px;
    display: inline-block;
    width: 14px;
    height: 14px;
    background: url(../assets/selecticon.png) center center no-repeat;
    top: 8px;
}

.selectBox ul {
    width: 99%;
    border: 1px solid #e4e4e4;
    border-top: 0;
    position: absolute;
    z-index: 99;
    left: 0px;
    background-color: #fff;
    display: none;
    max-height: 150px;
    overflow: auto;
}

.selectBox ul li {
    width: 94%;
    margin: 0;
    line-height: 30px;
    float: left;
    padding-left: 10px;
}

.selectBox ul li:hover {
    background-color: #ccc;
}

a {
    margin: 0;
    padding: 0;
    font-size: 100%;
    vertical-align: baseline;
    background: transparent;
    text-decoration: none;
    color: #5c5c5c;
    font-size: 100%;
}

.prEditzt ul li input {
    display: inline-block;
    width: 170px;
    height: 30px;
    line-height: 30px;
    font-size: 14px;
    color: #666;
    text-align: left;
    box-sizing: border-box;
    padding-left: 10px;
    border: 1px solid #d8d8d8;
    border-radius: 4px;
}
</style>

<template>
    <div>
        <div class="gMainCenter">
            <div class="prEditzt">
                <ul>
                    <li>
                        <span>产品来源</span>
                        <div class="prEditSelect fl">
                            <div class="selectBox">
                                <p>自研</p>
                                <ul>
                                    <li data-value="1">
                                        <a href="javascript:void(0)">自研</a>
                                    </li>
                                    <li data-value="2">
                                        <a href="javascript:void(0)">外采</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span>管理人</span>
                        <input type="text" placeholder="" />
                        <div class="clr"></div>
                    </li>
                    <li>
                        <span>产品来源</span>
                        <div class="prEditSelect fl">
                            <div class="selectBox">
                                <p>自研</p>
                                <ul>
                                    <li data-value="1">
                                        <a href="javascript:void(0)">自研</a>
                                    </li>
                                    <li data-value="2">
                                        <a href="javascript:void(0)">外采</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                   <li>
                        <span>产品来源</span>
                        <div class="prEditSelect fl">
                            <div class="selectBox">
                                <p>自研</p>
                                <ul>
                                    <li data-value="1">
                                        <a href="javascript:void(0)">自研</a>
                                    </li>
                                    <li data-value="2">
                                        <a href="javascript:void(0)">外采</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span>产品来源</span>
                        <div class="prEditSelect fl">
                            <div class="selectBox">
                                <p>自研</p>
                                <ul>
                                    <li data-value="1">
                                        <a href="javascript:void(0)">自研</a>
                                    </li>
                                    <li data-value="2">
                                        <a href="javascript:void(0)">外采</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span>产品来源</span>
                        <div class="prEditSelect fl">
                            <div class="selectBox">
                                <p>自研</p>
                                <ul>
                                    <li data-value="1">
                                        <a href="javascript:void(0)">自研</a>
                                    </li>
                                    <li data-value="2">
                                        <a href="javascript:void(0)">外采</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>

</script>




