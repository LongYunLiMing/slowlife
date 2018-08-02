package com.slowlife.service;

import com.blade.ioc.annotation.Bean;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.http.Session;
import com.slowlife.bean.User;
import com.slowlife.service.interfaces.UserLoginAndRegisterInterface;
import com.slowlife.utils.*;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.core.AnimaQuery;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Bean
public class UserLoginAndRegisterService implements UserLoginAndRegisterInterface {


    /**
     * 用户登录，要求提供账户密码
     * 账号：邮箱or电话号码
     * @param user
     * @return
     */
    @Override
    public String userLogin(JSONObject user) {

        JSONObject jsonObject=new JSONObject();

        if (!user.has("userPassword")){
            jsonObject.put(Conf.MSG,Conf.ERROR);

            return jsonObject.toString();
        }

        String pass=user.getString("userPassword");

        pass=Md5Util.getMd5(pass);

        jsonObject.put(Conf.MSG,Conf.ERROR);

        if (user.has("userPhone")){//号码登录

            String phone=user.getString("userPhone");

            User user1=Anima.select().from(User.class).where("user_phone",phone).and("user_password",pass).one();

            if (user1!=null){

                //登录成功，设置用户账号回话


                jsonObject.put(Conf.MSG,Conf.SUCCESS);

                String token=TokenUtil.createToken(phone,System.currentTimeMillis(),pass);

                jsonObject.put(Conf.TOKEN,token);
                User user2=Anima.select("user_number,user_email,user_phone,user_name").from(User.class).where("user_number=?",user1.getUserNumber()).one();
                JSONObject userObject=new JSONObject(user2);
                System.out.println("用户编号"+userObject.getInt("userNumber"));
                jsonObject.put("user",userObject);

            }else {
                jsonObject.put(Conf.RESULT,"账号或密码不正确");
            }

        }else if (user.has("userEmail")){//邮箱登录

            String email=user.getString("userEmail");
            User user1=Anima.select().from(User.class).where("user_email",email).and("user_password",pass).one();

            if (!(user1 == null)){

                //邮箱登录成功，保存回话


                jsonObject.put(Conf.MSG,Conf.SUCCESS);

//                System.out.println("验证信息------>>>"+user1.getUserVerification());

                String token=TokenUtil.createToken(email,System.currentTimeMillis(),pass);

                User user2=Anima.select("user_number,user_email,user_phone,user_name").from(User.class).where("user_number=?",user1.getUserNumber()).one();

                jsonObject.put(Conf.TOKEN,token);
                JSONObject userObject=new JSONObject(user2);
                System.out.println("用户名"+userObject.getInt("userNumber"));
                jsonObject.put("user",userObject);
            }else {
                jsonObject.put(Conf.RESULT,"账号或密码不正确");
            }

        }


        return jsonObject.toString();
    }


    /**
     * 注册需要电话号码以及邮箱与密码
     * @param user
     * @return
     */
    @Override
    public String userRegister(JSONObject user) {

        JSONObject jsonObject=new JSONObject();



        if (!user.has("userPassword")){

            jsonObject.put(Conf.MSG,Conf.ERROR);
            return jsonObject.toString();
        }

        String pass=user.getString("userPassword");

        pass=Md5Util.getMd5(pass);

        if (user.has("userPhone")){


            String phone=user.getString("userPhone");

            //检测是否是正确的电话号码
            if (!StringUtils.checkMobileNumber(phone)){

                jsonObject.put(Conf.RESULT,"非正确电话号码");

                return jsonObject.toString();
            }

            //先查询数据库是否已存在此号码，存在则返回错误信息
            User exit=Anima.select("user_phone,user_number").from(User.class).where("user_phone",phone).one();



            if (exit!=null){

                jsonObject.put(Conf.RESULT,"此账号已存在，请返回登录");
                return jsonObject.toString();

            }


            User user1=new User();

            user1.setUserPassword(pass);

            user1.setUserPhone(phone);

            String id=user1.save().asString();


            jsonObject.put(Conf.MSG,Conf.SUCCESS);

            String token=TokenUtil.createToken(phone,System.currentTimeMillis(),pass);

            jsonObject.put(Conf.TOKEN,token);

            User user2=Anima.select("user_number,user_email,user_phone,user_name").from(User.class).where("user_number=?",id).one();

            JSONObject object=new JSONObject(user2);

            jsonObject.put("user",object);


        }else if (user.has("userEmail")){

            String email=user.getString("userEmail");

            //检测是否是正确的邮箱格式
            if (!StringUtils.checkEmail(email)){
                jsonObject.put(Conf.RESULT,"非正确邮箱格式");

                return jsonObject.toString();
            }



            //先查询数据库是否已存在此email，存在则返回错误信息
            User exit=Anima.select("user_email").from(User.class).where("user_email",email).one();
            if (exit!=null){

                jsonObject.put(Conf.RESULT,"此账号已存在，请返回登录");

                return jsonObject.toString();

            }


            User user1=new User();

            user1.setUserPassword(pass);

            user1.setUserEmail(email);

            String id=user1.save().asString();


            jsonObject.put(Conf.MSG,Conf.SUCCESS);

            String token=TokenUtil.createToken(email,System.currentTimeMillis(),pass);

            jsonObject.put(Conf.TOKEN,token);

            User user2=Anima.select("user_number,user_email,user_phone,user_name").from(User.class).where("user_number=?",id).one();

            JSONObject object=new JSONObject(user2);
            jsonObject.put("user",object);

        }

        return jsonObject.toString();
    }
}
