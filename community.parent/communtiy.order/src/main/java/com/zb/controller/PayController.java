
package com.zb.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;

import com.zb.config.AlipayConfig;

import com.zb.feign.StoreFeignClient;
import com.zb.feign.TemGoodsFeignClient;
import com.zb.pojo.Orders;
import com.zb.pojo.TempGoods;
import com.zb.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController

/**
 * 添加完订单，支付的操作
 */

public class PayController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private TemGoodsFeignClient temGoodsFeignClient;

    @RequestMapping(value = "/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入pay");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("WIDout_trade_no") != null) {
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 订单名称，必填
            String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(subject);
            // 付款金额，必填
            String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
            // 商品描述，可空
            String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");
            // 超时时间 可空
            String timeout_express = "2m";
            // 销售产品码 必填
            String product_code = "QUICK_WAP_PAY";

/**********************/

            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            //调用RSA签名方式
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
            AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

            // 封装请求支付信息
            AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
            model.setOutTradeNo(out_trade_no);
            model.setSubject(subject);
            model.setTotalAmount(total_amount);
            model.setBody(body);
            model.setTimeoutExpress(timeout_express);
            model.setProductCode(product_code);
            alipay_request.setBizModel(model);
            // 设置异步通知地址
            alipay_request.setNotifyUrl(AlipayConfig.notify_url);
            // 设置同步地址
            alipay_request.setReturnUrl(AlipayConfig.return_url);

            // form表单生产
            String form = "";
            try {
                // 调用SDK生成表单
                form = client.pageExecute(alipay_request).getBody();
                response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
                response.getWriter().write(form);//直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();
            } catch (AlipayApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /**
     * 支付成功以后返回的页面
     *
     * @param request
     * @param response
     * @throws Exception
     */

    @RequestMapping(value = "/myreturn")
    public void myreturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("进入myreturn");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("支付成功， 支付宝系统回调的方法， 再次进入用户开发的程序中");
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(out_trade_no + "\t" + trade_no);
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
        if (!verify_result) {//验证成功
            //请在这里加上商户的业务逻辑程序代码
            //修改订单状态:根据订单号修改状态,添加交易编号
            System.out.println("付款成功，开始修改数据库");
            System.out.println("订单号"+out_trade_no);
            System.out.println("交易号"+trade_no);
            Orders orders = new Orders();
            //订单编号
            orders.setOrderNo(out_trade_no);
            //根据订单编号查询出订单详情
            Orders orders1 = ordersService.getOrdersById(out_trade_no);
            //添加交易编号
            orders.setTardeNo(trade_no);
            //设置状态为已支付
            orders.setOrderStatus(2);
            orders.setCount(orders1.getCount());
            orders.setGoodsName(orders1.getGoodsName());
            //商品编号
            orders.setGoodsNo(orders1.getGoodsNo());
            orders.setGoodsType(orders1.getGoodsType());
            //用户编号
            orders.setUserId(orders1.getUserId());
            //店铺编号
            orders.setStoreNo(orders1.getStoreNo());
            //根据tradeNo修改订单信息
            int count = ordersService.updateOrders(orders);
            //付完钱以后，修改临时库存表里状态为已支付
            if(count>0){
                //修改临时库存表的状态为已付钱
                //根据用户编号，和商品编号查到临时库存表信息
                TempGoods goods=new TempGoods();
                goods.setCreatedBy(orders1.getUserId().toString());
                goods.setGoodsId(Long.parseLong(orders1.getGoodsNo().toString()));
                TempGoods tempGoods1 = temGoodsFeignClient.getTempGoods(goods);
                TempGoods tempGoods=new TempGoods();
                tempGoods.setStatus(2);
                tempGoods.setId(tempGoods1.getId());
                //修改临时库存表状态为已支付
                temGoodsFeignClient.updateTempGoods(tempGoods);
            }

            //该页面可做页面美工编辑
            response.getWriter().println("success");
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            //////////////////////////////////////////////////////////////////////////////////////////
        } else {
            //该页面可做页面美工编辑
            response.getWriter().println("error");
        }
    }

}

