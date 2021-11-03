"use strict";
layui.define([], function (exprots) {
    // var baseUrl="http://123.207.95.77:8080";
    var baseUrl="http://localhost:8080";
    let okMock = {
        api: {
            base:baseUrl,
            findAllDoctor:baseUrl+"/admin/findAllDocter",
            findAllAudit:baseUrl+"/admin/findAllAudit",
            findAllPhoneOrder:baseUrl+"/doctor/porder/findMyPorders",
            findAllArticles:baseUrl+"/admin/article/findAll",
            findAllMorders:baseUrl+"/doctor/getMorders"
        }
    };
    exprots("okMock", okMock);
});
