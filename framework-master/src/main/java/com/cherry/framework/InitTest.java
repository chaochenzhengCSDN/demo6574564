//package com.cherry.framework;
//
//import com.cherry.framework.core.CHERRY;
//
//import com.cherry.framework.core.thread.BaseThread;
//import org.apache.commons.configuration2.XMLConfiguration;
//import org.apache.commons.configuration2.builder.fluent.Configurations;
//import org.apache.oro.text.regex.Pattern;
//import org.apache.oro.text.regex.PatternMatcher;
//import org.apache.oro.text.regex.Perl5Compiler;
//import org.apache.oro.text.regex.Perl5Matcher;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.security.auth.login.Configuration;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import java.util.List;
//import java.util.regex.MatchResult;
//
//
///**
// * @author Administrator
// * @date 2019-09-29 10:52
// */
//@WebServlet(loadOnStartup = 2)
//public class InitTest extends HttpServlet {
//    private static final Logger logger= LoggerFactory.getLogger(InitTest.class);
//
//    private XMLConfiguration systemConfigXml;
//
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String dbUserName;
//    @Value("${spring.datasource.password}")
//    private String dbPassword;
//    @Override
//    public void init() throws ServletException{
//        try {
//            //spring上下文
//            CHERRY.SPRING_CONTEXT=
//                    WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//            logger.info("spring上下文:"+CHERRY.SPRING_CONTEXT);
//            //WEB应用发布路径
//            CHERRY.WEB_APP_PATH=
//                    getServletContext().getRealPath("/");
//            CHERRY.WEB_APP_PATH=
//                    CHERRY.WEB_APP_PATH.substring(0,CHERRY.WEB_APP_PATH.length()-1);
//            logger.info("web应用磁盘路径:"+CHERRY.WEB_APP_PATH);
//            //初始化xml
//            Configurations configurations=new Configurations();
//            systemConfigXml=configurations.xml(
//                    new ClassPathResource("/system-config.xml").getFile());
//            InitXml();
//            InitThread();
//        }catch (Exception e){
//            logger.info("系统初始化错误:"+e);
//        }
//    }
//    /**
//     *  初始化xml
//     */
//    private void InitXml() throws Exception{
//        //1 system-config.xml -系统参数
//        CHERRY.STATUS=systemConfigXml.getString("status").trim();
//        logger.info("运行状态:",CHERRY.STATUS);
//        CHERRY.ATTACHMENT_ENGINE=
//                systemConfigXml.getString("attachment-engine").trim();
//        logger.info("附件存储引擎:"+CHERRY.ATTACHMENT_ENGINE);
//        //登录安全
//        CHERRY.LOGIN_LOCK=systemConfigXml.getString(
//                "login.lock").trim().equalsIgnoreCase("true");
//        logger.info("登录安全检查开启标识：{}", CHERRY.LOGIN_LOCK);
//        CHERRY.LOGIN_MAX_ERROR_TIMES=systemConfigXml.getInt("login.times");
//        logger.info("登录失败最大重试次数：{}", CHERRY.LOGIN_MAX_ERROR_TIMES);
//        CHERRY.LOGIN_LOCK_DURATION=systemConfigXml.getInt("login.duration");
//        logger.info("登录失败锁定时长：{}", CHERRY.LOGIN_LOCK_DURATION);
//        CHERRY.LOGIN_CODE=systemConfigXml.getString(
//                "login.code").trim().equalsIgnoreCase("true");
//        logger.info("登录页验证码功能开启标志：{}", CHERRY.LOGIN_CODE);
//        //2 数据库配置
//        Pattern pattern=null;
//        String url=this.url;
//        if(url.contains("mysql")){
//            CHERRY.DB_TYPE="mysql";
//            pattern=new Perl5Compiler().compile("jdbc:mysql://([^:]+):(\\d+)/([^\\?]+)");
//        }else if (url.contains("oracle")) {
//            // jdbc:oracle:thin:@192.168.100.151:1521:FORP
//            CHERRY.DB_TYPE = "oracle";
//            pattern = new Perl5Compiler().compile("jdbc:oracle:thin:@([^:]+):(\\d+):(\\w+)");
//        } else {
//            CHERRY.DB_TYPE = "unknown";
//        }
//        PatternMatcher patternMatcher=new Perl5Matcher();
//        if(patternMatcher.contains(url, pattern)){
//            MatchResult matchResult= (MatchResult) patternMatcher.getMatch();
//            CHERRY.DB_IP=matchResult.group(1);
//            CHERRY.DB_PORT=matchResult.group(2);
//            CHERRY.DB_NAME=matchResult.group(3);
//        }
//        CHERRY.DB_USER_NAME=this.dbUserName;
//        CHERRY.DB_PASSWORD=this.dbPassword;
//        logger.info("数据库："+CHERRY.DB_USER_NAME+CHERRY.DB_TYPE);
//        logger.debug("{}:{}/{}", CHERRY.DB_IP, CHERRY.DB_PORT, CHERRY.DB_USER_NAME);
//        logger.info("初始化XML参数【ok】");
//    }
//    /**
//     *  初始化线程
//     */
//    private void InitThread() throws Exception {
//       List<Object> threads= systemConfigXml.getList("threads.thread");
//       if(threads!=null &&threads.size()>0){
//           BaseThread baseThreadService;
//           String clazz,name,start;
//           for(int i=0;i<threads.size();i++){
//               try{
//                   clazz= (String) threads.get(i);
//                   name=systemConfigXml.getString("threads.thread("+i+")[@name]");
//                   start=systemConfigXml.getString("threads.thread("+i+")[@start]");
//                   baseThreadService= (BaseThread) (Class.forName(clazz.trim()).
//                           getConstructor(new Class[]{Stirng.class}).newInstance(name));
//                   if("true".equalsIgnoreCase(start.trim())){
//                       baseThreadService.start();
//                   }
//                   CHERRY.THREADS.put(clazz, baseThreadService);
//               }catch (Exception e){
//                   logger.error("Thread init failed：", e);
//               }
//           }
//       }
//        logger.info("初始化后台Threads参数[OK]");
//    }
//
////    @Override
////    public void destory() throws Exception{
////
////    }
//}
