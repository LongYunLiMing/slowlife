package com.slowlife.service;

import com.blade.ioc.annotation.Bean;
import com.blade.mvc.annotation.JSON;
import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import com.slowlife.bean.*;
import com.slowlife.service.interfaces.OrderInterface;
import com.slowlife.utils.Conf;
import com.slowlife.utils.StringUtils;
import io.github.biezhi.anima.Anima;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.plugin.net.protocol.jar.CachedJarURLConnection;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Bean
public class OrderService implements OrderInterface {


    @Override
    public String createOrder(String orderData) {


        JSONObject jsonObject=new JSONObject(orderData);

        JSONObject result=new JSONObject();

        if (!jsonObject.has("list")){

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();

        }
        //本次订单的地址
        String address=jsonObject.getString("address");
        //本次订单的总金额
        //Double money=jsonObject.getDouble("money");
        //本次订单的商家列表
        JSONArray merchantList=jsonObject.getJSONArray("list");
        //本次订单的用户编号
        int userNumber=jsonObject.getInt("userNumber");

        for(int i=0;i<merchantList.length();i++){
            JSONObject merchent=(JSONObject) merchantList.get(i);
            Integer merchentNumber=merchent.getInt("merchentNumber");
            String orderNumber=StringUtils.getOrderIdByUUId();
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp time=new Timestamp(date.getTime());
            Double money=merchent.getDouble("money");
            JSONArray commodityList=merchent.getJSONArray("commodityJSONArry");
            for(int j=0;j<commodityList.length();j++){
                JSONObject commodity=(JSONObject) commodityList.get(j);
                Order order=new Order();
                order.setAddressNumber(address);
                order.setCommodityNumber(commodity.getInt("commodityNumber"));
                order.setCommodityPrice(commodity.getFloat("commodityPrice"));
                order.setCommodityQuantity(commodity.getInt("count"));
                order.setOrderNumber(orderNumber);
                order.setOrderStatus(Conf.TO_BE_PROCESSED);
                order.setOrderDate(time.toString());
                order.setMerchantNumber(merchentNumber);
                order.setCommodityName(commodity.getString("commodityName"));
                order.setUserNumber(userNumber);
                order.save();//保存订单
            }
        }
        result.put(Conf.MSG,Conf.SUCCESS);
        return result.toString();
    }


    @Override
    public String getAddress(String addressData){

        System.out.println("输入的参数为"+addressData);

        JSONObject jsonObject=new JSONObject(addressData);

        JSONObject result=new JSONObject();

        if(!jsonObject.has("userNumber")){

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();

        }

        int userNumber=jsonObject.getInt("userNumber");

        System.out.println("获取地址的用户编号"+userNumber);

        List<Address> addressList=Anima.select("*").from(Address.class).where("user_number=?",userNumber).all();

        JSONArray jsonArray=new JSONArray(addressList);

        result.put(Conf.MSG,Conf.SUCCESS);

        result.put(Conf.RESULT,jsonArray);

        System.out.println("数据整理"+result.toString());

        return result.toString();
    }

    @Override
    public String orderGetCommodity(String data){

        JSONObject jsonObject=new JSONObject(data);

        System.out.println("获取数据"+jsonObject.toString());

        JSONObject result=new JSONObject();

        Double totalMoney=0.0;

        if(!jsonObject.has("list")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        JSONArray jsonArray=jsonObject.getJSONArray("list");
        System.out.println("获取商品数据为"+jsonArray.toString());
        System.out.println("本次订单的商品数："+jsonArray.length());
        int MC[][][]=new int[20][50][2];
        for(int i=0;i<jsonArray.length();i++){
            JSONObject object=(JSONObject) jsonArray.get(i);
            System.out.println("第"+i+"个商品数据"+object.toString());
            int number=object.getInt("commodityNumber");
            int count=object.getInt("count");
            Commodity commodity=Anima.select("merchant_number").from(Commodity.class).where("commodity_number=?",number).one();
            int merchantNumber=commodity.getMerchantNumber();
            boolean b=true;
            int o=0;
            while (b){
                if(MC[o][0][0]==merchantNumber){
                    boolean l=true;
                    int m=1;
                    while (l){
                        if(MC[o][m][0]!=0){
                            m++;
                        }else {
                            MC[o][m][0]=number;
                            MC[o][m][1]=count;
                            System.out.println(o+":"+m+"存放商品编号"+number+"购买数量："+MC[o][m][1]);
                            l=false;
                        }
                    }
                    break;
                }else{
                    if(MC[o][0][0]==0){
                        MC[o][0][0]=merchantNumber;
                        System.out.println("第"+o+"个位置存放商家信息"+merchantNumber);
                        MC[o][1][0]=number;
                        MC[o][1][1]=count;
                        System.out.println("第"+o+"1位置放置商品信息："+number+"购买数量："+MC[o][1][1]);
                        b=false;
                    }else {
                        o++;
                    }
                }
            }
        }
        List<OrderShow> orderShowList=new ArrayList<OrderShow>();
        boolean h=true;
        int z=0;
        while (h){
            System.out.println(z+"商家编号"+MC[z][0][0]);
            int merchant=MC[z][0][0];
            if(merchant==0){
                break;
            }
            OrderShow orderShow=new OrderShow();
            Double money=0.0;
            orderShow.setMerchentNumber(merchant);
            Merchent mer=Anima.select().from(Merchent.class).where("merchant_number=?",merchant).one();
            System.out.println("商家信息:"+mer.getMerchentName());
            orderShow.setMerchentName(mer.getMerchentName());
            orderShow.setMerchentPicture(mer.getMerchentPicture());
            List<Commodity> commodityList=new ArrayList<>();
            int y=1;
            boolean u=true;
            while (u){
                if(MC[z][y][0]!=0){
                    System.out.println(z+":"+y+"商品编号"+MC[z][y][0]+"数量"+MC[z][y][1]);
                    Commodity com=new Commodity();
                    com=Anima.select("commodity_number,commodity_name,commodity_picture,commodity_price").from(Commodity.class).where("commodity_number=?",MC[z][y][0]).one();
                    money+=com.getCommodityPrice()*MC[z][y][1];
                    com.setCount(MC[z][y][1]);
                    System.out.println("商品数据"+new JSONObject(com).toString());
                    commodityList.add(com);
                }else {
                    System.out.println(z+":"+y+"该商家没有商品了"+MC[z][y][0]);
                    break;
                }
                y++;
               // u=false;
            }
            System.out.println("商品数组"+new JSONArray(commodityList).toString());
            orderShow.setCommodityJSONArry(new JSONArray(commodityList));
            orderShow.setMoney(money);
            totalMoney+=money;
            orderShowList.add(orderShow);
            if(MC[z+1][0][0]==0){
                break;
            }
            z++;
        }
        result.put("money",totalMoney);
        result.put(Conf.RESULT,new JSONArray(orderShowList));
        result.put(Conf.MSG,Conf.SUCCESS);
        System.out.println("数据整理"+result.toString());
        return result.toString();

    }

}
